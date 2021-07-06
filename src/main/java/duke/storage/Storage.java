package duke.storage;

import duke.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    String dataDirectory;
    File file;

    public Storage(String dataDirectory, String pathname) {
        this.dataDirectory = dataDirectory;
        this.file = new File(pathname);
    }

    /**
     * Load data from file, parses, and saves as tasks
     * @return a list of tasks corresponding to the ones loaded
     */
    public List<Task> load()  {
        List<Task> list = new ArrayList<>();
        try {
            Files.createDirectories(Paths.get(dataDirectory));
            file.createNewFile();
            Scanner sc = new Scanner(file);
            String record;
            while (sc.hasNextLine()) {
                record = sc.nextLine().trim();
                String tokens[] = record.split("~", 3);
                String taskType = tokens[0];
                boolean isDone = Integer.parseInt(tokens[1]) == 1;
                String remainingText = tokens[2];
                String description;

                switch(taskType) {
                case "T":
                    description = remainingText;
                    list.add(new TodoTask(description, isDone));
                    break;
                case "E":
                    String eventTokens[] = remainingText.split("~");
                    description = eventTokens[0];
                    LocalDate at = LocalDate.parse(eventTokens[1]);
                    list.add(new EventTask(description, at, isDone));
                    break;
                case "D":
                    String deadlineTokens[] = remainingText.split("~");
                    description = deadlineTokens[0];
                    LocalDate by = LocalDate.parse(deadlineTokens[1]);
                    list.add(new DeadlineTask(description, by, isDone));
                    break;
                case "N":
                    String noteTokens[] = remainingText.split("~");
                    description = noteTokens[0];
                    String name = noteTokens[1];
                    list.add(new Note(description, name));
                    break;
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        finally {
            return list;
        }
    }

    /**
     * Stores data to file (rewritten after each function call)
     */
    public void write(TaskList tasks) {
        try {
            Files.createDirectories(Paths.get(dataDirectory));
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            for(Task task: tasks.getList()) {
                writer.write(task.toDBString() + System.lineSeparator());
            }
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
