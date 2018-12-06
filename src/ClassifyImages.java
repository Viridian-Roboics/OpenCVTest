import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class ClassifyImages {
    public static void main(String[] args){
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
                Mat resized = new Mat();
                //resize and grayscale
                Imgproc.resize(pixels,resized,
                        new Size(30,30),
                        0,0,Imgproc.INTER_AREA);
                Imgproc.cvtColor(resized,resized,Imgproc.COLOR_RGB2GRAY);
                Mat input = Mat.zeros(1,901, resized.type());


                //input.put(0,0,)
                //input.put(1,0,pixels.reshape(1,1));


            });
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
