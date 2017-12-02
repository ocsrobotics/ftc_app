package ocs.teamcode.Various;

import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.HashMap;

/**
 * Created by CJS on 11/4/17.
 */

public class ChainController {
    public enum psNames {TOP, CENTER, BOTTOM}
    public HashMap<psNames, Float> Positions = new HashMap();

    public psNames selectedPos = psNames.BOTTOM;

    float lowEnd;
    float highEnd;
    float range;

    float target;

    DcMotor motor;

    public ChainController(float lo, float hi, float curPos, DcMotor mtr) {
        lowEnd = lo;
        highEnd = hi;
        target = curPos;

        range = highEnd - lowEnd;

        Positions.put(psNames.TOP, highEnd);
        Positions.put(psNames.CENTER, (range / 2) + lowEnd);
        Positions.put(psNames.BOTTOM, lowEnd);

        motor = mtr;
    }

    public void update() {
        motor.setTargetPosition((int)target);
    }

    public void sendToPosition(psNames pos) {
        target = Positions.get(pos);
    }
}
