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
import org.example.demo3.Entidades.Discoteca;
import org.example.demo3.Entidades.Usuario;
import org.example.demo3.Negocio.LogicaDelNegocio;
import org.example.demo3.Negocio.Sesion;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
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
                            stopCamera();
                            if(validarBar(qrContent)){
                                Platform.runLater(() -> infoLabel.setText(qrContent));

                            }else{
                                Platform.runLater(() -> infoLabel.setText("La reserva no es en este bar"));
                            }
                        }
                    } catch (NotFoundException | IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
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

    private int processQRContent(String qrContent) {
        // Asumiendo que el contenido del QR es del tipo "ID: <idR> Discoteca: <nombreDisco> Fecha: <fecha> VIP: <vip> Cantidad personas: <cantidad>"
        String[] parts = qrContent.split(" ");
        int idR = 0;
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equals("ID:")) {
                idR = Integer.parseInt(parts[i + 1]);
                break;
            }
        }
        System.out.println("idR: " + idR);
        return idR;
    }

    private boolean validarBar(String qrContent) throws SQLException {
        boolean valido = false;
        LogicaDelNegocio logicaDelNegocio = LogicaDelNegocio.getInstancia();
        Sesion sesion = Sesion.obtenerInstancia();
        Usuario usuario = logicaDelNegocio.UsuarioCorreo(sesion.getCorreo());
        String[] parts = qrContent.split(" ");
        String nombre = null;
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equals("Discoteca:")) {
                nombre = parts[i + 1];
                for(int j=i+2; j<parts.length; j++){
                    if(!parts[j].equals("Fecha:")){
                        nombre= nombre + " " + parts[j];
                    }else{
                        break;
                    }
                }
                break;
            }
        }
        System.out.println("DESDE EL QR "+nombre);
        System.out.println(usuario.getNombre());
        if(usuario.getNombre().equals(nombre)){
            valido = true;
        }
        return valido;
    }
}
