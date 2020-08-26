import java.util.Scanner;
import java.util.ArrayList;

public class Chatbot {

    public static void chat() throws DukeException {
        String start = "Hello! I'm Duke \nWhat can I do for you?";
        System.out.println(start);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> arr = new ArrayList<>();
        while (true) {
            String line = sc.nextLine();
            Scanner scan = new Scanner(line);
            String in = scan.next();
            if (in.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (in.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int j = 1; j <= arr.size(); j++) {
                    String output = j + ". " + arr.get(j - 1);
                    System.out.println(output);
                }
            } else if (in.equals("done")) {
                System.out.println("Nice! I've marked this task as done:");
                int ind = scan.nextInt() - 1;
                arr.get(ind).markAsDone();
                System.out.println(arr.get(ind));
            } else if (in.equals("delete")) {
                System.out.println("Noted. I've removed this task:");
                int ind = scan.nextInt() - 1;
                System.out.println(arr.get(ind));
                arr.remove(ind);
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
            } else if (in.equals("todo")) {
                if (!scan.hasNext()) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                String desc = scan.nextLine().substring(1);
                System.out.println("Got it. I've added this task:");
                Todo curr = new Todo(desc);
                System.out.println(curr);
                arr.add(curr);
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
            } else if (in.equals("deadline")) {
                if (!scan.hasNext()) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                String desc = scan.next();
                System.out.println("Got it. I've added this task:");
                String dead;
                String word = scan.next();
                while (word.indexOf('/') < 0) {
                    desc += " ";
                    desc += word;
                    word = scan.next();
                }
                dead = (scan.nextLine()).substring(1);
                Deadline curr = new Deadline(desc, dead);
                System.out.println(curr);
                arr.add(curr);
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
            } else if (in.equals("event")) {
                if (!scan.hasNext()) {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                }
                String desc = scan.next();
                System.out.println("Got it. I've added this task:");
                String time;
                String word = scan.next();
                while (word.indexOf('/') < 0) {
                    desc += " ";
                    desc += word;
                    word = scan.next();
                }
                time = (scan.nextLine()).substring(1);
                Event curr = new Event(desc, time);
                System.out.println(curr);
                arr.add(curr);
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
