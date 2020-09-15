package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class TaskList {
    private final ArrayList<Task> taskList;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    /**
     * Constructor for empty taskList
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     *
     * @param savedTasks
     * @throws IOException
     */
    public TaskList (File savedTasks) throws IOException {
        this.taskList = new ArrayList<Task>();
        FileReader fr = new FileReader(savedTasks); //reads the file
        BufferedReader br = new BufferedReader(fr); //creates a buffering character input stream
        String line = br.readLine();
        while (line != null) {
            String[] parameters = line.split("\\|");
            String taskType = parameters[0];
            boolean completionStatus = parameters[1].equals("1");
            String taskDescription = parameters[2];
            Task task;
            if (taskType.equals("T")) {
                task = new Todo(taskDescription, completionStatus);
            } else if (taskType.equals("E")) {
                task = new Event(taskDescription, LocalDateTime.parse(parameters[3].strip()), completionStatus);
            } else {
                task = new Deadline(taskDescription, LocalDateTime.parse(parameters[3].strip()), completionStatus);
            }
            this.taskList.add(task);
            line = br.readLine();
        }
        fr.close();
    }

    private boolean descriptionIsPresent(String ...parameters) {
        return parameters.length > 0 && !parameters[0].isBlank();
    }

    private boolean dateIsPresent(String ...parameters) {
        return parameters.length > 1 && !parameters[1].isBlank();
    }

    /**
     * Adds a Todo Task to the task list.
     *
     * @param parameters the description of the task
     * @return the Todo task
     */
    public Task addTodoTask(String ...parameters) throws DukeExceptions.IncompleteCommandException {
        if (descriptionIsPresent(parameters)) {
            //assert parameters.length > 0 && !parameters[0].isBlank(): "insufficient amount of parameters";
            String taskDescription = parameters[0];
            Task newTask = new Todo(taskDescription);
            this.taskList.add(newTask);
            return newTask;
        } else {
            String errorMessage = "Master the description of Todo cannot be empty\n";
            throw new DukeExceptions.IncompleteCommandException(errorMessage);
        }
    }

    /**
     * Adds an Event Task to the task list.
     *
     * @param parameters the description of the task
     * @return the Event task
     */
    public Task addEventTask(String ...parameters) throws DukeExceptions.IncompleteCommandException {
        if (!descriptionIsPresent(parameters)) {
            String errorMessage = "Master the description of Event cannot be empty\n";
            throw new DukeExceptions.IncompleteCommandException(errorMessage);
        } else {
            //assert parameters.length == 2 : "insufficient amount of parameters";
            String eventDescription = parameters[0].strip();
            LocalDateTime eventDate = LocalDateTime.parse(parameters[1].strip(), formatter);
            Task newTask = new Event(eventDescription, eventDate);
            this.taskList.add(newTask);
            return newTask;
        }
    }

    /**
     * Adds a Deadline Task to the task list.
     *
     * @param parameters the description of the task
     * @return the Event task
     */
    public Task addDeadlineTask(String ...parameters) throws DukeExceptions.IncompleteCommandException {
        if (!descriptionIsPresent(parameters)) {
            String errorMessage = "Master the description of Event cannot be empty\n";
            throw new DukeExceptions.IncompleteCommandException(errorMessage);
        } else {
            //assert parameters.length == 2 : "insufficient amount of parameters";
            String eventDescription = parameters[0].strip();
            LocalDateTime eventDate = LocalDateTime.parse(parameters[1].strip(), formatter);
            Task newTask = new Deadline(eventDescription, eventDate);
            this.taskList.add(newTask);
            return newTask;
        }
    }

    /**
     * Deletes a task from the task list
     *
     * @param index the index of the task in the list
     * @return the deleted task
     */
    public Task deleteTask(int index) {
        Task task = this.taskList.get(index);
        this.taskList.remove(index);
        return task;
    }

    /**
     * Retrieves the tasks that is due/occurring at a specified date
     *
     * @param dueDate a string representation of the due/event date for the tasks to be searched up
     * @return the string representation of all the task due on that date
     */
    public String getTaskDueOn(String dueDate) {
        LocalDate date = LocalDate.parse(dueDate.strip(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String output = "";
        for (Task task : this.taskList) {
            if (task.isDueOn(date)) {
                output += task.toString() + "\n";
            }
        }
        return (output == "" ? "None\n" : output);
    }

    /**
     * Retrieves the tasks that has the specified keyword in their description
     *
     * @param parameter a string representation of the keyword to be searched up
     * @return the string representation of all the task that contains the keyword
     */

    public String getTaskWithKeyword(String parameter) {
        String output = "";
        for (Task task : this.taskList) {
            if (task.hasKeyword(parameter.strip())) {
                output += task.toString() + "\n";
            }
        }
        return (output == "" ? "None\n" : output);
    }

    /**
     * Returns True if all the tasks in the taskList is done.
     * @return True if all tasks in the taskList are marked done, false otherwise
     */
    public boolean allDone() {
        for (Task task : this.taskList) {
            if (!task.isDone()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Mark task at the index of the tasklist as done
     *
     * @param index
     * @return Returns the task that was marked as completed
     */
    public Task completeTask(int index) {
        this.getTask(index).markDone();
        return this.getTask(index);
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public int getNoTask() {
        return this.taskList.size();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    @Override
    public String toString() {
        String output;
        if (this.taskList.size() > 0) {
            output = "Here are the tasks in your list:\n";
            for (int i = 1; i < this.taskList.size() + 1; i++) {
                output += String.valueOf(i) + ". " + this.taskList.get(i - 1).toString() + "\n";
            }
        } else {
            output = "You have no tasks master :)";
        }
        return output;
    }
}
