import { Link, Stack, Typography } from "@mui/material";
import RegisterForm from "../component/Forms/RegisterForm/RegisterForm";
import { Link as RouterLink } from "react-router-dom";

const Register = () => {
  return (
    <>
        <Stack spacing={2} sx={{ mb: 5, position: "relative" }}>
          <Typography variant="h4">Get on with Your Gossip's.</Typography>

          <Stack direction="row" spacing={0.5}>
            <Typography variant="body2"> Already have an account? </Typography>

            <Link component={RouterLink} to={"/auth/login"} variant="subtitle2">
              Sign in
            </Link>
          </Stack>
        </Stack>
        {/* Form */}
        <RegisterForm />

        <Typography
          component="div"
          sx={{
            color: "text.secondary",
            mt: 3,
            typography: "caption",
            textAlign: "center",
          }}
        >
          {"By signing up, I agree to "}
          <Link underline="always" color="text.primary">
            Terms of Service
          </Link>
          {" and "}
          <Link underline="always" color="text.primary">
            Privacy Policy
          </Link>
          .
        </Typography>
    </>
  );
};

export default Register;
