package frc.robot.commands.CAS;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.PhotonCameraSubsystem;

public class SmartResetOdometry extends CommandBase {
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
            Pose2d robotPose = new Pose2d();
            // Pose2d robotPose = PhotonUtils.estimateFieldToRobot(kCameraHeight, kTargetHeight, kCameraPitch, kTargetPitch, Rotation2d.fromDegrees(-target.getYaw()), gyro.getRotation2d(), targetPose, cameraToRobot);
            m_driveTrain.resetOdometryFromPosition(robotPose);
            isReset = true;
        }
    }

    @Override
    public boolean isFinished(){
        return isReset;
    }
}
