import React, { useState } from 'react';
import { sailingApi, TokenService } from '../../services';
import { useNavigate } from 'react-router-dom';
import { LoginUtils } from '../../utils';
import { AuthContext } from '../../contexts';
import { Navigation } from "../../constants";
import "./login.css";
import jwtDecode from "jwt-decode";

    const Login = () => {
    const initialCredentialsState = {
        emailAddress: '',
        password: '',
        isLoading: false,
    };

    
    const [credentials, setCredentials] = useState(initialCredentialsState);
    const resetFormState = ({...initialCredentialsState});
    const navigate = useNavigate();
    const { state, dispatch } = AuthContext.useLogin();

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
        console.log(jwtDecode(jwt))
        dispatch({
            type: "login",
            jwt,
          });
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
    
        <div class="wrapper">
        <form class="form-signin">       
          <h2 class="form-signin-heading">Please login</h2>
          <input type="email" id='emailAddress' class="form-control" onChange={onChange} placeholder="Email Address" required="" autofocus=""  disabled={credentials.isLoading} />
          <input type="password" id='password' class="form-control" onChange={onChange} name="password" placeholder="Password" required=""  disabled={credentials.isLoading}/>      
          <button onClick={async () => await loginClicked()} class="btn btn-lg btn-primary btn-block" disabled={credentials.isLoading}>Login</button>   
        </form>
      </div>
    );
};

export default Login;