/*
Copyright 2026 FIRST Tech Challenge Team FTC

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.automove.AutoMove;
import org.firstinspires.ftc.teamcode.subassemblies.ComponentManager;

@Autonomous

public class DiffidulyiesBlue extends LinearOpMode {

    AutoMove autoMove;
    ComponentManager manager;
    String far;
    String near;
    int launchDelay = 100;
    int launchTargetAngle = 48;

    @Override
    public void runOpMode() {

        this.manager = new ComponentManager(this);
        this.autoMove = new AutoMove(this,"odo",0,0,0);

        waitForStart();
        if (opModeIsActive()) {
            sleep(4000);
            manager.gateClose();
            while (!autoMove.goToPos(0, 0) && opModeIsActive()) {
                while(!autoMove.goToRotation(0)){
                    autoMove.motorUpdate();
                }
                autoMove.motorUpdate();
            }//start
            while (!autoMove.goToPos(77, -3) && opModeIsActive()) {
                while(!autoMove.goToRotation(launchTargetAngle)){
                    autoMove.motorUpdate();
                }
                autoMove.motorUpdate();
            }//launch position
            manager.laucherOn();
            sleep(400);
            manager.shootWithSleep(near,launchDelay);
            while (!autoMove.goToPos(25, -2) && opModeIsActive()) {
                while(!autoMove.goToRotation(90)){
                    autoMove.motorUpdate();
                }
                autoMove.motorUpdate();
            }//line one lineup
            manager.intakeOn();
            while (!autoMove.goToPos(25, 40) && opModeIsActive()) {
                while(!autoMove.goToRotation(90)){
                    autoMove.motorUpdate();
                }
                autoMove.motorUpdate();
            }//line one intake
            sleep(75);
            while (!autoMove.goToPos(25, 10) && opModeIsActive()) {
                while(!autoMove.goToRotation(-45)){
                    autoMove.motorUpdate();
                }
                autoMove.motorUpdate();
            }//back up from line one
            manager.intakeOff();
            while (!autoMove.goToPos(78, -2) && opModeIsActive()) {
                while(!autoMove.goToRotation(launchTargetAngle)){
                    autoMove.motorUpdate();
                }
                autoMove.motorUpdate();
            }//launch position
            manager.shootWithSleep(near,launchDelay);
            while (!autoMove.goToPos(75, 10) && opModeIsActive()) {
                while(!autoMove.goToRotation(90)){
                    autoMove.motorUpdate();
                }
                autoMove.motorUpdate();
            }//turn to face line 3
            manager.intakeOn();
            while (!autoMove.goToPos(72, 34) && opModeIsActive()) {
                while(!autoMove.goToRotation(90)){
                    autoMove.motorUpdate();
                }
                autoMove.motorUpdate();
            }//intake line 3
            while (!autoMove.goToPos(72, -2) && opModeIsActive()) {
                while(!autoMove.goToRotation(launchTargetAngle)){
                    autoMove.motorUpdate();
                }
                autoMove.motorUpdate();
            }//launch position
            manager.intakeOff();
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
