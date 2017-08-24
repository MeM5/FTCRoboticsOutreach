package org.firstinspires.ftc.teamcode.Autonomous;

import android.os.Environment;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Motors.EncoderMotor;
import org.firstinspires.ftc.teamcode.Motors.Motor;
import org.firstinspires.ftc.teamcode.Motors.MotorPair;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.Scanner;


//DECLARE TELEOP
@Autonomous(name="Teleop Driving Playback", group="Linear Opmode")  // @Autonomous(...) is the other common choice
public class BasicTankDriveReplay extends LinearOpMode {

    //TIMER
    private ElapsedTime runtime = new ElapsedTime(); //Timer
    float nextTime;

    Scanner readingFile;
    File readingFileFile;
    String line;
    String[] lineParsed;
    File sdCard;
    File file;
    BufferedReader br;

    MotorPair xAxis = null;
    MotorPair yAxis = null;
    EncoderMotor liftMotor = null;




    @Override
    public void runOpMode() throws InterruptedException {
        try
        {
            File sdCard = Environment.getExternalStorageDirectory();
            File file = new File(sdCard,"recorder.txt");
            br = new BufferedReader(new FileReader(file));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        //DECLARE TELEMETRY
        telemetry.addData("Status", "Program Initialized");
        telemetry.update();

        //DRIVING MOTORS
        //OPMODE CODE
        waitForStart();  //Wait for the game to start
        runtime.reset(); //Reset the timer before it is supposed to start



        nextTime = (int)runtime.milliseconds()+10;

        xAxis = new MotorPair(new Motor(0.5f,hardwareMap.dcMotor.get("motor1X")), new Motor(0.5f,hardwareMap.dcMotor.get("motor2X")));
        yAxis = new MotorPair(new Motor(0.5f,hardwareMap.dcMotor.get("motor1Y")), new Motor(0.5f,hardwareMap.dcMotor.get("motor2Y")));
        liftMotor = new EncoderMotor(0.5f,hardwareMap.dcMotor.get("liftMotor"),0,1000);

        xAxis.motor1.motor.setDirection(DcMotorSimple.Direction.REVERSE);
        xAxis.motor2.motor.setDirection(DcMotorSimple.Direction.FORWARD);
        yAxis.motor1.motor.setDirection(DcMotorSimple.Direction.REVERSE);
        yAxis.motor2.motor.setDirection(DcMotorSimple.Direction.FORWARD);


        //OPMODE RUNNING
        while (opModeIsActive()) { //While opmode is running

            if (runtime.milliseconds()>nextTime) {
                try {
                    line = br.readLine();
                    lineParsed = line.split(":");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

                int[] positions = new int[lineParsed.length];
                for (int i = 0; i<lineParsed.length; i++) {
                    positions[i] = Integer.valueOf(lineParsed[i]);
                }

                //DRIVING CONTROL CODE
                xAxis.motor1.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                xAxis.motor2.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                yAxis.motor1.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                yAxis.motor2.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                liftMotor.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                xAxis.motor1.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                xAxis.motor2.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                yAxis.motor1.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                yAxis.motor2.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                liftMotor.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                moveX(positions[0], positions[1]);
                moveY(positions[2], positions[3]);
                liftMotor.moveToPosition(positions[4]);
                nextTime = positions[5];
            }

            //OPMODE DEFAULT CODE
            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
            //Datalog
            telemetry.update();                                             //Pushes to terminal
        }
    }

    public void moveX(int a, int b) throws java.lang.InterruptedException {
        xAxis.setPositionToMove(a,b,1,true);
    }

    public void moveY(int a, int b) throws java.lang.InterruptedException {
        yAxis.setPositionToMove(a,b,1,true);
    }
}