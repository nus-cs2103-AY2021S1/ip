package dukechatbot.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import dukechatbot.dukeoutput.DukeOutput;
import dukechatbot.parser.TaskParser;
import dukechatbot.task.Task;

/**
 * Loads and saves task list from / to file.
 */
public class Storage {

    private static final String LIST_FILE_PATH = "./data/duke.txt";

    /**
     * Loads the task list from stored tasks in a file.
     *
     * @return Task list.
     */
    public static List<Task> load() {
        File file = new File(LIST_FILE_PATH);
        File parentFile = file.getParentFile();
        List<Task> list = new ArrayList<>();
        if (!parentFile.exists()) {
            return list;
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] components = line.split("\\|");
                for (int i = 0; i < components.length; i++) {
                    components[i] = components[i].trim();
                }
                Task task = TaskParser.parseTaskFromDisk(components);
                list.add(task);
            }
        } catch (FileNotFoundException | NoSuchElementException e) {
            DukeOutput.getOutput("\u2639 OOPS! some error saving the list.");
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Saves the tasks in the task list to a file.
     *
     * @param taskList
     */
    public static void save(List<Task> taskList) {
        File file = new File(LIST_FILE_PATH);
        File parentFile = file.getParentFile();
        
        if (!parentFile.exists()) {
            parentFile.mkdir();
        }
        assert(parentFile.exists());
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (Task task : taskList) {
                fileWriter.write(task.getSaveFormat());
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            DukeOutput.getOutput("\u2639 OOPS! some error saving the list.");
        }
    }
}
