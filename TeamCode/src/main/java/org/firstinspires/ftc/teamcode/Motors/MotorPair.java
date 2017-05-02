package org.firstinspires.ftc.teamcode.Motors;

/**
 * Created by John_Kesler on 4/27/2017.
 */

public class MotorPair {
    private Motor motor1;
    private Motor motor2;

    public float velocity = 0;

    public MotorPair(Motor motorA, Motor motorB) {
        motor1 = motorA;
        motor2 = motorB;
    }

    public void toggleAllStates() {
        motor1.active = !motor1.active;
        motor2.active = !motor2.active;
    }

    public void turnAllOn() {
        motor1.active = true;
        motor2.active = true;
    }

    public void turnAllOff() {
        motor1.active = false;
        motor2.active = false;
    }

    public void setAllVelocity(float velocity) {
        motor1.velocity = velocity;
        motor2.velocity = velocity;
    }

    public void reverseMotorVelocity(boolean a, boolean b) {
        if (a) {
            motor1.reverse();
        }
        if (b) {
            motor2.reverse();
        }
    }
}
