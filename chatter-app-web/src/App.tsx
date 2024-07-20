import Router from "./routes/Routes";
import ThemeSettings from "./setting/ThemeSetting";
import ThemeProvider from "./theme/ThemeProvider";

function App() {
  return (
    <>
      <ThemeProvider>
        <ThemeSettings>
          {" "}
          <Router />{" "}
        </ThemeSettings>
      </ThemeProvider>
    </>
  );
}

export default App;
