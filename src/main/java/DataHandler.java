import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class DataHandler {
    public static void saveTask(String pathName, ArrayList<Task> data) throws IOException {
        // If no save file, create one
        if (!Files.exists(Paths.get(pathName))) {
            Files.createFile(Paths.get(pathName));
        }

        // Write to save file
        FileWriter fs = new FileWriter(pathName);
        for (Task i : data) {
            fs.write(i.toString() + System.lineSeparator());
        }
        fs.close();
    }
    
    public static ArrayList<Task> loadTask(String pathName) {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(pathName);

        // Loads the input from the file, if no file, return empty arraylist
        try {
            Scanner input = new Scanner(f);

            while (input.hasNext()) {
                String newTask = input.nextLine();

                // Get type of task
                char taskType = newTask.charAt(1);

                // Check if the task is done
                boolean isDone = newTask.contains("✓");

                // Format the string
                newTask = newTask.split(" ", 2)[1];
                newTask = newTask.replace("(", "/");
                newTask = newTask.replaceAll("[:)]", "");

                // Add the tasks
                String[] description;
                switch (taskType) {
                    case 'T':
                        tasks.add(new ToDo(newTask, isDone));
                        break;
                    case 'D':
                        description = Duke.stringSplit(newTask, "/by");
                        tasks.add(new Deadline(description[0], isDone, description[1]));
                        break;
                    case 'E':
                        description = Duke.stringSplit(newTask, "/at");
                        tasks.add(new Event(description[0], isDone, description[1]));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
        }

        return tasks;
    }
}
