package org.firstinspires.ftc.teamcode.subassemblies;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.EasyHardware.ExServoSimple;
public class Gate {

    ExServoSimple gate;

    public Gate(OpMode opmode) {
        gate = new ExServoSimple("gate", opmode);
    }
    public void open(){
        gate.setPosition(-0.8);
    }
    public void close(){
        gate.setPosition(0.8);
    }
}
