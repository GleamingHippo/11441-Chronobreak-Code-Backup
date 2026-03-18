package org.firstinspires.ftc.teamcode.subassemblies;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.EasyHardware.ExMotor;
public class Launcher {

        ExMotor shootMotor;
        int launch_tollarance = 50;
        int launch_target_velocity = 0;

        public Launcher(OpMode opmode) {
                shootMotor = new ExMotor("shoot_motor", opmode);
                this.shootMotor.ZeroPowerCoast();
        }
        public void setVelocity(int Velocity){
                launch_target_velocity = Velocity;
                shootMotor.setVelocity(launch_target_velocity);
        }
        public double getVelocity(){
                return shootMotor.getVelocity();
        }
        public boolean launchSpeedReached(){
            return (shootMotor.getVelocity() > (launch_target_velocity + launch_tollarance)) && (shootMotor.getVelocity() < (launch_target_velocity - launch_tollarance));
        }
}
