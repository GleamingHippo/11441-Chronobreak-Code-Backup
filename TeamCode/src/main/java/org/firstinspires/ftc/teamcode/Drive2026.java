package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.EasyHardware.Chassis;
//import org.firstinspires.ftc.teamcode.ExLibrary.ComputerVisonDecode;
import org.firstinspires.ftc.teamcode.EasyHardware.ExMotor;
import org.firstinspires.ftc.teamcode.EasyHardware.ExServo;

@TeleOp
public class Drive2026 extends OpMode {
    //~~~~~~~~~~~~~~~~~create objects~~~~~~~~~~~~~~~~~
    DistanceSensor ballDetectorFront;
    Chassis chassis;
    ExServo gate;
    ExMotor intake;
    ExMotor shootMotor;

    //~~~~~~~~~~~~~~~~~~create variables~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    double launchTargetSpeed = 0;
    double humanDriveSpeed = 0.5d;

    @Override
    public void init() { //~~~~~~~initialization code~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        this.chassis = new Chassis(this); //~~~~~~~~~assign chassis object to chassis class~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        this.shootMotor = new ExMotor("shoot_motor", this);  //~~~~~~~~~assign shootMotor object to ExMotor class~~~~~~~~~~~~~~~~~~~
        this.shootMotor.ZeroPowerCoast(); //~~~~~~~~~make it so that the shoot motor does not break, but rather coasts~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        this.intake = new ExMotor("intake", this); //~~~~~~~~~assign intake object to ExMotor class~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        this.gate = new ExServo("flip_servo", this, 2); //~~~~~~~~~assign gate object to servo class, type simple~~~~~~~~~

        this.ballDetectorFront = this.hardwareMap.get(DistanceSensor.class, "ball_detector_front"); //~assign ballDetectorFront to distance sensor class~

        this.telemetry.addData("Status", "Initialized"); //~~~~~~~~~~~~~add the status to telemetry class~~~~~~~~~~~~~
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

        if (this.gamepad2.dpad_up) launchTargetSpeed = -1600;
        else if (this.gamepad2.dpad_left) launchTargetSpeed = -1700;
        else if (this.gamepad2.dpad_right) launchTargetSpeed = -1500;
        else launchTargetSpeed = 0;

        this.shootMotor.setVelocity(launchTargetSpeed);

        telemetry.addData("Cur. Velocity", this.shootMotor.getVelocity());

        if (this.gamepad2.right_trigger > 0.2) {
            this.gate.setPosition(-0.8);
        } else {
            this.gate.setPosition(0.8);
        }

        telemetry.addData("DS", ballDetectorFront.getDistance(DistanceUnit.CM));

        if (gamepad2.y || gamepad2.b) {
            if (gamepad2.right_trigger < 0.2) this.intake.setPower(1.0d);
            else if ((Math.abs(this.shootMotor.getVelocity()) >= launchTargetSpeed + 100) && !(launchTargetSpeed == 0) && gamepad2.right_trigger > 0.2) this.intake.setPower(1.0d);
            else this.intake.setPower(0);
        } else if (this.gamepad2.dpad_down) {
            this.intake.setPower(-0.5d);
        }
        else if (this.gamepad2.left_trigger > 0.2){
            intake.setPower(0.7);
        } else {
            this.intake.setPower(0.0d);
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
