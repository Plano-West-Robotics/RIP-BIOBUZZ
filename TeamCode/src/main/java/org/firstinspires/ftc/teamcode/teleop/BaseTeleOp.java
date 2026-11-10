package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.core.control.Gamepads;
import org.firstinspires.ftc.teamcode.hardware.Hardware;

public abstract class BaseTeleOp extends OpMode {
    protected Hardware hardware;
    protected Gamepads gamepads;

    @Override
    public final void init() {
        hardware = new Hardware(hardwareMap);
        gamepads = new Gamepads(gamepad1);
        setup();
        telemetry.update();
    }

    public abstract void setup();

    @Override
    public void start() {
        gamepads.syncButtons(gamepad1);
    }

    @Override
    public final void loop() {
        gamepads.update(gamepad1);
        run();
        telemetry.update();
    }

    public abstract void run();

    @Override
    public void stop() {
        if (hardware != null) hardware.stop();
    }
}
