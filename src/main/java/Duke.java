import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n" + " What can I do for you?");

        List<String> ls = new ArrayList<>();
        int count = 1;
        String line = "";

        while (!line.equals("bye")) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            switch (line) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case "list":
                    System.out.println("____________________________________________________________");
                    for (int x = 0; x < ls.size(); x++) {
                        System.out.println(ls.get(x));
                    }
                    System.out.println("____________________________________________________________");
                    break;
                default:
                    count++;
                    System.out.println("____________________________________________________________");
                    System.out.println("added: " + line);
                    System.out.println("____________________________________________________________");
            }
        }
    }
}


