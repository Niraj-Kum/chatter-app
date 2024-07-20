import {
  Button,
  IconButton,
  InputAdornment,
  Stack,
  TextField,
} from "@mui/material";
import React, { useState } from "react";
import { IoEye, IoEyeOff } from "react-icons/io5";
import { yupResolver } from "@hookform/resolvers/yup";
import { FormProvider, useForm } from "react-hook-form";
import * as Yup from "yup";
import RegisterUser from "../../../api/auth";
import {makeStyles} from "@mui/styles";

const useStyles: any = makeStyles({
  input: {
    '& input[type=number]': {
        '-moz-appearance': 'textfield'
    },
    '& input[type=number]::-webkit-outer-spin-button': {
        '-webkit-appearance': 'none',
        margin: 0
    },
    '& input[type=number]::-webkit-inner-spin-button': {
        '-webkit-appearance': 'none',
        margin: 0
    }
  },
});

const RegisterForm = () => {
  const classes = useStyles();
  // const dispatch = useDispatch();
  // const {isLoading} = useSelector((state : any) => state.auth);
  const [showPassword, setShowPassword] = useState(false);

  const LoginSchema = Yup.object().shape({
    firstName: Yup.string().required("First name required"),
    lastName: Yup.string().required("Last name required"),
    password: Yup.string().required("Password is required"),
    username: Yup.string().required("Username is required"),
    pin: Yup.string().required("Pin is required"),
    birthDate: Yup.string().required("Birthdate is required"),
    mobile: Yup.string().required("Mobile is required"),
  });

  const defaultValues = {
    username: "ishikav",
    mobile: "9782045100",
    pin: "1628",
    password: "1234567",
    firstName: "Ishika",
    lastName: "Verma",
    birthDate: "1999-04-07",
  };

  const methods = useForm({
    resolver: yupResolver(LoginSchema),
    defaultValues,
  });

  const { reset, handleSubmit } = methods;

  const onSubmit = async (data: any) => {
    try {
      // submit data to backend
      await RegisterUser(data);
      console.log(data);
    } catch (error) {
      console.error(error);
      reset();
      // setError("afterSubmit", {
      //   ...error,
      //   message: error.message,
      // });
    }
  };

  return (
    <FormProvider {...methods}>
      <form onSubmit={handleSubmit(onSubmit)}>
        <Stack spacing={3} mb={4} useFlexGap flexWrap="wrap">
          {/* {!!errors.afterSubmit && (
          <Alert severity="error">{errors.afterSubmit.message}</Alert>
        )} */}

          <Stack
            direction={{ xs: "column", sm: "row" }}
            spacing={2}
          >
            {/* <CustomTextField name="firstName" label="First name" /> */}
            <TextField fullWidth name="firstName" label="First name" />
            <TextField fullWidth name="lastName" label="Last name" />
          </Stack>

          <TextField name="mobile" label="Mobile" type="number" className={classes.input} />           
          <TextField name="username" label="Username" />
          <TextField name="email" label="Email" />

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
        <Stack
          direction={{ xs: "column", sm: "row" }}
          justifyContent="space-between"
          spacing={3}
          mb={3}
        >
          <TextField fullWidth name="pin" label="PIN" />
          <TextField fullWidth name="birthDate" label="Birth Date" />
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
          Create Account
        </Button>
      </form>
    </FormProvider>
  );
};

export default RegisterForm;
