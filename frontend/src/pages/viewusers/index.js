import React, {useEffect, useState, useCallback} from "react";
import { sailingApi } from "../../services";
import { Paper } from '@mui/material';
import { toast } from 'react-toastify';
import Box from '@mui/material/Box';
import { DataGrid } from '@mui/x-data-grid';
import "./viewusers.css";
import 'react-toastify/dist/ReactToastify.css';
import { useNavigate } from 'react-router-dom';
import Snackbar from '@mui/material/Snackbar';
import Alert from '@mui/material/Alert';

const columns = [
	{ field: 'id', headerName: 'ID', width: 90 },
	{
	  field: 'name',
	  headerName: 'Name',
	  width: 150,
	  editable: false,
	},
	{
	  field: 'emailAddress',
	  headerName: 'Email',
	  type: 'string',
	  width: 110,
	  editable: false,
	},
	{
	  field: 'dob',
	  headerName: 'Date of Birth',
	  sortable: false,
	  width: 160,
	  valueGetter: (params) =>
		`${params.row.dob || ''}`,
	},
	{
		field: 'user_type',
		headerName: 'User type',
		type: 'number',
		width: 110,
		editable: true,
	},
  ];

	const ViewUsers = () => {
		
		const [rows, setRows] = useState();
		const [isLoading, setLoading] = useState();
		const [error, setError] = useState();
		const [snackbar, setSnackbar] = useState(null);
		const handleCloseSnackbar = () => setSnackbar(null);



	const getUsers = async (id)=>{
		try{
			const {data} = await sailingApi.appUsers.getMany();
			setRows(data);
            toast.info("Successfully retrieved all users data")
		}catch(error){
			setError(true);
            console.log(error);
            toast.error('Something went wrong!');
		}finally{
			setLoading = false;
		}
	}

	const processRowUpdate = useCallback(async (newRow) => {
		  console.log(newRow);
		  const {id, user_type} = newRow;
		  await sailingApi.appUsers.update(id, {user_type});
		  setSnackbar({ children: 'User successfully saved', severity: 'success' });
		},
	);

	useEffect(() => {
		getUsers()
	},[]);

	if (isLoading) return <div>Loading</div>;
    if (error) return <div>Something went wrong</div>;
    if (!rows) return <div>Users not found :(</div>;
	return (
	<div class="wrapper">
        <form class="form-signin"> 
			<h2>Edit user types</h2>
			<Box sx={{ height: 400, width: '100%' }}>
			<DataGrid
				rows={rows}
				columns={columns}
				pageSize={7}
				rowsPerPageOptions={[7]}
				disableSelectionOnClick
				experimentalFeatures={{ newEditingApi: true }}
				processRowUpdate={processRowUpdate}
			/>
				{!!snackbar && (
					<Snackbar
					open
					anchorOrigin={{ vertical: 'bottom', horizontal: 'center' }}
					onClose={handleCloseSnackbar}
					autoHideDuration={6000}
					>
					<Alert {...snackbar} onClose={handleCloseSnackbar} />
					</Snackbar>
      			)}
			</Box>
		</form>
	</div>
    );
};

export default ViewUsers;