import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class FileTest {
    public static void main(String[] args) throws IOException{
        String dir = "files";
        //Functional programing!?
       Files.list(new File(dir).toPath()).forEach(path -> {
           System.out.println(path);
       });
    }

}
