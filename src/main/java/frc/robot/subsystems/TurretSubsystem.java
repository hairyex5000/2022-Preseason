package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.drivers.LazyTalonFX;
import frc.lib.drivers.TalonFXFactory;
import frc.robot.Constants;

public class TurretSubsystem extends SubsystemBase {
    private static TurretSubsystem instance = null;

    public static TurretSubsystem getInstance() {
        if (instance == null) {
            instance = new TurretSubsystem();
        }
        return instance;
    }

    private final CANSparkMax mTurretMotor;
    private final RelativeEncoder m_encoder;

    // private final LazyTalonFX mTurretMotor;  
    private double setpoint;
    private SparkMaxPIDController m_turretController;
    private double Kp = 15, Ki = 0, Kd = 0;
    private double targetAngle;
    
    private TurretSubsystem() {
        // mTurretMotor = TalonFXFactory.createDefaultFalcon("Turret Motor", 45);
        mTurretMotor = new CANSparkMax(Constants.Ports.TURRET_NEO_MOTOR_ID, Constants.Ports.TURRET_NEO_MOTOR_TYPE);
        //configureMotor(mTurretMotor, true);
        // mTurretMotor.setSelectedSensorPosition(0);
        // mTurretMotor.neutralOutput();
        m_turretController = mTurretMotor.getPIDController();
        m_encoder  = mTurretMotor.getEncoder();
    }   

    public SparkMaxPIDController getPIDController() {
        return m_turretController;
    }

    public void increaseKp() {
        Kp++;
        m_turretController.setP(Kp);
    }
    
    public void increaseKi() {
        Ki++;
        m_turretController.setI(Ki);
    }

    public void increaseKd() {
        Kd++;
        m_turretController.setD(Kd);
    }

    private void configureMotor(CANSparkMax motor, boolean b){
        motor.setInverted(b);
        motor.setControlFramePeriodMs(Constants.kTimeOutMs);
        // motor.configVoltageCompSaturation(12.0, Constants.kTimeOutMs);
        motor.setVoltage(12.0);
        motor.enableVoltageCompensation(12.0);

        // motor.setNeutralMode(NeutralMode.Brake);

        m_turretController.setP(1.0);
        m_turretController.setI(0);
        m_turretController.setD(0);
        m_turretController.setFF(1.0);

        
        // talon.config_kF(0, 1.0, Constants.kTimeOutMs);
        // talon.config_kP(0, 1.0, Constants.kTimeOutMs);
        // talon.config_kI(0, 0, Constants.kTimeOutMs);
        // talon.config_kD(0, 0, Constants.kTimeOutMs);
    }

    public void setTurretVelocity(double velocity) {
        // mTurretMotor.set(ControlMode.Velocity, velocity);
        mTurretMotor.set(0.1);
    }

    public void setPosition(double pos) {
        // mTurretMotor.configMotionAcceleration(10000); // accel limit for motion profile, test value
        m_turretController.setSmartMotionMinOutputVelocity(3, 0);
        m_turretController.setSmartMotionMaxVelocity(5, 0);
        // mTurretMotor.configMotionCruiseVelocity(10000); // velo limit for motion profile, test value
        // mTurretMotor.set(ControlMode.MotionMagic, pos);
        setpoint = pos;
    }
    
    public double getSetpoint() {
        return setpoint;
    }
    
    public double getPosition() {
        //return mTurretMotor.getSelectedSensorPosition();
        return m_encoder.getPosition();
    }
    
    public void resetPosition() {
        //mTurretMotor.setSelectedSensorPosition(0);
        m_encoder.setPosition(0);
    }

    public void setTargetAngle(double angle) {
        targetAngle = angle;
    }

    public double getTargetAngle() {
        return targetAngle;
    }

    // // counter clockwise +90
    // // clockwise -270
    // public double getAngle() {
    //     double angle = 0;
    //     if (Math.copySign(1, setpoint) < 0) {
    //         // depends on bounds for falcon
	// 	} else if (Math.copySign(1, setpoint) > 0) {
    //         // depends on bounds for falcon
	// 	}
    //     return angle;
    // }

    @Override
    public void periodic() {
        // SmartDashboard.putNumber("turret setpoint", getSetpoint());
        // SmartDashboard.putNumber("turret position", getPosition());
        // SmartDashboard.putNumber("turret angle", getAngle());
        // SmartDashboard.putNumber("turret target angle", getTargetAngle());
    }

}
