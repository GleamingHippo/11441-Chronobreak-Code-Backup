package org.firstinspires.ftc.teamcode.EasyHardware;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.ServoImplEx;

/* JADX INFO: loaded from: classes7.dex */
public class ExServoPWM {
    ServoImplEx servoImplEx;

    public ExServoPWM(String name, OpMode opmode) {
        PwmControl.PwmRange customRange = new PwmControl.PwmRange(500.0d, 2500.0d);
        this.servoImplEx = (ServoImplEx) opmode.hardwareMap.get(ServoImplEx.class, name);
        this.servoImplEx.setPwmRange(customRange);
    }

    public void setPosition(double position) {
        this.servoImplEx.setPosition(position);
    }
}