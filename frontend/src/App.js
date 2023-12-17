import React from 'react'
import {Route, Routes} from 'react-router-dom'
import {NavBar} from './components/NavBar'
import {Home} from './pages/Home'
import {CoursesPage} from "./pages/courses/CoursesPage";
import {CoursesForm} from "./pages/courses/coursesForm/CoursesForm";
import {NotesPage} from "./pages/notes/NotesPage";
import {NotesForm} from "./pages/notes/notesForm/NotesForm";
import {ProtectedRoute} from "./components/ProtectedRoute";

export default function App() {
    return (
        <>
            <NavBar/>
            <Routes>
                <Route index element={<Home/>}/>
                <Route element={<ProtectedRoute/>}>
                    <Route path='/courses' element={<CoursesPage/>}/>
                    <Route path='/courses/:courseId' element={<CoursesForm/>}/>
                    <Route path='/notes' element={<NotesPage/>}/>
                    <Route path='notes/:noteId' element={<NotesForm/>}/>
                </Route>
            </Routes>
        </>
    )
}