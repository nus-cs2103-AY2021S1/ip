package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Manages saving and loading a {@link TaskList} to/from a specified saveFile.
 */
public class Storage {
    static final SimpleDateFormat INPUT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    static final SimpleDateFormat SAVE_DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy");

    private String saveFile;

    /**
     * Constructs the Storage with the specified saveFile location.
     * @param saveFile location of the saved taskList. eg. save.txt
     */
    public Storage(String saveFile) {
        this.saveFile = saveFile;
    }

    /**
     * Loads a TaskList from the specified saveFile location.
     * @return a TaskList constructed form the saveFile.
     * @throws DukeException if the specified saveFile is not found.
     */
    public TaskList load() throws DukeException{
        TaskList taskList = new TaskList();

        try {
            File save = new File(saveFile);
            Scanner saveSc = new Scanner(save);
            while (saveSc.hasNextLine()) {
                String taskString = saveSc.nextLine();
                Task loadedTask = stringToTask(taskString);
                taskList.addTask(loadedTask);
            }
            saveSc.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("save.txt not found. Creating empty list.");
        }

        return taskList;
    }

    /**
     * Saves the given TaskList into the specified saveFile location.
     * @param taskList the TaskList to be saved.
     */
    public void save(TaskList taskList) {
        try {
            FileWriter save = new FileWriter(saveFile);
            for (Task task : taskList) {
                save.write(task.toString() + "\n");
            }
            save.close();
        } catch (IOException e) {
            System.out.println("Saving failed!");
        }
    }


    /**
     * Constructs a Task from its String format.
     * @param taskString the String format to build a Task from.
     * @return the constructed Task.
     * @throws DukeException If the String has a wrong format and can't be converted.
     */
    private Task stringToTask(String taskString) throws DukeException{
        Task newTask;

        Scanner taskSc = new Scanner(taskString);
        String taskType = taskSc.next();
        String taskCompletion = taskSc.next();
        String taskDesc = taskSc.nextLine().trim();

        switch (taskType) {
        case "[TODO]":
            newTask = new Todo(taskDesc);
            break;
        case "[DEADLINE]":
            try {
                String[] nameAndDeadline = taskDesc.split(" \\| by: ");
                Date deadlineDate = SAVE_DATE_FORMAT.parse(nameAndDeadline[1]);
                newTask = new Deadline(nameAndDeadline[0], INPUT_DATE_FORMAT.format(deadlineDate));
            } catch (ParseException e) {
                throw new DukeException("Loading date error: " + e);
            }
            break;
        case "[EVENT]":
            try {
                String[] nameAndEvent = taskDesc.split(" \\| at: ");
                Date eventDate = SAVE_DATE_FORMAT.parse(nameAndEvent[1]);
                newTask = new Event(nameAndEvent[0], INPUT_DATE_FORMAT.format(eventDate));
            } catch (ParseException e) {
                throw new DukeException("Loading date error: " + e);
            }
            break;
        default:
            throw new DukeException("Loading task failed: unrecognized task type: " + taskType);
        }

        if (taskCompletion.equals("[✓]")) {
            newTask.markDone();
        }

        return newTask;
    }

}
