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

        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        Scanner scan = new Scanner(System.in);
        ArrayList<String> storage = new ArrayList<>();
        String in = scan.nextLine();
        while(!in.equals("bye")) {
            if (in.equals("list")) {
                for (int i = 0; i < storage.size(); i++) {
                    System.out.println("\t" + (i+1) + ". " + storage.get(i));
                }
            } else {
                System.out.println("\tadded: " + in);
                storage.add(in);
            }
            in = scan.nextLine();
        }
        System.out.println("\tBye. Hope to see you again soon!");

    }
}
