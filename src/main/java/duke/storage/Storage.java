package duke.storage;

import duke.tasks.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;


public class Storage {

    private static final String FILE_PATH = "data/duke.txt";
    private TaskList taskList;

    /**
     * Constructs a Storage object
     * @param taskList The TaskList that is to be set as the taskList of this Storage object
     */
    public Storage(TaskList taskList) {
        taskList = taskList;
    }

    /**
     * Writes the list of tasks to duke.txt.
     * @param taskList The list of tasks to be written in the file
     */

    public static void saveDataToFile(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);

            for (Task task : taskList.getListOfTasks()) {
                writer.write(task.showContent());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Reads data from the file.
     * @return The list of tasks in the file
     */
    public static TaskList read() {
        try {
            ArrayList<Task> listOfTasks = new ArrayList<>();
            File storageFile = new File(FILE_PATH);
            Scanner scanner = new Scanner(storageFile);
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                String[] wordsParsed = currentLine.split(" \\| ");
                String isTaskDone = wordsParsed[1].equals("1")
                        ? "true"
                        : "false";
                switch (wordsParsed[0]) {
                    case "T":
                        Task todo = new Todo(wordsParsed[2]);
                        todo.setStatus(isTaskDone);
                        listOfTasks.add(todo);
                        break;
                    case "D":
                        Task deadline = new Deadline(wordsParsed[2], wordsParsed[3]);
                        deadline.setStatus(isTaskDone);
                        listOfTasks.add(deadline);
                        break;
                    case "E":

                        Task event = new Event(wordsParsed[2], wordsParsed[3]);

                        event.setStatus(isTaskDone);
                        listOfTasks.add(event);
                        break;
                }
            }
            scanner.close();
            return new TaskList(listOfTasks);
        } catch (IOException e) {
            //System.out.println(e.toString());
            return new TaskList();
        }
    }

}
