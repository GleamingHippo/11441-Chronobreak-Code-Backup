/*
Note by Holden.
This was made very quickly and is mostly pieced together
example code. I understand 70% of it. If you have problems


*/



package org.firstinspires.ftc.teamcode.ExLibrary;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.UnnormalizedAngleUnit;

import java.util.Locale;

public class OdometryPod {

    private final LinearOpMode opMode;
    private final GoBildaPinpointDriver odo;

    private double lastUpdateTime = 0;

    // ---- Default Offsets in inches
    private static final double DEFAULT_X_OFFSET_IN = -1;
    private static final double DEFAULT_Y_OFFSET_IN = -5;

    // Constructor with full default initialization
    public OdometryPod(LinearOpMode opMode, String hardwareName) {
        this.opMode = opMode;

        this.odo = opMode.hardwareMap.get(GoBildaPinpointDriver.class, hardwareName);

        // Apply defaults
        setOffsets(DEFAULT_X_OFFSET_IN, DEFAULT_Y_OFFSET_IN);
        setPodType(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.REVERSED,
                GoBildaPinpointDriver.EncoderDirection.FORWARD);

        // Reset & calibrate IMU and pose
        resetPosAndIMU();
    }

    // Setup Methods

    /** Set odometry pod offsets from robot center (inches) */
    public void setOffsets(double xIn, double yIn) {
        odo.setOffsets(xIn, yIn, DistanceUnit.INCH);
    }

    /** Set the type of pod used */
    public void setPodType(GoBildaPinpointDriver.GoBildaOdometryPods podType) {
        odo.setEncoderResolution(podType);
    }

    /** Custom resolution in ticks per inch */
    public void setCustomResolution(double ticksPerInch) {
        odo.setEncoderResolution(ticksPerInch, DistanceUnit.INCH);
    }

    /** Set encoder directions for X and Y pods */
    public void setEncoderDirections(GoBildaPinpointDriver.EncoderDirection xDir,
                                     GoBildaPinpointDriver.EncoderDirection yDir) {
        odo.setEncoderDirections(xDir, yDir);
    }

    /** Reset full position and recalibrate IMU */
    public void resetPosAndIMU() {
        odo.resetPosAndIMU();
    }

    /** Recalibrate IMU only */
    public void recalibrateIMU() {
        odo.recalibrateIMU();
    }


    // Data Retrieval

    /** Update all Pinpoint data */
    public void update() {
        odo.update();
    }

    /** Update heading only (lower I²C load) */
    public void updateHeadingOnly() {
        odo.update(GoBildaPinpointDriver.ReadData.ONLY_UPDATE_HEADING);
    }

    // Position Data (Inches + Degrees)

    public Pose2D getPose() {
        return odo.getPosition();
    }

    public double getX() {
        this.opMode.telemetry.addData("x:",odo.getPosition().getX(DistanceUnit.INCH));
        return odo.getPosition().getX(DistanceUnit.INCH);
    }

    public double getY() {
        this.opMode.telemetry.addData("y:",odo.getPosition().getY(DistanceUnit.INCH));
        return odo.getPosition().getY(DistanceUnit.INCH);
    }

    public double getHeadingDeg() {
        return odo.getHeading(AngleUnit.DEGREES);
    }

    // Velocities (Inches/sec + Degrees/sec)

    public double getXVel() {
        return odo.getVelX(DistanceUnit.INCH);
    }

    public double getYVel() {
        return odo.getVelY(DistanceUnit.INCH);
    }

    public double getHeadingVelDeg() {
        return odo.getHeadingVelocity(UnnormalizedAngleUnit.DEGREES);
    }

    // Diagnostics

    public GoBildaPinpointDriver.DeviceStatus getStatus() {
        return odo.getDeviceStatus();
    }

    public double getPinpointFrequency() {
        return odo.getFrequency();
    }

    /** Returns Hub loop frequency based on delta time */
    public double getHubFrequency(double runtime) {
        double cycle = runtime - lastUpdateTime;
        lastUpdateTime = runtime;
        return 1.0 / cycle;
    }

    public double getXOffsetInches() {
        return odo.getXOffset(DistanceUnit.INCH);
    }

    public double getYOffsetInches() {
        return odo.getYOffset(DistanceUnit.INCH);
    }

    public double getYawScalar() {
        return odo.getYawScalar();
    }

    public void setPos(double x, double y){
        odo.setPosX(x,DistanceUnit.INCH);
        odo.setPosY(y,DistanceUnit.INCH);
    }

}