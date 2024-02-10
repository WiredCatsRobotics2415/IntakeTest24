package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    private CANSparkMax motor;
    private static Intake instance;
    private boolean state;
    private DigitalInput topIR;
    private DigitalInput bottomIR;

    public Intake() {
        motor = new CANSparkMax(RobotMap.INTAKE_MOTOR, CANSparkMax.MotorType.kBrushless);
        topIR = new DigitalInput(RobotMap.TOP_IR);
        bottomIR = new DigitalInput(RobotMap.BOTTOM_IR); 
        state = false;
    }

    public Command off() {
      return runOnce(
        () -> {
      motor_off();
        });
    }

    public void motor_in() {
        System.out.println("in");
        motor.set(-1);
    }

    public void motor_off() {
      System.out.println("off");
      motor.set(0);
  }

  public void motor_out() {
    System.out.println("out");
    motor.set(1);
}

    public static Intake getInstance() {
        if (instance == null) {
          instance = new Intake();
        }
        return instance;
      }

      public Command toggle() {
        return runOnce(
        () -> {
          System.out.println("Run");
          if (state == true) {
            motor_off();
            state = false;
        } else {
            motor_in();
            state = true;
        }
      });
      }

    public void RobotContainer() {
        if (speed>0) {
    
            if (firstIRSensor.get()){
                motor.set(0);
            } else {
                motor.set(speed)
            }
        } else {
            if (secondIRSensor.get()) {
                motor.set(0);
            } else {
                motor.set(speed);
            }
        }
        
    }

      public boolean hasNote() {
          return topIR.get();
        }

      public boolean inShooter() {
        return bottomIR.get();
      }

      public Command intakeIn() {
        return runOnce(
        () -> {
      motor_out();
      });
      }
}
