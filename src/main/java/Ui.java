package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Ui {
    private Scanner sc;
    private final String indentation = "  ";

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readLine() {
        return this.sc.nextLine();
    }

    public void welcome() {
        String message = "Hi! My name is Duke.\nWhat do you want me to do?";
        sendMessage(message);
    }

    public void exit() {
        String message = "Bye. Thank you for using me!";
        sendMessage(message);
    }

    public String doneSuccess(Task task) {
        return "Sucessfully marked this task as done:\n" + indentation + task.toString();
    }

    public String deleteSuccess(Task task, int taskSize) {
        return "Okay. I will delete this task:\n" + indentation + task + "\n" +
                "Now you have " + taskSize + " " + (taskSize == 1 ? "task " : "tasks ") + "in the list.";
    }

    public String addSuccess(Task task, int taskSize) {
        return "Okay. I will add this task:\n" + indentation + task + "\n" +
                "Now you have " + taskSize + " " + (taskSize == 1 ? "task " : "tasks ") + "in the list.";
    }

    public String showList(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here is the tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + ". " + tasks.getTask(i) + "\n");
        }

        return sb.toString().trim();
    }

    public String eachTaskAfter(LocalDate date, List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here is the tasks after "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":\n");
        int count = 1;
        for (Task task : tasks) {
            if (task instanceof ToDoTask) {
                continue;
            } else if (task instanceof DeadlineTask) {
                DeadlineTask deadlineTask = (DeadlineTask) task;
                if (date.isBefore(deadlineTask.getDate().toLocalDate())) {
                    sb.append(count + ". " + task + "\n");
                    count++;
                }
            } else if (task instanceof EventTask) {
                EventTask eventTask = (EventTask) task;
                if (date.isBefore(eventTask.getDate().toLocalDate())) {
                    sb.append(count + ". " + task + "\n");
                    count++;
                }
            }
        }

        return sb.toString().trim();
    }

    public void showError(DukeException e) {
        sendMessage(e.getMessage());
    }

    public void sendMessage(String sendMessage) {
        System.out.println(createLine(sendMessage));
    }

    public String createLine(String response) {
        Scanner sc = new Scanner(response);
        String equalSign = "=";
        int width = 75;
        StringBuilder sb = new StringBuilder();

        while (sc.hasNext()) {
            String textLine = indentation + sc.nextLine();
            sb.append(textLine + "\n");
            width = Math.max(width, textLine.length() + 2);
        }

        String line = equalSign.repeat(width);

        return line + "\n" + sb.toString() + line;
    }
}
