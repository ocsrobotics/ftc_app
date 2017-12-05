package ocs.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by CJS on 11/4/17.
 */

@Disabled
public class EncoderTelemetry extends OpMode {

    DcMotor test;

    public void init() {
        test = hardwareMap.dcMotor.get("motor_chain");
        test.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void loop() {
        test.setPower(gamepad1.left_stick_y);

        telemetry.addData("Encoder", test.getCurrentPosition());
        telemetry.update();
    }
}
