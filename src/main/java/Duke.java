package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private List<Task> tasks;
    private String indentation = "  ";
    private String BYE_COMMAND = "bye";
    private String LIST_COMMAND = "list";
    private String DONE_COMMAND = "done";

    Duke() {
        tasks = new ArrayList<>();
    }

    private void addTask(String task) {
        String message = "Added: " + task;
        tasks.add(new Task(task));
        sendMessage(message);

    }

    private void welcome() {
        String message = "Hi! My name is Duke.\nWhat do you want me to do?";
        sendMessage(message);
    }

    private void exit() {
        String message = "Bye. Thank you for using me!";
        sendMessage(message);
    }

    private void showAllTask() {
        String message;

        if (tasks.isEmpty()) {
            message = "You haven't add any task!";
        } else {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1) + ". " + tasks.get(i) + "\n");
            }
            message = sb.toString().trim();
        }

        sendMessage(message);
    }

    private void doneTask(int taskNumber) {
        String message;
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            message = "Invalid task number!";
        } else {
            Task task = tasks.get(taskNumber - 1);
            task.setStatusToDone();
            message = "Sucessfully marked this task as done: \n" + indentation + task.toString();
        }

        sendMessage(message);

    }

    private void takeUserInput() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();
            String[] userInputArr = userInput.split("\\s", 2);
            String command = userInputArr[0];

            if (command.equals(BYE_COMMAND)) {
                break;
            } else if (command.equals(LIST_COMMAND)) {
                showAllTask();
            } else if (command.equals(DONE_COMMAND)) {
                if (userInputArr.length == 1) {
                    doneTask(0);
                } else {
                    doneTask(Integer.parseInt(userInputArr[1]));
                }
            } else {
                addTask(userInput);
            }
        }
    }

    private void sendMessage(String sendMessage) {
        System.out.println(createLine(sendMessage));
    }

    private String createLine(String response) {
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

    public static void main(String[] args) {
        Duke duke = new Duke();

        duke.welcome();
        duke.takeUserInput();
        duke.exit();
    }
}