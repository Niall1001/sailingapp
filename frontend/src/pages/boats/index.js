import React, {useEffect, useState} from "react";
import { sailingApi } from "../../services";

const Boats = () => {
	const [boat, setBoat] = useState();
	const [isLoading, setLoading] = useState();
	const [error, setError] = useState();

	const getBoat = async (id)=>{
		try{
			const {data} = await sailingApi.boats.getOne(1);
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
		getBoat(1)
	},[]);

	if (isLoading) return <div>Loading</div>;
    if (error) return <div>Something went wrong</div>;
    if (!boat) return <div>Boat not found :(</div>;
	return (
        <div>
            <div className='boats-text'>
                <p>{boat.name}</p>
                <p>{boat.age}</p>
                <p>{boat.boatClass}</p>
				<p>{boat.description}</p>
				<p>{boat.id}</p>
				<p>{boat.sailNo}</p>
            </div>
        </div>
    );
};

export default Boats;