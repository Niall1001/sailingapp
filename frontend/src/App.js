import { Route, Routes, Navigate } from "react-router-dom";
import {
  Home,
  Events,
  Boats,
  Crews,
  Login,
  Register,
  UserProfile,
  CreateEvent
} from "./pages";
import { Navigation } from "./constants";
import { Navbar } from "./components";
import { AuthContext } from "./contexts";
import { useState, useEffect } from "react";
import {ToastContainer, toast} from 'react-toastify';

const AuthenticatedRoutes = () => {
  return (
    <>
      <Route
          path={Navigation.HOME}
          caseSensitive={false}
          element={<Home />}
        />
        <Route
          path={Navigation.EVENTS}
          caseSensitive={false}
          element={<Events />}
        />
      <Route
          path={Navigation.BOATS}
          caseSensitive={false}
          element={<Boats />}
        />
        <Route
          path={Navigation.CREWS}
          caseSensitive={false}
          element={<Crews />}
        />
        <Route
          path={Navigation.LOGIN}
          caseSensitive={false}
          element={<Login />}
        />
        <Route
          path={Navigation.USERPROFILE}
          caseSensitive={false}
          element={<UserProfile />}
        />
        <Route
          path={Navigation.CREATEEVENT}
          caseSensitive={false}
          element={<CreateEvent />}
        />
      <Route path="*" element={<Navigate to={Navigation.HOME} />} />
    </>
  );
};

const UnAuthenticatedRoutes = () => {
  return (
    <>
      <Route
          path={Navigation.HOME}
          caseSensitive={false}
          element={<Home />}
        />
      <Route
          path={Navigation.LOGIN}
          caseSensitive={false}
          element={<Login />}
        />
        <Route
          path={Navigation.REGISTER}
          caseSensitive={false}
          element={<Register />}
        />
    
      <Route path="*" element={<Navigate to={Navigation.LOGIN} />} />
    </>
  );
};

function App() {
  const { state, dispatch } = AuthContext.useLogin();
  const [loggedIn, setLoggedIn] = useState(false);
  
  
  useEffect(() => {
    const loggedIn = state.access;
    setLoggedIn(loggedIn);
    }, [state]);

  return (
    <div className="app">
      <Navbar />
        <Routes>
        {loggedIn ? AuthenticatedRoutes() : UnAuthenticatedRoutes()}
        </Routes>
        <ToastContainer 
        position="top-right"
        autoClose={1250}
        hideProgressBar
        newestOnTop
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
        theme="light"/>
    </div>
  );
}

export default App;