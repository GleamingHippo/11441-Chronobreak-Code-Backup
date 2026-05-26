package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
@TeleOp
public class LimelightVisontest extends LinearOpMode {

    private Limelight3A limelight;
    double distanceToApriltag;

    @Override
    public void runOpMode() throws InterruptedException
    {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");

        telemetry.setMsTransmissionInterval(11);

        limelight.pipelineSwitch(0);
        waitForStart();
        /*
         * Starts polling for data.
         */
        limelight.start();

        while (opModeIsActive()) {
            LLResult result = limelight.getLatestResult();
            if (result != null) {
                if (result.isValid()) {
                    Pose3D botpose = result.getBotpose();
//                    distanceToApriltag = tagDistance(result.getTa());
                    telemetry.addData("Tag Distance", distanceToApriltag);
                    telemetry.addData("Target X", result.getTx());
                    telemetry.addData("Target Area", result.getTa());
                    telemetry.addData("Botpose", botpose.toString());
                    telemetry.addData("Staus", "Apriltag acquired");
                }
                else telemetry.addData("Staus", "No target");
            }
            else telemetry.addData("Staus", "Null");
            telemetry.update();
        }
    }
}