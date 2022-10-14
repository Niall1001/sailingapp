import React, {useEffect, useState} from "react";
import { sailingApi } from "../../services";
import { Paper } from '@mui/material';

const Container = (boat) => {
	return (
	  <div style={{ height: "500px", width: "400px", marginLeft: "25px", marginRight: "25px" }}>
		<Paper style={{ height: "100%", width: "400px", margin: "16px" }}>{boat.name}</Paper>
	  </div>
	);
  };

const Boats = () => {
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
        <div style={{ width: "100%", overflow: "scroll", display: "flex" }}>
            {boat.map(({ ...boat }) => (
              <Container {...boat}/>
            ))}
		</div>
    );
};

export default Boats;