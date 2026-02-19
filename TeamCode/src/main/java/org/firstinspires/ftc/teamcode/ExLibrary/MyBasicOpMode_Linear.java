package org.firstinspires.ftc.teamcode.ExLibrary;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/* JADX INFO: loaded from: classes7.dex */
@TeleOp(group = "Linear OpMode", name = "Basic: Linear OpMode")
public class MyBasicOpMode_Linear extends LinearOpMode {
    @Override // com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
    public void runOpMode() {
        AutoMove automove = new AutoMove(this, "odo", 0.0d, 0.0d, 0.0d);
        waitForStart();
        while (opModeIsActive()) {
            automove.goToRotation(90.0d);
            while (!automove.goToPos(0.0d, 0.0d)) {
                automove.motorUpdate();
                this.telemetry.update();
            }
            automove.goToRotation(0.0d);
            while (!automove.goToPos(20.0d, 0.0d)) {
                automove.motorUpdate();
                this.telemetry.update();
            }
            automove.goToRotation(90.0d);
            while (!automove.goToPos(20.0d, 20.0d)) {
                automove.motorUpdate();
                this.telemetry.update();
            }
            automove.goToRotation(0.0d);
            while (!automove.goToPos(0.0d, 20.0d)) {
                automove.motorUpdate();
                this.telemetry.update();
            }
        }
    }
}