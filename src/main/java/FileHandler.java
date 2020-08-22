import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private static final String dataPath = "data/duke.txt";

    public static ArrayList<Task> load() throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();

        try {

            File dataFile = new File(dataPath);
            Scanner sc = new Scanner(dataFile);

            while (sc.hasNextLine()) {
                String nowTask = sc.nextLine();

                String[] taskComponents = nowTask.split(" / ");

                switch (taskComponents[0]) {
                case "T":
                    taskList.add(new Todo(taskComponents[1].equals("1"), taskComponents[2]));
                    break;
                case "E":
                    taskList.add(new Event(taskComponents[1].equals("1"),taskComponents[2]
                            ,LocalDateTime.parse(taskComponents[3])));
                    break;
                case "D":
                    taskList.add(new Deadline(taskComponents[1].equals("1"),taskComponents[2]
                            ,LocalDateTime.parse(taskComponents[3])));
                    break;
                }

            }
        } catch (FileNotFoundException e) {
            if (!new File(dataPath).exists()) {
                if (! new File("data").mkdir() || ! new File(dataPath).createNewFile()) {
                    throw new IOException("Cannot access the file.");
                }
            }
        }
        return taskList;
    }

    public static void save(ArrayList<Task> taskList) {
        try {
            FileWriter writer = new FileWriter("data/duke.txt", false);

            for (Task task : taskList) {
                writer.write(task.toFileStringFormat() + '\n');
            }

            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
