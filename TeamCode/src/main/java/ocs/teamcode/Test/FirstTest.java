package ocs.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Hardware;

import ocs.teamcode.Various.CLib;

/**
 * Created by CJS on 10/10/17.
 */

//@TeleOp
    @Disabled
public class FirstTest extends OpMode {

    CLib CLib = new CLib();

    DcMotor arm;
    DcMotor leftDrive, rightDrive;

    Servo rightServo, leftServo;

    public void init() {
        arm = hardwareMap.dcMotor.get("motor_arm");

        rightServo = hardwareMap.servo.get("servo_right");
        leftServo = hardwareMap.servo.get("servo_left");

        leftServo.setDirection(Servo.Direction.REVERSE);

        leftDrive = hardwareMap.dcMotor.get("drive_left");
        rightDrive = hardwareMap.dcMotor.get("drive_right");

        rightDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }

    public void loop() {
        drive();

        rightServo.setPosition((gamepad1.left_trigger * 0.25) + 0.3);
        leftServo.setPosition((gamepad1.left_trigger * 0.25) + 0.3);

        arm.setPower(gamepad1.right_stick_y > 0 ? Math.pow(gamepad1.right_stick_y, 2) : -Math.pow(gamepad1.right_stick_y, 2));

        if (gamepad1.a) {
            rightServo.setPosition(0.5);
            leftServo.setPosition(0.5);
        }
    }

    public void drive() {
        double leftInputY = gamepad1.left_stick_y > 0 ? gamepad1.left_stick_y * gamepad1.left_stick_y : -gamepad1.left_stick_y * gamepad1.left_stick_y;
        double leftInputX = gamepad1.left_stick_x > 0 ? gamepad1.left_stick_x * gamepad1.left_stick_x : -gamepad1.left_stick_x * gamepad1.left_stick_x;

        leftDrive.setPower(leftInputY < 0 ? (CLib.constrain(leftInputY + leftInputX, -1, 1)) : (CLib.constrain(leftInputY - leftInputX, -1, 1)));
        rightDrive.setPower(leftInputY < 0 ? (CLib.constrain(leftInputY - leftInputX, -1, 1)) : (CLib.constrain(leftInputY + leftInputX, -1, 1)));
    }
}
