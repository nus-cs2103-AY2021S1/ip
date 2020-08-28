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
     * Save the taskList into specified filePath
     * @param taskList The list of tasks to be saved
     * @param filePath The location of file to be overwritten
     */
    public static void save(List<Task> taskList, String filePath) {
        try {
            // Open file for write/overwrite
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task : taskList) {
                fileWriter.write(task.toCsv() + "\n");
            }

            // Close file
            fileWriter.flush();
            fileWriter.close();
            System.out.println("Save: " + taskList.size() + " entries");

        } catch (IOException e) {
            System.out.println("Error: Could not save to file. Ensure directory exists and file is not in use");
        }
    }

    /**
     * Load the taskList from specified filePath
     * @param taskList The taskList to be overwritten
     * @param filePath The filePath to load the Tasks
     */
    public static void load(List<Task> taskList, String filePath) {
        // Empty current list
        taskList.clear();

        Supplier<String> nextLine = () -> null;

        // Open file for reading
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            nextLine = () -> scanner.hasNext() ? scanner.nextLine() : null;
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found");
        }

        // Process each line as stream
        Stream.generate(nextLine)
                .takeWhile(Objects::nonNull)
                .filter(Predicate.not(String::isBlank))
                .map(String::trim)
                .map(line -> { // Convert Csv to Task
                    try {
                        String taskType = line.substring(0, line.indexOf(','));
                        return CsvToTask.valueOf(taskType).parse(line);
                    } catch(Exception e) {
                        System.out.println("Corrupt entry: " + line);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .forEach(taskList::add);

        System.out.println("Load: " + taskList.size() + " entries");
    }

}
