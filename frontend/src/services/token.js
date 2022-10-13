const getAuth = () => {
    const auth = localStorage.getItem("jwt");
    if (!auth) return null;
  
    return JSON.parse(auth);
  };
  
  const setAuth = (auth) => {
    if (auth) {
      localStorage.setItem("jwt", JSON.stringify(auth));
    }
  };
  
  const removeAuth = () => {
    localStorage.removeItem("jwt");
  };
  
  export default {
    getAuth,
    setAuth,
    removeAuth,
  };