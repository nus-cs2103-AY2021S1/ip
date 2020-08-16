import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String lines = "------------------------------------------------\n";
        System.out.println(lines + "Hello! I'm Duke!\n" + "What can I do for you?\n" + lines);

        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        ArrayList<Task> ls = new ArrayList<>();

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println(lines);
                System.out.println("  Here are the tasks in your list:\n");
                for (int i = 0; i < ls.size(); i++) {
                    System.out.println("  " + (i+1) + ". " + ls.get(i).toString());
                }
                System.out.println(lines);
                userInput = sc.nextLine();
            } else if (userInput.contains("done")) {
                try {
                    int index = userInput.charAt(5) - '0';
                    if (index < 1 || index > ls.size()) {
                        System.out.println(lines + "Invalid index! Try Again.\n" + lines);
                        userInput = sc.nextLine();
                    } else {
                        ls.get(index - 1).markAsDone();
                        System.out.println(lines + "  Nice! I have marked this task as done:\n");
                        System.out.println("    " + ls.get(index - 1).toString()
                                + "\n" + lines);
                        userInput = sc.nextLine();
                    }
                } catch(StringIndexOutOfBoundsException e) {
                    System.out.println(lines + "Index out of range! Try again.\n" + lines);
                    userInput = sc.nextLine();
                }
            }
            else {
                if (userInput.contains("todo")) {
                    String s = userInput.substring(5);
                    System.out.println(lines);
                    System.out.println("Got it. I have added this task:");
                    Todo temp = new Todo((s));
                    System.out.println("  " + temp.toString());
                    ls.add(temp);
                    System.out.println("Now you have " + ls.size() + " tasks in the list");
                    System.out.println(lines);
                    userInput = sc.nextLine();
                }
                if (userInput.contains("deadline")) {
                    int dateIndex = userInput.indexOf("/");
                    String s = userInput.substring(9, dateIndex);
                    String time = userInput.substring(dateIndex + 1);
                    System.out.println(lines);
                    System.out.println("Got it. I have added this task:");
                    Deadline temp = new Deadline(s, time);
                    System.out.println("  " + temp.toString());
                    ls.add(temp);
                    System.out.println("Now you have " + ls.size() + " tasks in the list");
                    System.out.println(lines);
                    userInput = sc.nextLine();
                }
                if (userInput.contains("event")) {
                    int dateIndex = userInput.indexOf("/");
                    String s = userInput.substring(6, dateIndex);
                    String time = userInput.substring(dateIndex + 1);
                    System.out.println(lines);
                    System.out.println("Got it. I have added this task:");
                    Event temp = new Event(s, time);
                    System.out.println("  " + temp.toString());
                    ls.add(temp);
                    System.out.println("Now you have " + ls.size() + " tasks in the list");
                    System.out.println(lines);
                    userInput = sc.nextLine();
                }
            }
        }

        System.out.println(lines + "    Bye! Hope to see you again soon." + "\n" + lines);

    }
}