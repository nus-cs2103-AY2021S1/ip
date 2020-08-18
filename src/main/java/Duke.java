import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("______________________");

        String input;
        ArrayList<String> tasks = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();

        while(!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("Task list");
                int i = 1;
                for (String task : tasks) {
                    System.out.println(i++ + ". " + task);
                }
            } else {
                tasks.add(input);
                System.out.println("added: " + input);
            }
            input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("--------------------------------");
    }
}

