package org.firstinspires.ftc.teamcode.ExLibrary;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.EasyHardware.Chassis;

/* JADX INFO: loaded from: classes7.dex */
public class BasicOdo {
    OpMode opmode;
    OdometryPod odo;
    Chassis chassis;

    public BasicOdo(LinearOpMode opmode) {
        this.opmode = opmode;
        odo = new OdometryPod(opmode, "odo");
        chassis = new Chassis(opmode);
    }

    public void OdoDriveBackwards(double distance, double speed, boolean opModeActive) {
        odo.update();
        odo.resetPosAndIMU();
        while ((odo.getX() < distance) && opModeActive) {
            chassis.driveDirection(0, 1, speed, 0);
            odo.update();

        }

    }
    public void OdoDriveForwards(double distance, double speed, boolean opModeActive) {
        odo.update();
        odo.resetPosAndIMU();
        while ((odo.getX() > distance) && opModeActive) {
            chassis.driveDirection(0, -1, speed, 0);
            odo.update();

        }
    }
    public void OdoStrafeLeft(double distance, double speed, boolean opModeActive) {
        odo.update();
        odo.resetPosAndIMU();
        while ((odo.getY() < distance) && opModeActive) {
            chassis.driveDirection(1, 0, speed, 0);
            odo.update();

        }
    }
    public void OdoStrafeRight(double distace, double speed, boolean opModeActive) {
        this.odo.update();
        odo.resetPosAndIMU();
        while ((odo.getY() > distace) && opModeActive) {
            chassis.driveDirection(-1, 0, speed, 0);
            odo.update();
        }
    }
}