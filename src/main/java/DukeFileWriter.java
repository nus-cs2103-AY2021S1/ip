import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DukeFileWriter {

    public static void writeToFile(String filePath, ArrayList<Task> taskArr) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : taskArr) {
            fw.write(t.toString());
            System.lineSeparator();
        }
        fw.close();
    }

    private static void appendToFile(String filePath, ArrayList<Task> taskArr) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(String.valueOf(taskArr));
        fw.close();
    }

//    public static void main(String[] args) {
//        String file2 = "temp/lines.txt";
//        try {
//            appendToFile(file2, "first line" + System.lineSeparator() + "second line");
//        } catch (IOException e) {
//            System.out.println("Something went wrong: " + e.getMessage());
//        }
//    }

}