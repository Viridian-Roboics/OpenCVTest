import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class ClassifyImages {
    public static void main(String[] args) {
        /* set up JFrame*/
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(500,300);
        JLabel label = new JLabel();
        frame.add(label);


        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);





        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat weights = ProcessData.readCSVtoMat("weights.csv",901);
        try{
            Files.list(new File("images").toPath()).forEach(path -> {

                BufferedImage image = null;
                File file = new File(path.toString());
                try{
                    image = ImageIO.read(file);
                }catch(IOException e){
                    e.printStackTrace();
                }

                Mat pixels = null;
                try {
                    pixels = ProcessData.BufferedImage2Mat(image);
                }catch(IOException e){
                    e.printStackTrace();
                }

                Mat resized = new Mat(30,30,pixels.type());
                //resize and grayscale
                Imgproc.resize(pixels,resized,
                        new Size(30,30),
                        0,0,Imgproc.INTER_AREA);
                Imgproc.cvtColor(resized,resized,Imgproc.COLOR_RGB2GRAY);
                resized = resized.reshape(1,1);
                resized.convertTo(resized,CvType.CV_64F);
                Mat input = new Mat(1,901, resized.type());
                double data[] = new double[900];
                resized.get(0,0,data);
                input.put(0,0,1);
                input.put(0,1,data);

                /*
                sigmoid function:
                h = 1 / 1 + e^-z
                 */
                Mat zMat = new Mat();
                Core.gemm(input,weights , 1,new Mat(),0,zMat);
                double z = zMat.get(0,0)[0];
                double h = 1 / (1 + Math.pow(Math.E,-z));
                String id;
                System.out.println(h);
                if(h >= .5){
                    id = "gold";
                }else{
                    id = "silver";
                }
                Mat d = pixels;
                Mat display = new Mat();
                int scale = 10;
                Imgproc.resize(pixels,display,
                        new Size(image.getWidth()/scale,image.getHeight()/scale),
                        0,0,Imgproc.INTER_AREA);
                BufferedImage displayImage = null;
                try{
                    displayImage = ProcessData.Mat2BufferedImage(display);
                }catch(IOException e){}
                ImageIcon icon = new ImageIcon(displayImage);
                label.setIcon(icon);
                frame.repaint();
                System.out.println(path + " is a " + id + "mineral");
                try{
                    Thread.sleep(2000);
                } catch(InterruptedException e){}
                //input.put(0,0,)
                //input.put(1,0,pixels.reshape(1,1));


            });
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
