import main.java.Deadline;
import main.java.Event;
import main.java.Task;
import main.java.ToDo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String border = "\n^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^\n";
        Scanner sc = new Scanner(System.in);
        String command;
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
                System.out.println(border + "Here are the task(s) in your list:\n");
                for (int i = 0; i < tList.size(); i++) {
                    System.out.println("\t" + (i + 1) + ". " + tList.get(i));
                }
                System.out.println(border);
            } else if (command.substring(0,4).equals("done")) {
                int index = Integer.parseInt(command.substring(5)) - 1;
                tList.get(index).setStatus(true);
                System.out.println(border + "Well done! I've marked this task as done:");
                System.out.println("\t" + tList.get(index) + border);
            } else if (command.substring(0,4).equals("todo")) {
                ToDo t = new ToDo(command.substring(5), false);
                tList.add(t);
                System.out.println(border + "Wyre at your service. I've added the task:\n\t" + t);
                System.out.println("Now you have " + tList.size() + " task(s) in the list.\n" + border);
            } else if (command.substring(0,8).equals("deadline")) {
                int escapeIndex = command.lastIndexOf("/");
                Deadline d = new Deadline(command.substring(9, escapeIndex - 1), false, command.substring(escapeIndex + 4));
                tList.add(d);
                System.out.println(border + "Wyre at your service. I've added the task:\n\t" + d);
                System.out.println("Now you have " + tList.size() + " task(s) in the list.\n" + border);
            } else if (command.substring(0,5).equals("event")) {
                int escapeIndex = command.lastIndexOf("/");
                Event e = new Event(command.substring(6, escapeIndex - 1), false, command.substring(escapeIndex + 4));
                tList.add(e);
                System.out.println(border + "Wyre at your service. I've added the task:\n\t" + e);
                System.out.println("Now you have " + tList.size() + " task(s) in the list.\n" + border);
            } else {
                /*
                tList.add(new Task(command, false));
                System.out.println(border + "added: " + command + border);*/
            }
        }

    }
}
