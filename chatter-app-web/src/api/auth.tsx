import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080/api/v1"
});

const RegisterUser = async (formValues: any) => {
  await api
    .post(
      "/auth/register",
      {
        ...formValues,
      },
      {
        headers: {
          "Content-Type": "application/json"
        },
      }
    )
    .then(function (response) {
      console.log(response);
    })
    .catch(function (error) {
      console.log(error);
    })
    .finally(() => {
      console.log("Register Complete")
    });
};

export default RegisterUser;
