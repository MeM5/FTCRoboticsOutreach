package org.firstinspires.ftc.teamcode.MotorRefactored;

import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.ArrayList;

/**
 * Created by John_Kesler on 4/27/2017.
 */

public class MotorPair {
    //The motors that make up the motorPair
    public ArrayList<Motor> motors;

    //Make a new motorPair using a predetermined array of motors
    public MotorPair(ArrayList<Motor> motors) {
        this.motors = motors;
    }

    //Toggle the states of all of the motors
    public void toggleAllStates() {
        for (Motor m : motors) {
            m.toggleActive();
        }
    }

    //Turn all of the motors on
    public void turnAllOn() {
        for (Motor m : motors) {
            m.active = true;
        }
    }

    //Turn all of the motors off
    public void turnAllOff() {
        for (Motor m : motors) {
            m.active = true;
        }
    }

    //Set the velocity of all of the motors
    public void setAllVelocity(float velocity) {
        for (Motor m : motors) {
            m.velocity = velocity;
        }
    }

    //Reverse the motor at position in the array
    public void reverseMotorVelocity(int position) {
        motors.get(position).reverse();
    }

    //Update the motors
    public void updateMotors() {
        for (Motor m : motors) {
            m.updateMotor();
        }
    }

    //Reset the motor encoder positions
    public void resetAllEncoders() {
        for (Motor m : motors) {
            m.resetEncoder();
        }
    }

    //Set the position of each motor in the motor array, each position in the array coresponds to a motor
    public void setPositionToMove(int[] positions, float powerin, float power, boolean reset) throws InterruptedException {
        if (reset) {
            resetAllEncoders();
        }

        setModes(DcMotor.RunMode.RUN_TO_POSITION);

        int motorpos = 0;
        for (Motor m : motors) {
            m.motor.setTargetPosition(positions[motorpos]);
            motorpos++;

            m.setVelocityAndMove(powerin);
        }
    }

    public void encodersOff() {
        setModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public int[] getPositions() { //Get positions of motors, formatted motor1, motor2
        int[] output = new int[motors.size()];
        int mCounter = 0;
        for (Motor m : motors) {
            output[mCounter] = m.motor.getCurrentPosition();
            mCounter++;
        }
        return output;
    }

    public void resetPositions() { //Reset motor positions back to 0
        setModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void setModes(DcMotor.RunMode mode) {
        for (Motor m : motors) {
            m.motor.setMode(mode);
        }
    }

    public boolean atPosition(float error) {
        boolean output = false;
        //Check motor1 position to target


        return output;
    }
}
