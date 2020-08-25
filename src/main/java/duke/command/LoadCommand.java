package duke.command;

import duke.task.Task;
import duke.task.TaskEnum;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

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
    public boolean hasUndo() {
        return false;
    }

    @Override
    public void execute() {

        // Empty current list
        taskList.clear();

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            Supplier<String> supplier = () -> scanner.hasNext() ? scanner.nextLine() : null;

            // Process each line as stream
            Stream.generate(supplier)
                    .takeWhile(Objects::nonNull)
                    .filter(Predicate.not(String::isBlank))
                    .map(String::trim)
                    .map(line -> {
                         try {
                             String taskType = line.substring(0, line.indexOf(','));
                             return TaskEnum.valueOf(taskType).fromCsv(line);
                         } catch(Exception e) {
                             System.out.println("Corrupt entry: " + line);
                             return null;
                         }
                    })
                    .filter(Objects::nonNull)
                    .forEach(taskList::add);

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
