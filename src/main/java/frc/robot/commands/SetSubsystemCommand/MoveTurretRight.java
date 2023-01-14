package frc.robot.commands.SetSubsystemCommand;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;

public class MoveTurretRight extends CommandBase {
    TurretSubsystem m_turret;
    
    public MoveTurretRight() {
        m_turret = TurretSubsystem.getInstance();
        addRequirements(m_turret);
    }
    
    @Override
    public void initialize() {
        m_turret.setTurretVelocity(0.2);
    }     

    @Override
    public boolean isFinished() {
        return true;
    }
}