package ocs.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by CJS on 12/2/17.
 */

@TeleOp
public class ColorTelemetry extends OpMode {

    ColorSensor color;

    public void init() {
        color = hardwareMap.colorSensor.get("sensor_color");
    }

    public void loop() {
        telemetry.addData("Red", color.red());
        telemetry.addData("Green", color.green());
        telemetry.addData("Blue", color.blue());
        telemetry.addData("Alpha", color.alpha());
    }
}
