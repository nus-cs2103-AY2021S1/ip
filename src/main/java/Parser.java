import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        fw.write(textToAdd);
        fw.close();
    }

    public static void parse(ArrayList<Task> tasks) {
        try {
            StringBuilder textToAdd = new StringBuilder();
            for (Task task : tasks) {
                textToAdd.append(task.toString()).append(System.lineSeparator());
            }
            writeToFile(textToAdd.toString());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
