import java.io.FileWriter;
import java.io.IOException;

public class FileWriting {

    private static void writeToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    public static void main(String[] args) {
        String updateFile = "data/duke.txt";
        try {
            writeToFile(updateFile, "first line\n" + "second line\n");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}