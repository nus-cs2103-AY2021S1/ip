package duke.storage;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String databasePath;

    public Storage(String databasePath) {
        this.databasePath = databasePath;
    }
    public List<Task> readAll() {
        List<Task> tasks = new ArrayList<>();
        try {
            File databaseFile = new File( databasePath);
            databaseFile.getParentFile().mkdirs();
            databaseFile.createNewFile();
            BufferedReader csvReader = new BufferedReader(new FileReader(databaseFile));
            String line;
            while ((line = csvReader.readLine()) != null) {
                Task task;
                String[] taskStr = line.split(",");
                switch (taskStr[0]) {
                case "T": {
                    task = new ToDo(taskStr[2], taskStr[1]);
                    break;
                }
                case "E": {
                    task = new Event(taskStr[2], taskStr[3], taskStr[1]);
                    break;
                }
                case "D": {
                    task = new Deadline(taskStr[2], taskStr[3], taskStr[1]);
                    break;
                }
                default: {
                    task = null;
                    break;
                }
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            System.err.println("Read failed. \nDatabase connection error.");
        }
        return tasks;
    }

    public void update(List<Task> tasks) {
        try {
            File databaseFile = new File(databasePath);
            FileWriter csvWriter = new FileWriter(databaseFile);
            for(Task task: tasks) {
                String[] formatTask = formatTask(task);
                csvWriter.append(String.join(",", formatTask));
                csvWriter.append('\n');
            }
            csvWriter.flush();
            csvWriter.close();
        } catch(IOException e) {
            System.err.println("Creation failed. \nDatabase connection error.");
        }
    }

    private String[] formatTask(Task task) {
        String[] formatTask = new String[] {
                task.getTaskType(),
                task.getStatus() ? "1" : "0",
                task.getTask(),
                task.getTime().isEmpty() ? "" : task.getTime().get()
        };
        return formatTask;
    }

}
