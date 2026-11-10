package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

public class Hardware {
    // Set these to match the REV Hub mounting orientation on the robot.
    private static final RevHubOrientationOnRobot.LogoFacingDirection LOGO_DIRECTION =
            RevHubOrientationOnRobot.LogoFacingDirection.UP;
    private static final RevHubOrientationOnRobot.UsbFacingDirection USB_DIRECTION =
            RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;

    public final DrivetrainMotors drivetrainMotors;
    public final IMU imu;

    public Hardware(HardwareMap hardwareMap) {
        drivetrainMotors = new DrivetrainMotors(hardwareMap);
        imu = hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot orientation =
                new RevHubOrientationOnRobot(LOGO_DIRECTION, USB_DIRECTION);
        if (!imu.initialize(new IMU.Parameters(orientation))) {
            throw new IllegalStateException("IMU initialization failed; check the hub configuration.");
        }
    }

    public void stop() {
        drivetrainMotors.stop();
    }
}
