import {useEffect, useState} from "react";
import {coursesApi} from "../../api/coursesApi";
import {Button, ButtonGroup, Container, Table} from "reactstrap";
import {Link} from "react-router-dom";
import {useAuth} from "react-oidc-context";

export const CoursesPage = () => {
    const auth = useAuth()
    const accessToken = auth.user.access_token
    const [courses, setCourses] = useState([]);
    const [rerender, setRerender] = useState(false);

    useEffect(() => {
        coursesApi.getAll(accessToken)
            .then(res => setCourses(res.data))
            .catch(err => console.error('[Fetch Error]:', err))
    }, [rerender]);

    const onDelete = async (course) => {
        try {
            await coursesApi.delete(course.id, accessToken);
            // Toggle the rerender state to trigger a component rerender
            setRerender((prev) => !prev);
        } catch (err) {
            console.error("[Fetch Error]:", err);
        }
    };
    const coursesList = courses.map(course => {
        return (
            <tr key={course.id}>
                <td style={{ whiteSpace: 'nowrap' }}>{course.id}</td>
                <td style={{ whiteSpace: 'nowrap' }}>{course.name}</td>
                <td align='center'>
                    <ButtonGroup>
                        <Button color='primary' tag={Link} to={'/courses/' + course.id}>
                            Edit
                        </Button>

                        <Button color='danger' onClick={() => onDelete(course)}>
                            Delete
                        </Button>
                    </ButtonGroup>
                </td>
            </tr>
        )
    })
    console.log(accessToken)
    return (
        <div className='App-header'>
            <Container fluid>
                <h3>Courses</h3>
                <Table striped bordered hover size='sm'>
                    <thead>
                    <tr>
                        <th width='80px'>Id</th>
                        <th>Title</th>
                        <th width='120px'>
                            <div align='center'>Action</div>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    {coursesList}
                    </tbody>
                </Table>
                <div className='float-middle'>
                    <Button size='lg' color='success' tag={Link} to='/courses/new'>
                        Add
                    </Button>
                </div>
            </Container>
        </div>
    )

}