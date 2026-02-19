package org.firstinspires.ftc.teamcode.ExLibrary;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;

/* JADX INFO: loaded from: classes7.dex */
public class ExServoCR {
    CRServo crServo;

    public ExServoCR(String name, OpMode opmode) {
        this.crServo = (CRServo) opmode.hardwareMap.get(CRServo.class, name);
    }

    public void setPower(double speed) {
        this.crServo.setPower(speed);
    }
}