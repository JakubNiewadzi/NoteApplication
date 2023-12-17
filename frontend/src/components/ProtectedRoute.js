import {useAuth} from "react-oidc-context";
import {Outlet} from "react-router-dom";
import {Loading} from "./Loading";

export const ProtectedRoute = () => {
    const auth = useAuth();

    if (auth.isLoading) {
        return Loading;
    } else if (!auth.isAuthenticated) {
        auth.signinRedirect({redirect_uri: window.location.href});
        return null;
    }

    return <Outlet/>;
}