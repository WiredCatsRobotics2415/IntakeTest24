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
      System.out.println("Off");
       motor.set(0);
        });
    }

    public Command on() {
      return runOnce(
        () -> {
        System.out.println("On");
        motor.set(1);
        });
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
      if (hasNote() == false) {
          if (state == true) {
            off();
            state = false;
        } else {
            on();
            state = true;
        }
      }
      });
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
      motor.set(-0.5);
      });
      }
}