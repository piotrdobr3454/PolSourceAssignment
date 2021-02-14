import http from "../http-common";

class NoteDataService {
  getAll() {
    return http.get("/get-notes");
  }

  getAllNotes() {
    return http.get("/get-all-notes");
  }

  get(id) {
    return http.get(`/get-notes/${id}`);
  }
   
  getVersion(id) {
    return http.get(`/get-version/${id}`);
  }

  getPreviousNotes(id) {
      return http.get(`/get-previous-notes/${id}`);
  }

  create(data) {
    return http.post("/create-new-note", data);
  }

  update(id, data) {
    return http.post("/modify-note", data);
  }

  delete(id) {
    return http.post("/delete-note", id);
  }
}

export default new NoteDataService();
