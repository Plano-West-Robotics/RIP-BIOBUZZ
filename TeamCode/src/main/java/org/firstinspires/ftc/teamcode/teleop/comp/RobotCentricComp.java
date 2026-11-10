package org.firstinspires.ftc.teamcode.teleop.comp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.core.control.Gamepads;
import org.firstinspires.ftc.teamcode.hardware.DrivetrainMotors;
import org.firstinspires.ftc.teamcode.subsystems.RobotCentricDrive;

@TeleOp(name = "BIOBUZZ Robot Centric", group = "Drive")
public class RobotCentricComp extends OpMode {
    private DrivetrainMotors motors;
    private Gamepads gamepads;
    private RobotCentricDrive drive;

    @Override
    public void init() {
        motors = new DrivetrainMotors(hardwareMap);
        gamepads = new Gamepads(gamepad1);
        drive = new RobotCentricDrive(motors);
        telemetry.addLine("Left stick: move | Right stick: turn | RB: slow");
        telemetry.update();
    }

    @Override
    public void start() {
        gamepads.syncButtons(gamepad1);
    }

    @Override
    public void loop() {
        gamepads.update(gamepad1);
        drive.update(gamepads);
    }

    @Override
    public void stop() {
        if (motors != null) motors.stop();
    }
}
