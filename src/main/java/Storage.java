import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
        try {
            Path file = Paths.get(this.filePath);

            if (!Files.exists(file)) {
                // Check if directory exists
                Path directoryPath = Paths.get("data/");
                if (!Files.exists(directoryPath)) {
                    Files.createDirectory(directoryPath);
                }
                Files.createFile(file);
            }

        } catch (IOException exception) {
            System.out.println(exception);
        }
    }

    List<Task> load() throws DukeException {
        File localTasks = new File(this.filePath);
        try {
            List<Task> tasks = new ArrayList<>();
            // Create Scanner using file as source
            Scanner sc = new Scanner(localTasks);
            while (sc.hasNext()) {
                String[] details = sc.nextLine().split(" \\| ");

                switch (details[0]) {
                    case "T": {
                        TodoTask todoTask = new TodoTask(details[2]);
                        if (details[1].equals("1")) {
                            todoTask.markAsDone();
                        }
                        tasks.add(todoTask);
                        break;
                    }
                    case "D": {
                        DeadlineTask deadlineTask = new DeadlineTask(details[2], details[3]);
                        if (details[1].equals("1")) {
                            deadlineTask.markAsDone();
                        }
                        tasks.add(deadlineTask);
                        break;
                    }
                    case "E": {
                        EventTask eventTask = new EventTask(details[2], details[3]);
                        if (details[1].equals("1")) {
                            eventTask.markAsDone();
                        }
                        tasks.add(eventTask);
                        break;
                    }
                }
            }
            sc.close();
            return tasks;
        } catch (FileNotFoundException exception) {
            throw new DukeException("File not found");
        }
    }
}
