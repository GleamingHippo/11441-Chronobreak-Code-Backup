package org.firstinspires.ftc.teamcode.subassemblies;

import static org.firstinspires.ftc.teamcode.subassemblies.ComponentManager.LaunchPosition;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.EasyHardware.ExServoSimple;
public class LaunchAngler {

    ExServoSimple launchAngler;

    public LaunchAngler(OpMode opmode) {
        launchAngler = new ExServoSimple("launch_angler", opmode);
    }

    public void setLaunchAngle(int Position) {
        if (Position == 0){
            launchAngler.setPosition(0.3);
        }
        else if (Position == 1){
            launchAngler.setPosition(0);
        }
        else if (Position == 2){
            launchAngler.setPosition(0);
        }
    }

    public void setLaunchAnglerPos(LaunchPosition Position){
        if (Position == LaunchPosition.BACK){
            launchAngler.setPosition(0.3);
        }
        else if (Position == LaunchPosition.FAR){
            launchAngler.setPosition(0);
        }
        else if (Position == LaunchPosition.CLOSE){
            launchAngler.setPosition(0);
        }
    }

    public void setPosition1() {
        launchAngler.setPosition(0.3);
    }
    public void setPosition2() {
        launchAngler.setPosition(0.1);
    }
    public void setPosition3() {
        launchAngler.setPosition(0);
    }
}
