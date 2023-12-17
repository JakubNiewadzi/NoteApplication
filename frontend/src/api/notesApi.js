import {backendApi} from "./backendApi";
import {bearerAuth} from "./bearerAuth";

const notesClient = backendApi("/note")

export const notesApi = {
    getAll(token) {
        console.log("getting all notes");
        return notesClient.get('', {
            headers: {Authorization: bearerAuth(token)}
        });
    },
    getById(id, token){
        console.log("Getting note with id: %s", id);
        return notesClient.get(`/${id}` , {
            headers: { Authorization: bearerAuth(token) }
        });
    },
    update(id, note, token){
        console.log("Updating note with id: %s", id);
        return notesClient.patch(`/${id}`,  note,{
            headers: { Authorization: bearerAuth(token) }
        });
    },
    create(note, token) {
        console.log("Creating note");
        return notesClient.post('', note , {
            headers: { Authorization: bearerAuth(token) }
        });
    },
    delete(id, token) {
        console.log("Deleting note with id: %s", id)
        return notesClient.delete(`/${id}`, {
            headers: { Authorization: bearerAuth(token) }
        });
    },
    getByCourseId(id, token){
        console.log("Getting notes by course id: %s", id);
        return notesClient.get(`/getByCourseId/${id}`, {
            headers: { Authorization: bearerAuth(token) }
        });
    }
}