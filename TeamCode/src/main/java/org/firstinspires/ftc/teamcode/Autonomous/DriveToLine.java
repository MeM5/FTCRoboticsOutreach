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

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Motors.EncoderMotor;
import org.firstinspires.ftc.teamcode.Motors.Motor;
import org.firstinspires.ftc.teamcode.Motors.MotorPair;

@TeleOp(name="DriveForwardsAndSpazOut", group="forGlory")  // @Autonomous(...) is the other common choice
public class DriveToLine extends LinearOpMode {

    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();

    MotorPair xAxis = new MotorPair(new Motor(0.5f,"motor1X",hardwareMap), new Motor(0.5f,"motor2X",hardwareMap));
    MotorPair yAxis = new MotorPair(new Motor(0.5f,"motor1Y",hardwareMap), new Motor(0.5f,"motor2Y",hardwareMap));
    EncoderMotor liftMotor = new EncoderMotor(0.5f,"liftMotor",hardwareMap,0,1000);

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        xAxis.turnAllOff();
        xAxis.reverseMotorVelocity(true, false);
        yAxis.turnAllOff();
        liftMotor.active = false;

        waitForStart();
        runtime.reset();

        xAxis.turnAllOn();
        yAxis.turnAllOff();

        wait(1000);

        xAxis.turnAllOff();
        xAxis.reverseMotorVelocity(true,false);

        wait(1000);

        xAxis.turnAllOn();
        yAxis.turnAllOn();

        wait(1000);

        xAxis.turnAllOff();
        yAxis.turnAllOff();

        while (opModeIsActive()) {

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }

    public void wait(int millis) {
        try {
            runtime.wait(millis);
        }
        catch (Exception e) {}
    }
}
