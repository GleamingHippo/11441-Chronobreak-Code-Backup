package org.firstinspires.ftc.teamcode.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/* JADX INFO: loaded from: classes6.dex */
public class HardwareMapAll {
    LinearOpMode myOpMode;
    DcMotor frontLeftMotor = null;
    DcMotor backLeftMotor = null;
    DcMotor frontRightMotor = null;
    DcMotor backRightMotor = null;

    public HardwareMapAll(LinearOpMode opmode) {
        this.myOpMode = opmode;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v2 ??, still in use, count: 1, list:
          (r0v2 ?? I:??[OBJECT, ARRAY]) from 0x000c: CHECK_CAST (r0v3 ?? I:com.qualcomm.robotcore.hardware.DcMotor) = (com.qualcomm.robotcore.hardware.DcMotor) (r0v2 ?? I:??[OBJECT, ARRAY])
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1123)
        	at jadx.core.dex.visitors.ConstructorVisitor.processInvoke(ConstructorVisitor.java:110)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:56)
        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:42)
        */
    public void init() {
        /*
            r3 = this;
            com.qualcomm.robotcore.eventloop.opmode.LinearOpMode r0 = r3.myOpMode
            com.qualcomm.robotcore.hardware.HardwareMap r0 = r0.hardwareMap
            java.lang.Class<com.qualcomm.robotcore.hardware.DcMotor> r1 = com.qualcomm.robotcore.hardware.DcMotor.class
            java.lang.String r2 = "left_front"
            void r0 = r0.<init>()
            com.qualcomm.robotcore.hardware.DcMotor r0 = (com.qualcomm.robotcore.hardware.DcMotor) r0
            r3.frontLeftMotor = r0
            com.qualcomm.robotcore.eventloop.opmode.LinearOpMode r0 = r3.myOpMode
            com.qualcomm.robotcore.hardware.HardwareMap r0 = r0.hardwareMap
            java.lang.Class<com.qualcomm.robotcore.hardware.DcMotor> r1 = com.qualcomm.robotcore.hardware.DcMotor.class
            java.lang.String r2 = "right_front"
            void r0 = r0.<init>()
            com.qualcomm.robotcore.hardware.DcMotor r0 = (com.qualcomm.robotcore.hardware.DcMotor) r0
            r3.frontRightMotor = r0
            com.qualcomm.robotcore.eventloop.opmode.LinearOpMode r0 = r3.myOpMode
            com.qualcomm.robotcore.hardware.HardwareMap r0 = r0.hardwareMap
            java.lang.Class<com.qualcomm.robotcore.hardware.DcMotor> r1 = com.qualcomm.robotcore.hardware.DcMotor.class
            java.lang.String r2 = "left_back"
            void r0 = r0.<init>()
            com.qualcomm.robotcore.hardware.DcMotor r0 = (com.qualcomm.robotcore.hardware.DcMotor) r0
            r3.backLeftMotor = r0
            com.qualcomm.robotcore.eventloop.opmode.LinearOpMode r0 = r3.myOpMode
            com.qualcomm.robotcore.hardware.HardwareMap r0 = r0.hardwareMap
            java.lang.Class<com.qualcomm.robotcore.hardware.DcMotor> r1 = com.qualcomm.robotcore.hardware.DcMotor.class
            java.lang.String r2 = "right_back"
            void r0 = r0.<init>()
            com.qualcomm.robotcore.hardware.DcMotor r0 = (com.qualcomm.robotcore.hardware.DcMotor) r0
            r3.backRightMotor = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.firstinspires.ftc.teamcode.teamcode.HardwareMapAll.init():void");
    }
}