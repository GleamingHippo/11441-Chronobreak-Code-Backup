package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.ExLibrary.Chassis;
import org.firstinspires.ftc.teamcode.ExLibrary.ComputerVisonDecode;
import org.firstinspires.ftc.teamcode.ExLibrary.ExMotor;
import org.firstinspires.ftc.teamcode.ExLibrary.ExServo;
import org.firstinspires.ftc.vision.opencv.ColorBlobLocatorProcessor;

@TeleOp
public class Drive2026 extends OpMode {
    DistanceSensor ballDetectorBack;
    DistanceSensor ballDetectorFront;
    Chassis chassis;
    ColorBlobLocatorProcessor colorLocatorGreen;
    ColorBlobLocatorProcessor colorLocatorPurple;
    ExServo flipServo;
    ExMotor intake;
    ExMotor motorTwo;
    ExMotor shootMotor;
    ComputerVisonDecode vision;
    double computerDriveTurn = 0.0d;
    double computerDriveSpeed = 0.5d;
    double humanDriveSpeed = 0.5d;
    boolean flipperUp = false;
    boolean computerVisonOn = false;
    int closestBallx = 0;

    @Override // com.qualcomm.robotcore.eventloop.opmode.OpMode
    public void init() {

        this.chassis = new Chassis(this);
        this.vision = new ComputerVisonDecode(this.hardwareMap, this.chassis);
        this.vision.computerVisonSetup(true);

        this.shootMotor = new ExMotor("shoot_motor", this);
        this.intake = new ExMotor("intake", this);

        this.flipServo = new ExServo("flip_servo", this, 2);

        this.ballDetectorFront = (DistanceSensor) this.hardwareMap.get(DistanceSensor.class, "ball_detector_front");
        this.ballDetectorBack = (DistanceSensor) this.hardwareMap.get(DistanceSensor.class, "ball_detector_back");

        this.telemetry.addData("Status", "Initialized");
        this.telemetry.update();
    }

    @Override // com.qualcomm.robotcore.eventloop.opmode.OpMode
    public void loop() {

        if (this.gamepad1.left_trigger > 0.2d) {
            this.computerDriveTurn = this.vision.alignToArtifactMauel();
        } else {
            this.computerDriveTurn = 0.0d;
        }

        if (this.gamepad1.right_stick_x > 0.05d && this.gamepad1.left_trigger <= 0.2d) {
            this.humanDriveSpeed = 0.7d;
        } else if (this.gamepad1.right_trigger > 0.2d) {
            this.humanDriveSpeed = 0.3d;
        } else if (this.gamepad1.left_trigger > 0.2d) {
            this.humanDriveSpeed = 1.0d;
        } else {
            this.humanDriveSpeed = 0.7d;
        }

        this.chassis.driveDirection(-this.gamepad1.left_stick_x, this.gamepad1.left_stick_y, this.humanDriveSpeed, ((double) this.gamepad1.right_stick_x) + this.computerDriveTurn);

        if (this.gamepad2.left_trigger > 0.2d) {
            this.shootMotor.setVelocity(-5000);
        } else {
            this.shootMotor.setVelocity(0);
        }

        if (this.gamepad2.right_trigger > 0.2) {
            this.flipServo.setPosition(1.0);
            this.flipperUp = true;
        } else {
            this.flipServo.setPosition(-0.8);
            this.flipperUp = false;
        }

        if ((this.ballDetectorBack.getDistance(DistanceUnit.CM) > 5.0d && this.gamepad2.y && !this.flipperUp) || this.gamepad2.b) {
            this.intake.setPower(1.0d);
        } else if (this.gamepad2.dpad_down) {
            this.intake.setPower(-0.5d);
        } else {
            this.intake.setPower(0.0d);
        }

        this.telemetry.update();
    }
    @Override // com.qualcomm.robotcore.eventloop.opmode.OpMode
    public void stop() {
        this.telemetry.addData("Status", "Stopped");
        this.telemetry.update();
    }
}