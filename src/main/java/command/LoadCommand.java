package command;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Load taskList from file
 */
public class LoadCommand extends Command {

    private final List<Task> taskList;
    private final String filePath;

    public LoadCommand(List<Task> taskList, String filePath) {
        this.taskList = taskList;
        this.filePath = filePath;
    }

    @Override
    public void execute() {

        // Empty current list
        taskList.clear();

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine().trim();
                if (line.isBlank()) continue;

                switch (line.charAt(0)) {
                    case 'T':
                        taskList.add(ToDo.fromCSV(line));
                        break;
                    case 'D':
                        taskList.add(Deadline.fromCSV(line));
                        break;
                    case 'E':
                        taskList.add(Event.fromCSV(line));
                        break;
                    default:
                        System.out.println("Corrupted entry: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found");
        }

        System.out.println("Load: " + taskList.size() + " entries");
    }

    @Override
    public void undo() {
        // Operation unsupported
        System.out.println("Undo: LoadCommand");
    }
}
