package ocs.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;

/**
 * Created by CJS on 10/10/17.
 */

@TeleOp
public class FirstTest extends OpMode {

    DcMotor arm;
    DcMotor leftDrive, rightDrive;

    public void init() {
        arm = hardwareMap.dcMotor.get("motor_arm");
        leftDrive = hardwareMap.dcMotor.get("drive_left");
        rightDrive = hardwareMap.dcMotor.get("drive_right");

        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void loop() {
        double leftInputY = gamepad1.left_stick_y > 0 ? gamepad1.left_stick_y * gamepad1.left_stick_y : -gamepad1.left_stick_y * gamepad1.left_stick_y;
        double leftInputX = gamepad1.left_stick_x > 0 ? gamepad1.left_stick_x * gamepad1.left_stick_x : -gamepad1.left_stick_x * gamepad1.left_stick_x;

        leftDrive.setPower(0.5 * (leftInputX + leftInputY));
        rightDrive.setPower(0.5 * (leftInputY - leftInputY));

        arm.setPower(gamepad1.right_stick_y > 0 ? Math.pow(gamepad1.right_stick_y, 2) : -Math.pow(gamepad1.right_stick_y, 2));
    }
}
