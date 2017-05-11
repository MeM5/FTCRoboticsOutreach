package org.firstinspires.ftc.teamcode.Motors;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by John_Kesler on 4/27/2017.
 */

public class MotorPair {
    public Motor motor1;
    public Motor motor2;

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

    public void updateMotors() {
        motor1.updateMotor();
        motor2.updateMotor();
    }

//    public void moveToPosition(int target, float velocity) {
//        while ((!motor1.doneMoving(target)||!motor2.doneMoving(target))) {
//            if (!motor1.doneMoving(target)) {
//                motor1.moveToPosition(target, velocity, true);
//            }
//            if (!motor2.doneMoving(target)) {
//                motor2.moveToPosition(target, velocity, true);
//            }
//        }
//    }

    public void setPositionToMove(int position, float velocity, boolean invertMotorTwo, boolean reset) throws java.lang.InterruptedException {
        if (reset) {
            motor1.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor2.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
        motor1.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motor1.motor.setTargetPosition(position);

        if (invertMotorTwo) {
            motor2.motor.setTargetPosition(-position);
        }
        else {
            motor2.motor.setTargetPosition(position);
        }

        motor1.motor.setPower(velocity);
        motor2.motor.setPower(velocity);
    }

    public void encodersOff() {
        motor1.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor2.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
