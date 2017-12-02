package ocs.teamcode.Various;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by CJS on 10/17/17.
 */

@TeleOp
public class CenterServos extends OpMode{

    Servo rightServo, leftServo;

    @Override
    public void init() {
        rightServo = hardwareMap.servo.get("servo_right");
        leftServo = hardwareMap.servo.get("servo_left");

        rightServo.setPosition(0.5);
        leftServo.setPosition(0.5);
    }

    @Override
    public void loop() {

    }
}
