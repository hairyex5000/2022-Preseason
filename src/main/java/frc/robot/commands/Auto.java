package frc.robot.commands;

import com.pathplanner.lib.*;
import com.pathplanner.lib.commands.PPSwerveControllerCommand;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DrivetrainSubsystem;

public class Auto extends CommandBase {
    DrivetrainSubsystem m_DrivetrainSubsystem = DrivetrainSubsystem.getInstance();
    PIDController xController = new PIDController(5.0, 0, 0);
    PIDController yController = new PIDController(5.0, 0, 0);
    PIDController thetaController = new PIDController(2.0, 0, 0);
    PathPlannerTrajectory examplePath = PathPlanner.loadPath("New path.path", new PathConstraints(4, 3));
    PPSwerveControllerCommand swe = new PPSwerveControllerCommand(examplePath, m_DrivetrainSubsystem.poseSupplier, xController, yController, thetaController, m_DrivetrainSubsystem.chassisConsumer, null);
}
