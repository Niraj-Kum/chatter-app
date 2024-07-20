import {
  createTheme,
  StyledEngineProvider,
  CssBaseline,
  ThemeProvider as MUIThemeProvider,
} from "@mui/material";
import { useMemo } from "react";
import useSettings from "../hooks/useSettings";
import palette from "./Palette";
import typography from "./Typography";
import shadows, { customShadows } from "./shadows";
import breakpoints from "./breakpoints";

export default function ThemeProvider({ children }: any) {
  const { themeMode, themeDirection } = useSettings();

  const isLight = themeMode === "light";

  const themeOptions = useMemo(
    () => ({
      palette: isLight ? palette.light : palette.dark,
      typography,
      breakpoints,
      shape: { borderRadius: 8 },
      direction: themeDirection,
      shadows: isLight ? shadows.light : shadows.dark,
      customShadows: isLight ? customShadows.light : customShadows.dark,
    }),
    [isLight, themeDirection]
  );

  const theme = createTheme(themeOptions as any);

  return (
    <StyledEngineProvider injectFirst>
      <MUIThemeProvider theme={theme}>
        <CssBaseline />
        {children}
      </MUIThemeProvider>
    </StyledEngineProvider>
  );
}
