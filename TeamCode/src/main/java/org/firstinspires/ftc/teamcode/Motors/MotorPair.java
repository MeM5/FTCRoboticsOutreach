package org.firstinspires.ftc.teamcode.Motors;

/**
 * Created by John_Kesler on 4/27/2017.
 */

public class MotorPair {
    private EncoderMotor motor1;
    private EncoderMotor motor2;

    public float velocity = 0;

    public MotorPair(EncoderMotor motorA, EncoderMotor motorB, float velocity) {
        motor1 = motorA;
        motor2 = motorB;
    }

    public void toggleAllStates() {
        motor1.active = !motor1.active;
        motor2.active = !motor2.active;
    }
}
