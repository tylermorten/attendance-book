(ns ^:figwheel-always attendance-book.core
    (:require [om.core :as om :include-macros true]
              [om.dom :as dom]
              [sablono.core :as html :refer-macros [html]]))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

(defn top-bar [cursor owner]
  (reify
    om/IRender
    (render [this]
      (html
       [:div.fixed
        [:nav.top-bar {:data-topbar "" :role "navigation"}
         [:ul.title-area
          [:li.name
           [:h1 [:a {:href "#"} "Attendance Book"]]]
          [:li.toggle-topbar.menu-icon
           [:a {:href "#"}
            [:span "Menu"]]]]
         [:section.top-bar-section
          [:ul.right
           [:li.active [:a {} "Right button Active"]]
           [:li.has-dropdown [:a {} "Right Button Dropdown"]
            [:ul.dropdown
             [:li [:a {} "First link in dropdown"]]]]
           [:li.has-form
            [:div.row.collapse
             [:div {:class "large-8 small-9 columns"}
              (dom/input #js {:ref "search"
                              :placeholder "Search"})]
             [:div {:class "large-4 small-3 columns"}
              (dom/a #js {:className "alert button expand"
                          :ref "search-button"
                          :onClick #(.alert js/window "You searched!")} "Find")]]]]]]]))))


(om/root
 (fn [data owner]
   (reify
     om/IRender
     (render [this]
       (om/build top-bar data)))) app-state
        {:target (. js/document (getElementById "app"))})
