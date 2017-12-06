package ocs.teamcode.Various;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by CJS on 11/4/17.
 */

public class ChainController {

    public enum psNames {
        TOP(100), CENTER(50), BOTTOM(0);

        private int position;

        psNames(int val) {
            this.position = val;
        }

        public int getValue() {
            return position;
        }

        public void setValue(int newVal) {
            position = newVal;
        }
    }

    public psNames selectedPos = psNames.BOTTOM;

    int range;
    int target = 0;

    DcMotor motor;

    public ChainController(int rng, DcMotor mtr) {
        range = rng;
        motor = mtr;
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        psNames.CENTER.setValue((range / 2) + 20);
        psNames.TOP.setValue(range);
    }

    public void update() { motor.setTargetPosition(target); }

    public void sendToPosition(psNames pos) { target = pos.getValue(); }

}
