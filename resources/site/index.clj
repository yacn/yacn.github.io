{:title "yacn.me"}

[:div
  [:div.row
    [:div.col-md-12
      [:h1 "Yet Another Clever Name"]]]
  [:div.row
    [:div.col-md-6
      [:p.lead.text-muted
        "Code and stuff."]]]
  [:div.row
    [:div.col-md-12
      [:p
        "Welcome to yet another clever blog. This is where I'll post about things that interest me. "
        "I'm passionate about building things, solving problems, and having fun. "
        "My current tools of choice are Ruby, Python, and Bash. Recently, I've been dabling in Clojure as well to scratch my functional itch. "
        "Here you'll find my " [:a {:href "/blog.html"} "blog"] ", and some of the " [:a {:href "/projects.html"} "projects"] " I'm working on, "
        "and a little more " [:a {:href "/about.html"} "about"] " myself."]
      [:p
        "I'm active on " [:a {:href "https://github.com/yacn"} "GitHub"] " and " [:a {:href "https://twitter.com/yaclevername"} "tweet"] " occasionally."]]
  [:br]
  [:div.row
    [:div.col-md-6
      [:h4 "Recent Blog Posts"]
      (map #(let [f % url (static.core/post-url f)
                  [metadata _] (static.io/read-doc f)
                  tags (.split (:tags metadata) " ")
                  date (static.core/parse-date
                        "yyyy-MM-dd" "dd MMMM yyyy"
                        (re-find #"\d*-\d*-\d*" (str f)))]
        (if (not (some #{"is3500blog" "assignment1" "assignment2" "assignment3"} tags))
          [:div
            [:div [:a {:href url} (:title metadata)]
            [:div date]]]))
          (take 28 (reverse (static.io/list-files :posts))))]
      [:div.col-md-6
        [:h4 "Recent Activity on Github"]
        [:div.gh-recent]]]]]
