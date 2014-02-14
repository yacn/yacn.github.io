{:title "IS3500 Blog"}

[:div
 [:div.row
  [:div.col-md-6
   [:h2 "Recent Posts"]
   (map #(let [f % url (static.core/post-url f)
               [metadata _] (static.io/read-doc f)
               tags (.split (:tags metadata) " ")
               date (static.core/parse-date
                     "yyyy-MM-dd" "dd MMM yyyy"
                     (re-find #"\d*-\d*-\d*" (str f)))]
    (if (some #{"is3500blog"} tags)
     [:div
      [:h4.blog-title [:a {:href url} (:title metadata)]]
      [:h5.blog-date date]]))
     (take 24 (reverse (static.io/list-files :posts))))]
  [:div.col-md-6
   [:h2 "Assignments"]
   [:ul
   (map (fn [tag]
         (if (some #{tag} ["is3500blog" "assignment1" "assignment2" "assignment3"])
          [:li [:a {:href (str "/tags/#" tag)} (str tag)]]))
        (keys (static.core/tag-map)))]
     ]]]
