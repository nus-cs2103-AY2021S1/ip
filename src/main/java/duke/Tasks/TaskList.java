package duke.Tasks;

import duke.Commands.AddCommand;
import duke.Commands.Command;
import duke.Exceptions.DukeException;
import duke.Ui.Ui;
import javafx.fxml.LoadException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Initialises a list to store all tasks captured in duke.
 */
public class TaskList {

    /** A list to store all user tasks. */
    private ArrayList<Task> listOfTasks;
    private ArrayList<String> preProcessedTasks;

    /**
     * Constructor to create an empty taskList.
     */
    public TaskList() {
        this.preProcessedTasks = new ArrayList<>();
        this.listOfTasks = new ArrayList<>();
    }

    /**
     * Constructor to load a taskList from the data file.
     * @param inputList List of tasks from data file.
     */
    public TaskList(ArrayList<String> inputList) throws DukeException {
        this.preProcessedTasks = inputList;
        this.listOfTasks = new ArrayList<>();
        this.parseFile(this.preProcessedTasks);
    }

    /**
     * Returns the list of tasks.
     * @return The list of tasks.
     */
    public ArrayList<Task> getListOfTasks() {
        return this.listOfTasks;
    }

    /**
     * Returns the list size.
     * @return The number of tasks in the list.
     */
    public int listSize() {
        return this.listOfTasks.size();
    }

    /**
     * Prints the descriptions of all tasks in the list.
     * @param ui The Ui object to print messages.
     */
    public void printList(Ui ui) {
        int counter = 1;
        for (Task task: this.listOfTasks) {
            ui.printTask(counter, task);
            counter += 1;
        }
    }

    /**
     * Adds a toDo task.
     * @param ui Ui object to print messages.
     * @param description Description of the task to be created.
     */
    public void addTodo(Ui ui, String description) {
        ToDo newTask = new ToDo(description);
        this.listOfTasks.add(newTask);
        ui.addTask(listOfTasks.size(), newTask);
    }

    /**
     * Adds a Event task.
     * @param ui Ui object to print messages.
     * @param description Description of the task to be created.
     * @param localDate Time of the task.
     * @param localTime Date of the task.
     */
    public void addEvent(Ui ui, String description, LocalDate localDate, LocalTime localTime) {
        Event newEvent = new Event(description, localDate, localTime);
        this.listOfTasks.add(newEvent);
        ui.addTask(listOfTasks.size(), newEvent);
    }

    /**
     * Adds a Deadline task.
     * @param ui Ui object to print messages.
     * @param description Description of the task to be created.
     * @param localDate Time of the task.
     * @param localTime Date of the task.
     */
    public void addDeadline(Ui ui, String description, LocalDate localDate, LocalTime localTime) {
        Deadline newDeadline = new Deadline(description, localDate, localTime);
        this.listOfTasks.add(newDeadline);
        ui.addTask(listOfTasks.size(), newDeadline);
    }

    /**
     * Marks a task as completed.
     * @param index The index of the task to be completed.
     * @param ui Ui object to print messages.
     */
    public void taskDone(int index, Ui ui) {
        Task temp = this.listOfTasks.get(index);
        temp.completed();
        ui.markDone(temp);
    }

    /**
     * Deletes a task.
     * @param index The index of the task to be deleted.
     * @param ui Ui object to print messages.
     */
    public void taskDelete(int index, Ui ui) {
        Task temp = this.listOfTasks.get(index);
        this.listOfTasks.remove(index);
        ui.markDelete(listOfTasks.size(), temp);
    }

    /**
     * Converts all tasks in the list to a format for saving to data file.
     * @return ArrayList of Strings representing each task to be saved by data file.
     */
    public ArrayList<String> convertToFile() {
        ArrayList<String> dataFile = new ArrayList<>();
        for (Task task: this.listOfTasks){
            dataFile.add(task.toData());
        }
        return dataFile;
    }

    /**
     * Find tasks that match the keyword from the task list.
     * @param ui Ui object to print messages to users.
     * @param description Task description.
     * @throws DukeException
     */
    public void findTask(Ui ui, String description) throws DukeException {
        ArrayList<Task> result = new ArrayList<>();
        for (Task t : this.listOfTasks) {
            if (t.isResult(description)) {
                result.add(t);
            }
        }
        if (result.size() == 0) {
            throw new DukeException("    No matching task found :(");
        }
        int counter = 1;
        ui.printResult();
        for (Task task: result) {
            ui.printTask(counter, task);
            counter += 1;
        }
    }

    public void parseFile(ArrayList<String> listOfTasks) throws DukeException {

        for (String t: listOfTasks) {
            String[] task = t.split("///", 4);
            if (task.length != 4) {
                throw new DukeException("    Error in data file dividers ///");
            }

            if (!task[1].equals("1") && !task[1].equals("0")) {
                throw new DukeException("    Error encountered in reading task done status." + !task[1].equals("0") + task[1]);
            }

            switch (task[0]) {

            case "T" :
                ToDo newTask = new ToDo(task[2]);
                if (task[1].equals("1")) {
                    newTask.completed();
                }
                this.listOfTasks.add(newTask);
                break;

            case "E" :
                String[] dateTime = task[3].split(" ", 2);
                if (dateTime.length != 2) {
                    throw new DukeException("    Error in date time formatting encountered in data file");
                }
                try {
                    LocalDate localDate = LocalDate.parse(dateTime[0]);
                    LocalTime localTime = LocalTime.parse(dateTime[1]);

                    Event newEvent = new Event(task[2], localDate, localTime);
                    if (task[1].equals("1")) {
                        newEvent.completed();
                    }
                    this.listOfTasks.add(newEvent);
                } catch (DateTimeParseException e) {
                    throw new DukeException("    Error in date time formatting encountered in data file");
                }
                break;

            case "D" :
                String[] dateTime1 = task[3].split(" ", 2);
                if (dateTime1.length != 2) {
                    throw new DukeException("    Error in date time formatting encountered in data file");
                }
                try {
                    LocalDate localDate = LocalDate.parse(dateTime1[0]);
                    LocalTime localTime = LocalTime.parse(dateTime1[1]);

                    Deadline newDeadline = new Deadline(task[2], localDate, localTime);
                    if (task[1].equals("1")) {
                        newDeadline.completed();
                    }
                    this.listOfTasks.add(newDeadline);
                } catch (DateTimeParseException e) {
                    throw new DukeException("    Error in date time formatting encountered in data file");
                }
                break;

            default:
                throw new DukeException("    Error parsing file.");
            }
        }
    }
}
