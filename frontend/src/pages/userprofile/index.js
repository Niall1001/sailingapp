import React, {useEffect, useState} from "react";
import { sailingApi } from "../../services";
import { Paper } from '@mui/material';

const UserProfile = () => {
	const [userProfile, setUserProfile] = useState();
	const [isLoading, setLoading] = useState();
	const [error, setError] = useState();

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
        <div style={{ height: "500px", width: "400px", marginLeft: "25px", marginRight: "25px" }}>
		<Paper style={{ height: "100%", width: "400px", margin: "16px" }}>{userProfile.name}</Paper>
	  </div>
    );
};

export default UserProfile;