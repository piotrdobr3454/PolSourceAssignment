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
            {{getPreviousNotes()}}
            <li v-for="note in previousNotes" :key="note.id">
              <!-- <button v-on:click="isHidden = true"> click here </button>
              <button v-on:click="isHidden = !isHidden"> close -->
              <div>
              <label><strong>ID:</strong></label> {{ note.id }}
              </div>
              <div>
                <label><strong>Title:</strong></label> {{ note.title }}
              </div>
              <div>
                <label><strong>Content:</strong></label> {{ note.content }}
              </div>
              <div>
                <label><strong>Date_initial:</strong></label> {{ note.date_initial }}
              </div>
              <div>
                <label><strong>Date_modified:</strong></label> {{ note.date_modified }}
              </div>
              <div>
                <label><strong>Original_id:</strong></label> {{ note.original }}
              </div>
              <div>
                <label><strong>Version_number:</strong></label> {{ note.version }}
              </div>
            </li>
          </div>
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
      title: "",
      previousNotes: []
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

    getPreviousNotes() {
      TutorialDataService.getPreviousNotes(this.currentNote.original)
      .then(response => {
        this.previousNotes = response.data;
        console.log(response.data);
      })
      .catch(e => {
          console.log(e);
        });
    },

    getVersion() {
      TutorialDataService.getVersion(this.currentNote.id)
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
