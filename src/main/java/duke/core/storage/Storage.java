package duke.core.storage;

import duke.core.task.Task;

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

/**
 * The Storage class provides methods to save/load the taskList to/from a csv file
 */
public class Storage {

    /**
     * Save and overwrite the taskList into specified filePath
     * @param taskList The list of tasks to be saved
     * @param filePath The location of file to be overwritten
     * @throws IOException If parent directory of filePath does not exist, if
     * filePath cannot be opened, or if an error occurs while writing to filePath
     */
    public static void save(List<Task> taskList, String filePath) throws IOException {

        // Open file for write/overwrite
        // throws IOException, IOException should be passed to the program logic
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            for (Task task : taskList) {
                fileWriter.write(task.toCsv() + "\n");
            }

            fileWriter.flush();
            // fileWriter is automatically closed by try-with-resources
        }

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
        Scanner fileScanner = new Scanner(file);
        Supplier<String> nextLine = () -> fileScanner.hasNextLine() ? fileScanner.nextLine() : null;

        Stream.generate(nextLine)
                .takeWhile(Objects::nonNull)
                .map(String::trim)
                .filter(Predicate.not(String::isBlank))
                .map(CsvToTask::parse)
                .forEach(taskList::add);

        fileScanner.close();

    }

}
