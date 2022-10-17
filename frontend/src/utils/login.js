import jwtDecode from "jwt-decode";

const isTokenExpired = (token) => {
  if (!token || !token.access) return true;

  const accessToken = jwtDecode(token.access);
  const currentTime = new Date().getTime() / 1000;

  if (currentTime < accessToken.exp) return false;

  return true;
};

const isLoggedIn = () => {
  const jwt = localStorage.getItem("jwt")

  if (!jwt) return false;

  // add expiry checks etc here
  const accessToken = jwtDecode(jwt);
  const currentTime = new Date().getTime() / 1000;

  if (currentTime > accessToken.exp) return false;

  return true;
};

const isAdminUser = (access) => {
  //if(typeof access !== "string" || !access) return false;
  //const {user_role_id} = jwtDecode(access);
  console.log(access)
  //return user_role_id === 1;
}

const isBoatOwnerUser = (access) => {
  if(typeof access !== "string" || !access) return false;
  const {user_role_id} = jwtDecode(access);
  return user_role_id === 2;
}

const isCrewUser = (access) => {
  if(typeof access !== "string" || !access) return false;
  const {user_role_id} = jwtDecode(access);
  return user_role_id === 3;
}

const Logout = () => {
  //check there isnt needed an if statement here
  const jwt = localStorage.removeItem("jwt")

  if(!jwt) return true;

  return false;
}

const TokenUtils = {
  isTokenExpired,
  isLoggedIn,
  Logout,
};

export default TokenUtils;