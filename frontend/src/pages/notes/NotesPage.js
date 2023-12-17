import React, {useEffect, useState} from "react";
import {notesApi} from "../../api/notesApi";
import {coursesApi} from "../../api/coursesApi";
import {
    Button,
    ButtonGroup,
    Container,
    Dropdown,
    DropdownItem,
    DropdownMenu,
    DropdownToggle,
    Table,
} from "reactstrap";
import {Link} from "react-router-dom";
import {useAuth} from "react-oidc-context";

const NoteTableRow = ({note, courses, onDelete}) => (
    <tr key={note.id}>
        <td style={{whiteSpace: 'nowrap'}}>{note.id}</td>
        <td style={{whiteSpace: 'nowrap'}}>{note.name}</td>
        <td style={{whiteSpace: 'nowrap'}}>{note.content}</td>
        <td style={{whiteSpace: 'nowrap'}}>{courses.find((course) => course.id === note.courseId).name}</td>
        <td style={{whiteSpace: 'nowrap'}}>{note.createdBy}</td>
        <td align='center'>
            <ButtonGroup>
                <Button color='primary' tag={Link} to={`/notes/${note.id}`}>
                    Edit
                </Button>
                <Button color='danger' onClick={() => onDelete(note.id)}>
                    Delete
                </Button>
            </ButtonGroup>
        </td>
    </tr>
);

const NoteTable = ({notes, courses, onDelete}) => (
    <Table striped bordered hover size='sm'>
        <thead>
        <tr>
            <th width='80px'>Id</th>
            <th>Name</th>
            <th>Content</th>
            <th>Course</th>
            <th>Created by</th>
            <th width='120px'>
                <div align='center'>Action</div>
            </th>
        </tr>
        </thead>
        <tbody>
        {notes.map((note) => (
            <NoteTableRow key={note.id} note={note} courses={courses} onDelete={onDelete}/>
        ))}
        </tbody>
    </Table>
);

const NoteDropdown = ({isOpen, toggleDropdown, selectedOption, handleOptionSelect, courses}) => (
    <Dropdown style={{width: '200px'}} isOpen={isOpen} toggle={toggleDropdown} className="w-100">
        <DropdownToggle caret size="lg">{selectedOption}</DropdownToggle>
        <DropdownMenu>
            <DropdownItem onClick={() => handleOptionSelect("All")}>All</DropdownItem>
            {courses.map((option, index) => (
                <DropdownItem key={index} value={option.name} onClick={() => handleOptionSelect(option.name)}>
                    {option.name}
                </DropdownItem>
            ))}
        </DropdownMenu>
    </Dropdown>
);

export const NotesPage = () => {
    const auth = useAuth()
    const accessToken = auth.user.access_token
    const [notes, setNotes] = useState([]);
    const [courses, setCourses] = useState([]);
    const [rerender, setRerender] = useState(false);
    const [dropdownOpen, setDropdownOpen] = useState(false);
    const [selectedOption, setSelectedOption] = useState("All");

    useEffect(() => {
        const fetchData = async () => {
            try {
                const [notesResponse, coursesResponse] = await Promise.all([
                    selectedOption === "All" ? notesApi.getAll(accessToken) : notesApi.getByCourseId(courses.find((course) => course.name === selectedOption).id, accessToken),
                    coursesApi.getAll(accessToken)
                ]);
                setNotes(notesResponse.data);
                setCourses(coursesResponse.data);
            } catch (error) {
                console.error('[Fetch Error]:', error);
            }
        };
        fetchData();
    }, [rerender, selectedOption]);

    const toggleDropdown = () => {
        setDropdownOpen((prevState) => !prevState);
    };

    const handleOptionSelect = (option) => {
        setSelectedOption(option);
        setDropdownOpen(false);
    };

    const onDelete = async (id) => {
        try {
            await notesApi.delete(id, accessToken);
            setRerender((prev) => !prev);
        } catch (err) {
            console.error("[Fetch Error]:", err);
        }
    };

    return (
        <div className='App-header'>
            <Container fluid>
                <h3>Courses</h3>
                <NoteTable notes={notes} courses={courses} onDelete={onDelete}/>
                <div className="d-flex justify-content-between align-items-center p-4">
                    <div>
                        <NoteDropdown
                            isOpen={dropdownOpen}
                            toggleDropdown={toggleDropdown}
                            selectedOption={selectedOption}
                            handleOptionSelect={handleOptionSelect}
                            courses={courses}
                        />
                    </div>
                    <div>
                        <ButtonGroup>
                            <Button size='lg' color='success' tag={Link} to='/notes/new'>
                                Add
                            </Button>
                        </ButtonGroup>
                    </div>
                </div>
            </Container>
        </div>
    );
};
