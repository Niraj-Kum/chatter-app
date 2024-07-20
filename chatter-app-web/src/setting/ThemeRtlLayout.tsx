import { useEffect } from "react";
import createCache from "@emotion/cache";
import { CacheProvider } from "@emotion/react";
import { useTheme } from "@mui/material/styles";

export const ThemeRtlLayout = ({ children }: any) => {
  const theme = useTheme();

  useEffect(() => {
    document.dir = theme.direction;
  }, [theme.direction]);

  const cacheRtl = createCache({
    key: theme.direction === "rtl" ? "rtl" : "css"
  });

  return <CacheProvider value={cacheRtl}>{children}</CacheProvider>;
};
