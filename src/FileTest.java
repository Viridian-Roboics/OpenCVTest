import org.opencv.core.*;
import org.opencv.imgproc.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class FileTest {
    public static void main(String[] args) throws IOException{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String dir = "files";
        //Functional programing!?
       Files.list(new File(dir).toPath()).forEach(path -> {
           System.out.println(path);
       });
        Mat test1 = Mat.ones(1,2, CvType.CV_8U);
        Mat test2 = Mat.zeros(1,3, CvType.CV_8U);
        Mat mask = Mat.zeros(1,3,CvType.CV_8U);

        MatOfInt mint = new MatOfInt(2,3,4);

        int data[] = new int[4];
        mint.get(0,0,data);
        //System.out.println(Arrays.toString(data));
        System.out.println(mint.dump());
        mint.push_back(new MatOfInt(3));
        System.out.println(mint.dump());

    }

}
