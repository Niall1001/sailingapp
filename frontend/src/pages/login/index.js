import React, { useState } from 'react';
import { sailingApi } from '../../services';
import { useNavigate } from 'react-router-dom';


const Login = () => {
    const initialCredentialsState = {
        emailAddress: '',
        password: '',
        isLoading: false,
    };

    const [credentials, setCredentials] = useState(initialCredentialsState);
    const resetFormState = ({...initialCredentialsState});
    const navigate = useNavigate();
    const [submitted, setSubmitted] = useState(false);

    const onChange = (e) => {
        const { id, value } = e.target;
        setCredentials({
            ...credentials,
            [id]: value,
        });
    };

    const onSubmit = async (e) => {
        e.preventDefault();
        setCredentials({ ...credentials, isLoading: true });
        try {
            const {
                data: { jwt },
            } = await sailingApi.appUsers.login({ emailAddress: credentials.emailAddress, password: credentials.password });
            localStorage.setItem('jwt', jwt);
            setSubmitted(true);
            navigate("/home");

        } catch (e) {
            alert('something went wrong!');
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
        <form onSubmit={onSubmit}>
            <label htmlFor='emailAddress'>Email:</label>
            <input type='email' id='emailAddress' onChange={onChange} disabled={credentials.isLoading} />
            <label htmlFor='password'>Password:</label>
            <input type='password' id='password' onChange={onChange} disabled={credentials.isLoading} />
            <button onClick={onSubmit} disabled={credentials.isLoading}>Login</button>
        </form>
    );

};

export default Login;