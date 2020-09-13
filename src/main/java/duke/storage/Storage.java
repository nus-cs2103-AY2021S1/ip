package duke.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.DukeAction;
import duke.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Priority;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * The Storage class implements a storage
 * with functionalities to load data from the
 * storage and write data to storage.
 *
 * @author Amy Lim Zhi Ting
 * @version v0.1
 */
public class Storage {
    protected String filepath;
    protected String dirpath;

    /**
     * Initialises a storage object that stores any updates to the
     * list of tasks in the hard disk and retrieves task information
     * from the hard disk when Duke starts.
     *
     * @param filepath string file path of the file where Duke stores
     *                 task information
     */
    public Storage(String filepath) {
        this.filepath = filepath;
        this.dirpath = filepath.substring(0, 6);
    }

    /**
     * Returns the list of tasks stored in the hard disk.
     *
     * @return List list of Task stored in hard disk.
     * @throws DukeException If could not load task information from hard disk.
     */
    public List<Task> loadData() throws DukeException {
        //read Duke's data file and load tasks into tasks list
        List<Task> tasks = new ArrayList<>();

        try {
            File dir = new File(this.dirpath);
            File taskFile = new File(this.filepath);
            if (dir.mkdir()) {
                // new directory created
                System.out.println("Welcome. Dino has created a new directory "
                        + "to store your data.");
                if (taskFile.createNewFile()) {
                    // new file created
                    System.out.println("Dino has successfully "
                            + "created a new file to store your task list.");
                } else {
                    // file creation failed
                    System.out.println("Dino could not create a new file to store your task list.");
                }
            } else if (taskFile.createNewFile()) {
                // directory already exists
                // new file created
                System.out.println("Welcome. Dino has successfully created "
                        + "a new file to store your task list.");
            } else {
                // directory and file already exist
                // load data from file
                Scanner scan = new Scanner(taskFile);
                loadDataFromFile(tasks, scan);

                System.out.println("Welcome back. Dino has successfully loaded your task data.");
            }
        } catch (IOException e) {
            throw new DukeException("Dino could not load your task data.");
        }
        System.out.println("____________________________________________________________");
        return tasks;
    }

    /**
     * Loads all the tasks from the file read by scanner, into the task list.
     * @param tasks task list to update
     * @param scan scanner that reads file
     * @throws DukeException If task type is invalid.
     */
    public void loadDataFromFile(List<Task> tasks, Scanner scan) throws DukeException {

        while (scan.hasNextLine()) {

            String taskString = scan.nextLine();
            if (!taskString.equals("")) {
                String[] taskSplit = taskString.split("!@#");
                String taskType = taskSplit[0];

                switch (taskType) {

                case "T":
                    loadTodoTask(taskSplit, tasks);
                    break;

                case "D":
                    loadDeadlineTask(taskSplit, tasks);
                    break;

                case "E":
                    loadEventTask(taskSplit, tasks);
                    break;

                default:
                    throw new DukeException("Invalid task type.");
                }
            }
        }
    }

    /**
     * Add todo task from file to task list.
     * @param taskSplit task string in file split by regex !@#
     * @param tasks task list
     */
    public void loadTodoTask(String[] taskSplit, List<Task> tasks) {
        String taskDone = taskSplit[1];
        String taskDesc = taskSplit[2];

        Todo todo = new Todo(taskDesc);

        if (taskDone.equals("1")) {
            todo.markAsDone();
        }

        String priority = taskSplit[3];
        switch (priority) {
        case "HIGH":
            todo.setPriority(Priority.HIGH);
            break;
        case "MID":
            todo.setPriority(Priority.MID);
            break;
        case "LOW":
            todo.setPriority(Priority.LOW);
            break;
        default:
            break;
        }

        tasks.add(todo);
    }

    /**
     * Add deadline task from file to task list.
     * @param taskSplit task string in file split by regex !@#
     * @param tasks task list
     */
    public void loadDeadlineTask(String[] taskSplit, List<Task> tasks) throws DukeException {
        String taskDone = taskSplit[1];
        String taskDesc = taskSplit[2];
        String taskDeadline = taskSplit[3] + " " + taskSplit[4];

        Deadline deadline = Deadline.createDeadline(taskDesc, taskDeadline);

        if (taskDone.equals("1")) {
            deadline.markAsDone();
        }

        String priority = taskSplit[5];
        switch (priority) {
        case "HIGH":
            deadline.setPriority(Priority.HIGH);
            break;
        case "MID":
            deadline.setPriority(Priority.MID);
            break;
        case "LOW":
            deadline.setPriority(Priority.LOW);
            break;
        default:
            break;
        }

        tasks.add(deadline);
    }

    /**
     * Add event task from file to task list.
     * @param taskSplit task string in file split by regex !@#
     * @param tasks task list
     */
    public void loadEventTask(String[] taskSplit, List<Task> tasks) throws DukeException {
        String taskDone = taskSplit[1];
        String taskDesc = taskSplit[2];
        String taskDateTime = taskSplit[3] + " " + taskSplit[4];

        Event event = Event.createEvent(taskDesc, taskDateTime);

        if (taskDone.equals("1")) {
            event.markAsDone();
        }

        String priority = taskSplit[5];
        switch (priority) {
        case "HIGH":
            event.setPriority(Priority.HIGH);
            break;
        case "MID":
            event.setPriority(Priority.MID);
            break;
        case "LOW":
            event.setPriority(Priority.LOW);
            break;
        default:
            break;
        }

        tasks.add(event);
    }

