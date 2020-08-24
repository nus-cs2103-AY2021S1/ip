package main.java;

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
    public void welcomeWord() {
        System.out.println("//////////\n" + "->Hello! I'm Duke\n" +
                "->What can I do for you?" + "\n");
    }

    /**
     * Prints history tasks.
     * @param taskList Task list with past tasks.
     */
    public void showHistory(TaskList taskList) {
        if (taskList.taskCount() != 0) {
            System.out.println("\n-> Saved List:\n");
            System.out.println(taskList.toString());
            System.out.println("\n");
        }
    }

    /**
     * Prints all tasks to screen.
     * If there is no task in task list, inform the user.
     * @param taskList List of all tasks.
     */
    public void listTask(TaskList taskList) {
        if (taskList.taskCount() > 0) {
            System.out.println("\n-> Current List:\n");
            System.out.println(taskList.toString());
            System.out.println("\n");
        } else {
            System.out.println("\n//////There is NO task in your list now//////\n");
        }
    }

    /**
     * Informs user the new task addition.
     * Prints information base on type of task.
     * Informs user the count of tasks in task list.
     * @param newTask New task adding.
     * @param taskList List of all tasks.
     */
    public void newTaskAdded(Task newTask, TaskList taskList) {
        if (newTask instanceof Todo) {
            Todo newTodo = (Todo) newTask;
            System.out.println(
                    "\n-> I have added a Todo:\n" +
                            newTodo.toString() +
                            "\nYou have " + taskList.taskCount() + " tasks in your list currently.\n"
            );
        } else if (newTask instanceof Event) {
            Event newEvent = (Event) newTask;
            System.out.println(
                    "\n-> I have added an Event:\n" +
                            newEvent.toString() +
                            "\nYou have " + taskList.taskCount() + " tasks in your list currently.\n"
            );
        } else if (newTask instanceof Deadline) {
            Deadline newDeadline = (Deadline) newTask;
            System.out.println(
                    "\n-> I have added a Deadline:\n" +
                            newDeadline.toString() +
                            "\nYou have " + taskList.taskCount() + " tasks in your list currently.\n"
            );
        }
    }

    /**
     * Gives information of invalid order.
     * @param orderName Order name of invalid order.
     * @return String representation of invalid order.
     */
    public String invalidOrder(String orderName) {
        return "\n-> Oops, there is an error...\n" +
            "-> please add correct description to \"" + orderName + "\" order\n";
    }

    /**
     * Informs user with the failure of parsing inputs.
     * @param userInput User input of order.
     */
    public void parseFail(String userInput) {
        if (userInput.startsWith("todo")) {
            invalidTodoOrder();
        } else if (userInput.startsWith("deadline")) {
            invalidDeadlineOrder();
        } else if (userInput.startsWith("event")) {
            invalidEventOrder();
        }
    }

    /**
     * Informs user the done order is invalid.
     */
    public void invalidDoneOrder() {
        System.out.println(invalidOrder("done") +
                "-> done {order of task in task list}\n");
    }

    /**
     * Informs user the adding todo order is invalid.
     */
    public void invalidTodoOrder() {
        System.out.println(invalidOrder("todo") +
                "-> todo {task content}\n");
    }

    /**
     * Informs user the adding deadline order is invalid.
     */
    public void invalidDeadlineOrder() {
        System.out.println(invalidOrder("deadline") +
                "-> deadline {task content} /{yyyy-mm-dd}\n");
    }

    /**
     * Informs user the adding event order is invalid.
     */
    public void invalidEventOrder() {
        System.out.println(invalidOrder("event") +
                "-> event {task content} /{yyyy-mm-dd}\n");
    }

    /**
     * Informs user the delete order is invalid.
     */
    public void invalidDeleteOrder() {
        System.out.println(invalidOrder("delete") +
                "-> delete {order of task in task list}\n");
    }

    /**
     * Informs user the task operation is not exist.
     */
    public void taskDoesNotExist() {
        System.out.println("\n-> Sorry, this task does not exist...\n");
    }

    /**
     * Informs user the done order has been operated successfully.
     * @param task Task marked as done.
     */
    public void taskDone(Task task) {
        System.out.println("\n-> Good job! I have marked this task as done:\n" + task.toString() + "\n");
    }

    /**
     * Informs user the delete order has been operated successfully.
     * @param taskList List of tasks.
     * @param deletingTask Task being deleted
     */
    public void taskDeleted(TaskList taskList, Task deletingTask) {
        System.out.println("\n-> I have removed this task:\n" + deletingTask.toString() + "\n");
        System.out.println("Now you have " + taskList.taskCount() + " tasks in your list." + "\n");
    }

    /**
     * Informs user there is a general error in his input.
     */
    public void generalError() {
        System.out.println(
                "\n-> Sorry I cannot understand, please tap in your order correctly." +
                        "\n-> todo {task content}   || add a todo task" +
                        "\n-> deadline {task content} /{time}   || add a deadline task" +
                        "\n-> event {task content} /{time} || add an event task" +
                        "\n-> list   || list all tasks" +
                        "\n-> done {order of task in task list}   || mark a task as done\n");
    }

    /**
     * Say Goodbye to user.
     */
    public void goodBye() {
        System.out.println("\nBye. Hope to see you again soon!");
    }
}
