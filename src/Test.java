import com.jmatio.io.*;
import com.jmatio.common.*;
import com.jmatio.types.*;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.CvType;
import java.io.*;
import java.util.*;

public class Test {
    public static void main(String args[]){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
        System.out.println("mat = " + mat.dump());
        File file = new File("src/processedimages.mat");
        try{
            MatFileReader reader = new MatFileReader(file);
            System.out.println(reader.getContent());
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("error");
        }

    }
}
