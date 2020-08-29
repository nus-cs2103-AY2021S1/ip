package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Encapsulates methods and information that relate to file operations
 * and storage of tasks.
 */
public class Storage {

    private static final String DIRECTORY = "data";
    private static final String FILE_LOCATION = "data/duke.txt";

    /**
     * Retrieves the list of tasks stored in the designated file
     * and stores them in a TaskList object.
     *
     * @return TaskList containing the tasks retrieved from the file.
     */
    public TaskList readFile() {

        List<Task> taskList = new ArrayList<>();
        try {
            File dataDirectory = new File(Storage.DIRECTORY);
            // make a data directory if the directory does not exist
            dataDirectory.mkdir();

            File dataFile = new File(Storage.FILE_LOCATION);
            // create an empty file to store the tasks if the file does not exist
            dataFile.createNewFile();

            Scanner sc = new Scanner(dataFile);

            while (sc.hasNextLine()) {
                String[] taskData = sc.nextLine().split(" \\|");
                if (taskData[0].equals("T")) {
                    Task toAdd = new Todo(taskData[2]);
                    if (taskData[1].equals(" 1")) {
                        toAdd.markAsDone();
                    }
                    taskList.add(toAdd);
                } else {
                    String dateTime = taskData[3].trim();
                    String[] dateTimeArray = dateTime.split(" ");
                    LocalDate taskDate = LocalDate.parse(dateTimeArray[0]);
                    LocalTime taskTime = LocalTime.parse(dateTimeArray[1]);
                    if (taskData[0].equals("D")) {
                        Task toAdd = new Deadline(taskData[2], taskDate, taskTime);
                        if (taskData[1].equals(" 1")) {
                            toAdd.markAsDone();
                        }
                        taskList.add(toAdd);
                    } else if (taskData[0].equals("E")) {
                        Task toAdd = new Event(taskData[2], taskDate, taskTime);
                        if (taskData[1].equals(" 1")) {
                            toAdd.markAsDone();
                        }
                        taskList.add(toAdd);
                    }
                }
            }
            sc.close();
        } catch (IOException ex) {
            String err = "Oh no! An error was encountered, the file data could not be read.";
            System.out.println(err);
        }
        return new TaskList(taskList);
    }

    /**
     * Stores the list of tasks, in the TaskList object, into the designated file.
     *
     * @param taskList TaskList object containing a list of tasks to be saved in the designated file.
     */
    public void saveToFile(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(Storage.FILE_LOCATION);
            for (int i = 0; i < taskList.getListSize(); i++) {
                fileWriter.write(taskList.getTask(i).convertTaskToFileString() + "\n");
            }
            fileWriter.close();
        } catch (IOException ex) {
            String err = "Oh no! An error is encountered and the task file could not be updated.";
            System.out.println(err);
        }
    }

}
