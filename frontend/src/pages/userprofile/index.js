import React, {useEffect, useState} from "react";
import { sailingApi } from "../../services";
import { Paper, Typography, TextField } from '@mui/material';
import Button from '@mui/material/Button';
import './userprofile.css';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useNavigate } from 'react-router-dom';

const UserProfile = () => {
	const [userProfile, setUserProfile] = useState();
	const initialCredentialsState = {
		name: '',
		emailAddress: '',
		dob: '',
		user_type: ''
	};
	const [edit, setEdit] = useState(false);
	const [isLoading, setLoading] = useState();
	const [error, setError] = useState();
	const resetFormState = ({...initialCredentialsState});
    const navigate = useNavigate();
	const [credentials, setCredentials] = useState(initialCredentialsState);

	const EditClick = () => {
		setEdit(true);
	}

	const CancelEdit = () => {
		setEdit(false);
	}

	const onChange = (e) => {
        const { id, value } = e.target;

        setCredentials({
            ...credentials,
            [id]: value,
        });
    };

	const SubmitChange = async (e) => {
        e.preventDefault();
        setCredentials({ ...credentials, isLoading: true });
        try {
            await sailingApi.appUsers.update(userProfile.id, { name: credentials.name ,emailAddress: credentials.emailAddress, dob: credentials.dob});
            toast.success('Successfully Updated User profile!');
            navigate("/login");
        } catch (e) {
            toast.error('Uh-oh! Something went wrong!');
            console.error(e);
            resetFormState();
        } finally {
            setCredentials({
                ...credentials,
                isLoading: false,
            });
        }
	}

	const getUserProfile = async ()=>{
		try{
			const {data} = await sailingApi.appUsers.getCurrentUser();
			setUserProfile(data);
			console.log(data);
		}catch(error){
			setError(true);
            console.log(error);
            alert('Something went wrong!');
		}finally{
			setLoading = false;
		}
	}

	useEffect(() => {
		getUserProfile()
	},[]);

	if (isLoading) return <div>Loading</div>;
    if (error) return <div>Something went wrong</div>;
    if (!userProfile) return <div>User not found :(</div>;
	return (
	<div class="wrapper">
		{edit ? 
		<form class="form-signin">
			<h2 class="form-signin-heading">Change Users Profile - Will require login again once complete</h2>
			<TextField id="name" placeholder={userProfile.name} label="Name" onChange={onChange} variant="standard" />
			<TextField id="emailAddress" placeholder={userProfile.emailAddress} label="Email" onChange={onChange} variant="standard" />
			<TextField type="date" id="dob" placeholder={userProfile.dob} onChange={onChange} variant="outlined" />
			<Button variant="outlined" onClick={SubmitChange}>Submit Changes</Button>
			<Button variant="outlined" color="error" onClick={CancelEdit}>Cancel</Button>
		</form>
		:
		<form class="form-signin">  
			<h2 class="form-signin-heading">Users Profile</h2>
			<Typography style={{ height: "100%",width: "400px", margin: "16px" }}>{userProfile.name}</Typography>
			<Typography style={{ height: "100%",width: "400px", margin: "16px" }}>{userProfile.emailAddress}</Typography>
			<Typography style={{ height: "100%",width: "400px", margin: "16px" }}>{userProfile.dob}</Typography>
			<Typography style={{ height: "100%",width: "400px", margin: "16px" }}>{userProfile.userAccessStatus.status}</Typography>
			<Button variant="outlined" onClick={EditClick}>Edit Profile</Button>
		</form>
		}
		
	</div>
	);
};

export default UserProfile;