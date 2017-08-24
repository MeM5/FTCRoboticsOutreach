package org.firstinspires.ftc.teamcode.MotorRefactored;

import com.qualcomm.robotcore.hardware.DcMotor;

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
}
