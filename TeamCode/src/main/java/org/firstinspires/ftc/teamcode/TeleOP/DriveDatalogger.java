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
package org.firstinspires.ftc.teamcode.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Motors.MotorPair;


@TeleOp(name="Main Drive Datalogging", group="TeleOp")  // @Autonomous(...) is the other common choice
public class DriveDatalogger extends OpMode
{
    /* Declare OpMode members. */
    DcMotor motor1X = null;
    DcMotor motor2X = null;
    DcMotor motor1Y = null;
    DcMotor motor2Y = null;

    @Override
    public void init() {
        //Mapping Motors to Config
        motor1X = hardwareMap.dcMotor.get("motor1X");
        motor2X = hardwareMap.dcMotor.get("motor2X");
        motor1Y = hardwareMap.dcMotor.get("motor1Y");
        motor2Y = hardwareMap.dcMotor.get("motor2Y");
        telemetry.addData("Initialize: ", "Motors Mapped");

        //Mapping Default Directions
        motor1X.setDirection(DcMotorSimple.Direction.REVERSE);
        motor2X.setDirection(DcMotorSimple.Direction.FORWARD);
        motor1Y.setDirection(DcMotorSimple.Direction.REVERSE);
        motor2Y.setDirection(DcMotorSimple.Direction.FORWARD);
        telemetry.addData("Initialize: ", "Directions Mapped");

        motor1X.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2X.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor1Y.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2Y.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
    }

    @Override
    public void loop() {
        telemetry.addData("Status", "Running: ");

        //Set Power for Translation and Rotation
        motor1X.setPower(gamepad1.right_stick_y + gamepad1.left_stick_x);
        motor2X.setPower(gamepad1.right_stick_y - gamepad1.left_stick_x);
        motor1Y.setPower(gamepad1.right_stick_x + gamepad1.left_stick_x);
        motor2Y.setPower(gamepad1.right_stick_x - gamepad1.left_stick_x);

        if (gamepad1.a) { //Reset encoders
            motor1X.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor2X.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor1Y.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor2Y.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            motor1X.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motor2X.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motor1Y.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motor2Y.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        telemetry.addData("motor1X - ", motor1X.getCurrentPosition());
        telemetry.addData("motor2X - ", motor2X.getCurrentPosition());
        telemetry.addData("motor1Y - ", motor1Y.getCurrentPosition());
        telemetry.addData("motor2Y - ", motor2Y.getCurrentPosition());

        telemetry.update();
    }

    @Override
    public void stop() {
    }

}
