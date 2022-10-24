import React, {useEffect, useState} from "react";
import { sailingApi } from "../../services";
import { Paper } from '@mui/material';
import "./boatuserpage.css";

const BoatUser = () => {
	const [boat, setBoat] = useState();
	const [isLoading, setLoading] = useState();
	const [error, setError] = useState();

	const getBoats = async (id)=>{
		try{
			const {data} = await sailingApi.boats.getMany();
			setBoat(data);
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
		getBoats()
	},[]);

	if (isLoading) return <div>Loading</div>;
    if (error) return <div>Something went wrong</div>;
    if (!boat) return <div>Boat not found :(</div>;
	return (
        <div className="splitScreen">
            <div className="topPane">Top</div>
            <div className="bottomPane">Bottom</div>
        </div>
    );
};

export default BoatUser;