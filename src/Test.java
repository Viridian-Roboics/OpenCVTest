import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

public class Test {
    public static void main(String args[])throws InterruptedException{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //load weights
        Mat weights = ProcessData.readCSVtoMat("weights.csv",901);
        //read and store the buffered test image
        BufferedImage image = null;
        File f = new File("botlogopsd.png");
        try{
            image = ImageIO.read(f);
        }catch(IOException e){
            e.printStackTrace();
        }
        //get the image in matrix form
        Mat pixels = null;
        try {
            pixels = ProcessData.BufferedImage2Mat(image);
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("size: " + pixels.size());
        System.out.println(Arrays.toString(pixels.get(0,0)));


        //Mat alt = Imgcodecs.imread("botlogopsd.png");
        //create a new matrix that will store the modified image
        Mat resized = new Mat();
        //resize and grayscale
        Imgproc.resize(pixels,resized,
                new Size(image.getHeight()/3,image.getWidth()/3),
                0,0,Imgproc.INTER_AREA);
        Imgproc.cvtColor(resized,resized,Imgproc.COLOR_RGB2GRAY);
        //convert matrix to buffered image to display
        BufferedImage resizedImage = null;
        try{
            resizedImage = ProcessData.Mat2BufferedImage(resized);
        }catch(IOException e){
            e.printStackTrace();
        }
        //create a Jframe for displaying the image. Display the original and then the resized image.
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(300,300);
        JLabel label = new JLabel();

        ImageIcon icon = new ImageIcon(image);
        label.setIcon(icon);
        frame.add(label);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Thread.sleep(2000);
        icon.setImage(resizedImage);
        frame.repaint();
    }
}
