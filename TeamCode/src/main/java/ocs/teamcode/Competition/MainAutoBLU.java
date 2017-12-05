package ocs.teamcode.Competition;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by CJS on 12/2/17.
 */

@Autonomous
public class MainAutoBLU extends OpMode {

    enum Stages {CHECK, BUMP_BACK, BUMP_FORE, STOP}
    Stages stage = Stages.CHECK;

    DcMotor leftDrive, rightDrive;
    Servo leftGrab, rightGrab, colorBoom;
    ColorSensor color;

    double time;

    public void init() {
        //Motors
        leftDrive = hardwareMap.dcMotor.get("drive_left");
        rightDrive = hardwareMap.dcMotor.get("drive_right");

        //leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        //Servos
        leftGrab = hardwareMap.servo.get("grab_left");
        rightGrab = hardwareMap.servo.get("grab_right");
        rightGrab.setDirection(Servo.Direction.REVERSE);

        leftGrab.setPosition(0);
        rightGrab.setPosition(0);

        colorBoom = hardwareMap.servo.get("color_boom");
        colorBoom.setDirection(Servo.Direction.REVERSE);
        colorBoom.setPosition(0.0);

        //Color Sensor
        color = hardwareMap.colorSensor.get("sensor_color");
    }

    public void loop() {
        telemetry.addData("Red", color.red());
        telemetry.addData("Grn", color.green());
        telemetry.addData("Blu", color.blue());

        switch (stage) {
            case CHECK:
                telemetry.addData("Stage", "Check");

                colorBoom.setPosition(0.7);

                if (color.red() >= 5 && color.blue() < 2) {
                    time = getRuntime() + 1;
                    stage = Stages.BUMP_BACK;
                }

                if (color.blue() >= 2 && color.red() < 5) {
                    time = getRuntime() + 1;
                    stage = Stages.BUMP_FORE;
                }
                break;

            case BUMP_FORE:
                telemetry.addData("Stage", "Bump Back");

                if (getRuntime() - time < 0) {
                    leftDrive.setPower(-0.1);
                    rightDrive.setPower(-0.1);
                } else {
                    leftDrive.setPower(0.0);
                    rightDrive.setPower(0.0);

                    stage = Stages.STOP;
                }
                break;

            case BUMP_BACK:
                telemetry.addData("Stage", "Bump Fore");

                if (getRuntime() - time < 0) {
                    leftDrive.setPower(0.1);
                    rightDrive.setPower(0.1);
                } else {
                    leftDrive.setPower(0.0);
                    rightDrive.setPower(0.0);

                    stage = Stages.STOP;
                }
                break;

            case STOP:
                telemetry.addData("Stage", "Stopped");
                colorBoom.setPosition(0);
                break;
        }

        telemetry.update();
    }
}
