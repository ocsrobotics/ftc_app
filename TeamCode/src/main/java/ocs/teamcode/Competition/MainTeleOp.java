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

    ChainController chainController;

    DcMotor leftDrive, rightDrive, chain;
    Servo leftGrab, rightGrab, colorBoom;



    //              !!!SET BEFORE RUNNING!!!
    float lowChainLimit = 0;
    float highChainLimit = 3000;
    //              !!!SET BEFORE RUNNING!!!


    //----------------------------------------------------------------------------------------------

    public void init() {
        //Setting up drive motors
        leftDrive = hardwareMap.dcMotor.get("drive_left");
        rightDrive = hardwareMap.dcMotor.get("drive_right");

      //  leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      //  rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);


        //Setting up chain driver
        chain = hardwareMap.dcMotor.get("motor_chain");
        chain.setDirection(DcMotorSimple.Direction.REVERSE);
        //chain.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        chain.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        chainController = new ChainController(lowChainLimit, highChainLimit, chain.getCurrentPosition(), chain);


        //Setting up grab servos
        leftGrab = hardwareMap.servo.get("grab_left");
        rightGrab = hardwareMap.servo.get("grab_right");

        rightGrab.setDirection(Servo.Direction.REVERSE);

        //Setting up color sensor servo
        colorBoom = hardwareMap.servo.get("color_boom");
        colorBoom.setDirection(Servo.Direction.REVERSE);
        colorBoom.setPosition(0.0);
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

        chain.setPower(gamepad2.right_stick_y * gamepad2.right_stick_y * (gamepad2.right_stick_y > 0 ? 1 : -1));


        //Automated controls
        if (gamepad2.dpad_up) {
            chainController.sendToPosition(ChainController.psNames.TOP);
        }
        if (gamepad2.dpad_left || gamepad2.dpad_right) {
            chainController.sendToPosition(ChainController.psNames.CENTER);
        }
        if (gamepad2.dpad_down) {
            chainController.sendToPosition(ChainController.psNames.BOTTOM);
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
        rightGrab.setPosition(pos);
        leftGrab.setPosition(pos);
    }
}
