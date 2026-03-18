package org.firstinspires.ftc.teamcode.subassemblies;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.EasyHardware.ExMotor;
public class Intake {

    ExMotor intake;

    public Intake(OpMode opmode) {
        intake = new ExMotor("intake", opmode);
        this.intake.ZeroPowerCoast();
    }
    public void setPower(double Velocity){
        intake.setPower(Velocity);
    }
}
