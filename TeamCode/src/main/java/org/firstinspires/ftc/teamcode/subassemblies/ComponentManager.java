package org.firstinspires.ftc.teamcode.subassemblies;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.automove.AutoMove;
import org.firstinspires.ftc.teamcode.EasyHardware.Chassis;

import java.util.Objects;

public class ComponentManager extends LinearOpMode{
    LinearOpMode opMode;
    Launcher launcher;
    public Intake intake;
    Chassis chassis;
    Gate gate;
    int targetSpeed;
    colorFinder colorFinder;
    public enum detectedColor{
        GREEN,
        PURPLE,
        UNKNOWN
    }
//    AutoMove autoMove;
    public ComponentManager(LinearOpMode opMode){
        this.opMode = opMode;
        this.launcher = new Launcher(opMode);
        this.intake = new Intake(opMode);
        this.gate = new Gate(opMode);
        this.chassis = new Chassis(opMode);
//        this.autoMove = new AutoMove(this,"odo",0,0,0);
    }
    public void sleepThing(int Datime){
        chassis.driveDirection(0,0,0,0);
        sleep(Datime);
    }
    public void shootWithSleep(String distance, int LaunchDelay){
        if (Objects.equals(distance, "far")) {
            targetSpeed = -1000;
        } else if (Objects.equals(distance, "moose")) {
            targetSpeed = -900;
        }
        else{
            targetSpeed = -1660;
        }
        gate.close();
        chassis.driveDirection(0,0,0,0);
        launcher.setVelocity(targetSpeed - 150);
        gate.open();
        sleep(400);

        intake.setPower(1);
        sleep(LaunchDelay);
        intake.setPower(0);
        launcher.setVelocity(targetSpeed);
        sleep(LaunchDelay + 100);

        intake.setPower(1);
        sleep(LaunchDelay);
        intake.setPower(0);
        sleep(LaunchDelay + 200);

        intake.setPower(1);
        sleep(LaunchDelay + 500);
        intake.setPower(0);
        sleep(LaunchDelay + 200);

        gate.close();
        launcher.setVelocity(targetSpeed);
        intake.setPower(0);
    }
    public void shootNearest(int LaunchDelay){
        targetSpeed = -1535;

        gate.close();
        chassis.driveDirection(0,0,0,0);
        launcher.setVelocity(targetSpeed - 170);
        gate.open();
        sleep(400);

        intake.setPower(1);
        sleep(LaunchDelay);
        intake.setPower(0);
        launcher.setVelocity(targetSpeed);
        sleep(LaunchDelay + 100);

        intake.setPower(1);
        sleep(LaunchDelay);
        intake.setPower(0);
        sleep(LaunchDelay + 200);

        intake.setPower(1);
        sleep(LaunchDelay + 500);
        intake.setPower(0);
        sleep(LaunchDelay + 200);

        gate.close();
        launcher.setVelocity(targetSpeed);
        intake.setPower(0);
    }
    public void shootBack(int LaunchDelay) {
        targetSpeed = -6000;

        gate.close();
        chassis.driveDirection(0, 0, 0, 0);
        launcher.setVelocity(targetSpeed - 170);
        gate.open();
        sleep(1000);

        intake.setPower(1);
        sleep(LaunchDelay);
        intake.setPower(0);
        launcher.setVelocity(targetSpeed);
        sleep(1000);

        intake.setPower(1);
        sleep(LaunchDelay);
        intake.setPower(0);
        sleep(1000);

        intake.setPower(1);
        sleep(LaunchDelay + 500);
        intake.setPower(0);
        sleep(LaunchDelay + 200);

        gate.close();
        launcher.setVelocity(targetSpeed);
        intake.setPower(0);
    }
    public void intakeOn(){
        gate.close();
        intake.setPower(1);
    }
    public void launcherOff(){
        launcherOff();
    }

    public void intakeOff(){
        intake.setPower(0);
    }
    public void laucherOn(){
        launcher.setVelocity(-1900);
    }
//    public void goToLocation(int x,int y,int r){
//        while (!autoMove.goToPos(x, y)) {
//            while(!autoMove.goToRotation(r)){
//                autoMove.motorUpdate();
//            }
//            autoMove.motorUpdate();
//        }
//    }

    public void gateClose(){
        gate.close();
    }

    public void gateOpen(){
        gate.open();
    }

    public detectedColor getDetectedColor(){
        return colorFinder.getColor();
    }


    @Override
    public void runOpMode() throws InterruptedException {
    }
}
