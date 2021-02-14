<template>
<div class="list row">
<div class="col-md-6">
    <h4>All Notes</h4>
    <li v-for="note in notes" :key="note.id">
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
</template>

<script>
import NoteDataService from "../services/NoteDataService";

export default {
  name: "note",
  data() {
    return {
      currentNote: null,
      notes: []
    };
  },
  methods: {
    retrieveNotes() {
      NoteDataService.getAllNotes()
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
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>
