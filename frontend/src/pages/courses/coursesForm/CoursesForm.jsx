import React, { useEffect, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom'
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap'
import { coursesApi } from '../../../api/coursesApi'

export const CoursesForm = () => {
    const navigate = useNavigate()
    const { courseId } = useParams()
    const [course, setCourse] = useState({
        name: ''
    })

    useEffect(() => {
        if (courseId !== 'new') {
            coursesApi.getById(courseId)
                .then((res) => {
                    setCourse(res.data)
                })
        }
    }, [courseId])

    const handleChange = (event) => {
        event.preventDefault()
        const target = event.target
        const name = target.name
        const value = target.value

        setCourse({ ...course, [name]: value})
    }
    const handleSubmit = async (event) => {
        event.preventDefault()

        if (course.id) {
            await coursesApi.update(course.id, course)
        } else {
            await coursesApi.create(course)
        }
        navigate('/courses')
    }

    const title = <h2>{course.id ? 'Edit course' : 'Add course'}</h2>

    return (
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
    )
}