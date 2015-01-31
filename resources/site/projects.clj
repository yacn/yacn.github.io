{:title "yacn.me - projects"}

[:div
  [:div.row
    [:div.col-md-12
      [:h2 "Projects"]
      [:h3 "Automated Media Server"]
      [:p
        "Through the use of various tools out there, I have put together a media server "
        "which automates the tedious task of downloading, renaming, and organizing "
        "various television series and movies. Through the use of "
        [:a {:href "https://plex.tv"} "Plex Media Server"] " I am able to share "
        "access to this server with friends and family, meaning that I am essentially "
        "running my own little SaaS! To see how I accomplish this, you can view my "
        "post about it "
        [:a {:href "/2014/01/01/plex-saas-overview/"} "here"] "."]
      [:h3 "EC2 Node Sensu Handler"]
      [:p
        "This is a Sensu handler for keepalive events. It checks whether or not "
        "a node exists in EC2. If it does, the event persists. If it does not, it "
        "is removed from the Sensu server as a client. You can read more about it "
        "in a post " [:a {:href "/2014/02/24/sensu-ec2-node-handler/"} "here"]
        " where I detail what problem led to the creation of this handler. You "
        "can also view the plugin directly in the "
        [:a {:href "https://github.com/sensu/sensu-community-plugins/"} "Sensu Community Plugins"]
        " repository "
        [:a {:href "https://github.com/sensu/sensu-community-plugins/blob/master/handlers/other/ec2_node.rb"} "here"]
        "."]]]]
