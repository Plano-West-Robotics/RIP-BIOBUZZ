package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class DrivetrainMotors {
    private final DcMotor frontLeft, frontRight, backLeft, backRight, intake, shooter;
    private final DcMotor[] motors;

    private final Servo  servoTurretOne, servoTurretTwo;//, servoGate;



    public DrivetrainMotors(HardwareMap hardwareMap) {
        // Match these names to the robot hardware configuration.
        frontLeft = hardwareMap.get(DcMotor.class, "fl");
        frontRight = hardwareMap.get(DcMotor.class, "fr");
        backLeft = hardwareMap.get(DcMotor.class, "bl");
        backRight = hardwareMap.get(DcMotor.class, "br");
        intake  = hardwareMap.get(DcMotor.class, "intake");
        shooter = hardwareMap.get(DcMotor.class, "shooter");
        servoTurretOne = hardwareMap.get(Servo.class, "servoOne");
        servoTurretTwo = hardwareMap.get(Servo.class, "servoTwo");
        servoTurretOne.setDirection(Servo.Direction.FORWARD);
        servoTurretTwo.setDirection(Servo.Direction.REVERSE);
        //servoGate = hardwareMap.get(Servo.class, "servoGate");
        motors = new DcMotor[]{frontLeft, frontRight, backLeft, backRight};
        stop();
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);
        intake.setDirection(DcMotorSimple.Direction.FORWARD);
        for (DcMotor motor : motors) {
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }



    public void setPower(double fl, double fr, double bl, double br,
                         double intakeVal, double shooterVal, double angle) {
        frontLeft.setPower(fl);
        frontRight.setPower(fr);
        backLeft.setPower(bl);
        backRight.setPower(br);
        intake.setPower(intakeVal);
        shooter.setPower(shooterVal);
        servoTurretOne.setPosition(angle);
        servoTurretTwo.setPosition(angle);
    }


    public void stop() {
        for (DcMotor motor : motors) motor.setPower(0);
        intake.setPower(0);
    }
}
