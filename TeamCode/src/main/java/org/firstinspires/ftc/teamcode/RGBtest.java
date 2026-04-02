package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.EasyHardware.Chassis;
//import org.firstinspires.ftc.teamcode.ExLibrary.ComputerVisonDecode;
import org.firstinspires.ftc.teamcode.EasyHardware.ExMotor;
import org.firstinspires.ftc.teamcode.EasyHardware.ExServo;

@TeleOp
public class RGBtest extends OpMode {
    //~~~~~~~~~~~~~~~~~create objects~~~~~~~~~~~~~~~~~
    ExServo rgb1;
    ColorSensor colorSensor;


    //~~~~~~~~~~~~~~~~~~create variables~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Override
    public void init(){ //~~~~~~~initialization code~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        this.rgb1 = new ExServo("rgb1", this, 3); //~~~~~~~~~assign gate object to servo class, type simple~~~~~~~~~

        this.colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");

        this.telemetry.addData("Status", "Initialized"); //~~~~~~~~~~~~~add the status to telemetry class~~~~~~~~~~~~~
        this.telemetry.update();


    }

    @Override // com.qualcomm.robotcore.eventloop.opmode.OpMode
    public void loop() {
        rgb1.setPosition(0.444);
        if ((!(colorSensor.green() < 700)) && (colorSensor.green() > 3000)){
            rgb1.setPosition(0.45);
            telemetry.addData("color", "green");
        }
        else if ((colorSensor.red() > 1000) && (colorSensor.blue() > 1000)){
            rgb1.setPosition(0.65);
            telemetry.addData("color", "purple");
        }
        else{
            rgb1.setPosition(0);
            telemetry.addData("color", "none");
        }
        telemetry.addData("Red", colorSensor.red());
        telemetry.addData("Green", colorSensor.green());
        telemetry.addData("Blue", colorSensor.blue());
        telemetry.update();
    }

    @Override
    public void stop() {
        this.telemetry.addData("Status", "Stopped");
        this.telemetry.update();
    }
}
