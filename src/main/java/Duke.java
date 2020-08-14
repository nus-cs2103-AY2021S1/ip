import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class Duke {
    public static void main(String[] args) {
//        Testing
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        Scanner scanner = new Scanner(System.in);
        String line = "";
        List<String> toDoLst = new ArrayList<>();

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while(!line.equals("bye")) {
            if (!line.equals("")) {
                if (line.equals("list")) {
                    for (String toDoItem: toDoLst) {
                        System.out.println(toDoItem);
                    }
                } else {
                    toDoLst.add(toDoLst.size() + 1 + ". " + line);

                    System.out.println("added: " + line);
                }
            }

            line = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}