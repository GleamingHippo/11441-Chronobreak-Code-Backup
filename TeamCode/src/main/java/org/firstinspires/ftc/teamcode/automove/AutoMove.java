package org.firstinspires.ftc.teamcode.automove;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


import org.firstinspires.ftc.teamcode.ExLibrary.OdometryPod;

import org.firstinspires.ftc.teamcode.EasyHardware.Chassis;

public class AutoMove{

    public OdometryPod odo;

    double goalX = 0;
    double goalY = 0;
    double goalAngle = 0;

    double startX;
    double startY;
    double startA;

    LinearOpMode opmode;

    public Chassis chassis;

    public AutoMove(LinearOpMode opmode, String name,double startX, double startY, double startA){
        this.odo = new OdometryPod(opmode,name);
        this.startX = 0;
        this.startY = 0;
        this.startA = 0;
        chassis = new Chassis(opmode);

        this.opmode = opmode;


    }

    public boolean goToPos(double x, double y){
        this.goalX = x;
        this.goalY = y;
        this.odo.update();
        if(Math.abs(x-(this.odo.getX()+this.startX)) + Math.abs(y-(this.odo.getY()+this.startY)) < .75){
            return true;
        }

        return false;
    }

    public boolean goToRotation(double a){
        this.goalAngle = a;
        if(Math.abs(this.odo.getHeadingDeg() - a) < 5){
            return true;
        }
        return false;
    }

    //updates wheel rotations.
    //Run once per mainloop
    public void motorUpdate(){
        this.odo.update();
        double yoff = -(this.goalY - (this.odo.getY()+this.startY) );
        double xoff = this.goalX - (this.odo.getX()+this.startX);
        double m = Math.sqrt(xoff*xoff+yoff*yoff);


        //

        double a = Math.atan2(xoff,yoff);

        a -= this.odo.getHeadingDeg()/360 * 2 * Math.PI;

        double s = .25+ //minimum speed? though it looks like it would constatly add .15
                (Math.abs(2*xoff)+Math.abs(4*yoff)// works with the offets, if i had to guess is centering the robot
                        +Math.abs((this.goalAngle-this.odo.getHeadingDeg())/2)//finds how it needs to turn
                ) / 150;

        if(s > 1){
            s = 1;
        }
        opmode.telemetry.addData("aOffset",yoff);

        xoff = Math.sin(-a)*m;
        yoff = Math.cos(-a)*m;




        //a s rot
        this.chassis.driveDirection(
                -(yoff),xoff, //offsets for the optometry pods to the pinpoint computer
                s, //tones down the speed
                (this.goalAngle-this.odo.getHeadingDeg())/-5);
    }

}