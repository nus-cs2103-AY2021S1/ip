import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReading {
    public static String printFileContents(String filePath) throws FileNotFoundException {
        String output = "";
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        System.out.println("Here is the current task status: \n");
        while (s.hasNext()) {
            String str = s.nextLine();
            output = output + str + '\n';
            System.out.println(str);
        }
        return output;
    }
}
