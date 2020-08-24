import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    protected String filePath;

    private Storage(String filePath) {
        this.filePath = filePath;
    }

    public static Storage init() {
        String filePath = System.getProperty("user.dir");
        File directory = new File(filePath + "/data");
        if (!directory.exists()) {
            directory.mkdir();
        }

        filePath = filePath + "/data/duke.txt";
        return new Storage(filePath);
    }

    public TaskList readStoredData() {
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            TaskList taskList = new TaskList();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                try {
                    taskList.add(createTask(line));
                } catch (InvalidSymbolException e) {
                    System.err.println("Not a valid line: " + line);
                    continue;
                }
            }
            return taskList;
        } catch (FileNotFoundException e1) {
            try {
                file.createNewFile();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            return new TaskList();
        }
    }

    protected Task createTask(String taskSummary) throws InvalidSymbolException {
        String identifier = taskSummary.split("\\|", 2)[0];
        switch (identifier) {
        case Event.SYMBOL:
            return Event.reconstructFromSummary(taskSummary);
        case ToDo.SYMBOL:
            return ToDo.reconstructFromSummary(taskSummary);
        case Deadline.SYMBOL:
            return Deadline.reconstructFromSummary(taskSummary);
        default:
            throw new InvalidSymbolException(identifier + " is not a valid type symbol!");
        }
    }

    public void updateFile(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(filePath);
            boolean isFirst = true;
            for (Task task : taskList) {
                if (isFirst) {
                    isFirst = false;
                    writer.write(task.getSummary());
                } else {
                    writer.write('\n' + task.getSummary());
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
