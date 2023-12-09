import {backendApi} from "./backendApi";

const coursesClient = backendApi("/course")

export const coursesApi =  {
    getAll()
    {
        console.log("getting all courses")
        return coursesClient.get();
    },
    getById (id) {
        console.log('Get course', id)
        return coursesClient.get(`/${id}`)
    },

    create (note) {
        console.log('Create course', note)
        return coursesClient.post('', note)
    },

    update (id, note) {
        console.log('Update course', id, note)
        return coursesClient.put(`/${id}`, note)
    },
    delete(id) {
        return coursesClient.delete(`/${id}`);
    }
}
