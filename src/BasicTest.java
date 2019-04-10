import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BasicTest {
    public static void main(String[] args){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //File f = new File("./images/123_0jpg");
        Mat imgMat = Imgcodecs.imread("./images/123_0.jpg");
        imgMat.convertTo(imgMat,CvType.CV_64F);

        double[] data= new double[3];
        imgMat.get(0,0,data);
        for (int l = 0; l < data.length;l++) data[l] /= 255;
        System.out.println(Arrays.toString(data));


        int width = imgMat.width();
        int height = imgMat.height();
        double[][][] array = new double[height][width][3];
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                double[] element = imgMat.get(i,j);
                for (int l = 0; l < element.length; l++) array[i][j][l] = element[l] / 255;
            }
        }
        System.out.println("height: " + array.length + " width: " + array[0].length);
        int numActions =3;
        double[] values = new double[3];
        for (int i = 0; i < numActions; i++){
            //create a one-hot array for each action
            double[] actionInput = new double[numActions];
            actionInput[i] = 1;

            //create object array to pass to tensorflow and to receive output
            Object[] inputs = {array,actionInput};
            double[] output = new double[1];
            Map<Integer,Object> outputs = new HashMap();
            outputs.put(0,output);
            //run the model!
           // model.runForMultipleInputsOutputs(inputs,outputs);
            //store the value of the action
            System.out.println();
            //telemetry.addData("Action: ",i);
            //telemetry.addData("Value: ", values[i]);
            //telemetry.update();
        }
        //System.out.println(Arrays.deepToString(array));

    }
}
