import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Duke {

    public static void readAndEcho(List<String> list) {
        String intro1 = "Hello! I'm Duke \n";
        String intro2 = "What can I do for you?";


        String greeting = addDividers(String.format("%" + (6 + intro1.length()) + "s", intro1) +
                String.format("%" + (6 + intro2.length()) + "s", intro2));
        System.out.println(greeting);


        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            String echo = addDividers(String.format("%" + (6 + input.length()) + "s", input));
            System.out.println(echo);
            input = sc.nextLine();
        }
        String goodbye = "Bye. Hope to see you again soon!";
        System.out.println(addDividers(String.format("%" + (6 + goodbye.length()) + "s", goodbye)));
    }

    private static String addDividers(String s) {
        String divider = "___________________________\n";
        String dividerFormatted = String.format("%" + (5 + divider.length()) + "s", divider);
        String added = dividerFormatted + s + "\n" + dividerFormatted;
        return added;
    }

    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        readAndEcho(arrayList);
    }
}
