package org.firstinspires.ftc.teamcode.ExLibrary;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/* JADX INFO: loaded from: classes7.dex */
@TeleOp(group = "Linear OpMode", name = "Basic: Linear OpMode")
public class MyBasicOpMode_Linear extends LinearOpMode {
    @Override // com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
    public void runOpMode() {
        waitForStart();
        if (opModeIsActive()) {
//            automove.goToRotation(90.0d);
            while (opModeIsActive()){
                this.telemetry.update();
            }
        }
    }
}