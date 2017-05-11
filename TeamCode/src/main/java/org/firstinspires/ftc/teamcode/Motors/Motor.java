package org.firstinspires.ftc.teamcode.Motors;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by John_Kesler on 4/25/2017.
 */

public class Motor {
    //Internal variables
    public DcMotor motor = null;

    //Public variables
    public float velocity = 0.0f;
    public boolean active = false;

    public Motor(float velocity, DcMotor motor) {
        this.motor = motor;
        this.velocity = velocity;
    }

    public void reverse() {
        velocity = -velocity;
    }

    public void toggleActive() {
        active = !active;
    }

    public void updateMotor() {
        if (active) {
            motor.setPower(velocity);
        }
        else {
            motor.setPower(0);
        }
    }

    public void moveToPosition(int position, float defaultVelocity, boolean reversing) {
        if (velocity==0) {
            velocity=defaultVelocity;
        }
        if (motor.getCurrentPosition()>position&&velocity<0&&reversing) {
            reverse();
        }
        if (motor.getCurrentPosition()<position&&velocity>0&&reversing) {
            reverse();
        }
        updateMotor();
    }

    public void continuousMoveToPosition(int target) {
        while (!doneMoving(target)) {
            moveToPosition(target, 0.5f, false);
        }
    }

    public boolean doneMoving(int target) {
        int distance = Math.abs(motor.getCurrentPosition()-target);
        if (distance<=1) { //3 encoder ticks away from target
            return true;
        }
        else {
            return false;
        }
    }
}
