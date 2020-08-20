import main.java.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String border = "\n^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^\n";
        Scanner sc = new Scanner(System.in);
        String command;
        String done = "[O]";
        String notDone = "[X]";
        ArrayList<Task> tList = new ArrayList<>();

        String logo = " _       _ \n"
                + "| |  _  | |_   _ ____ ___\n"
                + "| | | | | | |_/ |  _ \\  _ \\ \n"
                + "| |_| |_| |\\___ |    <  __/\n"
                + "\\___/\\___/ \\____|_| \\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(border + "Hi! I'm Wyre, your Personal Assistant Chatbot! :>\nWhat can I do for you today?" + border);
        while(true) {
            command = sc.nextLine();
            if(command.equals("bye")) {
                System.out.println(border + "Bye. Hope to see you again!" + border);
                break;
            } else if (command.equals("list")) {
                System.out.println(border + "Here are the tasks in your list:\n");
                for (int i = 0; i < tList.size(); i++) {
                    System.out.println((i + 1) + ". " + tList.get(i).getName() + " " + (tList.get(i).isDone() ? done : notDone));
                }
                System.out.println(border);
            } else if (command.substring(0,4).equals("done")) {
                int index = Integer.parseInt(command.substring(5)) - 1;
                tList.get(index).setStatus(true);

                System.out.println(border + "Well done! I've marked this task as done:");
                System.out.println("\t" + (tList.get(index).isDone() ? done : notDone) + " " + tList.get(index).getName() + border);
            } else {
                tList.add(new Task(command, false));
                System.out.println(border + "added: " + command + border);
            }
        }

    }
}
