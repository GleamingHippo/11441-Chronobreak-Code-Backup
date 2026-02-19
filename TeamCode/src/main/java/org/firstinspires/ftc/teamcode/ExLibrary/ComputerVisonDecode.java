package org.firstinspires.ftc.teamcode.ExLibrary;

import android.util.Size;
import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.opencv.ColorBlobLocatorProcessor;
import org.firstinspires.ftc.vision.opencv.ColorRange;
import org.firstinspires.ftc.vision.opencv.ImageRegion;
import org.opencv.core.RotatedRect;

/* JADX INFO: loaded from: classes7.dex */
public class ComputerVisonDecode {
    private final Chassis chassis;
    int closestBallx;
    int closestGreenBallx;
    int closestGreenBally;
    int closestPurpleBallx;
    int closestPurpleBally;
    private ColorBlobLocatorProcessor colorLocatorGreen;
    private ColorBlobLocatorProcessor colorLocatorPurple;
    private final HardwareMap hardwareMap;
    private VisionPortal portal;

    public ComputerVisonDecode(HardwareMap hardwareMap, Chassis chassis) {
        this.hardwareMap = hardwareMap;
        this.chassis = chassis;
    }

    public void computerVisonSetup(boolean computerVisonOn) {
        if (computerVisonOn) {
            this.colorLocatorGreen = new ColorBlobLocatorProcessor.Builder().setTargetColorRange(ColorRange.ARTIFACT_GREEN).setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY).setRoi(ImageRegion.asUnityCenterCoordinates(-1.0d, 0.3d, 1.0d, -1.0d)).setDrawContours(true).setBlurSize(5).build();
            this.colorLocatorPurple = new ColorBlobLocatorProcessor.Builder().setTargetColorRange(ColorRange.ARTIFACT_PURPLE).setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY).setRoi(ImageRegion.asUnityCenterCoordinates(-1.0d, 0.3d, 1.0d, -1.0d)).setDrawContours(true).setBlurSize(5).build();
            this.portal = new VisionPortal.Builder().addProcessor(this.colorLocatorGreen).addProcessor(this.colorLocatorPurple).setCameraResolution(new Size(960, 720)).setCamera((CameraName) this.hardwareMap.get(WebcamName.class, "Webcam 1")).build();
        }
    }

    public void alignToArtifact() {
        if (this.colorLocatorGreen == null || this.colorLocatorPurple == null || this.chassis == null) {
            return;
        }
        List<ColorBlobLocatorProcessor.Blob> blobsGreen = new ArrayList<>(this.colorLocatorGreen.getBlobs());
        List<ColorBlobLocatorProcessor.Blob> blobsPurple = new ArrayList<>(this.colorLocatorPurple.getBlobs());
        ColorBlobLocatorProcessor.Util.filterByCriteria(ColorBlobLocatorProcessor.BlobCriteria.BY_CONTOUR_AREA, 1200.0d, 20000.0d, blobsPurple);
        ColorBlobLocatorProcessor.Util.filterByCriteria(ColorBlobLocatorProcessor.BlobCriteria.BY_CONTOUR_AREA, 1200.0d, 20000.0d, blobsGreen);
        blobsGreen.sort(new Comparator() { // from class: org.firstinspires.ftc.teamcode.ExLibrary.ComputerVisonDecode$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Double.compare(((ColorBlobLocatorProcessor.Blob) obj2).getBoxFit().center.y, ((ColorBlobLocatorProcessor.Blob) obj).getBoxFit().center.y);
            }
        });
        blobsPurple.sort(new Comparator() { // from class: org.firstinspires.ftc.teamcode.ExLibrary.ComputerVisonDecode$$ExternalSyntheticLambda1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Double.compare(((ColorBlobLocatorProcessor.Blob) obj2).getBoxFit().center.y, ((ColorBlobLocatorProcessor.Blob) obj).getBoxFit().center.y);
            }
        });
        this.closestGreenBally = -1;
        this.closestPurpleBally = -1;
        Iterator<ColorBlobLocatorProcessor.Blob> it = blobsGreen.iterator();
        if (it.hasNext()) {
            ColorBlobLocatorProcessor.Blob b = it.next();
            RotatedRect boxFit = b.getBoxFit();
            this.closestGreenBallx = (int) boxFit.center.x;
            this.closestGreenBally = (int) boxFit.center.y;
        }
        Iterator<ColorBlobLocatorProcessor.Blob> it2 = blobsPurple.iterator();
        if (it2.hasNext()) {
            ColorBlobLocatorProcessor.Blob b2 = it2.next();
            RotatedRect boxFit2 = b2.getBoxFit();
            this.closestPurpleBallx = (int) boxFit2.center.x;
            this.closestPurpleBally = (int) boxFit2.center.y;
        }
        if (this.closestGreenBally == -1) {
            if (this.closestPurpleBally != -1) {
                this.closestBallx = this.closestPurpleBallx;
            } else {
                this.closestBallx = -1;
            }
        } else if (this.closestPurpleBally != -1 && this.closestGreenBally <= this.closestPurpleBally) {
            this.closestBallx = this.closestPurpleBallx;
        } else {
            this.closestBallx = this.closestGreenBallx;
        }
        if (Math.abs(this.closestBallx - 480) > 60) {
            if (this.closestBallx > 480) {
                this.chassis.driveDirection(0.0d, 0.0d, 0.3d, 1.0d);
                return;
            } else {
                this.chassis.driveDirection(0.0d, 0.0d, 0.3d, -1.0d);
                return;
            }
        }
        this.chassis.driveDirection(0.0d, 0.0d, 0.0d, 0.0d);
    }

    public double alignToArtifactMauel() {
        if (this.colorLocatorGreen == null || this.colorLocatorPurple == null || this.chassis == null) {
            return 0.0d;
        }
        List<ColorBlobLocatorProcessor.Blob> blobsGreen = new ArrayList<>(this.colorLocatorGreen.getBlobs());
        List<ColorBlobLocatorProcessor.Blob> blobsPurple = new ArrayList<>(this.colorLocatorPurple.getBlobs());
        ColorBlobLocatorProcessor.Util.filterByCriteria(ColorBlobLocatorProcessor.BlobCriteria.BY_CONTOUR_AREA, 1200.0d, 20000.0d, blobsPurple);
        ColorBlobLocatorProcessor.Util.filterByCriteria(ColorBlobLocatorProcessor.BlobCriteria.BY_CONTOUR_AREA, 1200.0d, 20000.0d, blobsGreen);
        blobsGreen.sort(new Comparator() { // from class: org.firstinspires.ftc.teamcode.ExLibrary.ComputerVisonDecode$$ExternalSyntheticLambda2
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Double.compare(((ColorBlobLocatorProcessor.Blob) obj2).getBoxFit().center.y, ((ColorBlobLocatorProcessor.Blob) obj).getBoxFit().center.y);
            }
        });
        blobsPurple.sort(new Comparator() { // from class: org.firstinspires.ftc.teamcode.ExLibrary.ComputerVisonDecode$$ExternalSyntheticLambda3
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Double.compare(((ColorBlobLocatorProcessor.Blob) obj2).getBoxFit().center.y, ((ColorBlobLocatorProcessor.Blob) obj).getBoxFit().center.y);
            }
        });
        this.closestGreenBally = -1;
        this.closestPurpleBally = -1;
        Iterator<ColorBlobLocatorProcessor.Blob> it = blobsGreen.iterator();
        if (it.hasNext()) {
            ColorBlobLocatorProcessor.Blob b = it.next();
            RotatedRect boxFit = b.getBoxFit();
            this.closestGreenBallx = (int) boxFit.center.x;
            this.closestGreenBally = (int) boxFit.center.y;
        }
        Iterator<ColorBlobLocatorProcessor.Blob> it2 = blobsPurple.iterator();
        if (it2.hasNext()) {
            ColorBlobLocatorProcessor.Blob b2 = it2.next();
            RotatedRect boxFit2 = b2.getBoxFit();
            this.closestPurpleBallx = (int) boxFit2.center.x;
            this.closestPurpleBally = (int) boxFit2.center.y;
        }
        if (this.closestGreenBally == -1) {
            if (this.closestPurpleBally != -1) {
                this.closestBallx = this.closestPurpleBallx;
            } else {
                this.closestBallx = -1;
            }
        } else if (this.closestPurpleBally != -1 && this.closestGreenBally <= this.closestPurpleBally) {
            this.closestBallx = this.closestPurpleBallx;
        } else {
            this.closestBallx = this.closestGreenBallx;
        }
        if (this.closestBallx == -1 || Math.abs(this.closestBallx - 480) <= 60) {
            return 0.0d;
        }
        return Math.max(-0.5d, Math.min(0.5d, ((double) (this.closestBallx - 480)) / 480.0d));
    }

    public void stop() {
        if (this.portal != null) {
            this.portal.close();
            this.portal = null;
        }
    }

    public int getClosestBallX() {
        return this.closestBallx;
    }
}