package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.EasyHardware.Chassis;
//import org.firstinspires.ftc.teamcode.ExLibrary.ComputerVisonDecode;
import org.firstinspires.ftc.teamcode.EasyHardware.ExMotor;
import org.firstinspires.ftc.teamcode.EasyHardware.ExServo;

@TeleOp
public class Drive2026 extends OpMode {
    //~~~~~~~~~~~~~~~~~create objects~~~~~~~~~~~~~~~~~
//    DistanceSensor ballDetectorFront;
    Chassis chassis;
    ExServo gate;
    ExMotor intake;
    ExMotor shootMotorOne;
    ExMotor shootMotorTwo;
    Limelight3A limelight3A;

    //~~~~~~~~~~~~~~~~~~create variables~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    double launchTargetSpeed = 0;
    double humanDriveSpeed = 0.5d;
    double targetDistance = 0;

    @Override
    public void init() { //~~~~~~~initialization code~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        this.chassis = new Chassis(this); //~~~~~~~~~assign chassis object to chassis class~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        this.shootMotorTwo = new ExMotor("shoot_motor_two", this);
        this.shootMotorOne = new ExMotor("shoot_motor_one", this);  //~~~~~~~~~assign shootMotorOne object to ExMotor class~~~~~~~~~~~~~~~~~~~
        this.shootMotorTwo.ZeroPowerCoast();
        this.shootMotorOne.ZeroPowerCoast(); //~~~~~~~~~make it so that the shoot motor does not break, but rather coasts~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        this.intake = new ExMotor("intake", this); //~~~~~~~~~assign intake object to ExMotor class~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        this.gate = new ExServo("flip_servo", this, 2); //~~~~~~~~~assign gate object to servo class, type simple~~~~~~~~~

//        this.ballDetectorFront = this.hardwareMap.get(DistanceSensor.class, "ball_detector_front"); //~assign ballDetectorFront to distance sensor class~

        this.telemetry.addData("Status", "Initialized"); //~~~~~~~~~~~~~add the status to telemetry class~~~~~~~~~~~~~

        limelight3A = hardwareMap.get(Limelight3A.class, "limelight");

        limelight3A.start();

        this.telemetry.update();
    }

    @Override // com.qualcomm.robotcore.eventloop.opmode.OpMode
    public void loop() {

        LLResult llResult = limelight3A.getLatestResult();

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

        if (llResult != null && llResult.isValid()){
            Pose3D botpose = llResult.getBotpose_MT2();
            telemetry.addData("targetArea", llResult.getTa());
            telemetry.addData("Bot Pose", botpose.toString());
        }

        if (this.gamepad2.a){
            intake.setPower(1);
        }
        else intake.setPower(0);

        if (this.gamepad2.b){
            shootMotorOne.setPower(1);
            shootMotorTwo.setPower(-1);
        }
        else{
            shootMotorOne.setPower(0);
            shootMotorTwo.setPower(0);
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
