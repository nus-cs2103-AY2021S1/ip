package duke.storage;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Storage {

    /**
     * Save and overwrite the taskList into specified filePath
     * @param taskList The list of tasks to be saved
     * @param filePath The location of file to be overwritten
     * @throws IOException If the named file exists but is a directory
     * rather than a regular file, does not exist but cannot be created,
     * cannot be opened for any other reason, or an IO error occurs while writing
     */
    public static void save(List<Task> taskList, String filePath) throws IOException {
        // Open file for write/overwrite (throws IOException)
        FileWriter fileWriter = new FileWriter(filePath);
        for (Task task : taskList) {
            fileWriter.write(task.toCsv() + "\n");
        }

        // Close file
        fileWriter.flush();
        fileWriter.close();
    }

    /**
     * Load and overwrite the taskList from specified filePath
     * @param taskList The taskList to be overwritten
     * @param filePath The filePath to load the Tasks
     * @throws FileNotFoundException If filePath cannot be opened
     */
    public static void load(List<Task> taskList, String filePath) throws FileNotFoundException {
        // Empty current list
        taskList.clear();

        // Attempt to open file for reading (throws FileNotFoundException)
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        Supplier<String> nextLine = () -> scanner.hasNext() ? scanner.nextLine() : null;

        // Process each line as stream
        Stream.generate(nextLine)
                .takeWhile(Objects::nonNull)
                .filter(Predicate.not(String::isBlank))
                .map(String::trim)
                .map(line -> { // Convert Csv to Task
                    try {
                        String taskType = line.substring(0, line.indexOf(','));
                        return CsvToTask.valueOf(taskType).parse(line);
                    } catch (Exception e) {
                        System.err.println("Corrupt entry: " + line); // Todo: logger
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .forEach(taskList::add);

    }

}
