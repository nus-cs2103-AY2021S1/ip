package duke.Tasks;

import duke.Ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Initialises a list to store all tasks captured in duke.
 */
public class TaskList {

    /** A list to store all user tasks. */
    private ArrayList<Task> listOfTasks;

    private ArrayList<String> preProcessedTask;

    /**
     * Constructor to create an empty taskList.
     */
    public TaskList() {
        this.preProcessedTask = new ArrayList<>();
        this.listOfTasks = new ArrayList<>();
    }

    /**
     * Constructor to load a taskList from the data file.
     * @param inputList List of tasks from data file.
     */
    public TaskList(ArrayList<String> inputList){
        this.preProcessedTask = inputList;
        this.listOfTasks = new ArrayList<>();
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
        for (Task task : this.listOfTasks){
            dataFile.add(task.toData());
        }
        return dataFile;
    }
}
