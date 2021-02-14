import http from "../http-common";

class TutorialDataService {
  getAll() {
    return http.get("/get-notes");
  }

  get(id) {
    return http.get(`/get-notes/${id}`);
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

export default new TutorialDataService();
