/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Motors.EncoderMotor;
import org.firstinspires.ftc.teamcode.Motors.Motor;
import org.firstinspires.ftc.teamcode.Motors.MotorPair;

@Autonomous(name="Red Autonomous", group="forGlory")  // @Autonomous(...) is the other common choice
public class RedAutonomous extends LinearOpMode {

    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();

    MotorPair xAxis = null;
    MotorPair yAxis = null;
    EncoderMotor liftMotor = null;

    @Override
    public void runOpMode() throws InterruptedException {
        xAxis = new MotorPair(new Motor(0.5f,hardwareMap.dcMotor.get("motor1X")), new Motor(0.5f,hardwareMap.dcMotor.get("motor2X")));
        yAxis = new MotorPair(new Motor(0.5f,hardwareMap.dcMotor.get("motor1Y")), new Motor(0.5f,hardwareMap.dcMotor.get("motor2Y")));
        liftMotor = new EncoderMotor(0.5f,hardwareMap.dcMotor.get("liftMotor"),0,1000);

        xAxis.motor1.motor.setDirection(DcMotorSimple.Direction.REVERSE);
        xAxis.motor2.motor.setDirection(DcMotorSimple.Direction.FORWARD);
        yAxis.motor1.motor.setDirection(DcMotorSimple.Direction.REVERSE);
        yAxis.motor2.motor.setDirection(DcMotorSimple.Direction.FORWARD);

        telemetry.addData("Status", "Initialized");
        telemetry.update();


        waitForStart();
        runtime.reset();

        xAxis.turnAllOff();
        xAxis.reverseMotorVelocity(false, true);
        yAxis.turnAllOff();
        liftMotor.active = false;
        xAxis.updateMotors();
        yAxis.updateMotors();


//
//        xAxis.setPositionToMove(-2693, -2954, 0.5f, true);
//        wait(2500);
//        yAxis.setPositionToMove(6146, 6116, 0.5f, true);
//        wait(2500);
//        xAxis.setPositionToMove(-3099, -3805, 0.5f, true);
//        wait(2500);
//        xAxis.setPositionToMove(-924, 1385, 0.5f, true);
//        yAxis.setPositionToMove(-1326, 1319, 0.5f, true);

        xAxis.setPositionToMove(-478, -7492, 1.0f, true);
        yAxis.setPositionToMove(14101, 7070, 1.0f, true);




        while (opModeIsActive()) {

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();

            idle();
        }
    }

    public void wait(int millis) {
        double nextTime = runtime.milliseconds()+millis;
        while (runtime.milliseconds()<nextTime) {
            telemetry.addData("waiting:", "waiting");
            telemetry.update();
            idle();
        }
    }
}
