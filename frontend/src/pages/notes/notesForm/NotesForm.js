import {Link, useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {notesApi} from "../../../api/notesApi";
import {Button, Container, Form, FormGroup, Input, Label} from "reactstrap";
import {coursesApi} from "../../../api/coursesApi";
import {useAuth} from "react-oidc-context";

export const NotesForm = () => {
    const auth = useAuth()
    const accessToken = auth.user.access_token
    const navigate = useNavigate();
    const {noteId} = useParams();
    const [note, setNote] = useState({
        name: '',
        content: '',
        courseId: '',
    });

    const [courses, setCourses] = useState([]);


    useEffect(() => {
            const fetchData = async () => {
                try {
                    if (noteId !== 'new') {
                        const [notesResponse, coursesResponse] = await Promise.all([
                            notesApi.getById(noteId, accessToken),
                            coursesApi.getAll(accessToken)
                        ]);
                        setNote(notesResponse.data);
                        setCourses(coursesResponse.data);
                    } else {
                        coursesApi.getAll(accessToken)
                            .then(res => setCourses(res.data))
                    }
                } catch
                    (error) {
                    console.error('[Fetch Error]:', error);
                }
            };
            fetchData();
        }, [noteId]
    );

    const handleChange = (event) => {
        const target = event.target;
        const value = target.value;
        const name = target.name;

        setNote({...note, [name]: value});
    };

    const handleFormChange = (event) => {
        if (event.target.value !== "") {
            const target = event.target;
            const name = target.name;
            const selectedCourse = courses.find(course => course.name === target.value).id;

            setNote({
                ...note,
                [name]: selectedCourse // Set the course ID or an empty string if not found
            });
        }
    }

    const handleSubmit = async (event) => {
        event.preventDefault()

        if (note.id) {
            await notesApi.update(note.id, note, accessToken);
        } else {
            await notesApi.create(note, accessToken);
        }
        navigate('/notes');
    };

    const title = <h2>{note.id ? 'Edit note' : 'Add Note'}</h2>;
    console.log(note.courseId !== '')
    return (
        <div className='App-header'>
            <Container>
                {title}
                <Form onSubmit={handleSubmit}>
                    <FormGroup>
                        <Label for='name'>Title</Label>
                        <Input
                            id='name'
                            name='name'
                            type='text'
                            value={note.name || ''}
                            onChange={handleChange}
                            autoComplete='Name'
                        />
                    </FormGroup>
                    <FormGroup>
                        <Label for='content'>Text</Label>
                        <Input
                            id='content'
                            name='content'
                            type='text'
                            value={note.content || ''}
                            onChange={handleChange}
                            autoComplete='Content'
                        />
                    </FormGroup>
                    <FormGroup>
                        <Label for='courseId'>Course</Label>
                        <Input
                            type="select"
                            name='courseId'
                            id="courseId"
                            onChange={handleFormChange}
                            value={note.courseId !== '' ? courses.find((course) => course.id === note.courseId).name : ''}
                        >
                            <option value="">Select a course</option>
                            {courses.map((option, index) => (
                                <option key={index} value={option.name}>
                                    {option.name}
                                </option>
                            ))}
                        </Input>
                    </FormGroup>
                    <FormGroup>
                        <Button color='primary' type='submit'>Save</Button>{' '}
                        <Button color='secondary' tag={Link} to='/notes'>Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    );
}