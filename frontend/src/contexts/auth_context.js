import React, { createContext, useReducer } from "react";
import { TokenService } from "../services";

const AuthContext = createContext();

function authReducer(state, action) {
    switch (action.type) {
      case "login": {
        return {
          ...state,
          access: action.access,
        };
      }
      case "logout": {
        return {};
      }
      default: {
        throw new Error(`Unhandled action type: ${action.type}`);
      }
    }
  }

  function AuthProvider({ children }) {
    const auth = TokenService.getAuth() ?? {};
  
    const [state, dispatch] = useReducer(authReducer, auth);
    const value = { state, dispatch };
    return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
  }
  
  function useLogin() {
    const context = React.useContext(AuthContext);
    if (context === undefined) {
      throw new Error("useLogin must be used within a AuthProvider");
    }
    return context;
  }

  export default { AuthProvider, useLogin };