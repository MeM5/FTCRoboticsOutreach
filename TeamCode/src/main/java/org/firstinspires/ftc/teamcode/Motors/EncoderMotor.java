package org.firstinspires.ftc.teamcode.Motors;

import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by John_Kesler on 4/25/2017.
 */

public class EncoderMotor extends Motor {
    //Class only variables
    private float encoderPosition = 0;
    private int previousEncoderPosition = 0;


    //Public variables
    public int minEncoderTicks = 0;
    public int maxEncoderTicks = 0;


    public EncoderMotor(float velocity, String name, HardwareMap hw, int minEncoderTicks, int maxEncoderTicks)  {
        super (velocity,name,hw);
        this.minEncoderTicks = minEncoderTicks;
        this.maxEncoderTicks = maxEncoderTicks;
    }

    public void updateEncoderPosition() {
        if (motor.getCurrentPosition()>previousEncoderPosition) {
            encoderPosition++;
        }
        else if (motor.getCurrentPosition()<previousEncoderPosition) {
            encoderPosition--;
        }
    }

    public boolean withinRange() {
        if (encoderPosition>maxEncoderTicks||encoderPosition<minEncoderTicks) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public void updateMotor() {
        if (active&&withinRange()) {
            super.updateMotor();
        }
        else {
            motor.setPower(0);
        }
    }

    public void moveToPosition(int position) {
        if (encoderPosition>position&&super.velocity<0) {
            reverse();
        }
        if (encoderPosition<position&&super.velocity>0) {
            reverse();
        }
    }

    public void moveToPosition(int position, float defaultVelocity) {
        if (velocity==0) {
            velocity=defaultVelocity;
        }
        if (encoderPosition>position&&super.velocity<0) {
            reverse();
        }
        if (encoderPosition<position&&super.velocity>0) {
            reverse();
        }
    }
}
