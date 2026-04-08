package org.firstinspires.ftc.teamcode.subassemblies;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.teamcode.EasyHardware.ExServoCR;
import org.firstinspires.ftc.teamcode.EasyHardware.ExServoPWM;
import org.firstinspires.ftc.teamcode.EasyHardware.ExServoSimple;

public class colorFinder{

    NormalizedColorSensor colorFinder;

    public colorFinder(String name, OpMode opmode) {
        this.colorFinder = opmode.hardwareMap.get(NormalizedColorSensor.class, name);
    }

    public ComponentManager.detectedColor getColor() {
        NormalizedRGBA colors = colorFinder.getNormalizedColors();
        float normRed, normBlue, normGreen;

        normRed = colors.red / colors.alpha;
        normBlue = colors.blue / colors.alpha;
        normGreen = colors.green / colors.alpha;

//        telemetry.addData("red", normRed);
//        telemetry.addData("green", normGreen);
//        telemetry.addData("blue", normBlue);

        return ComponentManager.detectedColor.UNKNOWN;
    }
}
