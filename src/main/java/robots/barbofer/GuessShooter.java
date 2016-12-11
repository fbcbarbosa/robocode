package robots.barbofer;

import robocode.*;
import java.awt.Color;
import java.lang.Math;

/**
 * GuessShooter - a robot by Fernando Barbosa
 */
public class GuessShooter extends AdvancedRobot {

	public void run() {
		setBodyColor(Color.black);

		while (true) {
			turnGunRight(360);
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {

		double firepower = 1;

		if (getGunHeat() == 0) {
			stop();

			double angle = 180 - e.getHeading() + getGunHeading();
			double bulletVelocity = 20 - 3 * firepower;
			double ratio = e.getVelocity() / bulletVelocity;

			double adjustedAngle = Math.toDegrees(ratio * Math.sin(Math.toRadians(angle)));
			double normalizedAdjustedAngle = norm(adjustedAngle);

			double correction = Math.toDegrees(Math.asin(Math.toRadians(normalizedAdjustedAngle)));

			double absoluteBearing = getHeading() + e.getBearing();
			double bearingFromGun = norm(absoluteBearing - getGunHeading());

			double solution = norm(bearingFromGun + correction);

			out.println("");
			out.println("------ FIRE SOLUTION ------");
			out.println("Enemy Heading: " + e.getHeading());
			out.println("GunHeading: " + getGunHeading());
			out.println("Angle: " + angle);
			out.println("Ratio: " + ratio);
			out.println("AdjustedAngle: " + adjustedAngle);
			out.println("NormalizedAdjustedAngle: " + normalizedAdjustedAngle);
			out.println("Correction: " + correction);
			out.println("AbsoluteBearing: " + absoluteBearing);
			out.println("BearingFromGun: " + bearingFromGun);
			out.println("Solution: " + solution);

			turnGunRight(solution);
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
