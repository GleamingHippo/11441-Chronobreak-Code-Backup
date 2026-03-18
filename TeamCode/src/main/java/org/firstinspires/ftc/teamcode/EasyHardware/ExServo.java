package org.firstinspires.ftc.teamcode.EasyHardware;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/* JADX INFO: loaded from: classes7.dex */
public class ExServo {
    public static final int EXSERVOCR = 1;
    public static final int EXSERVOPWM = 3;
    public static final int EXSERVOSIMPLE = 2;
    ExServoCR exServoCR;
    ExServoPWM exServoPWM;
    ExServoSimple exServoSimple;
    private int servoType;

    public ExServo(String name, OpMode opmode, int servoType) {
        this.servoType = servoType;
        opmode.telemetry.addData("EXSERVO", "Level 1");
        opmode.telemetry.update();
        if (servoType == 1) {
            opmode.telemetry.update();
            this.exServoCR = new ExServoCR(name, opmode);
        } else if (servoType == 2) {
            this.exServoSimple = new ExServoSimple(name, opmode);
        } else if (servoType == 3) {
            this.exServoPWM = new ExServoPWM(name, opmode);
        }
    }

    public void setPower(double speed) {
        if (this.servoType == 1) {
            this.exServoCR.setPower(speed);
        }
    }

    public void setPosition(double position) {
        if (this.servoType == 2) {
            this.exServoSimple.setPosition(position);
        } else if (this.servoType == 3) {
            this.exServoPWM.setPosition(position);
        }
    }
}