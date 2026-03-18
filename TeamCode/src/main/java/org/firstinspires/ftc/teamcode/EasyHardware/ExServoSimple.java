package org.firstinspires.ftc.teamcode.EasyHardware;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

/* JADX INFO: loaded from: classes7.dex */
public class ExServoSimple {
    Servo simpleServo;

    public ExServoSimple(String name, OpMode opmode) {
        this.simpleServo = (Servo) opmode.hardwareMap.get(Servo.class, name);
    }

    public void setPosition(double position) {
        this.simpleServo.setPosition(position);
    }
}