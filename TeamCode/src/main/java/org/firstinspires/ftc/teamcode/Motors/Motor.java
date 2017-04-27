package org.firstinspires.ftc.teamcode.Motors;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by John_Kesler on 4/25/2017.
 */

public class Motor {
    //Internal variables
    DcMotor motor = null;

    //Public variables
    public float velocity = 0.0f;
    public boolean active = false;

    public Motor(float velocity, String name, HardwareMap hardwareMap) {
        motor = hardwareMap.dcMotor.get(name);
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
