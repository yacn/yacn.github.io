---
title: Sensu EC2 Node Handler
tags: sensu chef monitoring stability
template: default.clj
---

# Sensu EC2 Node Handler

## The Problem

At work, we run [Sensu](https://sensuapp.org) as the base for our
metrics, monitoring, and alerting pipeline. A while back, we were
running into an issue with phantom keepalive alerts due to how Sensu
was determining whether or not a node still existed. The way it was
determining this was to query our [Chef](https://www.getchef.com)
server to check whether or not the client and node existed there.
However, due to how we provision and terminate nodes (using an internal
knife plugin), the clients and nodes on the Chef server are not removed
until the next time they are to be created. (Really, we should update 
the deletion component to remove the clients and nodes, but it is a 
low priority at this point).

Due to this workflow, when we would terminate a cluster, we would
encounter a "keepalive storm", where we would be flooded with keepalive
alerts for the cluster we just terminated. This would cause our Sensu
server to become overloaded with queued up events to handle. When our
next Chef run would execute, the Sensu server would be signaled to restart.
However, due to the massive event queue that had built up, the time needed
to restart the process would exceed the threshold set for the service's 
script, set in `/etc/default/sensu`. I believe the default was 10
seconds, we increased it to 30 while investigating to try and soothe
the inevitable Chef runs.

Nevertheless, the process would nearly always fail to restart, which
caused the Chef run to fail and would leave teh Sensu server in an
incorrect state. The remedy this state, we owuld need to manually stop
and start each of the Sensu components (API, server, client) manually.
This was very annoying and inconveniencing, so I set out to find a 
fix for the situation.

## The Solution

To solve our phantom keepalives and subsequent "keepalive storms", I
created a Sensu handler for the keepalive event. When it handles a keepalive
event, instead of checking the Chef server to determine whether the node
still exists, it checks EC2. If the node is not found in EC2, it is removed
from Sensu as a client.

When we implemented this handler at work, we were able to eliminate the
"keepalive storms" and phantom keepalive alerts, thus increasing the
stability of our Sensu server. You can check out the plugin in the
official [Sensu Community Plugins](https://github.com/sensu/sensu-community-plugins/blob/master/handlers/other/ec2_node.rb)
repository on GitHub. Hopefully it will be of some use to others.
