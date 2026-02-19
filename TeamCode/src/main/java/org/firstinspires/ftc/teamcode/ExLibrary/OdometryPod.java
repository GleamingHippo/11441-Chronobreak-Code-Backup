package org.firstinspires.ftc.teamcode.ExLibrary;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

/* JADX INFO: loaded from: classes7.dex */
public class OdometryPod {
    private static final double DEFAULT_X_OFFSET_IN = 0.0d;
    private static final double DEFAULT_Y_OFFSET_IN = -2.0d;
    private double lastUpdateTime = 0.0d;
    private final GoBildaPinpointDriver odo;
    private final LinearOpMode opMode;

    public OdometryPod(LinearOpMode opMode, String hardwareName) {
        this.opMode = opMode;
        this.odo = (GoBildaPinpointDriver) opMode.hardwareMap.get(GoBildaPinpointDriver.class, hardwareName);
        setOffsets(0.0d, DEFAULT_Y_OFFSET_IN);
        setPodType(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.REVERSED, GoBildaPinpointDriver.EncoderDirection.REVERSED);
        resetPosAndIMU();
    }

    public void setOffsets(double xIn, double yIn) {
        this.odo.setOffsets(xIn, yIn, DistanceUnit.INCH);
    }

    public void setPodType(GoBildaPinpointDriver.GoBildaOdometryPods podType) {
        this.odo.setEncoderResolution(podType);
    }

    public void setCustomResolution(double ticksPerInch) {
        this.odo.setEncoderResolution(ticksPerInch, DistanceUnit.INCH);
    }

    public void setEncoderDirections(GoBildaPinpointDriver.EncoderDirection xDir, GoBildaPinpointDriver.EncoderDirection yDir) {
        this.odo.setEncoderDirections(xDir, yDir);
    }

    public void resetPosAndIMU() {
        this.odo.resetPosAndIMU();
    }

    public void recalibrateIMU() {
        this.odo.recalibrateIMU();
    }

    public void update() {
        this.odo.update();
    }

    public void updateHeadingOnly() {
        this.odo.update(GoBildaPinpointDriver.ReadData.ONLY_UPDATE_HEADING);
    }

    public Pose2D getPose() {
        return this.odo.getPosition();
    }

    public double getX() {
        this.opMode.telemetry.addData("x:", Double.valueOf(this.odo.getPosition().getX(DistanceUnit.INCH)));
        return this.odo.getPosition().getX(DistanceUnit.INCH);
    }

    public double getY() {
        this.opMode.telemetry.addData("y:", Double.valueOf(this.odo.getPosition().getY(DistanceUnit.INCH)));
        return this.odo.getPosition().getY(DistanceUnit.INCH);
    }

    public double getHeadingDeg() {
        return this.odo.getHeading(AngleUnit.DEGREES);
    }

    public double getXVel() {
        return this.odo.getVelX(DistanceUnit.INCH);
    }

    public double getYVel() {
        return this.odo.getVelY(DistanceUnit.INCH);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 ??, still in use, count: 1, list:
          (r0v1 ?? I:double) from 0x0008: RETURN (r0v1 ?? I:double)
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1123)
        	at jadx.core.dex.visitors.ConstructorVisitor.processInvoke(ConstructorVisitor.java:110)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:56)
        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:42)
        */
    public double getHeadingVelDeg() {
        /*
            r2 = this;
            com.qualcomm.hardware.gobilda.GoBildaPinpointDriver r0 = r2.odo
            org.firstinspires.ftc.robotcore.external.navigation.UnnormalizedAngleUnit r1 = org.firstinspires.ftc.robotcore.external.navigation.UnnormalizedAngleUnit.DEGREES
            void r0 = r0.<init>(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.firstinspires.ftc.teamcode.ExLibrary.OdometryPod.getHeadingVelDeg():double");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 ??, still in use, count: 1, list:
          (r0v1 ?? I:com.qualcomm.hardware.gobilda.GoBildaPinpointDriver$DeviceStatus) from 0x0006: RETURN (r0v1 ?? I:com.qualcomm.hardware.gobilda.GoBildaPinpointDriver$DeviceStatus)
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1123)
        	at jadx.core.dex.visitors.ConstructorVisitor.processInvoke(ConstructorVisitor.java:110)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:56)
        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:42)
        */
    public com.qualcomm.hardware.gobilda.GoBildaPinpointDriver.DeviceStatus getStatus() {
        /*
            r1 = this;
            com.qualcomm.hardware.gobilda.GoBildaPinpointDriver r0 = r1.odo
            void r0 = r0.<init>()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.firstinspires.ftc.teamcode.ExLibrary.OdometryPod.getStatus():com.qualcomm.hardware.gobilda.GoBildaPinpointDriver$DeviceStatus");
    }

    public double getPinpointFrequency() {
        return this.odo.getFrequency();
    }

    public double getHubFrequency(double runtime) {
        double cycle = runtime - this.lastUpdateTime;
        this.lastUpdateTime = runtime;
        return 1.0d / cycle;
    }

    public double getXOffsetInches() {
        return this.odo.getXOffset(DistanceUnit.INCH);
    }

    public double getYOffsetInches() {
        return this.odo.getYOffset(DistanceUnit.INCH);
    }

    public double getYawScalar() {
        return this.odo.getYawScalar();
    }
}