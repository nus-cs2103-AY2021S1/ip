import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    File dataFile;

    public Storage(String directoryPath, String dataFilePath) {
        try {
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            dataFile = new File(dataFilePath);
            dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    public ArrayList<String> readFile() {
        ArrayList<String> lines = new ArrayList<>();
        try {
            Scanner sc = new Scanner(dataFile);
            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
            return lines;
    }

    public void saveTaskList(ArrayList<Task> lst) {
        StringBuilder sb = new StringBuilder();
        for (Task task : lst) {
            sb.append(task);
            sb.append("\n");
        }
        try {
            writeToFile(sb.toString());
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    private void writeToFile(String textToAdd) throws IOException {
        FileWriter fileWriter = new FileWriter(dataFile.getPath());
        fileWriter.write(textToAdd);
        fileWriter.close();
    }
}
