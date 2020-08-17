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
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            if (line.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int x = 0; x < ls.size(); x++) {
                    System.out.println(ls.get(x));
                }
                System.out.println("____________________________________________________________");
            }
            if (line.equals("list") == false) {
                ls.add(count + ". " + line);
                count++;
                System.out.println("____________________________________________________________");
                System.out.println("added: " + line);
                System.out.println("____________________________________________________________");
            }
        }
    }
}

