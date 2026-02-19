package org.firstinspires.ftc.teamcode.ExLibrary;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/* JADX INFO: loaded from: classes7.dex */
public class AutoMove {
    Chassis chassis;
    OdometryPod odo;
    LinearOpMode opmode;
    double startA;
    double startX;
    double startY;
    double goalX = 0.0d;
    double goalY = 0.0d;
    double goalAngle = 0.0d;

    public AutoMove(LinearOpMode opmode, String name, double startX, double startY, double startA) {
        this.odo = new OdometryPod(opmode, name);
        this.startX = startX;
        this.startY = startY;
        this.startA = startA;
        this.chassis = new Chassis(opmode);
        this.opmode = opmode;
    }

    public boolean goToPos(double x, double y) {
        this.goalX = x;
        this.goalY = y;
        this.odo.update();
        if (Math.abs(x - (this.odo.getX() + this.startX)) + Math.abs(y - (this.odo.getY() + this.startY)) < 0.75d) {
            return true;
        }
        return false;
    }

    public boolean goToRotation(double a) {
        this.goalAngle = a;
        if (Math.abs(this.odo.getHeadingDeg() - a) < 5.0d) {
            return true;
        }
        return false;
    }

    public void motorUpdate() {
        this.odo.update();
        double yoff = -(this.goalY - (this.odo.getY() + this.startY));
        double xoff = this.goalX - (this.odo.getX() + this.startX);
        double m = Math.sqrt((xoff * xoff) + (yoff * yoff));
        double a = Math.atan2(xoff, yoff) - (((this.odo.getHeadingDeg() / 360.0d) * 2.0d) * 3.141592653589793d);
        this.opmode.telemetry.addData("aOffset", Double.valueOf(yoff));
        double xoff2 = Math.sin(-a) * m;
        double yoff2 = Math.cos(-a) * m;
        this.chassis.driveDirection(yoff2, xoff2, (((Math.abs(2.0d * xoff2) + Math.abs(4.0d * yoff2)) + Math.abs((this.goalAngle - this.odo.getHeadingDeg()) / 0.8d)) / 50.0d) + 0.15d, (this.goalAngle - this.odo.getHeadingDeg()) / 0.5d);
    }
}