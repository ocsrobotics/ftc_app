package ocs.teamcode.Structs;

import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.ArrayList;
import java.util.Dictionary;

/**
 * Created by CJS on 10/3/17.
 */

public class MGroup {
    Dictionary<String, DcMotor> motors;

    MGroup(String m1, String m2) {
        motors.put(m1, new DcMotor);
        motors.put(m2, new DcMotor);
    }

    MGroup(String m1, String m2, String m3) {
        motors.put(m1, new DcMotor);
        motors.put(m2, new DcMotor);
        motors.put(m3, new DcMotor);
    }

    MGroup(String m1, String m2, String m3, String m4) {
        motors.put(m1, new DcMotor);
        motors.put(m2, new DcMotor);
        motors.put(m3, new DcMotor);
        motors.put(m4, new DcMotor);
    }

    //Implement initialization for motors
}
