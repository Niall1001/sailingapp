import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import { BrowserRouter as Router } from "react-router-dom";
import { AuthContext } from "./contexts";

ReactDOM.render(
  <React.StrictMode>
    <Router>
      <AuthContext.AuthProvider>
          <App />
      </AuthContext.AuthProvider>
    </Router>
  </React.StrictMode>,
  document.getElementById("root")
);