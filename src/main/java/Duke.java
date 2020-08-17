import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> store = new ArrayList<>();

        System.out.println("Hello! I'm meimei ^_^\nI could scream at you all day!");
        String command = sc.nextLine();

        while(!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < store.size(); i++) {
                    System.out.println((i+1) + "." + store.get(i).toString());
                }
                command = sc.nextLine();
            }
            if (command.contains("done")) {
                int k = Integer.parseInt(command.split(" ")[1]);
                store.get(k-1).markAsDone();
                System.out.println("Nice! This task is marked as done!");
                System.out.println(store.get(k-1));
                command = sc.nextLine();
            }
            else {
                store.add(new Task(command));
                System.out.println("added: " + command);
                command = sc.nextLine();
            }
        }
        System.out.println("Bye. Meimei will miss you!");
    }
}

