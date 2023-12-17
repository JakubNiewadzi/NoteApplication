import {backendApi} from "./backendApi";
import {bearerAuth} from "./bearerAuth";

const coursesClient = backendApi("/course")

export const coursesApi =  {
    getAll(token)
    {
        console.log("getting all courses")
        return coursesClient.get('', {
            headers: { Authorization: bearerAuth(token) }
        })
    },
    getById (id, token) {
        console.log('Get course', id)
        return coursesClient.get(`/${id}`, {
            headers: {Authorization: bearerAuth(token)}
        })
    },

    create (course, token) {
        console.log('Create course', course)
        return coursesClient.post('', course, {
            headers: {Authorization: bearerAuth(token)}
        })
    },

    update (id, course, token) {
        console.log('Update course', id, course)
        return coursesClient.patch(`/${id}`, course, {
            headers: {Authorization: bearerAuth(token)}
        })
    },
    delete(id, token) {
        return coursesClient.delete(`/${id}`, {
            headers: {Authorization: bearerAuth(token)}
        });
    }
}
