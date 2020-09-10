package fei.tool;

import fei.exception.FeiException;
import fei.task.Task;

public class Ui {
    public String showLoadingError() {
        return FeiException.loadingException().getMessage();
    }

    public static String greeting() {
        return "Hello! It's me!\n"
                + "I'm Fei.\n"
                + "What can I do for you?";

    }

    public String byeMessage() {
        return "Bye. Hope to see you again soon!\n";
    }

    public String showList(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "There's currently no task in your list.\n";
        }
        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            response.append(String.format("%d.%s\n", i + 1, task));
        }
        return response.toString();
    }

    public String showMatching(TaskList tasks, String keyword) {
        StringBuilder response = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String description = task.getDescription();
            String[] words = description.split(" ");
            for (String word : words) {
                if (word.equals(keyword)) {
                    response.append(String.format("%d.%s%\n", i + 1, task));
                }
            }
        }
        return response.toString();
    }

    public String markDoneMsg(Task task) {
        return "Nice! I've marked this task as done: \n" + task;
    }

    public String addTaskMsg(Task added, TaskList tasks) {
        return "I've added this task: \n "
                + added + "\n"
                + String.format("Now you have %d tasks in the list.", tasks.size());
    }

    public String removeTaskMsg(Task removed, TaskList tasks) {
        return "Noted. I've removed this task: \n "
                + removed + "\n"
                + String.format("Now you have %d tasks in the list.", tasks.size());
    }

    public String showStats(TaskList tasks) {
        int done = 0;
        int total = tasks.size();
        for (int i = 0; i < total; i++) {
            if (tasks.get(i).isDone()) {
                done++;
            }
        }
        return String.format("You now have %d out of %d tasks marked as done.\n"
                + "There are still %d task(s) left.\n", done, total, total - done);
    }

}

