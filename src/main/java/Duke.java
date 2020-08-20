import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        chat();
    }

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
