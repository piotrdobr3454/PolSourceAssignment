import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: "/",
      alias: "/notes",
      name: "notes",
      component: () => import("./components/NotesList")
    },
    {
      path: "/get-notes/:id",
      name: "notes-details",
      component: () => import("./components/Note")
    },
    {
      path: "/add",
      name: "add",
      component: () => import("./components/AddNote")
    },
    {
      path: "/viewhistory/:id",
      name: "viewhistory",
      component: () => import("./components/ViewHistory")
    },
    {
      path: "/allnotes",
      name: "allnotes",
      component: () => import("./components/AllNotes") 
    }
  ]
});
