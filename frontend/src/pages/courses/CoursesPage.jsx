import {useEffect, useState} from "react";
import {coursesApi} from "../../api/coursesApi";
import {Button, ButtonGroup, Container, Table} from "reactstrap";
import {Link} from "react-router-dom";

export const CoursesPage = () => {
    const [courses, setCourses] = useState([]);
    useEffect(() => {
        coursesApi.getAll()
            .then(res => {
                setCourses(res.data)
            }).catch(err => console.error('[Fetch Error]:', err))
    }, [courses]);

    const coursesList = courses.map(course => {
        return (
            <tr key={course.id}>
                <td style={{ whiteSpace: 'nowrap' }}>{course.id}</td>
                <td style={{ whiteSpace: 'nowrap' }}>{course.name}</td>
                <td align='center'>
                    <ButtonGroup>
                        <Button size='sm' color='primary' tag={Link} to={'/courses/' + course.id}>
                            Edit
                        </Button>

                        <Button size='sm' color='danger' onClick={() => coursesApi.delete(course.id)}>
                            Delete
                        </Button>
                    </ButtonGroup>
                </td>
            </tr>
        )
    })

    return (
        <div>
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
                <div className='float-right'>
                    <Button color='success' tag={Link} to='/courses/new'>
                        Add
                    </Button>
                </div>
            </Container>
        </div>
    )

}