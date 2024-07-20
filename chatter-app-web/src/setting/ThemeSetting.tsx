import SettingsDrawer from "./drawer";
import ThemeContrast from "./ThemeContrast";
import ThemeColorPresets from "./ThemeColorPresets";
import ThemeLocalization from "./ThemeLocalization";
import { ThemeRtlLayout } from "./ThemeRtlLayout";

export default function ThemeSettings({ children }: any) {
  return (
    <ThemeColorPresets>
      <ThemeContrast>
        <ThemeLocalization>
          <ThemeRtlLayout>
            {children}
            <SettingsDrawer />
          </ThemeRtlLayout>
        </ThemeLocalization>
      </ThemeContrast>
    </ThemeColorPresets>
  );
}
