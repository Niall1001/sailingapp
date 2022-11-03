import React, {useEffect, useState} from "react";
import { sailingApi } from "../../services";
import { Paper } from '@mui/material';
import "./boatuserpage.css";

const BoatUser = () => {
	const [isLoading, setLoading] = useState();
	const [error, setError] = useState();

	

	useEffect(() => {
		
	},[]);

	if (isLoading) return <div>Loading</div>;
    if (error) return <div>Something went wrong</div>;
	return (
        <div className="splitScreen">
            <div className="topPane">Left</div>
            <div className="bottomPane">Right</div>
        </div>
    );
};

export default BoatUser;