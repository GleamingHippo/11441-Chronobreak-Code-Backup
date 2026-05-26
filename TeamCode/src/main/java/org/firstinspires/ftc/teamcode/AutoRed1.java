package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.automove.AutoMove;
import org.firstinspires.ftc.teamcode.subassemblies.ComponentManager;

@Autonomous

public class AutoRed1 extends LinearOpMode {

    AutoMove autoMove;
    ComponentManager manager;
    String far;
    String near;
    int launchDelay = 100;
    int launchTargetAngle = -42;

    @Override
    public void runOpMode() {

        this.manager = new ComponentManager(this);
        this.autoMove = new AutoMove(this,"odo",0,0,0);

        waitForStart();
        if (opModeIsActive()) {
            manager.gateClose();
            while (!autoMove.goToPos(0, 0) && opModeIsActive()) {
                while(!autoMove.goToRotation(0)){
                    autoMove.motorUpdate();
                }
                autoMove.motorUpdate();
            }//start
            while (!autoMove.goToPos(77, 3) && opModeIsActive()) {
                while(!autoMove.goToRotation(launchTargetAngle)){
                    autoMove.motorUpdate();
                }
                autoMove.motorUpdate();
            }//launch position
            manager.laucherOn();
            sleep(400);
            manager.shootWithSleep(near,launchDelay);
            while (!autoMove.goToPos(30, 2) && opModeIsActive()) {
                while(!autoMove.goToRotation(-90)){
                    autoMove.motorUpdate();
                }
                autoMove.motorUpdate();
            }//line one lineup
            manager.intakeOn();
            while (!autoMove.goToPos(30, -40) && opModeIsActive()) {
                while(!autoMove.goToRotation(-90)){
                    autoMove.motorUpdate();
                }
                autoMove.motorUpdate();
            }//line one intake
            sleep(75);
            while (!autoMove.goToPos(30, -10) && opModeIsActive()) {
                while(!autoMove.goToRotation(-45)){
                    autoMove.motorUpdate();
                }
                autoMove.motorUpdate();
            }//back up from line one
            manager.intakeOff();
            while (!autoMove.goToPos(78, 2) && opModeIsActive()) {
                while(!autoMove.goToRotation(launchTargetAngle)){
                    autoMove.motorUpdate();
                }
                autoMove.motorUpdate();
            }//launch position
            manager.shootWithSleep(near,launchDelay);
            while (!autoMove.goToPos(75, -10) && opModeIsActive()) {
                while(!autoMove.goToRotation(-90)){
                    autoMove.motorUpdate();
                }
                autoMove.motorUpdate();
            }//turn to face line 3
            manager.intakeOn();
            while (!autoMove.goToPos(75, -38) && opModeIsActive()) {
                while(!autoMove.goToRotation(-90)){
                    autoMove.motorUpdate();
                }
                autoMove.motorUpdate();
            }//intake line 3
            while (!autoMove.goToPos(78, 2) && opModeIsActive()) {
                while(!autoMove.goToRotation(launchTargetAngle)){
                    autoMove.motorUpdate();
                }
                autoMove.motorUpdate();
            }//launch position
            manager.intakeOff();
            manager.shootWithSleep(near,launchDelay);
            while (!autoMove.goToPos(55, 0) && opModeIsActive()) {
                while(!autoMove.goToRotation(-90)){
                    autoMove.motorUpdate();
                }
                autoMove.motorUpdate();
            }//Line up for line 2
            manager.intakeOn();
            while (!autoMove.goToPos(50, -41) && opModeIsActive()) {
                while(!autoMove.goToRotation(-90)){
                    autoMove.motorUpdate();
                }
                autoMove.motorUpdate();
            }//Intake line 2
            while (!autoMove.goToPos(50, 0) && opModeIsActive()) {
                while(!autoMove.goToRotation(-45)){
                    autoMove.motorUpdate();
                }
                autoMove.motorUpdate();
            }//Line up for line 2
            manager.intakeOff();
            while (!autoMove.goToPos(78, 2) && opModeIsActive()) {
                while(!autoMove.goToRotation(launchTargetAngle)){
                    autoMove.motorUpdate();
                }
                autoMove.motorUpdate();
            }//Launch postion
            manager.shootWithSleep(near,launchDelay);
            while (!autoMove.goToPos(40, 0) && opModeIsActive()) {
                while(!autoMove.goToRotation(0)){
                    autoMove.motorUpdate();
                }
                autoMove.motorUpdate();
            }//start
        }
    }
}