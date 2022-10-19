import React, {useEffect, useState} from "react";
import { sailingApi } from "../../services";
import { Paper } from '@mui/material';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const Container = (user) => {
	return (
	  <div style={{ height: "500px", width: "400px", marginLeft: "25px", marginRight: "25px" }}>
		<Paper style={{ height: "100%", width: "400px", margin: "16px" }}>{user.name}
        <button>Update user type</button>
        </Paper>
	  </div>
	);
  };

const ViewUsers = () => {
	const [users, setUsers] = useState();
	const [isLoading, setLoading] = useState();
	const [error, setError] = useState();

	const getUsers = async (id)=>{
		try{
			const {data} = await sailingApi.appUsers.getMany();
			setUsers(data);
            toast.info("Successfully retrieved all users data")
		}catch(error){
			setError(true);
            console.log(error);
            toast.error('Something went wrong!');
		}finally{
			setLoading = false;
		}
	}

	useEffect(() => {
		getUsers()
	},[]);

	if (isLoading) return <div>Loading</div>;
    if (error) return <div>Something went wrong</div>;
    if (!users) return <div>Users not found :(</div>;
	return (
        <div style={{ width: "100%", overflow: "scroll", display: "flex" }}>
            {users.map(({ ...users }) => (
              <Container {...users}/>
            ))}
		</div>
    );
};

export default ViewUsers;