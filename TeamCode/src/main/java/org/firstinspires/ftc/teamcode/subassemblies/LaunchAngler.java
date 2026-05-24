package org.firstinspires.ftc.teamcode.subassemblies;

import static org.firstinspires.ftc.teamcode.subassemblies.ComponentManager.LaunchPosition;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.EasyHardware.ExServoSimple;
public class LaunchAngler {

    ExServoSimple launchAngler;

    public LaunchAngler(OpMode opmode) {
        launchAngler = new ExServoSimple("launch_angler", opmode);
    }

    public void setLaunchAnglerPos(LaunchPosition Position){
        if (Position == LaunchPosition.BACK){
            launchAngler.setPosition(0);
        }
        else if (Position == LaunchPosition.FAR){
            launchAngler.setPosition(0);
        }
        else if (Position == LaunchPosition.CLOSE){
            launchAngler.setPosition(0);
        }
    }
}
