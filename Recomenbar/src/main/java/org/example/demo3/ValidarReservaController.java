package org.example.demo3;

import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.opencv.opencv_core.IplImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ValidarReservaController {

    @FXML
    private ImageView qrImageView;

    @FXML
    private Label infoLabel;

    @FXML
    private Button leerQRButton;

    private ScheduledExecutorService timer;
    private OpenCVFrameGrabber grabber;
    private OpenCVFrameConverter.ToIplImage converter;
    private Java2DFrameConverter java2DConverter;

    @FXML
    public void onIniciarCamaraButtonClick() {
        grabber = new OpenCVFrameGrabber(0); // 0 es para la cámara por defecto
        converter = new OpenCVFrameConverter.ToIplImage();
        java2DConverter = new Java2DFrameConverter();
        timer = Executors.newSingleThreadScheduledExecutor();
        try {
            grabber.start();
            Runnable frameGrabber = () -> {
                Frame frame = null;
                try {
                    frame = grabber.grab();
                } catch (FrameGrabber.Exception e) {
                    e.printStackTrace();
                }
                if (frame != null) {
                    BufferedImage bufferedImage = java2DConverter.convert(frame);
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    qrImageView.setImage(image);
                    try {
                        String qrContent = decodeQRCode(bufferedImage);
                        if (qrContent != null) {
                            Platform.runLater(() -> infoLabel.setText(qrContent));
                            stopCamera();
                        }
                    } catch (NotFoundException | IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.scheduleAtFixedRate(frameGrabber, 0, 33, TimeUnit.MILLISECONDS); // ~30 frames per second
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }
    }

    private void stopCamera() {
        try {
            if (grabber != null) {
                grabber.stop();
            }
            if (timer != null) {
                timer.shutdown();
            }
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }
    }

    private String decodeQRCode(BufferedImage bufferedImage) throws NotFoundException, IOException {
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result = new MultiFormatReader().decode(bitmap);
        return result.getText();
    }
}
