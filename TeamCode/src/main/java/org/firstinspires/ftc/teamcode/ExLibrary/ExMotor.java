package org.firstinspires.ftc.teamcode.ExLibrary;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

/* JADX INFO: loaded from: classes7.dex */
public class ExMotor {
    DcMotorEx motor;
    double ticksPerRotation = 384.0d;

    public ExMotor(String name, OpMode opmode) {
        this.motor = (DcMotorEx) opmode.hardwareMap.dcMotor.get(name);
        this.motor.setDirection(DcMotorSimple.Direction.FORWARD);
        this.motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.motor.setPIDFCoefficients(
            DcMotor.RunMode.RUN_USING_ENCODER,
            new PIDFCoefficients(0.1,0,0,11.7)
        );
    }

    public void goToRotation(double rotation) {
        this.motor.setTargetPosition((int) (this.ticksPerRotation * rotation));
        this.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void resetEncoder() {
        this.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void setPower(double speed) {
        this.motor.setPower(speed);
    }

    public void setEncoderDrive() {
        this.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void setPowerDrive() {
        this.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setRotationTicks(double ticksPerRotation) {
        this.ticksPerRotation = ticksPerRotation;
    }
    public void setVelocity(double velocity){
        this.motor.setVelocity(velocity);
    }
}