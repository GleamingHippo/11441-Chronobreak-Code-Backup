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
    Limelight3A limelight;

    //~~~~~~~~~~~~~~~~~~create variables~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    double launchTargetSpeed = 0;
    double humanDriveSpeed = 0.5d;
    double targetDistance = 0;
    double distanceToApriltag;
    double ApriltagTx = 0;

    //Apriltag launch speed tuning variables:
    double minDistance = 0;
    double maxDistance = 100;
    double maxSpeed = 3000;
    double minSpeed = 1000;

    @Override
    public void init() { //~~~~~~~initialization code~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        this.chassis = new Chassis(this); //~~~~~~~~~assign chassis object to chassis class~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        this.shootMotorTwo = new ExMotor("shoot_motor_two", this);
        this.shootMotorOne = new ExMotor("shoot_motor_one", this);  //~~~~~~~~~assign shootMotorOne object to ExMotor class~~~~~~~~~~~~~~~~~~~
        this.shootMotorTwo.ZeroPowerCoast();
        this.shootMotorOne.ZeroPowerCoast(); //~~~~~~~~~make it so that the shoot motor does not break, but rather coasts~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        this.intake = new ExMotor("intake", this); //~~~~~~~~~assign intake object to ExMotor class~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        this.gate = new ExServo("flip_servo", this, 2); //~~~~~~~~~assign gate object to servo class, type simple~~~~~~~~~

        this.telemetry.addData("Status", "Initialized"); //~~~~~~~~~~~~~add the status to telemetry class~~~~~~~~~~~~~

        limelight = hardwareMap.get(Limelight3A.class, "limelight");

        limelight.pipelineSwitch(8);

        limelight.start();

        telemetry.setMsTransmissionInterval(11);

        this.telemetry.update();
    }

    @Override // com.qualcomm.robotcore.eventloop.opmode.OpMode
    public void loop() {

        LLResult LimelightRaw = limelight.getLatestResult();
        if (LimelightRaw != null) {
            if (LimelightRaw.isValid()) {
                Pose3D botpose = LimelightRaw.getBotpose();
                ApriltagTx = LimelightRaw.getTx();
                distanceToApriltag = tagDistance(LimelightRaw.getTa());
                telemetry.addData("Tag Distance", distanceToApriltag);
                telemetry.addData("Target X", LimelightRaw.getTx());
                telemetry.addData("Target Area", LimelightRaw.getTa());
                telemetry.addData("Botpose", botpose.toString());
                telemetry.addData("Limelight Staus", "Apriltag acquired");
            }
            else telemetry.addData("Limelight Staus", "No target");
        }
        else telemetry.addData("Limelight Staus", "Null");


        if (this.gamepad1.right_stick_x > 0.05d && this.gamepad1.left_trigger <= 0.2d) {
            this.humanDriveSpeed = 0.7d;
        } else if (this.gamepad1.right_trigger > 0.2d) {
            this.humanDriveSpeed = 0.3d;
        } else if (this.gamepad1.left_trigger > 0.2d) {
            this.humanDriveSpeed = 1.0d;
        } else {
            this.humanDriveSpeed = 0.7d;
        }

        if (gamepad2.right_trigger > 0.2){
            double computerTurn = 0;
            if (ApriltagTx > 0) {
                computerTurn = -0.2;
            }
            else if (ApriltagTx < 0){
                computerTurn = -0.2;
            }
            this.chassis.driveDirection(0, 0, 1, computerTurn);
        }
        else{
            this.chassis.driveDirection(-this.gamepad1.left_stick_x, this.gamepad1.left_stick_y, this.humanDriveSpeed, this.gamepad1.right_stick_x);
        }

        if (this.gamepad2.right_trigger > 0.2) {
            this.gate.setPosition(-0.8);
        } else {
            this.gate.setPosition(0.8);
        }

        if (this.gamepad2.a){
            intake.setPower(1);
        }
        else intake.setPower(0);

        //calculate target launch speed
        double targetLaunchSpeed =
            maxSpeed -
                ((distanceToApriltag - minDistance) / (maxDistance - minDistance))
                    * (maxSpeed - minSpeed);
        // Clamp to limits
        targetLaunchSpeed = Math.max(minSpeed, Math.min(maxSpeed, targetLaunchSpeed));

        if (this.gamepad2.b){
            shootMotorOne.setPower(targetLaunchSpeed);
            shootMotorTwo.setPower(-targetLaunchSpeed);
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
    public double tagDistance (double ta){
        double scale = 0; //scale from math site
        return (scale / ta);
    }
}
