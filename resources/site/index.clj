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
        "I'm passionate about building scalable systems, creating tools, and having fun. "
        "My current languages of choice are Ruby and Python. I've been dabling in Clojure a bit as well, "
        "along with the Rust alpha for my capstone class."]
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
