package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.teamcode.ExLibrary.Chassis;
import org.firstinspires.ftc.teamcode.ExLibrary.ComputerVisonDecode;
import org.firstinspires.ftc.teamcode.ExLibrary.ExMotor;
import org.firstinspires.ftc.teamcode.ExLibrary.ExServo;

/* JADX INFO: loaded from: classes7.dex */
@Autonomous(group = "Autonomous", name = "Autonomosus2025")
public class Autonomosus2025 extends LinearOpMode {
    DistanceSensor ballDetectorBack;
    DistanceSensor ballDetectorFront;
    private Chassis chassis;
    ExServo flipServo;
    ExMotor intake;
    ExMotor shootMotor;
    private ComputerVisonDecode vision;

    @Override // com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
    public void runOpMode() throws InterruptedException {
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

        waitForStart();
        if (opModeIsActive()) {

        }
    }
}

/*

            old auto code

            this.chassis.driveDirection(0.0d, 1.0d, 0.5d, 0.0d);
            sleep(1500L);

            this.chassis.driveDirection(0.0d, 0.0d, 0.0d, 0.0d);

            this.shootMotor.setPower(-0.9d);
            sleep(2000L);
            this.intake.setPower(-1.0d);
            sleep(400L);
            this.intake.setPower(0.0d);
            sleep(100L);
            this.flipServo.setPosition(-1.0d);
            sleep(1100L);
            this.flipServo.setPosition(1.0d);
            sleep(600L);
            this.intake.setPower(-1.0d);
            sleep(900L);
            sleep(100L);
            this.intake.setPower(0.0d);
            sleep(100L);
            this.flipServo.setPosition(-1.0d);
            sleep(1000L);
            this.flipServo.setPosition(1.0d);
            sleep(600L);
            this.intake.setPower(-1.0d);
            sleep(1000L);
            sleep(100L);
            this.intake.setPower(0.0d);
            sleep(100L);
            this.flipServo.setPosition(-1.0d);
            sleep(1000L);
            this.flipServo.setPosition(1.0d);
            sleep(600L);
            this.intake.setPower(-1.0d);
            sleep(1000L);
            sleep(100L);
            this.intake.setPower(0.0d);
            sleep(100L);
            this.flipServo.setPosition(-1.0d);
            sleep(1000L);
            this.flipServo.setPosition(1.0d);
            sleep(600L);
            this.intake.setPower(-1.0d);
            sleep(1000L);
            this.shootMotor.setPower(0.0d);

            this.chassis.driveDirection(0.0d, 0.0d, 1.0d, 1.0d);
            sleep(430L);
            this.chassis.driveDirection(0.0d, 0.0d, 0.0d, 0.0d);
            this.intake.setPower(-1.0d);
            this.chassis.driveDirection(0.0d, -1.0d, 0.3d, 0.0d);
            sleep(3000L);
            this.intake.setPower(0.5d);
            this.chassis.driveDirection(0.0d, 1.0d, 0.5d, 0.0d);
            sleep(400L);
            this.intake.setPower(0.0d);
            sleep(1500L);
            this.chassis.driveDirection(0.0d, 0.0d, 1.0d, -1.0d);
            sleep(430L);
            this.chassis.driveDirection(0.0d, 0.0d, 0.0d, 0.0d);

            this.shootMotor.setPower(-0.9d);
            sleep(2000L);
            this.intake.setPower(-1.0d);
            sleep(400L);
            this.intake.setPower(0.0d);
            sleep(100L);
            this.flipServo.setPosition(-1.0d);
            sleep(1100L);
            this.flipServo.setPosition(1.0d);
            sleep(600L);
            this.intake.setPower(-1.0d);
            sleep(900L);
            sleep(100L);
            this.intake.setPower(0.0d);
            sleep(100L);
            this.flipServo.setPosition(-1.0d);
            sleep(1000L);
            this.flipServo.setPosition(1.0d);
            sleep(600L);
            this.intake.setPower(-1.0d);
            sleep(1000L);
            sleep(100L);
            this.intake.setPower(0.0d);
            sleep(100L);
            this.flipServo.setPosition(-1.0d);
            sleep(1000L);
            this.flipServo.setPosition(1.0d);
            sleep(600L);
            this.intake.setPower(-1.0d);
            sleep(1000L);
            sleep(100L);
            this.intake.setPower(0.0d);
            sleep(100L);
            this.flipServo.setPosition(-1.0d);
            sleep(1000L);
            this.flipServo.setPosition(1.0d);
            sleep(600L);
            this.intake.setPower(-1.0d);
            sleep(1000L);
            this.shootMotor.setPower(0.0d);
 */