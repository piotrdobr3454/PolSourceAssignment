<template>
  <div class="list row">
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
         <router-link :to="'/allnotes'" class="badge badge-warning">View all notes</router-link>
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
        <div>
          <label><strong>Original_id:</strong></label> {{ currentNote.original }}
        </div>
        <div>
          <label><strong>Version_number:</strong></label> {{ currentNote.version }}
        </div>
        <router-link :to="'/get-notes/' + currentNote.id" class="badge badge-warning">Edit</router-link>
        </div>
         <router-link :to="'/viewhistory/' + currentNote.id" class="badge badge-warning">View History</router-link>
      </div>

      <div v-else>
        <br />
        <p>Please click on a Note</p>
      </div>
          
    </div>
  </div>
</template>

<script>
import NoteDataService from "../services/NoteDataService";

export default {
  name: "notes-list",
  data() {
    return {
      notes: [],
      currentNote: null,
      currentIndex: -1,
      title: "",
      previousNotes: []
    };
  },
  methods: {
    retrieveNotes() {
      NoteDataService.getAll()
        .then(response => {
          this.notes = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    getVersion() {
      NoteDataService.getVersion(this.currentNote.id)
        .then(response => {
          this.currentNote.version = response.data;
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
