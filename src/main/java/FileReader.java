import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    public static String printFileContents(String filePath) throws FileNotFoundException {
        String content = "";
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String data = scanner.nextLine();
            content = content + data + '\n';
            System.out.println(data);
        }
        return content;
    }
}