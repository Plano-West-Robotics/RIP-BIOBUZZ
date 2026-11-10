package org.firstinspires.ftc.teamcode.teleop.comp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.subsystems.FieldCentricDrive;
import org.firstinspires.ftc.teamcode.teleop.BaseTeleOp;

@TeleOp(name = "BIOBUZZ Mecanum Starter", group = "Drive")
public class MainComp extends BaseTeleOp {
    private FieldCentricDrive drive;

    @Override
    public void setup() {
        drive = new FieldCentricDrive(hardware);
    }

    @Override
    public void start() {
        super.start();
        drive.resetHeading();
    }

    @Override
    public void run() {
        drive.update(gamepads);
        drive.addTelemetry(telemetry);
    }
}
