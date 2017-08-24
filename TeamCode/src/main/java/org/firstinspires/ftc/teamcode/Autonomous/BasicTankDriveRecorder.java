package org.firstinspires.ftc.teamcode.Autonomous;

import android.content.Context;
import android.os.Environment;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.io.*;


//DECLARE TELEOP
@TeleOp(name="Teleop Driving Recorder", group="Linear Opmode")  // @Autonomous(...) is the other common choice
public class BasicTankDriveRecorder extends LinearOpMode {

    PrintWriter pw;

    //TIMER
    private ElapsedTime runtime = new ElapsedTime(); //Timer

    //PrintString
    String printingString = "";
    float nextTime = 0;

    /* Declare OpMode members. */
    DcMotor motor1X = null;
    DcMotor motor2X = null;
    DcMotor motor1Y = null;
    DcMotor motor2Y = null;
    DcMotor liftMotor = null;





    @Override
    public void runOpMode() throws InterruptedException {
        //Mapping Motors to Config
        motor1X = hardwareMap.dcMotor.get("motor1X");
        motor2X = hardwareMap.dcMotor.get("motor2X");
        motor1Y = hardwareMap.dcMotor.get("motor1Y");
        motor2Y = hardwareMap.dcMotor.get("motor2Y");
        liftMotor = hardwareMap.dcMotor.get("liftMotor");
        telemetry.addData("Initialize: ", "Motors Mapped");

        //Mapping Default Directions
        motor1X.setDirection(DcMotorSimple.Direction.REVERSE);
        motor2X.setDirection(DcMotorSimple.Direction.FORWARD);
        motor1Y.setDirection(DcMotorSimple.Direction.REVERSE);
        motor2Y.setDirection(DcMotorSimple.Direction.FORWARD);
        liftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        telemetry.addData("Initialize: ", "Directions Mapped");

        motor1X.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2X.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor1Y.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2Y.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        try {
            //Open the HUG file
            File sdCard = Environment.getExternalStorageDirectory();
            File dir = new File(sdCard.getAbsolutePath());
            dir.mkdirs();
            File file = new File(dir, "recorder.txt");
            FileOutputStream fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            // DECLARE TELEMETRY
            telemetry.addData("Status", "Program Initialized");
            telemetry.update();

            //OPMODE CODE
            waitForStart();  //Wait for the game to start
            runtime.reset(); //Reset the timer before it is supposed to start

            nextTime = (int)runtime.milliseconds()+10;

            //OPMODE RUNNING
            while (opModeIsActive()) { //While opmode is running

                //DRIVING CONTROL CODE
                //Set Power for Translation and Rotation
                motor1X.setPower((gamepad1.right_stick_y + gamepad1.left_stick_x));
                motor2X.setPower((gamepad1.right_stick_y - gamepad1.left_stick_x));
                motor1Y.setPower((gamepad1.right_stick_x + gamepad1.left_stick_x));
                motor2Y.setPower((gamepad1.right_stick_x - gamepad1.left_stick_x));

                if (gamepad1.a) { //Reset encoders
                    motor1X.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    motor2X.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    motor1Y.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    motor2Y.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                    motor1X.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    motor2X.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    motor1Y.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    motor2Y.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                    printingString = motor1X.getCurrentPosition() + ":" + motor2X.getCurrentPosition() + ":" + motor1Y.getCurrentPosition() + ":" + motor2Y.getCurrentPosition() + ":" + liftMotor.getCurrentPosition() + ":" + runtime.milliseconds();
                    pw.println(printingString);
                }

                if (gamepad1.dpad_up) {
                    liftMotor.setPower(0.5f);
                }
                else if (gamepad1.dpad_down) {
                    liftMotor.setPower(-0.5f);
                }
                else {
                    liftMotor.setPower(0.0f);
                }
                //This will make the robot move
                telemetry.update();




                //OPMODE DEFAULT CODE
                idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
                //Datalog
                telemetry.update();                                             //Pushes to terminal

            }
        }
        catch(Exception e){
            System.out.println("TH15 C0D3 H4S B33N H4CKE0 BY 12010");
            System.out.println("The error: " + e.toString());
        }
        pw.flush();
        pw.close();
    }
}