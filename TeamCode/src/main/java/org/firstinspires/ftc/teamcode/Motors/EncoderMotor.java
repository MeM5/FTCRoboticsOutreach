package org.firstinspires.ftc.teamcode.Motors;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by John_Kesler on 4/25/2017.
 */

public class EncoderMotor extends Motor {
    //Class only variables
    private int encoderPosition = 0;
    private int previousEncoderPosition = 0;


    //Public variables
    public int minEncoderTicks = 0;
    public int maxEncoderTicks = 0;


    public EncoderMotor(float velocity, DcMotor motor, int minEncoderTicks, int maxEncoderTicks)  {
        super (velocity,motor);
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

    public void continuousMoveToPosition(int target) {
        while (!doneMoving(target)) {
            moveToPosition(target);
        }
    }

    public void moveToPosition(int position) {
        if (encoderPosition>position&&super.velocity<0) {
            reverse();
        }
        if (encoderPosition<position&&super.velocity>0) {
            reverse();
        }
        updateMotor();
    }

    public void moveToPosition(int position, float defaultVelocity, boolean reversing) {
        if (velocity==0) {
            velocity=defaultVelocity;
        }
        if (encoderPosition>position&&super.velocity<0&&reversing) {
            reverse();
        }
        if (encoderPosition<position&&super.velocity>0&&reversing) {
            reverse();
        }
        updateMotor();
    }

    public void resetPosition() {
        encoderPosition = 0;
        previousEncoderPosition = 0;
    }

    public boolean doneMoving(int target) {
        int distance = Math.abs(encoderPosition-target);
        if (distance<=3) { //3 encoder ticks away from target
            return true;
        }
        else {
            return false;
        }
    }
}
