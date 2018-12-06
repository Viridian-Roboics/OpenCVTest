import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ProcessData {
   /*
   Accepts a file's name, and size of matrix stored as well as if it contains a constant as the first
   entry. Returns an Array
    */
    public static Mat readCSVtoMat(String filename, int size){
        Mat mat = Mat.zeros(size,1,CvType.CV_64F);
        File f = new File(filename);
        try{

            Scanner s = new Scanner(f);
            int c = 0;
            while(s.hasNextDouble()){
                double d = s.nextDouble();
                mat.put(c,0,d);
                c++;
            };
            s.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return mat;
    }
    //following two methods from https://stackoverflow.com/questions/14958643/converting-bufferedimage-to-mat-in-opencv
    public static Mat BufferedImage2Mat(BufferedImage image) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);
        byteArrayOutputStream.flush();
        return Imgcodecs.imdecode(new MatOfByte(byteArrayOutputStream.toByteArray()), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
    }
    public static BufferedImage Mat2BufferedImage(Mat matrix)throws IOException {
        MatOfByte mob=new MatOfByte();
        Imgcodecs.imencode(".png", matrix, mob);
        return ImageIO.read(new ByteArrayInputStream(mob.toArray()));
    }

}
