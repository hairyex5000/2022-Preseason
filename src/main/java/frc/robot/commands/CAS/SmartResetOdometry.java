package frc.robot.commands.CAS;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.util.GameAprilTags;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.PhotonCameraSubsystem;
import frc.robot.Constants;
import edu.wpi.first.math.geometry.Pose3d;

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
            // var target = m_Camera.getBestTarget();
            Pose3d targetPose = GameAprilTags.getInstance().getPose(targetID);
            // Pose2d robotPose = PhotonUtils.estimateFieldToRobot(kCameraHeight, kTargetHeight, kCameraPitch, kTargetPitch, Rotation2d.fromDegrees(-target.getYaw()), gyro.getRotation2d(), targetPose, cameraToRobot);
            // m_driveTrain.resetOdometryFromPosition(robotPose);
            isReset = true;
        }
    }

    @Override
    public boolean isFinished(){
        return isReset;
    }
}
