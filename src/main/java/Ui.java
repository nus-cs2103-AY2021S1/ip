package main.java;

public class Ui {
    private static final String INDENTATION = "    ";

    private void print(String message) {
        System.out.println(message);
    }

    public void greetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String sentence = "Hola! Duke reporting for duty\nWhat can I do for you sir?";
        print(logo + sentence);
    }

    public void goodBye() {
        print("Bye Master. Hope to see you again soon!");
    }

    private String indentMessage(String message) {
        return INDENTATION + message;
    }

    private String buildMessage(String[] strings) {
        String result = "";
        for (int i = 0; i < strings.length; i++) {
            result += strings[i] + (i == (strings.length - 1) ? "" : "\n");
        }
        return result;
    }

    public void markTaskAsDone(Task task) {
        String result = "Nice! I've marked this task as done:\n";
        result += indentMessage(task.toString());
        print(result);
    }

    // for add, delete task from the list
    public void taskListModify(String message, Task task, TaskList taskList) {
        String newTaskListLength = "Now you have " + taskList.getSize() + " items in the list";
        String taskDescription = indentMessage(task.toString());
        String[] strings = {message, taskDescription, newTaskListLength};
        String result = buildMessage(strings);
        print(result);
    }

    public void printFullList(TaskList taskList) {
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.getSize(); i++) {
            int taskNumber = i + 1;
            String taskDescription = taskNumber + "." + taskList.getTask(i).toString()
                    + (i == taskList.getSize() - 1 ? "" : "\n");
            result = result + taskNumber + "." + indentMessage(taskDescription);
        }
        print(result);
    }

    public void printError(DukeException error) {
        print(error.toString());
    }
}
