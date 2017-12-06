package ocs.teamcode.Various;

/**
 * Library for useful methods
 * Created by CJS on 10/17/17.
 */

public class CLib {

    //Constrain a number within a boundary
    public int constrain(int base, int low, int high) {

        if (base < low) {
            base = (base > high ? high : base);
        }

        return base;
    }

    public float constrain(float base, float low, float high) {

        if (base < low) {
            base = (base > high ? high : base);
        }

        return base;
    }

    public double constrain(double base, double low, double high) {

        if (base < low) {
            base = (base > high ? high : base);
        }

        return base;
    }


    public int map(int base, int low1, int high1, int low2, int high2) {
        double range1 = high1 - low1;
        double range2 = high2 - low2;

        double pct = base / range1;
        double pct2 = pct * range2;

        double fin = pct2 + low2;

        return (int)fin;
    }


    public float map(float base, float low1, float high1, float low2, float high2) {
        double range1 = high1 - low1;
        double range2 = high2 - low2;

        double pct = base / range1;
        double pct2 = pct * range2;

        double fin = pct2 + low2;

        return (float)fin;
    }


    public double map(double base, double low1, double high1, double low2, double high2) {
        double range1 = high1 - low1;
        double range2 = high2 - low2;

        double pct = base / range1;
        double pct2 = pct * range2;

        double fin = pct2 + low2;

        return fin;
    }
}
