import React from 'react'
import {Nav, Navbar, NavbarBrand} from 'reactstrap'
import {NavLink} from 'react-router-dom'
import {useAuth} from 'react-oidc-context'

export const NavBar = () => {
    const auth = useAuth();
    const style = ({isActive}) => ({
        fontWeight: isActive ? 'bold' : 'normal'
    });

    const handleLogin = () => {
        auth.signinRedirect();
    };

    const handleLogout = () => {
        auth.signoutRedirect();
    };

    return (
        <Navbar color='dark' dark expand='md'>
            <Nav>
                <NavbarBrand tag={NavLink} to='/' style={style}>Home</NavbarBrand>
                <NavbarBrand tag={NavLink} to='/courses' style={style}>Courses</NavbarBrand>
                <NavbarBrand tag={NavLink} to='/notes' style={style}>Notes</NavbarBrand>
                {!auth.isAuthenticated ? (
                    <NavbarBrand tag={NavLink} onClick={handleLogin}>
                        Login
                    </NavbarBrand>
                )
                : auth.isAuthenticated && (
                    <NavbarBrand tag={NavLink} to='/' onClick={handleLogout} style={{ right: 0 }}>
                        Logout
                    </NavbarBrand>
                )}
            </Nav>
        </Navbar>
    )
}