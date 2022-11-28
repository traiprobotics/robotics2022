package frc.robot;

import edu.wpi.first.wpilibj.SerialPort;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

public class MixedAuto {

  public static WPI_TalonFX wheelMotor = new WPI_TalonFX(RobotMap.WHEEL_MOTOR_PORT);

  // private static AHRS ahrs = new AHRS(SerialPort.Port.kMXP);

  private static boolean spinningWheel = false;
  private static boolean doneColor = false;
  private static boolean doneRotations = false;
  private static String currentColor = "";
  private static String lastColor = "";
  private static String targetColor = "";
  private static int currentRotations = 0;
  private static int targetRotations = 0;

  public static void align() {
    LimeLight.limeLightSetPipeline(1);
  
    float x = LimeLight.limeLightGetX();

    float turn = 0f;
              
    if(LimeLight.limeLightTargetFound()) {
      DriveTrain.drive = false;

      turn = Math.abs(x) / 150 + 0.18f;

      if(x > 1f) {
        DriveTrain.driveArcade(turn, 0f);
      } else if (x < -1f ) {
        DriveTrain.driveArcade(-turn, 0f);
      }
    }
  }

  // public static double getGyro() {
  //   return ahrs.getAngle() % 360d;
  // }

  // public static void faceAngle(double pAngle, double pTolerance) {
  //   if (getGyro() < pAngle - pTolerance) {
  //     DriveTrain.driveArcade(0.5f, 0f);
  //   } else if (getGyro() > pAngle + pTolerance) {
  //     DriveTrain.driveArcade(-0.5f, 0f);
  //   }
  // }

 

  public static void stopDriver() {
    DriveTrain.drive = true;
    LimeLight.limeLightSetPipeline(0);
  }

  public static void stopShooter() {
    spinningWheel = false;
    wheelMotor.set(0f);
  }
  
}
