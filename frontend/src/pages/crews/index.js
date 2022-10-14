import React, {useEffect, useState} from "react";
import { sailingApi } from "../../services";
import { Paper } from '@mui/material';

const Container = (crew) => {
	return (
	  <div style={{ height: "500px", width: "400px", marginLeft: "25px", marginRight: "25px" }}>
		<Paper style={{ height: "100%", width: "400px", margin: "16px" }}>{crew.name}</Paper>
	  </div>
	);
  };

const Events = () => {
	const [crew, setCrew] = useState();
	const [isLoading, setLoading] = useState();
	const [error, setError] = useState();

	const getCrews = async ()=>{
		try{
			const {data} = await sailingApi.appUsers.getMany();
			setCrew(data);
		}catch(error){
			setError(true);
            console.log(error);
            alert('Something went wrong!');
		}finally{
			setLoading = false;
		}
	}

	useEffect(() => {
		getCrews()
	},[]);
	
	
	if (isLoading) return <div>Loading</div>;
    if (error) return <div>Something went wrong</div>;
    if (!crew) return <div>Crew not found :(</div>;
	return (
		<div style={{ width: "100%", overflow: "scroll", display: "flex" }}>
            {crew.map(({ ...crew }) => (
              <Container {...crew}/>
            ))}
		</div>
    );
};

export default Events;