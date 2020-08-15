package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private List<String> tasks;
    private String BYE_COMMAND = "bye";
    private String LIST_COMMAND = "list";

    Duke() {
        tasks = new ArrayList<>();
    }

    private void addTask(String task) {
        String message = "Added: " + task;
        tasks.add(task);
        System.out.println(message);
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

    private void takeUserInput() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();

            if (userInput.equals(BYE_COMMAND)) {
                break;
            }else if (userInput.equals(LIST_COMMAND)) {
                showAllTask();
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
        String indentation = "  ";
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
