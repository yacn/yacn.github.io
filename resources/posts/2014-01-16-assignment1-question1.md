---
template: default.clj
title: Assignment 1 Question 1
tags: assignment1
---

# Assignment 1 Question 1

## Google Drive Terms of Service Liability

This was the hardst post to write, as the information is
scattered over so many places. However, this is what I've
gathered from research.

### Uptime

Google guarantees 99.9% uptime, which is 8.76 hours
of downtime per year.

### Terms of Service

The [end-user terms](http://www.google.com/intl/en/policies/terms/)
of service state that:

>You retain ownership of any intellectual property rights
>that you hold in that content. In short, what belongs to
>stays yours.

https://www.google.com/apps/intl/en/terms/user_terms.html
Additionally, section 7 of the user terms of service state:

>You agree that Google has no responsibility or liability
>for the deletion or failure to store any Content and other
>communications maintained or tranmitted by Google services.

The above seems to imply that if something were to happen to
your data, the liability is on you to have a backup, not Google.
I believe that this is fair enough, as Google Drive is not
billed as a data backup solution. For something like that, you
may want to consider Amazon S3 or Glacier, depending on how
often you'll access the data.

Furthur down in section 7, Google states:

>You acknowledge that Google may have set no fixed upper limit on the number
>of transmissions you may send or receive through Google services or the
>amount of storage space used; however, we retain the right, at our sole
>discretion, to create limits at any time with or without notice.

Basically, if you become a heavy power user of Google's services, you
should probably contact them to ensure that they don't disrupt your
operations.
