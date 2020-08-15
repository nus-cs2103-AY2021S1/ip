import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static ArrayList<String> newList = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        Scanner scan = new Scanner(System.in);

        while (scan.hasNext()) {
            String nextLine = scan.nextLine();
            if (nextLine.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (nextLine.equals("list")) {
                for (int i = 0; i < newList.size(); i++ ) {
                    System.out.println((i+1) + ". " + newList.get(i));
                }
            } else {
                newList.add(nextLine);
                System.out.println("Added: " + nextLine);
            }
        }
    }

}
