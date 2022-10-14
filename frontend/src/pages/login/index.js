import React, { useState } from 'react';
import { sailingApi, TokenService } from '../../services';
import { useNavigate } from 'react-router-dom';
import { LoginUtils } from '../../utils';
import { AuthContext } from '../../contexts';
import { Navigation } from "../../constants";


    const Login = () => {
    const initialCredentialsState = {
        emailAddress: '',
        password: '',
        isLoading: false,
    };

    const LoggedIn = LoginUtils.isLoggedIn();
    const [credentials, setCredentials] = useState(initialCredentialsState);
    const resetFormState = ({...initialCredentialsState});
    const navigate = useNavigate();
    const { dispatch } = AuthContext.useLogin();

    const onChange = (e) => {
        const { id, value } = e.target;
        setCredentials({
            ...credentials,
            [id]: value,
        });
    };

    const loginClicked = async () => {
        setCredentials({ ...credentials, isLoading: true });
        try {
            const {
                data: { jwt },
            } = await sailingApi.appUsers.login({ emailAddress: credentials.emailAddress, password: credentials.password });
        TokenService.setAuth(jwt);
        navigate(Navigation.HOME);
      }catch (e) {
        alert('Wrong login Details provided! Please try again');
        console.error(e);
        resetFormState();
    } finally {
        setCredentials({
            ...credentials,
            isLoading: false,
            });
        }
    };

	return (
        <form>
            <label htmlFor='emailAddress'>Email:</label>
            <input type='email' id='emailAddress' onChange={onChange} disabled={credentials.isLoading} />
            <label htmlFor='password'>Password:</label>
            <input type='password' id='password' onChange={onChange} disabled={credentials.isLoading} />
            <button onClick={async () => await loginClicked()} disabled={credentials.isLoading}>Login</button>
        </form>
    );

};

export default Login;