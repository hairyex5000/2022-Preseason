package frc.robot.commands.CAS;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PhotonCameraSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class TurretConstantAlign extends CommandBase {
	TurretSubsystem m_turret;
	PhotonCameraSubsystem m_PhotonCamera;

	public TurretConstantAlign() {
		m_turret = TurretSubsystem.getInstance();
		m_PhotonCamera = PhotonCameraSubsystem.getInstance();
		addRequirements(m_turret, m_PhotonCamera);
	}
	
	// NEED TO FIND TURRET BOUNDS BEFORE DOING THIS

	@Override
	public void initialize() {
		
	}

	@Override
	public void execute() {

	}

	
}
