<template>
  <div class="list row">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Search by title"
          v-model="title"/>
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button"
            @click="searchTitle"
          >
            Search
          </button>
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <h4>Notes List</h4>
      <ul class="list-group">
        <li class="list-group-item"
          :class="{ active: index == currentIndex }"
          v-for="(note, index) in notes"
          :key="index"
          @click="setActiveNote(note, index)"
        >
          {{note.title }}
        </li>
      </ul>

    </div>
    <div class="col-md-6">
      <div v-if="currentNote">
        <h4>Note</h4>
        <div>
          <label><strong>ID:</strong></label> {{ currentNote.id }}
        </div>
        <div>
          <label><strong>Title:</strong></label> {{ currentNote.title }}
        </div>
        <div>
          <label><strong>Content:</strong></label> {{ currentNote.content }}
        </div>
        <div>
          <label><strong>Date_initial:</strong></label> {{ currentNote.date_initial }}
        </div>
        <div>
          <label><strong>Date_modified:</strong></label> {{ currentNote.date_modified }}
        </div>
        <div>
          <label><strong>Version_number:</strong></label> {{ currentNote.version_number }}
        </div>
        <router-link :to="'/get-notes/' + currentNote.id" class="badge badge-warning">Edit</router-link>
      </div>
      <div v-else>
        <br />
        <p>Please click on a Note</p>
      </div>
    </div>
  </div>
</template>

<script>
import TutorialDataService from "../services/TutorialDataService";

export default {
  name: "notes-list",
  data() {
    return {
      notes: [],
      currentNote: null,
      currentIndex: -1,
      title: ""
    };
  },
  methods: {
    retrieveNotes() {
      TutorialDataService.getAll()
        .then(response => {
          this.notes = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    refreshList() {
      this.retrieveNotes();
      this.currentNote = null;
      this.currentIndex = -1;
    },

    setActiveNote(note, index) {
      this.currentNote = note;
      this.currentIndex = index;
    },
    
    searchTitle() {
      TutorialDataService.findByTitle(this.title)
        .then(response => {
          this.notes = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.retrieveNotes();
  }
};
</script>

<style>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}
</style>
