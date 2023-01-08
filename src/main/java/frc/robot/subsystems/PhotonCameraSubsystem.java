package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonPipelineResult;

public class PhotonCameraSubsystem extends SubsystemBase {
	private static PhotonCameraSubsystem instance = null;

	public static PhotonCameraSubsystem getInstance() {

		if (instance == null) {
			instance = new PhotonCameraSubsystem();
		}
		return instance;
	}

	final double CAMERA_HEIGHT_METERS = Units.inchesToMeters(35);
	final double TARGET_HEIGHT_METER = Units.inchesToMeters(104);
	final double CAMERA_PITCH_RADIANS = Units.degreesToRadians(27);

	PhotonCamera camera = new PhotonCamera("gloworm");
	PhotonPipelineResult result = camera.getLatestResult();
	double lastYaw;
	double lastDistance;

	public PhotonCameraSubsystem() {
		// might have to put some nt initilizations in here...
	}

	public boolean hasTarget() {
		return result.hasTargets();
	}

	public double getYaw() {
		if (result.hasTargets()) {
			lastYaw = result.getBestTarget().getYaw();
		}
		return lastYaw;
	}

	public double getDistance() {
		if (result.hasTargets()) {
			lastDistance = PhotonUtils.calculateDistanceToTargetMeters(CAMERA_HEIGHT_METERS, TARGET_HEIGHT_METER,
					CAMERA_PITCH_RADIANS, Units.degreesToRadians(result.getBestTarget().getPitch()));
		}
		return lastDistance;
	}

	@Override
	public void periodic() {
		result = camera.getLatestResult();
		PIDController m_turretController = new PIDController(5, 0, 0);
		if (result.hasTargets()) {
			var targetYaw = result.getBestTarget().getYaw();
			if (Math.abs(targetYaw) > 2) {
				var rotSpeed = m_turretController.calculate(targetYaw, 0);
				TurretSubsystem.getInstance().setTurretVelocity(rotSpeed);
			}
			// if (result.getBestTarget().getYaw() > 2) {
			// 	TurretSubsystem.getInstance().decreasePosition(100);
			// } else if (result.getBestTarget().getYaw() < -2) {
			// 	TurretSubsystem.getInstance().increasePosition(100);
			// }
		} else {
			TurretSubsystem.getInstance().setTurretVelocity(0);
		}
		SmartDashboard.putBoolean("PV has target", hasTarget());
		SmartDashboard.putNumber("PV last yaw", getYaw());
		SmartDashboard.putNumber("PV last distance", getDistance());
	}
}
