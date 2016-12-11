package robots.barbofer;

import robocode.*;
import java.awt.Color;
import java.math.*;

/**
 * Shooter - a robot by Fernando Barbosa
 */
public class Shooter extends AdvancedRobot {

	public void run() {
		setBodyColor(Color.black);

		while (true) {
			turnGunRight(360);
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		if (getGunHeat() == 0) {
			double gunBearing = norm(e.getBearing() + getHeading() - getGunHeading());
			turnGunRight(gunBearing);
			fire(1);
		}
	}

	private double norm(double degrees) {
		if (degrees >= 180) {
			return norm(degrees - 360);
		} else if (degrees < -180) {
			return norm(degrees + 360);
		}

		return degrees;
	}
}
