import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  user: {},
  sideBar: {
    open: false,
    type: "CONTACT",
  },
  isLoggedIn: true,
  tab: 0,
  snackbar: {
    open: null,
    severity: null,
    message: null,
  },
  users: [],
  all_users: [],
  friends: [],
  friendRequests: [],
  chat_type: null,
  room_id: null,
  call_logs: [],
};

const slice = createSlice({
  name: "app",
  initialState,
  reducers: {
    fetchCallLogs(state, action) {
      state.call_logs = action.payload.call_logs;
    },
    fetchUser(state, action) {
      state.user = action.payload.user;
    },
    updateUser(state, action) {
      state.user = action.payload.user;
    },
    toggleSideBar(state) {
      state.sideBar.open = !state.sideBar.open;
    },
    updateSideBarType(state, action) {
      state.sideBar.type = action.payload.type;
    },
    updateTab(state, action) {
      state.tab = action.payload.tab;
    },

    openSnackBar(state: any, action: any) {
      console.log(action.payload);
      state.snackbar.open = true;
      state.snackbar.severity = action.payload.severity;
      state.snackbar.message = action.payload.message;
    },
    closeSnackBar(state: any) {
      console.log("This is getting executed");
      state.snackbar.open = false;
      state.snackbar.message = null;
    },
    updateUsers(state, action) {
      state.users = action.payload.users;
    },
    updateAllUsers(state, action) {
      state.all_users = action.payload.users;
    },
    updateFriends(state, action) {
      state.friends = action.payload.friends;
    },
    updateFriendRequests(state, action) {
      state.friendRequests = action.payload.requests;
    },
    selectConversation(state: any, action: any) {
      state.chat_type = "individual";
      state.room_id = action.payload.room_id;
    },
  },
});

export default slice.reducer;

export const closeSnackBar = () => async (dispatch: any, getState: any) => {
  dispatch(slice.actions.closeSnackBar());
};

export const showSnackbar =
  ({ severity, message }: any) =>
  async (dispatch: any, getState: any) => {
    dispatch(
      slice.actions.openSnackBar({
        message,
        severity,
      } as any)
    );

    setTimeout(() => {
      dispatch(slice.actions.closeSnackBar());
    }, 4000);
  };

export function ToggleSidebar() {
  return async (dispatch: any, getState: any) => {
    dispatch(slice.actions.toggleSideBar());
  };
}
export function UpdateSidebarType(type: any) {
  return async (dispatch: any, getState: any) => {
    dispatch(slice.actions.updateSideBarType({ type }));
  };
}
export function UpdateTab(tab: any) {
  return async (dispatch: any, getState: any) => {
    dispatch(slice.actions.updateTab(tab));
  };
}

export const SelectConversation = ({ room_id }: any) => {
  return async (dispatch: any, getState: any) => {
    dispatch(slice.actions.selectConversation({ room_id } as any));
  };
};
