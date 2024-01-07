import React, {useEffect, useState} from 'react'
import {Link, useNavigate, useParams} from 'react-router-dom'
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap'
import {coursesApi} from '../../../api/coursesApi'
import '../../../App.css'
import {useAuth} from "react-oidc-context";

export const CoursesForm = () => {
    const auth = useAuth()
    const accessToken = auth.user.access_token
    const username = auth.user.profile.preferred_username
    const navigate = useNavigate();
    const {courseId} = useParams();
    const [course, setCourse] = useState({
        name: ''
    });

    useEffect(() => {
        if (courseId !== 'new') {
            console.log(accessToken)
            coursesApi.getById(courseId, accessToken)
                .then((res) => {
                    setCourse(res.data, accessToken)
                });
        }
    }, [courseId]);

    const handleChange = (event) => {
        event.preventDefault();
        const target = event.target;
        const name = target.name;
        const value = target.value;

        setCourse({...course, [name]: value});
    };
    const handleSubmit = async (event) => {
        event.preventDefault();

        if (course.id) {
            if (course.createdBy === username) {
                await coursesApi.update(course.id, course, accessToken);
            }
        } else {
            await coursesApi.create(course, accessToken);
        }
        navigate('/courses');
    };

    const title = <h2>{course.id ? 'Edit course' : 'Add course'}</h2>;
    console.log(course);

    return (
        <div className='Site-content'>
            <Container>
                {title}
                <Form onSubmit={handleSubmit}>
                    <FormGroup>
                        <Label for='name'>Name</Label>
                        <Input
                            id='name'
                            type='text'
                            name='name'
                            value={course.name || ''}
                            onChange={handleChange}
                            autoComplete='Name'
                        />
                    </FormGroup>
                    <FormGroup>
                        <Button color='primary' type='submit'>Save</Button>{' '}
                        <Button color='secondary' tag={Link} to='/courses'>Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    );
}