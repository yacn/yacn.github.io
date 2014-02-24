; This is based off of Chris Smith's default template, available at:
; https://github.com/crsmithdev/crsmithdev.com
[:html
 {:xmlns "http://www.w3.org/1999/xhtml" :lang "en" :xml:lang "en"}
 [:head
  [:meta {:http-equiv "content-type" :content "text/html; charset=UTF-8"}]
  [:meta {:name "description" :content (:description metadata)}]
  [:meta {:name "keywords" :content (:tags metadata)}]
  [:meta {:name "author" :content (:author-name (static.config/config))}]
  [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
  [:link {:rel "icon" :href "/img/favicon.ico" :type "image/x-icon"}]
  [:link {:rel "shortcut icon" :href "/img/favicon.ico" :type "image/x-icon"}]
  [:link {:rel "stylesheet" :type "text/css" :href "//cdnjs.cloudflare.com/ajax/libs/font-awesome/3.2.1/css/font-awesome.min.css"}]
  [:link {:rel "stylesheet" :type "text/css" :href "//fonts.googleapis.com/css?family=Source+Code+Pro|Open+Sans"}]
  [:link {:rel "stylesheet" :type "text/css" :href "/css/bootstrap.min.css"}]
  [:link {:rel "stylesheet" :type "text/css" :href "/css/yacn.css"}]
  [:title (:title metadata)]]
 [:body
  [:div.header
   [:nav.navbar.navbar-inverse.navbar-fixed-top {:role "navigation"}
    [:div.container
     [:div.navbar-header
      [:button.navbar-toggle {:type "button" :data-toggle "collapse" :data-target ".navbar-ex1-collapse"}
      [:span.sr-only "Toggle navigation"]
      [:span.icon-bar]
      [:span.icon-bar]
      [:span.icon-bar]]
      [:a.navbar-brand {:href "/index.html"} "YACN"]]
     [:div.collapse.navbar-collapse.navbar-ex1-collapse
      [:ul.nav.navbar-nav
       [:li [:a {:href "/blog.html"} "Blog"]]
       [:li [:a {:href "/is3500/blog.html"} "IS3500 Blog"]]
       [:li [:a {:href "/projects.html"} "Projects"]]
       [:li [:a {:href "/about.html"} "About"]]]
      [:ul.nav.navbar-nav.navbar-right
       [:li [:a.icon-header {:href "https://github.com/yacn"} [:i.icon-github.icon-2x]]
       [:li [:a.icon-header {:href "https://twitter.com/yaclevername"} [:i.icon-twitter.icon-2x]]
       [:li [:a.icon-header {:href "https://www.linkedin.com/in/idboehman"} [:i.icon-linkedin.icon-2x]]
       [:li [:a.icon-header {:href "http://yacn.me/rss-feed"} [:i.icon-rss.icon-2x]] ]]]]]]]]]
  [:div.content
   [:div.container
    (if (= (:type metadata) :post)
     [:div.row
      [:div.col-md-12
       content
       (reduce
             (fn[h v]
               (conj h [:a {:href (str "/tags/#" v)} (str v " ")]))
             [:div#post-tags "Tags: "]
             (.split (:tags metadata) " "))
       [:div.shares
        [:a.twitter-share-button {:href "https://twitter.com/share" :data-via "yaclevername"} "Tweet"]]
       [:br]
       [:div#disqus_thread]
       [:script {:type "text/javascript"}
        "var disqus_shortname = 'yacn';"
        "var disqus_identifier = 'http://yacn.me' + window.location.pathname;"
        "var disqus_url = disqus_identifier;"
        "(function() {"
          "var dsq = document.createElement('script'); dsq.type = 'text/javascript'; dsq.async = true;"
          "dsq.src = '//' + disqus_shortname + '.disqus.com/embed.js';"
          "(document.getElementsByTagName('head')[0] || document.getElementsByTagName('body')[0]).appendChild(dsq);"
        "})();"]
       [:script {:type "text/javascript"}
        "!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';"
        "if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+'://platform.twitter.com/widgets.js';"
        "fjs.parentNode.insertBefore(js,fjs);}}(document, 'script', 'twitter-wjs');"]]]
     content)
   [:script {:src "//cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"}]
   [:script {:src "//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.0.3/js/bootstrap.min.js"}]
   [:script {:src "//cdnjs.cloudflare.com/ajax/libs/prettify/r298/prettify.js"}]
   ;[:script {:src "//google-code-prettify.googlecode.com/svn/trunk/src/lang-clj.js"}]
   [:script {:src "/js/yacn.js"}]
 [:div.footer
  [:div.container
   [:div.row
    [:div.col-md-12
     [:p "Built with "
      [:a {:href "https://getbootstrap.com/"} "Bootstrap"] " and "
      [:a {:href "https://github.com/nakkaya/static"} "Static"] ". "
      "Based off of "
      [:a {:href "http://crsmithdev.com"} "Chris Smith"] "'s "
      "site"
      [:br]
      "&copy; 2014 " [:a {:href "http://yacn.me"} "Yet Another Clever Name"]]]]]]]]]]
