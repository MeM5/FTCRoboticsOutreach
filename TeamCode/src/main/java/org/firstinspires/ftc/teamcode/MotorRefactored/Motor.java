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

    //Make a motor using a default velocity and a motor reference
    public Motor(float velocity, DcMotor motor) {
        this.motor = motor;
        this.velocity = velocity;
    }

    //Set the motor's velocity and automatically make it active
    public void setVelocityAndMove(float velocity) {
        this.velocity = velocity;
        active = true;
        updateMotor();
    }

    //Reverse the velocity of the motor
    public void reverse() {
        velocity = -velocity;
    }

    //Toggle whether or not the motor is active
    public void toggleActive() {
        active = !active;
    }

    //Update the motor's velocity based on whether or not the motor is active
    public void updateMotor() {
        if (active) {
            motor.setPower(velocity);
        }
        else {
            motor.setPower(0);
        }
    }

    //Reset the encoder position
    public void resetEncoder() {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
