package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.EasyHardware.Chassis;
//import org.firstinspires.ftc.teamcode.ExLibrary.ComputerVisonDecode;
import org.firstinspires.ftc.teamcode.EasyHardware.ExMotor;
import org.firstinspires.ftc.teamcode.EasyHardware.ExServo;
import org.firstinspires.ftc.teamcode.subassemblies.ComponentManager;
import org.firstinspires.ftc.teamcode.subassemblies.colorFinder;

@TeleOp
public class RGBtest extends OpMode {
    //~~~~~~~~~~~~~~~~~create objects~~~~~~~~~~~~~~~~~
    ExServo rgb1;
    colorFinder colorFinder;
    public enum detectedColor{
        GREEN,
        PURPLE,
        UNKNOWN
    }



    //~~~~~~~~~~~~~~~~~~create variables~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Override
    public void init(){ //~~~~~~~initialization code~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        this.rgb1 = new ExServo("rgb1", this, 3); //~~~~~~~~~assign gate object to servo class, type simple~~~~~~~~~
        this.colorFinder = new colorFinder("colorSensor",this);
        this.telemetry.addData("Status", "Initialized"); //~~~~~~~~~~~~~add the status to telemetry class~~~~~~~~~~~~~
        this.telemetry.update();
    }

    @Override // com.qualcomm.robotcore.eventloop.opmode.OpMode
    public void loop() {
        telemetry.addData("detectedColor", colorFinder.getColor());

    }

    @Override
    public void stop() {
        this.telemetry.addData("Status", "Stopped");
        this.telemetry.update();
    }
}
