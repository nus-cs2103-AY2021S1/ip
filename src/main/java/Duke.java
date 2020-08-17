import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> store = new ArrayList<>();

        System.out.println("Hello! I'm meimei ^_^\nI could scream at you all day!");
        String command = sc.nextLine();

        while(!command.equals("bye")) {
            String key = command.split(" ",2)[0];
            if (key.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < store.size(); i++) {
                    System.out.println((i+1) + "." + store.get(i).toString());
                }
                command = sc.nextLine();
            }
            else if (key.equals("done")) {
                //System.out.println("what");
                int k = Integer.parseInt(command.split(" ")[1]);
                store.get(k-1).markAsDone();
                System.out.println("Nice! This task is marked as done!");
                System.out.println(store.get(k-1));
                command = sc.nextLine();
            }
            //adding of task
            else {
                String remain = command.split(" ", 2)[1];
                if (key.equals("todo")) {
                    store.add(new Todo(remain));
                    System.out.println("Got it. I've added this task:\n" + store.get(store.size() -1 ));
                } else {
                    String description = remain.split("/", 2)[0];

                    if (key.equals("deadline")) {
                        String by = remain.split("/by", 2)[1];
                        store.add(new Deadline(description, by));
                    }
                    if (key.equals("event")) {
                        String at = remain.split("/at", 2)[1];
                        store.add(new Event(description, at));
                    }
                    System.out.println("Got it. I've added this task:\n" + store.get(store.size() -1 ));
                }
                System.out.println("Now you have " + store.size() + " tasks in the list.");
                command = sc.nextLine();
            }
            //System.out.println("Got it. I've added this task:\n" + store.get(store.size() -1 ));


        }
        System.out.println("Bye. Meimei will miss you!");
    }
}

