<template>
<div class="list row">
<div class="col-md-6">
  <div v-if="currentNote">
    <h4>Note History</h4>
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

  <div v-else>
    <br />
    <p>Please click on a Note...</p>
  </div>
</div>
</div>
</template>

<script>
import NoteDataService from "../services/NoteDataService";

export default {
  name: "note",
  data() {
    return {
      currentNote: null,
      message: '',
      previousNotes: []
    };
  },
  methods: {
    getNote(id) {
      NoteDataService.get(id)
        .then(response => {
          this.currentNote = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    getPreviousNotes() {
      NoteDataService.getPreviousNotes(this.currentNote.original)
      .then(response => {
        this.previousNotes = response.data;
        console.log(response.data);
      })
      .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.message = '';
    this.getNote(this.$route.params.id);
  }
};
</script>

<style>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>
