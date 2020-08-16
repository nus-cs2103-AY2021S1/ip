import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Duke {
    private static ArrayList<Task> list = new ArrayList<>();

    private static void say(String s) {
        System.out.println("______________________________");
        System.out.println(s);
        System.out.println("______________________________");
    }

    private static void printList() {
        System.out.println("______________________________");
        if (list.size() > 0) {
            for (int i = 1; i <= list.size(); i++) {
                System.out.println(i + "." + list.get(i - 1));
            }
        } else {
            System.out.println("List is empty.");
        }
        System.out.println("______________________________");
    }

    private static void printDone(String input) {
        try {
            String number = input.substring(5);
            int index = parseInt(number) - 1;
            list.get(index).setDone();
            say("Marked this task as done:\n" + list.get(index));
        } catch (NumberFormatException e) {
            say("Error! Integer should follow 'done' command.");
        } catch (IndexOutOfBoundsException e) {
            say("Error! Enter a valid task number.");
        }
    }

    private static void handleToDo(String input) {
        String pattern = "(todo\\s)(.+)";
        if (input.matches(pattern)) {
            String task = input.replaceAll(pattern, "$2");
            Task next = new ToDo(task);
            list.add(next);
            String text = "Added ToDo '" + task + "' to your list!";
            say(text);
        } else {
            say("Error! Task should follow 'todo' command.");
        }
    }

    private static void handleDeadline(String input) {
        String pattern = "(deadline\\s)(.+)\\s(/by\\s)(.+)";
        if (input.matches(pattern)) {
            String task = input.replaceAll(pattern, "$2");
            String time = input.replaceAll(pattern, "$4");
            Task next = new Deadline(task, time);
            list.add(next);
            String text = "Added Deadline '" + task + "' to your list!";
            say(text);
        } else {
            say("Error! 'deadline' command should be followed by task and '/by' date");
        }
    }

    private static void handleEvent(String input) {
        String pattern = "(event\\s)(.+)\\s(/at\\s)(.+)";
        if (input.matches(pattern)) {
            String task = input.replaceAll(pattern, "$2");
            String time = input.replaceAll(pattern, "$4");
            Task next = new Event(task, time);
            list.add(next);
            String text = "Added Event '" + task + "' to your list!";
            say(text);
        } else {
            say("Error! 'event' command should be followed by task and '/bat' date");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo =
                "█████████████████████████████████████████████████████████████\n" +
                "█░░░░░░░░░░░░░░███░░░░░░░░░░░░░░░░░░░░░░░░░█░░░░░░░░░░░░░░███\n" +
                "█░░▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄░░█░░▄▀▄▀▄▀▄▀▄▀░░███\n" +
                "█░░▄▀░░░░░░▄▀░░███░░▄▀░░░░░░░░░░░░░░░░░▄▀░░█░░▄▀░░░░░░▄▀░░███\n" +
                "█░░▄▀░░██░░▄▀░░███░░▄▀░░█████████████░░▄▀░░█░░▄▀░░██░░▄▀░░███\n" +
                "█░░▄▀░░░░░░▄▀░░░░█░░▄▀░░█████████████░░▄▀░░█░░▄▀░░░░░░▄▀░░░░█\n" +
                "█░░▄▀▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░█░░█░███░█░░█░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░█\n" +
                "█░░▄▀░░░░░░░░▄▀░░█░░▄▀░░█████░░░█████░░▄▀░░█░░▄▀░░░░░░░░▄▀░░█\n" +
                "█░░▄▀░░████░░▄▀░░█░░▄▀░░█████████████░░▄▀░░█░░▄▀░░████░░▄▀░░█\n" +
                "█░░▄▀░░░░░░░░▄▀░░█░░▄▀░░░░░░░░░░░░░░░░░▄▀░░█░░▄▀░░░░░░░░▄▀░░█\n" +
                "█░░▄▀▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░█\n" +
                "█░░░░░░░░░░░░░░░░█░░░░░░░░░░░░░░░░░░░░░░░░░█░░░░░░░░░░░░░░░░█\n" +
                "█████████████████████████████████████████████████████████████";
        String welcome = "Hello I am\n" + logo +"\nPlease say something.";
        say(welcome);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("Here is your list:");
                printList();
            } else if (input.startsWith("done")) {
                printDone(input);
            } else if (input.startsWith("todo")) {
                handleToDo(input);
            } else if (input.startsWith("deadline")) {
                handleDeadline(input);
            } else if (input.startsWith("event")) {
                handleEvent(input);
            } else {
                say("Sorry, I don't know what you just said.");
            }
            input = sc.nextLine();
        }
        say("Goodbye!");
        sc.close();
    }
}
