package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;
    private Ui ui;

    /**
     * Constructor of TaskList class.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        ui = new Ui();
    }

    /**
     * Second constructor of TaskList class when we retrieve data at the beginning.
     *
     * @param tasks list of tasks from database.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        ui = new Ui();
    }

    /**
     * Gets current list of tasks.
     *
     * @return current list of tasks.
     */
    public List<Task> getTasksList() {
        return this.tasks;
    }

    /**
     * Displays tasks to the users.
     */
    public void showTasks() {
        ui.printLineBackground();
        System.out.println("        Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("            " + (i + 1) + "." + tasks.get(i));
        }
        ui.printLineBackground();
    }

    /**
     * Gets reply to show all the tasks to the user.
     *
     * @return the reply to display all the tasks as string.
     */
    public String showTasksAsString() {
        String result = "___________________________________________\n";
        result += "        Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            result += "            " + (i + 1) + "." + tasks.get(i) + "\n";
        }
        result += "___________________________________________";
        return result;
    }
    /**
     * Sets the task at the particular position as done.
     *
     * @param taskPosition position of the task.
     */
    public void setDone(int taskPosition) {
        tasks.get(taskPosition - 1).markAsDone();
        ui.printLineBackground();
        System.out.println("        Nice! I've marked this task as done:");
        System.out.println("            " + tasks.get(taskPosition - 1));
        ui.printLineBackground();
    }

    /**
     * Sets the task at the particular position as done and display message.
     *
     * @param taskPosition position of the task.
     * @return the reply to DONE task as string.
     */
    public String setDoneAsString(int taskPosition) {
        tasks.get(taskPosition - 1).markAsDone();
        String result = "___________________________________________\n";
        result += "        Nice! I've marked this task as done:\n";
        result += "            " + tasks.get(taskPosition - 1) + "\n";
        result += "___________________________________________";
        return result;
    }

    /**
     * Adds a task type Todo.
     *
     * @param description description of the task.
     */
    public void addToDo(String description) {
        Task toDo = new Todo(description);
        tasks.add(toDo);
        ui.printLineBackground();
        System.out.println("        Got it. I've added this task:");
        System.out.println("            " + toDo);
        System.out.println("        Now you have " + tasks.size() + " tasks in the list.");
        ui.printLineBackground();
    }

    /**
     * Adds a task type Todo and display message.
     *
     * @param description description of the task.
     * @return the reply to Todo task as string.
     */
    public String addToDoAsString(String description) {
        Task toDo = new Todo(description);
        tasks.add(toDo);
        String result = "___________________________________________\n";
        result += "        Got it. I've added this task:\n";
        result += "            " + toDo + "\n";
        result += "        Now you have " + tasks.size() + " tasks in the list.\n";
        result += "___________________________________________";
        return result;
    }

    /**
     * Adds a task type Deadline
     *
     * @param description description of the task.
     * @param by time to finish the task.
     */
    public void addDeadline(String description, String by) {
        Task deadline = new Deadline(description, by);
        tasks.add(deadline);
        ui.printLineBackground();
        System.out.println("        Got it. I've added this task:");
        System.out.println("            " + deadline);
        System.out.println("        Now you have " + tasks.size() + " tasks in the list.");
        ui.printLineBackground();
    }

    /**
     * Adds a task type Deadline and display message.
     *
     * @param description description of the task.
     * @param by time to finish the task.
     * @return the reply to Deadline task as string.
     */
    public String addDeadlineAsString(String description, String by) {
        Task deadline = new Deadline(description, by);
        tasks.add(deadline);
        String result = "___________________________________________\n";
        result += "        Got it. I've added this task:\n";
        result += "            " + deadline + "\n";
        result += "        Now you have " + tasks.size() + " tasks in the list.\n";
        result += "___________________________________________";
        return result;
    }

    /**
     * Adds a task type Event.
     *
     * @param description description of the task.
     * @param at time that event occurs.
     */
    public void addEvent(String description, String at) {
        Task event = new Event(description, at);
        tasks.add(event);
        ui.printLineBackground();
        System.out.println("        Got it. I've added this task:");
        System.out.println("            " + event);
        System.out.println("        Now you have " + tasks.size() + " tasks in the list.");
        ui.printLineBackground();
    }

    /**
     * Adds a task type Event and display message.
     *
     * @param description
     * @param by
     * @return the reply to Event task as string.
     */
    public String addEventAsString(String description, String by) {
        Task event = new Event(description, by);
        tasks.add(event);
        String result = "___________________________________________\n";
        result += "        Got it. I've added this task:\n";
        result += "            " + event + "\n";
        result += "        Now you have " + tasks.size() + " tasks in the list.\n";
        result += "___________________________________________";
        return result;
    }

    /**
     * Deletes task at the particular position.
     *
     * @param taskPosition position of the task that user wants to delete.
     */
    public void deleteTask(int taskPosition) {
        ui.printLineBackground();
        System.out.println("        Noted. I've removed this task:");
        System.out.println("            " + tasks.remove(taskPosition - 1));
        System.out.println("        Now you have " + tasks.size() + " tasks in the list.");
        ui.printLineBackground();
    }

    /**
     * Deletes task at the particular position and display message.
     *
     * @param taskPosition position of the task that user wants to delete.
     * @return the reply to DELETE command as string.
     */
    public String deleteTaskAsString(int taskPosition) {
        String result = "___________________________________________\n";
        result += "        Noted. I've removed this task:\n";
        result += "            " + tasks.remove(taskPosition - 1) + "\n";
        result += "        Now you have " + tasks.size() + " tasks in the list.\n";
        result += "___________________________________________";
        return result;
    }
    /**
     * Filters tasks by a particular condition.
     *
     * @param inputArray an array of input information.
     */
    public void filterTask(String[] inputArray) {
        QueryOnTasks queryOnTasks = new QueryOnTasks();
        if (inputArray[1].equals("date")) {
            List<Task> queryList = queryOnTasks.filterByDate(tasks, inputArray[2]);
            ui.printLineBackground();
            System.out.println("        Here are the tasks in your query list:");
            for (int i = 0; i < queryList.size(); i++) {
                System.out.println("            " + (i + 1) + "." + queryList.get(i));
            }
            ui.printLineBackground();
        } else if (inputArray[1].equals("month")) {
            List<Task> queryList = queryOnTasks.filterByMonth(tasks, inputArray[2]);
            ui.printLineBackground();
            System.out.println("        Here are the tasks in your query list:");
            for (int i = 0; i < queryList.size(); i++) {
                System.out.println("            " + (i + 1) + "." + queryList.get(i));
            }
            ui.printLineBackground();
        } else if (inputArray[1].equals("year")) {
            List<Task> queryList = queryOnTasks.filterByYear(tasks, inputArray[2]);
            ui.printLineBackground();
            System.out.println("        Here are the tasks in your query list:");
            for (int i = 0; i < queryList.size(); i++) {
                System.out.println("            " + (i + 1) + "." + queryList.get(i));
            }
            ui.printLineBackground();
        }
    }

    /**
     * Finds tasks by a keyword.
     *
     * @param keyword keyword to find the tasks.
     */
    public void findTasks(String keyword) {
        ui.printLineBackground();
        System.out.println("        Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                System.out.println("        " + (i + 1) + "." + tasks.get(i));
            }
        }
        ui.printLineBackground();
    }
}
