package frc.robot.commands.CAS;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PhotonCameraSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class TurretConstantAlign extends CommandBase {
	TurretSubsystem m_turret;
	PhotonCameraSubsystem m_PhotonCamera;
	double targetYaw;
	double rotSpeed;
	PIDController m_turretController;

	public TurretConstantAlign() {
		m_turret = TurretSubsystem.getInstance();
		m_PhotonCamera = PhotonCameraSubsystem.getInstance();
		m_turretController = new PIDController(0.1, 0, 0);
		addRequirements(m_turret, m_PhotonCamera);
	}
	
	// NEED TO FIND TURRET BOUNDS 

	@Override
	public void initialize() {
		
	}

	@Override
	public void execute() {
		if(m_PhotonCamera.hasTarget()) {
			targetYaw = m_PhotonCamera.getYaw();
			rotSpeed = m_turretController.calculate(targetYaw, 0);
			m_turret.setTurretVelocity(rotSpeed);
		}
	}

}
