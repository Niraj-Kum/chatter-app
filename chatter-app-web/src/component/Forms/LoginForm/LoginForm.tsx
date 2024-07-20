import { useState } from "react";

import { Link as RouterLink } from "react-router-dom";

import { Link, Stack, IconButton, InputAdornment, TextField, Button } from "@mui/material";

import { IoEye, IoEyeOff } from "react-icons/io5";

const LoginForm = () => {
  const [showPassword, setShowPassword] = useState(false);

  const onSubmit = async () => {
    console.log("login button clicked");
  };

  return (
    <form onSubmit={onSubmit}>
      <Stack spacing={3}>

        <TextField name="email" label="Email address" />

        <TextField
          name="password"
          label="Password"
          type={showPassword ? "text" : "password"}
          InputProps={{
            endAdornment: (
              <InputAdornment position="end">
                <IconButton
                  onClick={() => setShowPassword(!showPassword)}
                  edge="end"
                >
                  {showPassword ? <IoEye /> : <IoEyeOff />}
                </IconButton>
              </InputAdornment>
            ),
          }}
        />
      </Stack>

      <Stack alignItems="flex-end" sx={{ my: 2 }}>
        <Link
          component={RouterLink}
          to="/auth/reset-password"
          variant="body2"
          color="inherit"
          underline="always"
        >
          Forgot password?
        </Link>
      </Stack>

      <Button
        fullWidth
        color="inherit"
        size="large"
        type="submit"
        variant="contained"
        sx={{
          bgcolor: "text.primary",
          color: "common.white",
          "&:hover": {
            bgcolor: "text.primary",
            color: "common.white",
          },
        }}
      >
        Login
      </Button>
    </form>
  );
};

export default LoginForm;

// (theme) =>
//   //theme.palette.mode === "light" ? "common.white" : "grey.800",
// "&:hover": {
//   bgcolor: "text.primary",
//   color: (theme) =>
//     theme.palette.mode === "light" ? "common.white" : "grey.800",
// },
