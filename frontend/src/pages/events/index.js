import React, {useEffect, useState} from "react";
import { sailingApi } from "../../services";
import { Paper } from '@mui/material';

const Container = (event) => {
	return (
	  <div style={{ height: "500px", width: "400px", marginLeft: "25px", marginRight: "25px" }}>
		<Paper style={{ height: "100%", width: "400px", margin: "16px" }}>{event.name}</Paper>
	  </div>
	);
  };

const Events = () => {
	const [event, setEvent] = useState();
	const [isLoading, setLoading] = useState();
	const [error, setError] = useState();

	const getEvents = async (id)=>{
		try{
			const {data} = await sailingApi.events.getMany();
			setEvent(data);
		}catch(error){
			setError(true);
            alert('Something went wrong!');
		}finally{
			setLoading = false;
		}
	}
	useEffect(() => {
		getEvents()
	},[]);

	if (isLoading) return <div>Loading</div>;
    if (error) return <div>Something went wrong</div>;
    if (!event) return <div>Event not found :(</div>;
	return (
        <div style={{ width: "100%", overflow: "scroll", display: "flex" }}>
            {event.map(({ ...event }) => (
              <Container {...event}/>
            ))}
		</div>
    );
};

export default Events;