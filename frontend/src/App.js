import { Route, Routes, Navigate } from "react-router-dom";
import {
  Home,
  Events,
  Boats,
  Crews,
  Login,
  Register
} from "./pages";
import { TokenUtils } from "./utils";
import { Navigation } from "./constants";
import { Navbar } from "./components";


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
  const loggedIn = TokenUtils.isLoggedIn();



  return (
    <div className="app">
      <Navbar />
      <Routes>
      {loggedIn && AuthenticatedRoutes()}
      {!loggedIn && UnAuthenticatedRoutes()}
      </Routes>
    </div>
  );
}

export default App;