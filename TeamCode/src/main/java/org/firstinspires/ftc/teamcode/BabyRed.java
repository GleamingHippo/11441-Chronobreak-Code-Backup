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

public class BabyRed extends LinearOpMode {

    AutoMove autoMove;
    ComponentManager manager;
    String far;
    String near;
    String moose;
    int launchDelay = 100;
    int launchTargetAngle = -5;
    int intakeLineAngle = 90;

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
            while (!autoMove.goToPos(-33, 3) && opModeIsActive()) {
                while(!autoMove.goToRotation(launchTargetAngle)){
                    autoMove.motorUpdate();
                }
                autoMove.motorUpdate();
            }//launch position
            manager.laucherOn();
            sleep(400);
            manager.shootNearest(launchDelay);
            manager.intakeOff();
        }
    }
}
