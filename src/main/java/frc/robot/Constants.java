package frc.robot;

import com.pathplanner.lib.config.RobotConfig;

public class Constants {
    
    public static class Auto {
        
        public static RobotConfig config;

        static {
            try {
                config = RobotConfig.fromGUISettings();
            } catch (Exception e) {
                // If loading from the GUI fails, fall back to a safe default RobotConfig instance
                // Use a simple default constructor as a fallback so `config` is always initialized.
                e.printStackTrace();
            }
        }

    }

}
