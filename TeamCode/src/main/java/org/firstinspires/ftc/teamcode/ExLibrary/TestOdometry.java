package org.firstinspires.ftc.teamcode.ExLibrary;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/* JADX INFO: loaded from: classes7.dex */
@TeleOp(group = "Linear OpMode", name = "Test Odometry")
public class TestOdometry extends LinearOpMode {
    OdometryPod odo;

    @Override // com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
    public void runOpMode() {
        this.odo = new OdometryPod(this, "odo");
        waitForStart();
        while (opModeIsActive()) {
            this.odo.update();
            this.telemetry.addData("x: ", Double.valueOf(this.odo.getX()));
            this.telemetry.addData("y: ", Double.valueOf(this.odo.getY()));
            this.telemetry.addData("angle: ", Double.valueOf(this.odo.getHeadingDeg()));
            this.telemetry.update();
        }
    }
}