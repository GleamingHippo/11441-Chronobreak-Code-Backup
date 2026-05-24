package org.firstinspires.ftc.teamcode.subassemblies;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.EasyHardware.ExMotor;
public class LauncherOne {

        ExMotor shootMotor;

        public LauncherOne(OpMode opmode) {
                shootMotor = new ExMotor("shoot_motor_1", opmode);
                this.shootMotor.ZeroPowerCoast();
        }
        public void setVelocity(int launch_target_velocity){
                shootMotor.setVelocity(launch_target_velocity);
        }
        public double getVelocity(){
                return shootMotor.getVelocity();
        }
        public boolean launchSpeedReached(int launch_tolerance, int launch_target_velocity){
                return (shootMotor.getVelocity() > (launch_target_velocity + launch_tolerance)) && (shootMotor.getVelocity() < (launch_target_velocity - launch_tolerance));
        }
}
