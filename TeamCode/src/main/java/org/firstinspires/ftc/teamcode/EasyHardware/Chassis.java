package org.firstinspires.ftc.teamcode.EasyHardware;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/* JADX INFO: loaded from: classes7.dex */
public class Chassis {
    ExMotor backLeft;
    ExMotor backRight;
    ExMotor frontLeft;
    ExMotor frontRight;
    OpMode opmode;

    public Chassis(OpMode opmode) {
        this.opmode = opmode;
        this.frontLeft = new ExMotor("front_left", opmode);
        this.frontRight = new ExMotor("front_right", opmode);
        this.backLeft = new ExMotor("back_left", opmode);
        this.backRight = new ExMotor("back_right", opmode);
    }

    public void driveDirection(double x, double y, double speed, double rotationVal) {
        this.opmode.telemetry.addData("rotVal", rotationVal);
        this.opmode.telemetry.addData("XStick", y);
        this.opmode.telemetry.addData("YStick", x);
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rotationVal), 1.0d);
        double frontLeftPower = ((y - rotationVal) + x) / denominator;
        double backLeftPower = ((y - rotationVal) - x) / denominator;
        double frontRightPower = (((-y) - rotationVal) + x) / denominator;
        double backRightPower = (((-y) - rotationVal) - x) / denominator;
//        this.opmode.telemetry.addData("FL Pow", frontLeftPower);
        this.frontLeft.setPower(frontLeftPower * speed);
        this.frontRight.setPower(frontRightPower * speed);
        this.backLeft.setPower(backLeftPower * speed);
        this.backRight.setPower(backRightPower * speed);
    }
}