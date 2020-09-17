package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * handle loading files and saving program's data to disk
 */
public class Storage {
    private final List<String> inputData;
    private File file;

    /**
     * Storage constructor, use to pass in the data filename
     * If the file doesn't exist, create it
     *
     * @param filename the name of the data file
     */
    public Storage(String filename) {
        assert (!filename.isEmpty());
        try {
            file = new File(filename);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred when interacting with file.");
            e.printStackTrace();
        }
        inputData = readFileToLines();
    }

    /**
     * load the saved list of tasks
     *
     * @return the data of the file provided in the constructor
     */
    public static List<Task> parseFromString(List<String> extData) throws IllegalStateException {
        List<Task> tasks = new ArrayList<>();
        Queue<String> data = new LinkedList<>(extData);
        while (!data.isEmpty()) {
            String type = data.poll();
            String desc = data.poll();
            int status = Integer.parseInt(data.poll());
            switch (type) {
            case "todo":
                tasks.add(new Todo(desc));
                break;
            case "deadline":
                String by = data.poll();
                tasks.add(new Deadline(desc, by));
                break;
            case "event":
                String at = data.poll();
                if (!data.isEmpty()) {
                    try {
                        LocalDate date = Parser.parseDate(data.peek());
                    } catch (IllegalArgumentException e) {
                        tasks.add(new Event(desc, at));
                        break;
                    }
                    tasks.add(new Event(desc, at, data.poll()));
                    break;
                }
                tasks.add(new Event(desc, at));
                break;
            default:
                throw new IllegalStateException("The data file is corrupted");
            }
            if (status == 1) {
                tasks.get(tasks.size() - 1).setDone();
            }
        }
        return tasks;
    }

    private List<String> readFileToLines() {
        List<String> dataToReturn = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                dataToReturn.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            assert (false); // not possible since if file hasn't existed, it will be created in the constructor
        }
        return dataToReturn;
    }

    public List<Task> load() {
        return parseFromString(inputData);
    }

    /**
     * save the current list of tasks to disk
     *
     * @param lst list of all tasks
     */
    public void write(List<Task> lst) {
        try {
            FileWriter writer = new FileWriter(file);
            for (Task task : lst) {
                writer.write(task.toDisk() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred when write to file");
            e.printStackTrace();
        }

    }
}
