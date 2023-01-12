package frc.robot.commands.CAS;

import org.photonvision.PhotonUtils;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.util.GameAprilTags;
import frc.robot.Constants;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.PhotonCameraSubsystem;
import edu.wpi.first.math.geometry.Pose3d;

public class AprilTagOdometryCustom extends CommandBase {
    DrivetrainSubsystem m_driveTrain;
    PhotonCameraSubsystem m_camera;
    GameAprilTags m_aprilTags;
    boolean isReset = false;
    public AprilTagOdometryCustom() {
        m_camera = PhotonCameraSubsystem.getInstance();
        m_driveTrain = DrivetrainSubsystem.getInstance();
        m_aprilTags = GameAprilTags.getInstance();
        addRequirements(m_camera, m_driveTrain);

    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if (m_camera.hasTarget()) {
            Pose3d targetPose = m_aprilTags.getPose(m_camera.getID());
            double cameraPitch = 0.0; //temporary;
            double dist = PhotonUtils.calculateDistanceToTargetMeters(Constants.limelightHeight, targetPose.getZ(), cameraPitch, 0);
            double yaw = Math.abs(m_camera.getYaw());
            double yChange = dist * Math.sin(yaw);
            double b = dist * Math.sin(90 - yaw);
            double zDif = Math.abs(Constants.limelightHeight - targetPose.getZ());
            double xChange = Math.sqrt(b * b - zDif * zDif);

            double newX;
            if (m_camera.getID() < 5 ) {
                newX = targetPose.getX() - xChange;
            }
            else {
                newX = targetPose.getX() + xChange;
            }
            if (m_camera.getYaw() < 0) {
                m_driveTrain.resetOdometryFromPosition(newX, targetPose.getY() + yChange);
            }
            else {
                m_driveTrain.resetOdometryFromPosition(newX, targetPose.getY() - yChange);
            }
            isReset = true;
        }
    }

    @Override 
    public boolean isFinished() {
        return isReset;
    }
}
