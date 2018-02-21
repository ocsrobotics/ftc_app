package ocs.teamcode.Competition;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import ocs.teamcode.Various.ChainController;
import ocs.teamcode.Various.CLib;

/**
 * Created by CJS on 11/4/17.
 */

@TeleOp
public class MainTeleOp extends OpMode {

    CLib tools = new CLib();

    DcMotor leftDrive, rightDrive, chainMotor;
    Servo leftGrab, rightGrab, colorBoom;



    //              !!!SET BEFORE RUNNING!!!
    int chainRange = 3400;
    //              !!!SET BEFORE RUNNING!!!


    //----------------------------------------------------------------------------------------------

    public void init() {
        //Setting up drive motors
        leftDrive = hardwareMap.dcMotor.get("drive_left");
        rightDrive = hardwareMap.dcMotor.get("drive_right");

        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);


        //Setting up chain driver
        chainMotor = hardwareMap.dcMotor.get("motor_chain");

        chainMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        chainMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        //Setting up grab servos
        leftGrab = hardwareMap.servo.get("grab_left");
        rightGrab = hardwareMap.servo.get("grab_right");

        rightGrab.setDirection(Servo.Direction.REVERSE);

        setGrabPos(0.0f);

        //Setting up color sensor servo
        colorBoom = hardwareMap.servo.get("color_boom");
        colorBoom.setDirection(Servo.Direction.REVERSE);
        colorBoom.setPosition(0.0);
    }

    public void init_loop() {
        if (chainMotor.getCurrentPosition() < 10) {
            chainMotor.setPower(0.2);
        } else {
            chainMotor.setPower(0.0);
        }
    }

    public void start() {
        chainMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        chainMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void loop() {
        //Direct input controls:
        boolean shouldBoostRight = (gamepad1.right_trigger > 0);
        boolean shouldBoostLeft = (gamepad1.left_trigger > 0);

        telemetry.addData("RightTrigger", gamepad1.right_trigger);
        telemetry.addData("LeftTrigger", gamepad1.left_trigger);

        setDrivePower(
                gamepad1.right_stick_y,
                gamepad1.left_stick_y,
                shouldBoostRight,
                shouldBoostLeft
        );

        setGrabPos(gamepad2.right_trigger);


        //Augmented controls
        if (gamepad2.right_stick_y < 0 && chainMotor.getCurrentPosition() < chainRange) {
            chainMotor.setPower(-gamepad2.right_stick_y);
        } else if (gamepad2.right_stick_y > 0 && chainMotor.getCurrentPosition() > 0) {
            chainMotor.setPower(-gamepad2.right_stick_y);
        } else {
            chainMotor.setPower(0.0f);
        }


        telemetry.update();
    }

    //----------------------------------------------------------------------------------------------

    void setDrivePower(float rPow, float lPow, boolean rBoost, boolean lBoost) {
        float rightInput = (rPow * rPow * (rPow > 0 ? 1.0f : -1.0f)) / 4f;
        float leftInput = (lPow * lPow * (lPow > 0 ? 1.0f : -1.0f)) / 4f;

        if (rBoost) {
            rightInput *= 4f;
        }

        if (lBoost) {
            leftInput *= 4f;
        }

        rightDrive.setPower(rightInput);
        leftDrive.setPower(leftInput);

        telemetry.addData("RightInput", rightInput);
        telemetry.addData("RightIsBoosted", rBoost);
        telemetry.addData("LeftInput", leftInput);
        telemetry.addData("LeftIsBoosted", lBoost);
    }

    void setGrabPos(float pos) {
        leftGrab.setPosition(pos);
        rightGrab.setPosition(leftGrab.getPosition());
    }
}
