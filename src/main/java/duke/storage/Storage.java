package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;


/**
 * Storage is used to store data in hardware and displays available data in client's server.
 */
public class Storage {
    private String filePath;
    private Scanner inputSource;

    /**
     * Constructor of Storage class.
     *
     * @param filePath relative address of file path
     */
    public Storage(String filePath) {
        try {
            this.filePath = filePath;
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File file = new File("./data/duke.txt");
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("There are some errors when retrieving data!");
            }
            inputSource = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            System.out.println("File: " + filePath + " cannot be found!");
            System.exit(0);
        }
    }

    /**
     * Loads all data from database.
     *
     * @return List list of tasks in database.
     */
    public List<Task> loadTasks() {
        List<Task> list = new ArrayList<>();
        while (inputSource.hasNextLine()) {
            String currentInput = inputSource.nextLine();
            String[] inputArray = currentInput.split("\\|");
            if (inputArray[0].trim().equals("T")) {
                list.add(new Todo(inputArray[2].trim()));
            } else if (inputArray[0].trim().equals("D")) {
                list.add(new Deadline(inputArray[2].trim(), inputArray[3].trim()));
            } else if (inputArray[0].trim().equals("E")) {
                list.add(new Event(inputArray[2].trim(), inputArray[3].trim()));
            }

            if (inputArray[1].trim().equals("1")) {
                list.get(list.size() - 1).markAsDone();
            }
        }
        return list;
    }

    /**
     * Saves tasks in database.
     *
     * @param list list of data that clients enter.
     * @throws FileNotFoundException If the file path cannot be found.
     */
    public void saveTasks(List<Task> list) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(filePath));
        for (Task task: list) {
            printWriter.println(task.toStringFile());
        }
        printWriter.close();
    }
}

