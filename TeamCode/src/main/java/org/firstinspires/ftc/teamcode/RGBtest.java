package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.EasyHardware.Chassis;
//import org.firstinspires.ftc.teamcode.ExLibrary.ComputerVisonDecode;
import org.firstinspires.ftc.teamcode.EasyHardware.ExMotor;
import org.firstinspires.ftc.teamcode.EasyHardware.ExServo;

@TeleOp
public class RGBtest extends OpMode {
    //~~~~~~~~~~~~~~~~~create objects~~~~~~~~~~~~~~~~~
    ExServo rgb1;
    //~~~~~~~~~~~~~~~~~~create variables~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Override
    public void init() { //~~~~~~~initialization code~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        this.rgb1 = new ExServo("flip_servo", this, 3); //~~~~~~~~~assign gate object to servo class, type simple~~~~~~~~~

        this.telemetry.addData("Status", "Initialized"); //~~~~~~~~~~~~~add the status to telemetry class~~~~~~~~~~~~~
        this.telemetry.update();
    }

    @Override // com.qualcomm.robotcore.eventloop.opmode.OpMode
    public void loop() {
        rgb1.setPosition(0.444);
    }

    @Override
    public void stop() {
        this.telemetry.addData("Status", "Stopped");
        this.telemetry.update();
    }
}
