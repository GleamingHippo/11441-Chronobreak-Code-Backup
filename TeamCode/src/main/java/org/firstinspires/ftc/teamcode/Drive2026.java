package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.EasyHardware.Chassis;
//import org.firstinspires.ftc.teamcode.ExLibrary.ComputerVisonDecode;
import org.firstinspires.ftc.teamcode.EasyHardware.ExMotor;
import org.firstinspires.ftc.teamcode.EasyHardware.ExServo;
import org.firstinspires.ftc.teamcode.subassemblies.ComponentManager;
import org.firstinspires.ftc.teamcode.subassemblies.Cords;
import org.firstinspires.ftc.teamcode.automove.AutoMove;

@TeleOp
public class Drive2026 extends OpMode {
    //~~~~~~~~~~~~~~~~~create objects~~~~~~~~~~~~~~~~~
    Chassis chassis;
    ExServo gate;
    ExServo launchAngler;
    ExMotor intake;
    ExMotor shootMotorOne;
    ExMotor shootMotorTwo;
    ComponentManager manager;

    //~~~~~~~~~~~~~~~~~~create variables~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    double launchTargetSpeed = 0;
    double humanDriveSpeed = 0.5d;
    boolean launchModeOVERRIDE = false;
    float launchModeOVERRIDETimer = 0;
    int launchPosition = 2;

    //automove speed ramping tuning variables:
    double minDistance = 0;
    double maxDistance = 100;
    double maxSpeed = 1700;
    double minSpeed = 1500;
    double robotX = 0;
    double robotY = 0;

    AutoMove autoMove;
    @Override
    public void init() { //~~~~~~~initialization code~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        this.chassis = new Chassis(this); //~~~~~~~~~assign chassis object to chassis class~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        this.shootMotorTwo = new ExMotor("shoot_motor_two", this);
        this.shootMotorOne = new ExMotor("shoot_motor_one", this);  //~~~~~~~~~assign shootMotorOne object to ExMotor class~~~~~~~~~~~~~~~~~~~
        this.shootMotorTwo.ZeroPowerCoast();
        this.shootMotorOne.ZeroPowerCoast(); //~~~~~~~~~make it so that the shoot motor does not break, but rather coasts~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        this.intake = new ExMotor("intake", this); //~~~~~~~~~assign intake object to ExMotor class~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        this.gate = new ExServo("gate", this, 2); //~~~~~~~~~assign gate object to servo class, type simple~~~~~~~~~
        this.launchAngler = new ExServo("launch_angler", this, 2);
        autoMove = Cords.saved_move;
        this.telemetry.addData("LAUNCH MODE OVERRIDE", "OFF");
        this.telemetry.addData("Status", "Initialized"); //~~~~~~~~~~~~~add the status to telemetry class~~~~~~~~~~~~~

        telemetry.setMsTransmissionInterval(11);

        this.telemetry.update();
    }

    @Override // com.qualcomm.robotcore.eventloop.opmode.OpMode
    public void loop() {

        if (this.gamepad1.right_stick_x > 0.05d && this.gamepad1.left_trigger <= 0.2d) {
            this.humanDriveSpeed = 0.7d;
        } else if (this.gamepad1.right_trigger > 0.2d) {
            this.humanDriveSpeed = 0.3d;
        } else if (this.gamepad1.left_trigger > 0.2d) {
            this.humanDriveSpeed = 1.0d;
        } else {
            this.humanDriveSpeed = 0.7d;
        }

        this.chassis.driveDirection(-this.gamepad1.left_stick_x, this.gamepad1.left_stick_y, this.humanDriveSpeed, this.gamepad1.right_stick_x);

        if (this.gamepad2.right_trigger > 0.2) {
            this.gate.setPosition(-0.8);
        } else {
            this.gate.setPosition(0.8);
        }

        if (this.gamepad2.a){
            intake.setPower(1);
        }
        else{
            intake.setPower(0);
        }

        //update the X&Y
        robotX = autoMove.odoX();
        robotY = autoMove.odoY();

        //calculate the distance to the goal ~~~~~~~~~~~~~~~SET THIS ASAP!!!~~~~~~~~~~~~~~~~~`
        double distanceToGoal = Math.sqrt(
                Math.pow(0 - robotY, 2) +
            Math.pow(0 - robotX, 2)
        );

        //update the angle of the upper servo
        if (distanceToGoal < 30){
            launchPosition = 0;
        }
        else if (distanceToGoal < 90){
            launchPosition = 1;
        }
        else{
            launchPosition = 2;
        }
        manager.setLaunchAngle(launchPosition);

        //use the left trigger
        if (gamepad2.left_trigger > 0.2){
            //calculate target launch speed
            double targetLaunchSpeed =
                maxSpeed -
                    ((distanceToGoal - minDistance) / (maxDistance - minDistance))
                        * (maxSpeed - minSpeed);
            // Clamp to limits
            targetLaunchSpeed = Math.max(minSpeed, Math.min(maxSpeed, targetLaunchSpeed));

            if (this.gamepad2.x){
                launchModeOVERRIDETimer += 0.1F;
            }
            else if (!launchModeOVERRIDE){
                launchModeOVERRIDETimer = 0;
            }
            if (launchModeOVERRIDETimer == 10){
                launchModeOVERRIDE = true;
            }

            if (!launchModeOVERRIDE) this.telemetry.addData("LAUNCH MODE OVERRIDE", "OFF");
            else this.telemetry.addData("LAUNCH MODE OVERRIDE", "!!!!!!!!!!!!!ON!!!!!!!!!!!!!!");

            if (!launchModeOVERRIDE) {
                if (this.gamepad2.dpad_left) {
                    shootMotorOne.setPower(targetLaunchSpeed);
                    shootMotorTwo.setPower(-targetLaunchSpeed);
                } else if (this.gamepad2.dpad_right) {
                    shootMotorOne.setPower(2000);
                    shootMotorTwo.setPower(-2000);
                } else {
                    shootMotorOne.setPower(0);
                    shootMotorTwo.setPower(0);
                }
            }

            else{
                if (this.gamepad2.dpad_up) launchTargetSpeed = -1600;
                else if (this.gamepad2.dpad_left) launchTargetSpeed = -1700;
                else if (this.gamepad2.dpad_right) launchTargetSpeed = -1500;
                else launchTargetSpeed = 0;
            }

        }

        this.telemetry.update();
    }

    @Override

    public void stop() {
        chassis.driveDirection(0,0,0,0);
        this.telemetry.addData("Status", "Stopped");
        this.telemetry.update();
    }
}
