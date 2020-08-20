import java.util.Scanner;
import java.util.ArrayList;

public class Chatbot {
    public static void chat() {
        String start = "Hello! I'm Duke \nWhat can I do for you?";
        System.out.println(start);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> arr = new ArrayList<>();
        while (true) {
            String in = sc.next();
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
                int ind = sc.nextInt() - 1;
                arr.get(ind).markAsDone();
                System.out.println(arr.get(ind));
            } else if (in.equals("todo")) {
                System.out.println("Got it. I've added this task:");
                String desc = sc.nextLine();
                Todo curr = new Todo(desc);
                System.out.println(curr);
                arr.add(curr);
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
            } else if (in.equals("deadline")) {
                System.out.println("Got it. I've added this task:");
                String desc = sc.next();
                String dead;
                String word = sc.next();
                while (word.indexOf('/') < 0) {
                    desc += " ";
                    desc += word;
                    word = sc.next();
                }
                dead = (sc.nextLine()).substring(1);
                Deadline curr = new Deadline(desc, dead);
                System.out.println(curr);
                arr.add(curr);
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
            } else if (in.equals("event")) {
                System.out.println("Got it. I've added this task:");
                String desc = sc.next();
                String time;
                String word = sc.next();
                while (word.indexOf('/') < 0) {
                    desc += " ";
                    desc += word;
                    word = sc.next();
                }
                time = (sc.nextLine()).substring(1);
                Event curr = new Event(desc, time);
                System.out.println(curr);
                arr.add(curr);
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
            } else {
                in = in + " " + sc.next();
                Task curr = new Task(in);
                arr.add(curr);
                String output = "added: " + in;
                System.out.println(output);
            }
        }
    }
}
