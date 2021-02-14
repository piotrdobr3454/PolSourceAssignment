<template>
  <div v-if="currentNote" class="edit-form">
    <h4>Note</h4>
    <form>
      <div class="form-group">
        <label for="title">Title</label>
        <input type="text" class="form-control" id="title"
          v-model="currentNote.title"
        />
      </div>
      <div class="form-group">
        <label for="description">Content</label>
        <input type="text" class="form-control" id="content"
          v-model="currentNote.content"
        />
      </div>

    </form>

    <!-- <button class="badge badge-primary mr-2"
      v-if="currentTutorial.published"
      @click="updatePublished(false)"
    >
      UnPublish
    </button>
    <button v-else class="badge badge-primary mr-2"
      @click="updatePublished(true)"
    >
      Publish
    </button> -->

    <button class="badge badge-danger mr-2"
      @click="deleteNote"
    >
      Delete
    </button>

    <button type="submit" class="badge badge-success"
      @click="updateNote"
    >
      Update
    </button>
    <p>{{ message }}</p>
  </div>

  <div v-else>
    <br />
    <p>Please click on a Note...</p>
  </div>
</template>

<script>
import TutorialDataService from "../services/TutorialDataService";

export default {
  name: "note",
  data() {
    return {
      currentNote: null,
      message: ''
    };
  },
  methods: {
    getNote(id) {
      TutorialDataService.get(id)
        .then(response => {
          this.currentNote = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    // updatePublished(status) {
    //   var data = {
    //     id: this.currentNote.id,
    //     title: this.currentNote.title,
    //     content: this.currentNote.content,
    //     published: status
    //   };

    //   TutorialDataService.update(this.currentTutorial.id, data)
    //     .then(response => {
    //       this.currentTutorial.published = status;
    //       console.log(response.data);
    //     })
    //     .catch(e => {
    //       console.log(e);
    //     });
    // },

    updateNote() {
      TutorialDataService.update(this.currentNote.id, this.currentNote)
        .then(response => {
          console.log(response.data);
          this.message = 'The note was updated successfully!';
        })
        .catch(e => {
          console.log(e);
        });
    },

    deleteNote() {
      TutorialDataService.delete(this.currentNote.id)
        .then(response => {
          console.log(response.data);
          this.$router.push({ name: "notes" });
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
