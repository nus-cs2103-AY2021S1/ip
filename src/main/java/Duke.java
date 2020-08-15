package main.java;

import java.util.Scanner;

public class Duke {
    private String BYE_COMMAND = "bye";

    public void echo(String text) {
        String message = text;
        sendMessage(createLine(message));
    }

    private void welcome() {
        String message = "Hi! My name is Duke.\nWhat do you want me to do?";
        sendMessage(createLine(message));
    }

    private void exit() {
        String message = "Bye. Thank you for using me!";
        sendMessage(createLine(message));
    }

    public void takeUserInput() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();

            if (userInput.equals(BYE_COMMAND)) {
                break;
            } else {
                echo(userInput);
            }
        }
    }

    private void sendMessage(String sendMessage) {
        System.out.println(sendMessage);
    }

    public String createLine(String response) {
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
