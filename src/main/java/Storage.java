import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String savePath;

    Storage(String savePath) {
        this.savePath = savePath;
    }

    public void saveTask(TaskList data) throws IOException {
        // If no save file, create one
        if (!Files.exists(Paths.get(savePath))) {
            Files.createFile(Paths.get(savePath));
        }

        // Write to save file
        FileWriter fs = new FileWriter(savePath);
        for (int i = 0; i < data.length(); i++) {
            fs.write(data.getTask(i).toString() + System.lineSeparator());
        }
        fs.close();
    }
    
    public ArrayList<Task> loadTask() {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(savePath);

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
                        description = Parser.stringSplit(newTask, "/by");
                        tasks.add(new Deadline(description[0], isDone, LocalDate.parse(description[1])));
                        break;
                    case 'E':
                        description = Parser.stringSplit(newTask, "/at");
                        tasks.add(new Event(description[0], isDone, LocalDate.parse(description[1])));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
        }

        return tasks;
    }
}
