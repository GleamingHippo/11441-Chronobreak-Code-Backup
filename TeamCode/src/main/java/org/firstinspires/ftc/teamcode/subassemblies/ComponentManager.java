package org.firstinspires.ftc.teamcode.subassemblies;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.EasyHardware.Chassis;

import java.util.Objects;

public class ComponentManager extends LinearOpMode{
    LinearOpMode opMode;
    LauncherOne launcherOne;
    LauncherTwo launcherTwo;
    public Intake intake;
    Chassis chassis;
    Gate gate;
    LaunchAngler launchAngler;
    int targetSpeed;
    colorFinder colorFinder;
    public enum LaunchPosition {
        FAR,
        CLOSE,
        BACK
    }
    public enum detectedColor{
        GREEN,
        PURPLE,
        UNKNOWN
    }
//    AutoMove autoMove;
    public ComponentManager(LinearOpMode opMode){
        this.opMode = opMode;
        this.launcherOne = new LauncherOne(opMode);
        this.launcherTwo = new LauncherTwo(opMode);
        this.intake = new Intake(opMode);
        this.gate = new Gate(opMode);
        this.launchAngler = new LaunchAngler(opMode);
        this.chassis = new Chassis(opMode);
    }
    public void shootBack(String distance, int LaunchDelay){
        launchAngler.setLaunchAnglerPos(LaunchPosition.BACK);
    }
    public void shootFar(int LaunchDelay){
        launchAngler.setLaunchAnglerPos(LaunchPosition.FAR);
    }
    public void shootClose(int LaunchDelay){
        launchAngler.setLaunchAnglerPos(LaunchPosition.CLOSE);
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
        launcherOne.setVelocity(targetSpeed - 150);
        launcherTwo.setVelocity(-(targetSpeed - 150));
        gate.open();
        sleep(400);

        intake.setPower(1);
        sleep(LaunchDelay);
        intake.setPower(0);
        launcherOne.setVelocity(targetSpeed);
        launcherTwo.setVelocity(-targetSpeed);
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
        launcherOne.setVelocity(targetSpeed);
        launcherTwo.setVelocity(-targetSpeed);
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
        launcherOne.setVelocity(-1900);
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
