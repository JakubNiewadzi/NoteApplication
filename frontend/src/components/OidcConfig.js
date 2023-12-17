export const OidcConfig = {
    authority: process.env.REACT_APP_KEYCLOAK_BASE_URL + '/realms/noteapp',
    client_id: 'frontend',
    onSigninCallback: () => {
        window.history.replaceState(
            {},
            document.title,
            window.location.pathname
        )
    }
}
