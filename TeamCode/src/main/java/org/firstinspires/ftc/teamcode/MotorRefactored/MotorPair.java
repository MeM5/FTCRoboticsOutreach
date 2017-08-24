package org.firstinspires.ftc.teamcode.MotorRefactored;

import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.ArrayList;

/**
 * Created by John_Kesler on 4/27/2017.
 */

public class MotorPair {
    public Motor motor1;
    public Motor motor2;

    public ArrayList<Motor> motors;

    public float velocity = 0;

    public MotorPair(ArrayList<Motor> motors) {
        this.motors = motors;
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

    public void setPositionToMove(int motor1position, int motor2position, float power, boolean reset) throws InterruptedException {
        if (reset) {
            motor1.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor2.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
        motor1.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motor1.motor.setTargetPosition(motor1position);
        motor2.motor.setTargetPosition(motor2position);

        motor1.motor.setPower(power);
        motor2.motor.setPower(power);
    }

    public void encodersOff() {
        motor1.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor2.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public int[] getPositions() { //Get positions of motors, formatted motor1, motor2
        return new int[] {
                motor1.motor.getCurrentPosition(),
                motor2.motor.getCurrentPosition()
        };
    }

    public void resetPositions() { //Reset motor positions back to 0
        motor1.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