    /**
     * Writes action done to task into hard disk.
     * If action is ADD, Dino adds task to hard disk.
     * If action is DELETE, Dino deletes task from hard disk.
     * If action is MARK_DONE, Dino marks the specific task in hard disk as done.
     *
     * @param task Task to be acted upon.
     * @param action DukeAction action done to task.
     * @throws DukeException If Dino could not write to hard disk.
     */
    public void writeToFile(Task task, DukeAction action) throws DukeException {
        try {
            File inputFile = new File(this.filepath);
            File tempFile = new File("./data/myTempFile.txt");
            BufferedReader reader = new BufferedReader(new FileReader(this.filepath));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            switch (action) {

            case ADD:
                FileWriter fw = new FileWriter(this.filepath, true);
                fw.write(task.storedTaskString() + "\n");
                fw.close();
                System.out.println("Success!");
                break;

            case DELETE:
                deleteTaskFromFile(task, reader, writer);
                deleteAndReplaceFile(inputFile, tempFile);
                break;

            case MARK_DONE:
                markTaskDoneInFile(task, reader, writer);
                deleteAndReplaceFile(inputFile, tempFile);
                break;

            case SET_PRIORITY:
                setTaskPriorityInFile(task, reader, writer);
                deleteAndReplaceFile(inputFile, tempFile);
                break;

            default:
                throw new DukeException("Not a valid action.");
            }

        } catch (IOException e) {
            throw new DukeException("Dino could not write task data to hard disk.");
        }
    }

    /**
     * Deletes task from the file.
     * @param task task to be deleted
     * @param reader reader to read file
     * @param writer writer to write to file
     * @throws IOException If I/O operation interrupted.
     */
    public void deleteTaskFromFile(Task task, BufferedReader reader, BufferedWriter writer)
            throws IOException {
        String lineToRemove = task.storedTaskString();
        String currentLine;

        while ((currentLine = reader.readLine()) != null) {
            if (!currentLine.equals(lineToRemove)) {
                writer.write(currentLine);
                writer.newLine();
            }
        }

        writer.close();
        reader.close();
        System.gc();
    }

    /**
     * Marks task in file as done.
     * @param task task to be marked done
     * @param reader reader to read file
     * @param writer writer to write to file
     * @throws IOException If I/O operation interrupted.
     */
    public void markTaskDoneInFile(Task task, BufferedReader reader, BufferedWriter writer)
            throws IOException {
        String lineToMarkDone = task.storedTaskString();
        String currentL;

        while ((currentL = reader.readLine()) != null) {
            if (!currentL.equals(lineToMarkDone)) {
                writer.write(currentL);
            } else {
                String taskType = currentL.substring(0, 4);
                String taskDesc = currentL.substring(5);
                writer.write(taskType + "1" + taskDesc);
            }
            writer.newLine();
        }

        writer.close();
        reader.close();
        System.gc();
    }

    /**
     * Sets priority of task in file.
     * @param task Task to set priority
     * @param reader reader to read file
     * @param writer writer to write to file
     * @throws IOException If I/O operation interrupted.
     * @throws DukeException If priority is not valid.
     */
    public void setTaskPriorityInFile(Task task, BufferedReader reader, BufferedWriter writer)
            throws IOException, DukeException {
        Priority priority = task.getPriority();
        String currentTask = task.storedTaskString();
        String taskToSetPriority;

        switch (priority) {
        case HIGH:
            taskToSetPriority = currentTask.substring(0, currentTask.length() - 7);
            break;
        case MID:
        case LOW:
            taskToSetPriority = currentTask.substring(0, currentTask.length() - 6);
            break;
        default:
            throw new DukeException("Something went wrong in setting priority to task in file.");
        }

        String currentL;
        while ((currentL = reader.readLine()) != null) {
            if (!currentL.contains(taskToSetPriority)) {
                writer.write(currentL);
            } else {
                writer.write(task.storedTaskString());
            }
            writer.newLine();
        }

        writer.close();
        reader.close();
        System.gc();
    }

    /**
     * Deletes input file and renames temporary file as the input file.
     * @param inputFile file to be deleted
     * @param tempFile file to be renamed
     * @throws DukeException If input file could not be deleted,
     * or temporary file could not be renamed.
     */
    public void deleteAndReplaceFile(File inputFile, File tempFile) throws DukeException {
        if (inputFile.delete()) {
            // original file successfully deleted
            if (tempFile.renameTo(inputFile)) {
                // temporary file successfully renamed to original file
                System.out.println("Success!");
            } else {
                throw new DukeException("Dino could not write task data to hard disk.");
            }
        } else {
            throw new DukeException("Dino could not write task data to hard disk.");
        }
    }
}
