{:title "yacn.me - blog"}

[:div
 [:div.row
  [:div.col-md-12
   [:h2 "Blog"]
   (map #(let [f % url (static.core/post-url f)
               [metadata _] (static.io/read-doc f)
               tags (.split (:tags metadata) " ")
               date (static.core/parse-date
                     "yyyy-MM-dd" "dd MMM yyyy"
                     (re-find #"\d*-\d*-\d*" (str f)))]
    (if (not (some #{"is3500blog" "assignment1" "assignment2" "assignment3"} tags))
     [:div
      [:h4.blog-title [:a {:href url} (:title metadata)]]
      [:h5.blog-date date]]))
     (take 8 (reverse (static.io/list-files :posts))))]]]
