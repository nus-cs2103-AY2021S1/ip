package main.java;

public class Ui {

    public Ui() {

    }

    public void welcomeWord() {
        System.out.println("//////////\n" + "->Hello! I'm Duke\n" +
                "->What can I do for you?" + "\n");
    }

    public void showHistory(TaskList taskList) {
        if (taskList.taskCount() != 0) {
            System.out.println("\n-> Saved List:\n");
            System.out.println(taskList.toString());
            System.out.println("\n");
        }
    }

    public void listTask(TaskList taskList) {
        if (taskList.taskCount() > 0) {
            System.out.println("\n-> Current List:\n");
            System.out.println(taskList.toString());
            System.out.println("\n");
        } else {
            System.out.println("\n//////There is NO task in your list now//////\n");
        }
    }

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

    public String invalidOrder(String orderName) {
        return "\n-> Oops, there is an error...\n" +
            "-> please add correct description to \"" + orderName + "\" order\n";
    }

    public void parseFail(String userInput) {
        if (userInput.startsWith("todo")) {
            invalidTodoOrder();
        } else if (userInput.startsWith("deadline")) {
            invalidDeadlineOrder();
        } else if (userInput.startsWith("event")) {
            invalidEventOrder();
        }
    }

    public void invalidDoneOrder() {
        System.out.println(invalidOrder("done") +
                "-> done {order of task in task list}\n");
    }

    public void invalidTodoOrder() {
        System.out.println(invalidOrder("todo") +
                "-> todo {task content}\n");
    }

    public void invalidDeadlineOrder() {
        System.out.println(invalidOrder("deadline") +
                "-> deadline {task content} /{yyyy-mm-dd}\n");
    }

    public void invalidEventOrder() {
        System.out.println(invalidOrder("event") +
                "-> event {task content} /{yyyy-mm-dd}\n");
    }

    public void invalidDeleteOrder() {
        System.out.println(invalidOrder("delete") +
                "-> delete {order of task in task list}\n");
    }

    public void taskDoesNotExist() {
        System.out.println("\n-> Sorry, this task does not exist...\n");
    }

    public void taskDone(Task task) {
        System.out.println("\n-> Good job! I have marked this task as done:\n" + task.toString() + "\n");
    }

    public void taskDeleted(TaskList taskList, Task deletingTask) {
        System.out.println("\n-> I have removed this task:\n" + deletingTask.toString() + "\n");
        System.out.println("Now you have " + taskList.taskCount() + " tasks in your list." + "\n");
    }

    public void generalError() {
        System.out.println(
                "\n-> Sorry I cannot understand, please tap in your order correctly." +
                        "\n-> todo {task content}   || add a todo task" +
                        "\n-> deadline {task content} /{time}   || add a deadline task" +
                        "\n-> event {task content} /{time} || add an event task" +
                        "\n-> list   || list all tasks" +
                        "\n-> done {order of task in task list}   || mark a task as done\n");
    }

    public void goodBye() {
        System.out.println("\nBye. Hope to see you again soon!");
    }
}
