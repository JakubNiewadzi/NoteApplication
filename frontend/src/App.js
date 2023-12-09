import React from 'react'
import {Route, Routes} from 'react-router-dom'
import {NavBar} from './components/NavBar'
import {Home} from './pages/Home'
import {CoursesPage} from "./pages/courses/CoursesPage";
import {CoursesForm} from "./pages/courses/coursesForm/CoursesForm";

export default function App () {
    return (
        <>
            <NavBar />
            <Routes>
                <Route index element={<Home />} />
                <Route path='/courses' element={<CoursesPage />} />
                <Route path='/courses/:courseId' element={<CoursesForm/>} />
            </Routes>
        </>
    )
}