package org.firstinspires.ftc.teamcode.subassemblies;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.EasyHardware.ExServoSimple;
public class Gate {

    ExServoSimple gate;

    public Gate(OpMode opmode) {
        gate = new ExServoSimple("flip_servo", opmode);
    }
    public void open(){
        gate.setPosition(-0.8);
    }
    public void close(){
        gate.setPosition(0.8);
    }
}
