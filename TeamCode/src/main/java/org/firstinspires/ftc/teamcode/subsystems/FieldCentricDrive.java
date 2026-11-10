package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.core.control.Gamepads;
import org.firstinspires.ftc.teamcode.hardware.DrivetrainMotors;
import org.firstinspires.ftc.teamcode.hardware.Hardware;

public class FieldCentricDrive {

    private static final double NORMAL_SPEED = 1.0;
    private static final double SLOW_SPEED = 0.5;
    private static final double TURN_MULTIPLIER = 0.85;

    private final DrivetrainMotors motors;
    private final IMU imu;
    private double heading, fl, fr, bl, br;
    private boolean slowMode;

    public FieldCentricDrive(Hardware hardware) {
        motors = hardware.drivetrainMotors;
        imu = hardware.imu;
    }

    public void update(Gamepads gamepads) {
        if (gamepads.justPressedHeadingReset()) resetHeading();
        heading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        double forward = gamepads.getForward();
        double strafe = gamepads.getStrafe();
        double turn = gamepads.getTurn() * TURN_MULTIPLIER;
        double robotStrafe = strafe * Math.cos(-heading) - forward * Math.sin(-heading);
        double robotForward = strafe * Math.sin(-heading) + forward * Math.cos(-heading);
        slowMode = gamepads.isSlowMode();
        double speed = slowMode ? SLOW_SPEED : NORMAL_SPEED;
        double denominator = Math.max(
                Math.abs(robotForward) + Math.abs(robotStrafe) + Math.abs(turn), 1.0);
        fl = (robotForward + robotStrafe + turn) / denominator * speed;
        fr = (robotForward - robotStrafe - turn) / denominator * speed;
        bl = (robotForward - robotStrafe + turn) / denominator * speed;
        br = (robotForward + robotStrafe - turn) / denominator * speed;
        motors.setPower(fl, fr, bl, br);
    }

    public void resetHeading() {
        imu.resetYaw();
    }

    public void addTelemetry(Telemetry telemetry) {
//        telemetry.addData("Mode", slowMode ? "Field-centric slow" : "Field-centric");
//        telemetry.addData("Heading (degrees)", "%.1f", Math.toDegrees(heading));
//        telemetry.addData("Front L / R", "%.2f / %.2f", fl, fr);
//        telemetry.addData("Back L / R", "%.2f / %.2f", bl, br);
    }
}
