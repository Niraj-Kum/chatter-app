import { Navigate, useRoutes } from "react-router-dom";
import AuthLayout from "../component/Forms/AuthLayout";
import { lazy, Suspense } from "react";
import LoadingScreen from "../component/LoadingScreen/LoadingScreen";

export default function Router() {
  return useRoutes([
    {
      path: "/auth",
      element: <AuthLayout />,
      children: [
        { path: "login", element: <LoginPage /> },
        { path: "register", element: <RegisterPage /> },
        { path: "reset-password", element: <ResetPasswordPage /> },
        { path: "new-password", element: <NewPasswordPage /> },
        {path: "verify", element: <VerifyPage /> },
      ],
    },
    // {
    //   path: "/",
    //   element: <DashboardLayout />,
    //   children: [
    //     { element: <Navigate to={DEFAULT_PATH} replace />, index: true },
    //     { path: "app", element: <GeneralApp /> },
    //     { path: "group", element: <Group /> },
    //     { path: "settings", element: <Settings /> },
    //     { path: "conversation", element: <Conversation /> },
    //     { path: "chats", element: <Chats /> },
    //     { path: "contact", element: <Contact /> },
    //     { path: "profile", element: <Profile /> },

    //     {path: "call", element: <CallPage />},
        
    //     { path: "404", element: <Page404 /> },
    //     { path: "*", element: <Navigate to="/404" replace /> },
    //   ],
    // },

    { path: "*", element: <Navigate to="/404" replace /> },
  ]);
}

const Loadable = (Component: any) => (props: any) => {
    return (
      <Suspense fallback={<LoadingScreen />}>
        <Component {...props} />
      </Suspense>
    );
  };

// const GeneralApp = Loadable(
//     lazy(() => import("../pages/dashboard/GeneralApp"))
//   );
//   const Conversation = Loadable(
//     lazy(() => import("../pages/dashboard/Conversation"))
//   );
//   const Chats = Loadable(lazy(() => import("../pages/dashboard/Chats")));
//   const Group = Loadable(lazy(() => import("../pages/dashboard/Group")));
//   const CallPage = Loadable(lazy(() => import("../pages/dashboard/Call")));
//   const Contact = Loadable(lazy(() => import("../sections/Dashboard/Contact")));
//   const Page404 = Loadable(lazy(() => import("../pages/Page404")));
  
  const LoginPage = Loadable(lazy(() => import("../pages/Login")));
  const VerifyPage = Loadable(lazy(() => import("../pages/Verify")));
  const RegisterPage = Loadable(lazy(() => import("../pages/Register")));
  const ResetPasswordPage = Loadable(
    lazy(() => import("../pages/ResetPassword"))
  );
  const NewPasswordPage = Loadable(
    lazy(() => import("../pages/NewPassword"))
  );
  
  // Settings
//   const Settings = Loadable(lazy(() => import("../pages/dashboard/Settings")));
//   const Profile = Loadable(
//     lazy(() => import("../pages/dashboard/Settings/Profile"))
//   );
  