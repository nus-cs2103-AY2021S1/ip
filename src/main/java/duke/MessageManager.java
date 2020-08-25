package duke;

import duke.tasks.Task;

public class MessageManager {

    static String greetMessage = "Hello. What can I do for you?";
    static String byeMessage = "Bye. Hope to see you again soon!";
    static String listStringFormat = "Here are the tasks in your list:\n" +
            "%sYou have %s task(s) in your list.";
    static String successfulTaskAddStringFormat = "Got it. I've added this task:\n" +
            "%s\n" +
            "Now you have %d task(s) in the list.";
    static String successfulTaskCompleteStringFormat = "Nice! I've marked this task as done:\n" +
            "%s";
    static String successfulTaskDeleteStringFormat = "Noted. I've removed this task:\n" +
            "%s\n" +
            "Now you have %d task(s) in the list.";

    public static String getListMessage(TaskManager taskManager) {
        return String.format(
                listStringFormat,
                taskManager.toString(),
                taskManager.getTaskCount());
    }


    public static String getDeleteSuccessMessage(Task task, TaskManager taskManager) {
        return String.format(
                successfulTaskDeleteStringFormat,
                task,
                taskManager.getTaskCount());
    }

    public static String getGreetMessage() {
        return greetMessage;
    }

    public static String getByeMessage() {
        return byeMessage;
    }

    public static String getCompleteSuccessMessage(Task task) {
        return String.format(
                successfulTaskCompleteStringFormat,
                task);
    }

    public static String getAddSuccessMessage(Task task, TaskManager taskManager) {
        return String.format(
                successfulTaskAddStringFormat,
                task,
                taskManager.getTaskCount());
    }}
