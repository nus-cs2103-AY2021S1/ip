package seedu.duke;

import java.util.List;

/**
 * Holds the interaction between user and program.
 */
public class Ui {

    /**
     * Default constructor.
     */
    public Ui() {
    }

    /**
     * Prints welcome words to screen.
     */
    public String welcomeWord() {
        return ("//////////\n" + "->Hello! I'm Duke\n"
                + "->What can I do for you?" + "\n");
    }

    /**
     * Prints history tasks.
     *
     * @param taskList Task list with past tasks.
     */
    public String showHistory(TaskList taskList) {
        String result = "";
        if (taskList.taskCount() != 0) {
            result += "\n-> Saved List:\n"
                    + taskList.toString()
                    + "\n";
        } else {
            result = "No Task in your list now.";
        }
        return result;
    }

    /**
     * Prints all tasks to screen.
     * If there is no task in task list, inform the user.
     *
     * @param taskList List of all tasks.
     */
    public String listTask(TaskList taskList) {
        String result = "";
        if (taskList.taskCount() > 0) {
            result += "\n-> Current List:\n" + taskList.toString() + "\n";
        } else {
            result += "\n//////There is NO task in your list now//////\n";
        }
        return result;
    }

    /**
     * Informs user the new task addition.
     * Prints information base on type of task.
     * Informs user the count of tasks in task list.
     *
     * @param newTask  New task adding.
     * @param taskList List of all tasks.
     */
    public String newTaskAdded(Task newTask, TaskList taskList) {
        String result = "";
        if (newTask instanceof Todo) {
            Todo newTodo = (Todo) newTask;
            result += (
                    "\n-> I have added a Todo:\n"
                            + newTodo.toString()
                            + "\nYou have "
                            + taskList.taskCount()
                            + " tasks in your list currently.\n");
        } else if (newTask instanceof Event) {
            Event newEvent = (Event) newTask;
            result += (
                    "\n-> I have added an Event:\n"
                            + newEvent.toString()
                            + "\nYou have " + taskList.taskCount()
                            + " tasks in your list currently.\n");
        } else if (newTask instanceof Deadline) {
            Deadline newDeadline = (Deadline) newTask;
            result += ("\n-> I have added a Deadline:\n"
                            + newDeadline.toString()
                            + "\nYou have "
                            + taskList.taskCount()
                            + " tasks in your list currently.\n");
        }
        return result;
    }

    /**
     * Gives information of invalid order.
     *
     * @param orderName Order name of invalid order.
     * @return String representation of invalid order.
     */
    public String invalidOrder(String orderName) {
        return "\n-> Oops, there is an error...\n"
                + "-> please add correct description to \"" + orderName + "\" order\n";
    }

    /**
     * Informs user with the failure of parsing inputs.
     *
     * @param userInput User input of order.
     */
    public String parseFail(String userInput) {
        String result = "";
        if (userInput.startsWith("todo")) {
            result = invalidTodoOrder();
        } else if (userInput.startsWith("deadline")) {
            result = invalidDeadlineOrder();
        } else if (userInput.startsWith("event")) {
            result = invalidEventOrder();
        }
        return result;
    }

    /**
     * Informs user the done order is invalid.
     */
    public String invalidDoneOrder() {
        return (invalidOrder("done")
                + "-> done {order of task in task list}\n");
    }

    /**
     * Informs user the adding todo order is invalid.
     */
    public String invalidTodoOrder() {
        return (invalidOrder("todo")
                + "-> todo {task content}\n");
    }

    /**
     * Informs user the adding deadline order is invalid.
     */
    public String invalidDeadlineOrder() {
        return (invalidOrder("deadline")
                + "-> deadline {task content} /{yyyy-mm-dd}\n");
    }

    /**
     * Informs user the adding event order is invalid.
     */
    public String invalidEventOrder() {
        return (invalidOrder("event")
                + "-> event {task content} /{yyyy-mm-dd}\n");
    }

    /**
     * Informs user the delete order is invalid.
     */
    public String invalidDeleteOrder() {
        return (invalidOrder("delete")
                + "-> delete {order of task in task list}\n");
    }

    /**
     * Informs user the find order is invalid
     *
     * @return A string representing invalid find information.
     */
    public String invalidFindOrder() {
        return (invalidOrder("find")
                + "-> find {keyword}\n");
    }

    /**
     * Informs user the task operation is not exist.
     */
    public String taskDoesNotExist() {
        return ("\n-> Sorry, this task does not exist...\n");
    }

    /**
     * Informs user the done order has been operated successfully.
     *
     * @param task Task marked as done.
     */
    public String taskDone(Task task) {
        return ("\n-> Good job! I have marked this task as done:\n" + task.toString() + "\n");
    }

    /**
     * Informs user the delete order has been operated successfully.
     *
     * @param taskList     List of tasks.
     * @param deletingTask Task being deleted
     */
    public String taskDeleted(TaskList taskList, Task deletingTask) {
        return ("\n-> I have removed this task:\n" + deletingTask.toString() + "\n")
                + ("Now you have " + taskList.taskCount() + " tasks in your list." + "\n");
    }

    /**
     * Informs user there is a general error in his input.
     */
    public String generalError() {
        return (
                "\n-> Sorry I cannot understand, please tap in your order correctly."
                        + "\n-> todo {task content}   || add a todo task"
                        + "\n-> deadline {task content} /{time}   || add a deadline task"
                        + "\n-> event {task content} /{time} || add an event task"
                        + "\n-> list   || list all tasks"
                        + "\n-> done {order of task in task list}   || mark a task as done"
                        + "\n-> switch {list name without '.txt'} || switch to/create a task list"
                        + "\n-> current list || view current task list you are using"
                        + "\n-> all lists || view all task lists you created\n");
    }

    /**
     * Prints result of find order.
     *
     * @param resultList List of result from find order.
     */
    public String showFindResult(List<Task> resultList) {
        String result = "";
        result += ("//////////Matching Result In Your List///////////\n");
        if (resultList.size() == 0) {
            result += ("No Match Result...\n");
        } else {
            for (int i = 0; i < resultList.size(); i++) {
                result += (i + ". " + resultList.get(i).toString());
            }
        }
        result += ("\n//////////////////////////////////////////////////\n");
        return result;
    }

    /**
     * Prints all the task lists.
     *
     * @return A string presents all the task lists.
     */
    public String showAllLists() {
        String result = "You have these task lists:\n";
        Storage storage = new Storage();
        List<String> allLists = storage.getAllLists();
        for (String listName : allLists) {
            if (!listName.equals("currentList.txt")) {
                result += listName + "\n";
            }
        }
        return result;
    }

    /**
     * Give the representation of current under usage task list.
     *
     * @return A string representing current task list.
     */
    public String showCurrentList() {
        String result = "You are using this list:\n";
        Storage storage = new Storage();
        result += storage.getCurrentList();
        return result;
    }

    public String listSwitched(String userInput) {
        return "Switched to:" + userInput;
    }

    public String invalidSwitchOrder() {
        return "Sorry your switch order is invalid.";
    }

    /**
     * Gives response to null order.
     *
     * @return Response to null order.
     */
    public String noOrder() {
        return "I can't guess your heart man.";
    }

    /**
     * Say Goodbye to user.
     */
    public String goodBye() {
        return ("\nBye. Hope to see you again soon!");
    }
}
