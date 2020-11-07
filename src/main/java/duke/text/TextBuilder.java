package duke.text;

import duke.tasks.Task;

import java.util.ArrayList;

public class TextBuilder {

    public static String buildTaskListMsg(String header, ArrayList<Task> taskList) {
        StringBuilder temp = new StringBuilder(header);
        int counter = 1;
        for (Task task : taskList) {
            temp.append("\n");
            temp.append(counter).append(". ").append(task.toString());
            counter++;
        }
        return temp.toString();
    }

    public static String buildTaskAddedSuccessMsg(Task task, int size) {
        return ("Got it. I've added this task:\n  " +
                size + ". " + task.toString() + "\n" +
                "Now you have " + size + " tasks in the list.");
    }

    public static String buildDuplicateTaskMsg(Task duplicateTask, int duplicateTaskNum) {
        return ("Task already exists:\n  " +
                (duplicateTaskNum + 1) + ". " + duplicateTask.toString() + "\n" +
                "No new task is added");
    }

    public static String buildMarkDoneMsg(Task task, int position) {
        if (task.markDone()) {
            return ("beri gude, finish that thing liao\n  " +
                    position + ". " + task.toString());
        } else {
            return ("Task alr finish liao\n  " +
                    position + ". " + task.toString());
        }
    }

    public static String buildDeleteTaskMsg(Task task, int position, int numTasksLeft) {
        return ("Noted. I've removed this task:\n  " +
                position + ". " + task.toString() + "\n" +
                "Now you have " + numTasksLeft + " tasks in the list.");
    }
}
