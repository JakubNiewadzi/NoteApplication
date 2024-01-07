import {backendApi} from "./backendApi";
import {bearerAuth} from "./bearerAuth";

const coursesClient = backendApi("/course")

export const coursesApi = {
    getAll(token) {
        console.log("getting all courses")
        return coursesClient.get('', {
            headers: {Authorization: bearerAuth(token)}
        })
    },
    getById(id, token) {
        console.log('Get a course with id: %s', id)
        return coursesClient.get(`/${id}`, {
            headers: {Authorization: bearerAuth(token)}
        })
    },

    create(course, token) {
        console.log('Create a course', course)
        return coursesClient.post('', course, {
            headers: {Authorization: bearerAuth(token)}
        })
    },

    update(id, course, token) {
        console.log('Update a course', id, course)
        return coursesClient.patch(`/${id}`, course, {
            headers: {Authorization: bearerAuth(token)}
        })
    },
    delete(id, token) {
        console.log('Delete a course', id)
        return coursesClient.delete(`/${id}`, {
            headers: {Authorization: bearerAuth(token)}
        });
    }
}
