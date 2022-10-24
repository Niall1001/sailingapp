import { useEffect, useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";
import { LoginUtils } from '../utils';
import { AuthContext } from "../contexts";

import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Menu from '@mui/material/Menu';
import MenuIcon from '@mui/icons-material/Menu';
import Container from '@mui/material/Container';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import Tooltip from '@mui/material/Tooltip';
import MenuItem from '@mui/material/MenuItem';
import AdbIcon from '@mui/icons-material/Adb';
import React from "react";
import { TokenService } from "../services";
import navigation from "../constants/navigation";
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

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
    name: "Boats",
    url: "/boats",
  },
  {
    name: "Crews",
    url: "/crews",
  }
];

const UnAuthpages = [
  {
    name: "Login",
    url: "/login",
  },
  {
    name: "Register",
    url: "/register",
  }
];

const admin_settings = [
  {
    name: "Create Event",
    url: "/create-event",
  },
  {
    name: "View all users",
    url: "/app-users",
  }
];

const boat_owner_settings = [
  {
    name: "View Boat Page",
    url: "/boat-page",
  }
];

const crew_settings = [
  {
    name: "View Profile",
    url: "/profile",
  }
];

const Navbar = () => {
  const { state, dispatch } = AuthContext.useLogin();
  const navigate = useNavigate();
  const [isAdmin, setIsAdmin] = useState(false);
  const [isCrew, setIsCrew] = useState(false);
  const [isBoatOwner, setBoatOwner] = useState(false);
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [anchorElNav, setAnchorElNav] = React.useState(null);
  const [anchorElUser, setAnchorElUser] = React.useState(null);

  const handleOpenNavMenu = (event) => {
    setAnchorElNav(event.currentTarget);
  };
  const handleOpenUserMenu = (event) => {
    setAnchorElUser(event.currentTarget);
  };

  const handleCloseNavMenu = () => {
    setAnchorElNav(null);
  };

  const handleCloseUserMenu = () => {
    setAnchorElUser(null);
  };

  const Logout = () => {  
    navigate(navigation.LOGIN);
    setIsLoggedIn(false);
    TokenService.removeAuth();
    toast.success("Successfully logged out")
  }

  React.useEffect(() => {
    const loggedIn = state.access;
    handleCloseUserMenu();
    setIsLoggedIn(loggedIn);
    setIsAdmin(loggedIn && LoginUtils.isAdminUser(state.access))
    setIsCrew(loggedIn && LoginUtils.isCrewUser(state.access))
    setBoatOwner(loggedIn && LoginUtils.isBoatOwnerUser(state.access))
    }, [state]);

  return (
    <AppBar position="static">
      <Container maxWidth="xl">
        <Toolbar disableGutters>
          <Typography
            variant="h6"
            noWrap
            component="a"
            sx={{
              mr: 2,
              display: { xs: 'none', md: 'flex' },
              fontFamily: 'monospace',
              fontWeight: 700,
              letterSpacing: '.3rem',
              color: 'inherit',
              textDecoration: 'none',
            }}
          >
            Sailing Application
          </Typography>

          <Box sx={{ flexGrow: 1, display: { xs: 'flex', md: 'none' } }}>
            <IconButton
              size="large"
              aria-label="account of current user"
              aria-controls="menu-appbar"
              aria-haspopup="true"
              color="inherit"
            >
              <MenuIcon />
            </IconButton>
            <Menu
              id="menu-appbar"
              anchorEl={anchorElNav}
              anchorOrigin={{
                vertical: 'bottom',
                horizontal: 'left',
              }}
              keepMounted
              transformOrigin={{
                vertical: 'top',
                horizontal: 'left',
              }}
              open={Boolean(anchorElNav)}
              onClose={handleCloseNavMenu}
              sx={{
                display: { xs: 'block', md: 'none' },
              }}
            >
            </Menu>
          </Box>
          <AdbIcon sx={{ display: { xs: 'flex', md: 'none' }, mr: 1 }} />
          <Typography
            variant="h5"
            noWrap
            component="a"
            href=""
            sx={{
              mr: 2,
              display: { xs: 'flex', md: 'none' },
              flexGrow: 1,
              fontFamily: 'monospace',
              fontWeight: 700,
              letterSpacing: '.3rem',
              color: 'inherit',
              textDecoration: 'none',
            }}
          >
            Sailing Application
          </Typography>
          <Box sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' } }}>
            {isLoggedIn ? Authpages.map(({ name, url }) => (
              <NavLink
              to={url}
              style={{ textDecoration: "none", color: "#83314E" }}
            >
               <Button
               key={name}
               onClick={handleCloseNavMenu}
               sx={{ my: 2, color: 'white', display: 'block' }}
             >
               {name}
             </Button>
             </NavLink>
            )) : UnAuthpages.map(({ name, url }) => (
              <NavLink
              to={url}
              style={{ textDecoration: "none", color: "#83314E" }}
              >
                <Button
                key={name}
                onClick={handleCloseNavMenu}
                sx={{ my: 2, color: 'white', display: 'block' }}
                >
                  {name}
                </Button>
             </NavLink>
            ))} 
          </Box>
          {isLoggedIn ? 
          <Box sx={{ flexGrow: 0 }}>
            <Tooltip title="Open settings">
              <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
                <Avatar alt="Remy Sharp"/>
              </IconButton>
            </Tooltip>
            <Menu
              sx={{ mt: '45px' }}
              id="menu-appbar"
              anchorEl={anchorElUser}
              anchorOrigin={{
                vertical: 'top',
                horizontal: 'right',
              }}
              keepMounted
              transformOrigin={{
                vertical: 'top',
                horizontal: 'right',
              }}
              open={Boolean(anchorElUser)}
              onClose={handleCloseUserMenu}
            >
              {isCrew ? crew_settings.map(({name, url}) => (
                <NavLink
                to={url}
                style={{ textDecoration: "none", color: "#83314E" }}
                >
                  <MenuItem key={name} onClose={handleCloseUserMenu}>
                    <Typography textAlign="center">{name}</Typography>
                  </MenuItem>
                </NavLink>
              )): <div></div>}
              {isAdmin ? admin_settings.map(({name, url}) => (
                <NavLink
                to={url}
                style={{ textDecoration: "none", color: "#83314E" }}
                >
                  <MenuItem key={name} onClose={handleCloseUserMenu}>
                    <Typography textAlign="center">{name}</Typography>
                  </MenuItem>
                </NavLink>
              )): <div></div>}
              {isBoatOwner ? boat_owner_settings.map(({name, url}) => (
                <NavLink
                to={url}
                style={{ textDecoration: "none", color: "#83314E" }}
                >
                  <MenuItem key={name} onClose={handleCloseUserMenu}>
                    <Typography textAlign="center">{name}</Typography>
                  </MenuItem>
                </NavLink>
              )): <div></div>}
              <MenuItem key="Logout" onClick={Logout} onClose={handleCloseUserMenu}>
                    <Typography textAlign="center">Logout</Typography>
              </MenuItem>
            </Menu>
          </Box>: <div></div>}
        </Toolbar>
      </Container>
    </AppBar>
  );
};
export default Navbar;