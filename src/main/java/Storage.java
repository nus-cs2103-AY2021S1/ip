import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    public static ArrayList<Task> readFromFile(String filepath) throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filepath);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String input = s.nextLine();
            tasks.add(StorageParser.parse(input));
        }
        s.close();
        return tasks;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = readFromFile(filePath);
        return tasks;
    }

    public void saveToFile(ArrayList<Task> taskArr) throws IOException {
        File oldFile= new File(filePath);
        oldFile.delete();
        File newFile= new File(filePath);
        FileWriter fileWriter = new FileWriter(newFile, false);
        for (Task task : taskArr) {
            fileWriter.write(task.toString());
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.close();
    }
}
