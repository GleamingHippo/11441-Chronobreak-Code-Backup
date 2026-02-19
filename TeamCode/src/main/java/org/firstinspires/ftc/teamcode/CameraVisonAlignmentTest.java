//package org.firstinspires.ftc.teamcode;
//
//import android.util.Size;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.sun.tools.doclint.DocLint;
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.Iterator;
//import java.util.List;
//import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;
//import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//import org.firstinspires.ftc.teamcode.ExLibrary.Chassis;
//import org.firstinspires.ftc.vision.VisionPortal;
//import org.firstinspires.ftc.vision.opencv.ColorBlobLocatorProcessor;
//import org.firstinspires.ftc.vision.opencv.ColorRange;
//import org.firstinspires.ftc.vision.opencv.ImageRegion;
//import org.opencv.core.RotatedRect;
//
///* JADX INFO: loaded from: classes7.dex */
//@Autonomous(group = "Autonomous", name = "CameraVisonAlignmentTest")
//public class CameraVisonAlignmentTest extends LinearOpMode {
//    Chassis chassis;
//
//    @Override // com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
//    public void runOpMode() throws InterruptedException {
//        ColorBlobLocatorProcessor colorLocatorGreen;
//        ColorBlobLocatorProcessor colorLocatorPurple;
//        int closestBallx;
//        this.chassis = new Chassis(this);
//        ColorBlobLocatorProcessor colorLocatorGreen2 = null;
//        ColorBlobLocatorProcessor colorLocatorPurple2 = null;
//        int closestBallx2 = 0;
//        int closestGreenBallx = 0;
//        int closestPurpleBallx = 0;
//        if (1 != 0) {
//            colorLocatorGreen2 = new ColorBlobLocatorProcessor.Builder().setTargetColorRange(ColorRange.ARTIFACT_GREEN).setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY).setRoi(ImageRegion.asUnityCenterCoordinates(-1.0d, 0.3d, 1.0d, -1.0d)).setDrawContours(true).setBlurSize(5).build();
//            colorLocatorPurple2 = new ColorBlobLocatorProcessor.Builder().setTargetColorRange(ColorRange.ARTIFACT_PURPLE).setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY).setRoi(ImageRegion.asUnityCenterCoordinates(-1.0d, 0.3d, 1.0d, -1.0d)).setDrawContours(true).setBlurSize(5).build();
//            new VisionPortal.Builder().addProcessor(colorLocatorGreen2).addProcessor(colorLocatorPurple2).setCameraResolution(new Size(960, 720)).setCamera((CameraName) this.hardwareMap.get(WebcamName.class, "Webcam 1")).build();
//        }
//        this.telemetry.addData("Status", "Initialized");
//        this.telemetry.update();
//        waitForStart();
//        while (opModeIsActive()) {
//            if (1 == 0) {
//                colorLocatorGreen = colorLocatorGreen2;
//                colorLocatorPurple = colorLocatorPurple2;
//            } else {
//                this.telemetry.addData("Purple count", Integer.valueOf(colorLocatorPurple2.getBlobs().size()));
//                this.telemetry.addData("Green count", Integer.valueOf(colorLocatorGreen2.getBlobs().size()));
//                List<ColorBlobLocatorProcessor.Blob> blobsGreen = new ArrayList<>(colorLocatorGreen2.getBlobs());
//                List<ColorBlobLocatorProcessor.Blob> blobsPurple = new ArrayList<>(colorLocatorPurple2.getBlobs());
//                ColorBlobLocatorProcessor.Util.filterByCriteria(ColorBlobLocatorProcessor.BlobCriteria.BY_CONTOUR_AREA, 1200.0d, 20000.0d, blobsPurple);
//                ColorBlobLocatorProcessor.Util.filterByCriteria(ColorBlobLocatorProcessor.BlobCriteria.BY_CONTOUR_AREA, 1200.0d, 20000.0d, blobsGreen);
//                blobsGreen.sort(new Comparator() { // from class: org.firstinspires.ftc.teamcode.CameraVisonAlignmentTest$$ExternalSyntheticLambda0
//                    @Override // java.util.Comparator
//                    public final int compare(Object obj, Object obj2) {
//                        return Double.compare(((ColorBlobLocatorProcessor.Blob) obj2).getBoxFit().center.y, ((ColorBlobLocatorProcessor.Blob) obj).getBoxFit().center.y);
//                    }
//                });
//                blobsPurple.sort(new Comparator() { // from class: org.firstinspires.ftc.teamcode.CameraVisonAlignmentTest$$ExternalSyntheticLambda1
//                    @Override // java.util.Comparator
//                    public final int compare(Object obj, Object obj2) {
//                        return Double.compare(((ColorBlobLocatorProcessor.Blob) obj2).getBoxFit().center.y, ((ColorBlobLocatorProcessor.Blob) obj).getBoxFit().center.y);
//                    }
//                });
//                int closestGreenBally = -1;
//                int closestPurpleBally = -1;
//                Iterator<ColorBlobLocatorProcessor.Blob> it = blobsGreen.iterator();
//                if (!it.hasNext()) {
//                    colorLocatorGreen = colorLocatorGreen2;
//                    colorLocatorPurple = colorLocatorPurple2;
//                } else {
//                    ColorBlobLocatorProcessor.Blob b = it.next();
//                    RotatedRect boxFit = b.getBoxFit();
//                    colorLocatorGreen = colorLocatorGreen2;
//                    colorLocatorPurple = colorLocatorPurple2;
//                    this.telemetry.addData("Ball Cords Green", String.valueOf((int) boxFit.center.x) + DocLint.TAGS_SEPARATOR + String.valueOf((int) boxFit.center.y));
//                    closestGreenBallx = (int) boxFit.center.x;
//                    closestGreenBally = (int) boxFit.center.y;
//                }
//                Iterator<ColorBlobLocatorProcessor.Blob> it2 = blobsPurple.iterator();
//                if (it2.hasNext()) {
//                    ColorBlobLocatorProcessor.Blob b2 = it2.next();
//                    RotatedRect boxFit2 = b2.getBoxFit();
//                    this.telemetry.addData("Ball Cords Purple", String.valueOf((int) boxFit2.center.x) + DocLint.TAGS_SEPARATOR + String.valueOf((int) boxFit2.center.y));
//                    closestPurpleBallx = (int) boxFit2.center.x;
//                    closestPurpleBally = (int) boxFit2.center.y;
//                }
//                if (closestGreenBally == -1) {
//                    if (closestPurpleBally == -1) {
//                        closestBallx = -1;
//                    } else {
//                        closestBallx = closestPurpleBallx;
//                    }
//                } else if (closestPurpleBally == -1) {
//                    if (closestGreenBally == -1) {
//                        closestBallx = -1;
//                    } else {
//                        closestBallx = closestGreenBallx;
//                    }
//                } else if (closestGreenBally > closestPurpleBally) {
//                    closestBallx = closestGreenBallx;
//                } else {
//                    closestBallx = closestPurpleBallx;
//                }
//                this.telemetry.addData("Closest Ball", Integer.valueOf(closestBallx));
//                closestBallx2 = closestBallx;
//            }
//            if (closestBallx2 == -1) {
//                this.chassis.driveDirection(0.0d, 0.0d, 0.0d, 0.0d);
//            } else if (closestBallx2 > 600) {
//                this.chassis.driveDirection(0.0d, 0.0d, 0.5d, 1.0d);
//            } else if (closestBallx2 < 300) {
//                this.chassis.driveDirection(0.0d, 0.0d, 0.5d, -1.0d);
//            } else {
//                this.chassis.driveDirection(0.0d, 0.0d, 0.0d, 0.0d);
//            }
//            this.telemetry.addData("Status", "Running");
//            this.telemetry.update();
//            colorLocatorGreen2 = colorLocatorGreen;
//            colorLocatorPurple2 = colorLocatorPurple;
//        }
//    }
//}