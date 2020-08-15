import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" +
                "     What can I do for you?");
        System.out.println("________________________________________");

        Scanner sc = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();
        while (true) {
            String s = sc.nextLine();
            if (s.equals("bye")) {
                System.out.println("________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("________________________________________");
                System.exit(0);
            } else if (s.equals("list")) {
                System.out.println("________________________________________");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i+1) + ". " + tasks.get(i));
                }
                    System.out.println("________________________________________");
            } else {
                System.out.println("________________________________________");
                System.out.println("added: " + s);
                tasks.add(s);
                System.out.println("________________________________________");
            }
        }

    }
}
