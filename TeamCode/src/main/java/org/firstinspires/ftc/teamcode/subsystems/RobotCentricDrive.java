package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.core.control.Gamepads;
import org.firstinspires.ftc.teamcode.hardware.DrivetrainMotors;

public class RobotCentricDrive {

    private static final double NORMAL_SPEED = 1.0;
    private static final double SLOW_SPEED = 0.5;
    private static final double TURN_MULTIPLIER = 0.85;

    private final DrivetrainMotors motors;

    public RobotCentricDrive(DrivetrainMotors motors) {
        this.motors = motors;
    }

    public void update(Gamepads gamepads) {
        double forward = gamepads.getForward();
        double strafe = gamepads.getStrafe();
        double turn = gamepads.getTurn() * TURN_MULTIPLIER;
        double speed = gamepads.isSlowMode() ? SLOW_SPEED : NORMAL_SPEED;
        double denominator = Math.max(
                Math.abs(forward) + Math.abs(strafe) + Math.abs(turn), 1.0);
        double fl = (forward + strafe + turn) / denominator * speed;
        double fr = (forward - strafe - turn) / denominator * speed;
        double bl = (forward - strafe + turn) / denominator * speed;
        double br = (forward + strafe - turn) / denominator * speed;
        motors.setPower(fl, fr, bl, br);
    }
}
