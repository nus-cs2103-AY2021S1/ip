package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a storage object to handle read and write.
 * @author Tee Kok Siang
 */
public class Storage {
    private static final String FILE_DIR = System.getProperty("user.dir") + "/";

    private List<String> dirs;
    private String filePath;

    public Storage(String filePath) {
        dirs = Arrays.asList(filePath.split("/"));
        this.filePath = FILE_DIR;
    }

    public List<Task> load() {
        try {
            for (int i = 0; i < dirs.size(); i++) {
                if (i == dirs.size() - 1) {
                    filePath = filePath.concat(dirs.get(i));
                    break;
                }
                filePath = filePath.concat(dirs.get(i)).concat("/");
                File fileDir = new File(filePath);
                // if directory not exists, create file
                if (!fileDir.exists()) {
                    fileDir.mkdir();
                }
            }
            File file = new File(filePath);
            // if file not exists, create file
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String currentLine;
            List<Task> tasks = new ArrayList<>();
            while ((currentLine = reader.readLine()) != null) {
                tasks.add(getTaskFromLine(currentLine));
            }
            reader.close();
            return tasks;
        } catch (IOException ioException) {
            System.out.println(String.format("IOException: %s", ioException.getLocalizedMessage()));
        }
        return new ArrayList<>();
    }

    public void writeFile(TaskList taskList) {
        List<Task> tasks = taskList.getTasks();
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : tasks) {
            String taskInfo = task.toFileString() + "\n";
            stringBuilder.append(taskInfo);
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(stringBuilder.toString());
            writer.close();
        } catch (IOException ioException) {
            System.out.println("IOException: " + ioException.getLocalizedMessage());
        }
    }

    private Task getTaskFromLine(String line) {
        List<String> words = Arrays.asList(line.split(" \\| "));
        Task task;
        if (words.get(0).equals("T")) {
            task = new Todo(words.get(2));
        } else if (words.get(0).equals("D")) {
            task = new Deadline(words.get(2), words.get(3));
        } else {
            task = new Event(words.get(2), words.get(3));
        }
        if (words.get(1).equals("1")) {
            task.markDone();
        }
        return task;
    }
}
