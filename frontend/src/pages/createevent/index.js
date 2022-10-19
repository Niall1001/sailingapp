import React, {useEffect, useState} from "react";
import { sailingApi } from "../../services";
import { Paper } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import {toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const CreateEvent = () => {
    const initialCredentialsState = {
        name: '',
        type: '',
        date: '',
        description: '',
        status: 1,
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
        console.log(credentials);
        try {
            await sailingApi.events.createEvent({ name: credentials.name ,type: credentials.type, date: credentials.date, description: credentials.description, status: credentials.status });
            toast.success('Successfully Created Event!');
        } catch (e) {
            toast.error('Something went wrong with creating the event!');
            console.error(e);
            
        } finally {
            setCredentials({
                ...credentials,
                isLoading: false,
            });
        }
    }
	return (
        <div class="wrapper">    
          <h2 class="form-signin-heading">Create Event</h2>
          <input type='name' id='name' placeholder="Name of Event" onChange={onChange} disabled={credentials.isLoading} />
            <input type='type' id='type' placeholder="type of event" onChange={onChange} disabled={credentials.isLoading} />
            <input type="date" id='date' placeholder="date of event" onChange={onChange} disabled={credentials.isLoading} />
            <input type='description' id='description' placeholder="Description" onChange={onChange} disabled={credentials.isLoading} />
            <input type='number' id='status' placeholder="Status of Event" onChange={onChange} disabled={credentials.isLoading} />
            <button class="btn btn-lg btn-primary btn-block" onClick={onSubmit} disabled={credentials.isLoading}>Create</button>
      </div>
    );
};

export default CreateEvent;