import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Duke {
    private static ArrayList<Task> list = new ArrayList<>();

    static class Task {
        private final String text;
        private boolean done = false;

        public Task(String text) {
            this.text = text;
        }

        public void setDone() {
            this.done = true;
        }

        @Override
        public String toString() {
            return done
                    ? "[✓] " + text
                    : "[✗] " + text;
        }
    }

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
                try {
                    String number = input.substring(5);
                    int index = parseInt(number) - 1;
                    list.get(index).setDone();
                    say("Marked this task as done:\n" + list.get(index));
                } catch (NumberFormatException e) {
                    say("Error! Integer should follow 'done' command.");
                } catch (IndexOutOfBoundsException e) {
                    say ("Error! Enter a valid task number.");
                }
            } else {
                Task next = new Task(input);
                list.add(next);
                String text = "Added '" + input + "' to your list!";
                say(text);
            }
            input = sc.nextLine();
        }
        say("Goodbye!");
        sc.close();
    }
}
