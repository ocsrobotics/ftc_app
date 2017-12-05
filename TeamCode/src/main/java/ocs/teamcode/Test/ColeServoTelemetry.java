package ocs.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by CJS on 12/2/17.
 */

@TeleOp
public class ColeServoTelemetry extends OpMode {

    Servo s;

    public void init() {
        s = hardwareMap.servo.get("color_boom");
        s.setDirection(Servo.Direction.REVERSE);
        s.setPosition(0);
    }

    public void loop() {
        s.setPosition(gamepad1.right_trigger);
        telemetry.addData("Servo Position", s.getPosition());
        telemetry.update();
    }
}
