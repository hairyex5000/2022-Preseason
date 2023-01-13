package frc.robot.commands.CAS;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.util.GameAprilTags;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.PhotonCameraSubsystem;
import frc.robot.Constants;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Transform3d;

public class SmartResetOdometry extends CommandBase {
    int kTargetPitch = 0;
    DrivetrainSubsystem m_driveTrain;
    PhotonCameraSubsystem m_Camera;
    boolean isReset;

    public SmartResetOdometry(){
        m_driveTrain = DrivetrainSubsystem.getInstance();
        addRequirements(m_driveTrain);
        m_Camera = PhotonCameraSubsystem.getInstance();
        addRequirements(m_Camera);
        isReset = false;
    }

    @Override
    public void initialize(){
        
    }

    @Override
    public void execute(){
        if(m_Camera.hasTarget()){
            int targetID = m_Camera.getID();
            PhotonTrackedTarget target = m_Camera.getBestTarget();
            Pose3d targetPose = GameAprilTags.getInstance().getPose(targetID);
            //Pose2d robotPose = PhotonUtils.estimateFieldToRobot(Constants.limelightHeight, targetPose.getZ(), Constants.limelightPitch, Rotation2d.fromDegrees(-target.getYaw()), m_driveTrain.getGyroscopeRotation(), targetPose, new Transform2d());
            Pose3d robotPose = PhotonUtils.estimateFieldToRobotAprilTag(target.getBestCameraToTarget(), targetPose, new Transform3d());
            // Pose2d robotPose = PhotonUtils.estimateFieldToRobot(kCameraHeight, kTargetHeight, kCameraPitch, kTargetPitch, Rotation2d.fromDegrees(-target.getYaw()), gyro.getRotation2d(), targetPose, cameraToRobot);
            m_driveTrain.resetOdometryFromPosition(robotPose.getX(), robotPose.getY(), robotPose.getRotation().getZ());
            isReset = true;
            
        }
    }

    @Override
    public boolean isFinished(){
        return isReset;
    }
}
