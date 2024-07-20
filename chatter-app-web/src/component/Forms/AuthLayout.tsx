import { Container, Stack } from "@mui/material";
import { Navigate, Outlet } from "react-router-dom";

const AuthLayout = () => {
    const isLoggedIn = false; // useSelector((state: any) => state.auth);
  
    if (isLoggedIn) {
      return <Navigate to={"/app"} />;
    }
  
    return (
      <>
        <Container sx={{ mt: 5 }} maxWidth="sm">
          <Stack spacing={5}>
            <Stack
              sx={{ width: "100%" }}
              direction="column"
              alignItems={"center"}
            >
              {/* <img style={{ height: 120, width: 120 }} src={'../../assets/images/logo.ico'} alt="Logo" /> */}
            </Stack>
            <Outlet />
          </Stack>
        </Container>
      </>
    );
  };
  
  export default AuthLayout;
  