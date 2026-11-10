package org.firstinspires.ftc.teamcode.core.control;

import com.qualcomm.robotcore.hardware.Gamepad;

public class Gamepads {

    private static final double DEADZONE = 0.05;

    private double forward, strafe, turn;
    private boolean slowMode, resetPressed, resetWasPressed;

    public Gamepads(Gamepad gamepad1) {
        syncButtons(gamepad1);
    }

    public void syncButtons(Gamepad gamepad1) {
        resetWasPressed = gamepad1.dpad_right;
        resetPressed = false;
    }

    public void update(Gamepad gamepad1) {
        forward = deadzone(-gamepad1.left_stick_y);
        strafe = deadzone(gamepad1.left_stick_x);
        turn = deadzone(gamepad1.right_stick_x);
        slowMode = gamepad1.right_bumper;
        boolean pressed = gamepad1.dpad_right;
        resetPressed = pressed && !resetWasPressed;
        resetWasPressed = pressed;
    }

    public double getForward() { return forward; }
    public double getStrafe() { return strafe; }
    public double getTurn() { return turn; }
    public boolean isSlowMode() { return slowMode; }
    public boolean justPressedHeadingReset() { return resetPressed; }

    private double deadzone(double value) {
        return Math.abs(value) < DEADZONE ? 0.0 : value;
    }
}
