const getAuth = () => {
    const auth = localStorage.getItem("jwt");
    
    return auth;
  };
  
  const setAuth = (jwt) => {
      localStorage.setItem("jwt", jwt);
      const auth = localStorage.getItem("jwt");
      if (!auth) return false;
  
    return true;
  };
  
  const removeAuth = () => {
    localStorage.removeItem("jwt");
  };
  
  export default {
    getAuth,
    setAuth,
    removeAuth,
  };