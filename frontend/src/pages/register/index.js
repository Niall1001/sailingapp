import React, { useState } from 'react';
import { sailingApi } from '../../services';
import { useNavigate } from 'react-router-dom';
import {toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

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
            console.log(credentials);
            await sailingApi.appUsers.regsiter({ name: credentials.name ,emailAddress: credentials.emailAddress, dob: credentials.dob, password: credentials.password, user_type: credentials.user_type });
            toast.success('Successfully Registered!');
            navigate("/login");
        } catch (e) {
            toast.error('something went wrong!');
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
          <h2 class="form-signin-heading">Please Register</h2>
          <input class="form-control" type='name' id='name' placeholder="Name" onChange={onChange} disabled={credentials.isLoading} />
            <input class="form-control" type='email' id='emailAddress' placeholder="Email" onChange={onChange} disabled={credentials.isLoading} />
            <input class="form-control" type="date" id='dob' placeholder="DOB" onChange={onChange} disabled={credentials.isLoading} />
            <input class="form-control" type='password' id='password' placeholder="Password" onChange={onChange} disabled={credentials.isLoading} />
            <input class="form-control" type='usertype' id='user_type' placeholder="Usertype (1 - Admin, 2 - Crew, 3 - Boat Owner)" onChange={onChange} disabled={credentials.isLoading} />
            <button class="btn btn-lg btn-primary btn-block" onClick={onSubmit} disabled={credentials.isLoading}>Register</button>
        </form>
      </div>
    );

};

export default Register;