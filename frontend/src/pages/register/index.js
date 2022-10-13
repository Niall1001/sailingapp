import React, { useState } from 'react';
import { sailingApi } from '../../services';
import { useNavigate } from 'react-router-dom';


const Register = () => {
    const initialCredentialsState = {
        name: '',
        emailAddress: '',
        dob: '',
        password: '',
        user_type: '',
        isLoading: false,
    };

    const [credentials, setCredentials] = useState(initialCredentialsState);
    const resetFormState = ({...initialCredentialsState});
    const navigate = useNavigate();

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
            debugger;
            await sailingApi.appUsers.regsiter({ name: credentials.name ,emailAddress: credentials.emailAddress, dob: credentials.dob, password: credentials.password, user_type: credentials.user_type });
            navigate("/login");
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
            <label htmlFor='name'>Name:</label>
            <input type='name' id='name' onChange={onChange} disabled={credentials.isLoading} />
            <label htmlFor='emailAddress'>Email:</label>
            <input type='email' id='emailAddress' onChange={onChange} disabled={credentials.isLoading} />
            <label htmlFor='dob'>DOB:</label>
            <input type="date" id='dob' onChange={onChange} disabled={credentials.isLoading} />
            <label htmlFor='password'>Password:</label>
            <input type='password' id='password' onChange={onChange} disabled={credentials.isLoading} />
            <label htmlFor='user_type'>User type:</label>
            <input type='user_type' id='user_type' onChange={onChange} disabled={credentials.isLoading} />
            <button onClick={onSubmit} disabled={credentials.isLoading}>Register</button>
        </form>
    );

};

export default Register;