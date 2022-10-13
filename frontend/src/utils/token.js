import jwtDecode from "jwt-decode";

const isLoggedIn = () => {
  const jwt = localStorage.getItem("jwt")

  if (!jwt) return false;

  // add expiry checks etc here
  const accessToken = jwtDecode(jwt);
  const currentTime = new Date().getTime() / 1000;

  if (currentTime > accessToken.exp) return false;

  return true;
};

const Logout = () => {
  //check there isnt needed an if statement here
  const jwt = localStorage.removeItem("jwt")

  if(!jwt) return true;

  return false;
}

const TokenUtils = {
  isLoggedIn,
  Logout,
};

export default TokenUtils;