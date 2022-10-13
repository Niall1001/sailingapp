import * as React from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import Menu from "@mui/material/Menu";
import MenuIcon from "@mui/icons-material/Menu";
import Container from "@mui/material/Container";
import Button from "@mui/material/Button";
import MenuItem from "@mui/material/MenuItem";
import { NavLink } from "react-router-dom";
import { useNavigate } from 'react-router-dom';
import { TokenUtils } from '../utils';

const Authpages = [
  {
    name: "Home",
    url: "/home",
  },
  {
    name: "Events",
    url: "/events",
  },
  {
    name: "Crews",
    url: "/crews",
  },
  {
    name: "Boats",
    url: "/boats",
  },
];

const UnAuthpages = [
  {
    name: "Login",
    url: "/login",
  },
  {
    name: "Register",
    url: "/register",
  },

];

const LogoutPage = [
  {
    name: "Logout",
    url: "/login",
  },
];

const Logout = () => {
  const loggedOut = TokenUtils.Logout();
  const navigate = NavLink();
  
  navigate("/login");
}

const Navbar = () => {
  const loggedIn = TokenUtils.isLoggedIn();
  const [anchorElNav, setAnchorElNav] = React.useState(null);

  const handleOpenNavMenu = (event) => {
    setAnchorElNav(event.currentTarget);
  };

  const handleCloseNavMenu = () => {
    setAnchorElNav(null);
  };

  return (
    <AppBar position="static" style={{ background: "#FFFFFF" }} elevation={0}>
      <Container maxWidth="xl">
        <Toolbar disableGutters>
          <Typography
            style={{ color: "#0000FF" }}
            variant="h6"
            noWrap
            component="div"
            sx={{ mr: 2, display: { xs: "none", md: "flex" } }}
          >
            <NavLink to="/" style={{ textDecoration: "none" }}>
            </NavLink>
          </Typography>

          <Box sx={{ flexGrow: 1, display: { xs: "flex", md: "none" } }}>
            <IconButton
              style={{ color: "#83314E" }}
              size="large"
              aria-label="account of current user"
              aria-controls="menu-appbar"
              aria-haspopup="true"
              onClick={handleOpenNavMenu}
              color="inherit"
            >
              <MenuIcon style={{ color: "#83314E" }} />
            </IconButton>
            <Menu
              style={{ color: "#83314E" }}
              id="menu-appbar"
              anchorEl={anchorElNav}
              anchorOrigin={{
                vertical: "bottom",
                horizontal: "left",
              }}
              keepMounted
              transformOrigin={{
                vertical: "top",
                horizontal: "left",
              }}
              open={Boolean(anchorElNav)}
              onClose={handleCloseNavMenu}
              sx={{
                display: { xs: "block", md: "none" },
              }}
            >
              {Authpages.map(({ name, url }) => (
                <NavLink
                  to={url}
                  style={{ textDecoration: "none", color: "#83314E" }}
                >
                  <MenuItem key={name} onClick={handleCloseNavMenu}>
                    {name}
                  </MenuItem>
                </NavLink>
              ))}
            </Menu>
          </Box>
          <Typography
            style={{ color: "#83314E" }}
            variant="h6"
            noWrap
            component="div"
            sx={{ flexGrow: 1, display: { xs: "flex", md: "none" } }}
          >
            <NavLink
              to="/"
              style={{ textDecoration: "none", color: "#83314E" }}
            >
            </NavLink>
          </Typography>
          <Box
            className="nav"
            style={{ textDecoration: "none", color: "#0000FF" }}
            sx={{ flexGrow: 1, display: { xs: "none", md: "flex" } }}
          >
            {!loggedIn && UnAuthpages.map(({ name, url }) => (
              <NavLink to={url} style={{ textDecoration: "none" }}>
                <Button
                  style={{ color: "#83314E", textDecoration: "none" }}
                  key={name}
                  sx={{ my: 2, color: "white", display: "block" }}
                >
                  {name}
                </Button>
              </NavLink>
            ))}
          </Box>
          <Box
            className="nav"
            style={{ textDecoration: "none", color: "#0000FF" }}
            sx={{ flexGrow: 1, display: { xs: "none", md: "flex" } }}
          >
            {loggedIn && Authpages.map(({ name, url }) => (
              <NavLink to={url} style={{ textDecoration: "none" }}>
                <Button
                  style={{ color: "#83314E", textDecoration: "none" }}
                  key={name}
                  sx={{ my: 2, color: "white", display: "block" }}
                >
                  {name}
                </Button>
              </NavLink>
            ))}
          </Box>
          <Box
            className="nav"
            style={{ textDecoration: "none", color: "#0000FF" }}
            sx={{ flexGrow: 1, display: { xs: "none", md: "flex" } }}
          >
            {loggedIn && LogoutPage.map(({ name, url }) => (
              <NavLink to={url} style={{ textDecoration: "none" }}>
                <Button
                  onClick={Logout}
                  style={{ color: "#83314E", textDecoration: "none" }}
                  key={name}
                  sx={{ my: 2, color: "white", display: "block" }}
                >
                  {name}
                </Button>
              </NavLink>
            ))}
          </Box>
        </Toolbar>
      </Container>
    </AppBar>
  );
};
export default Navbar;