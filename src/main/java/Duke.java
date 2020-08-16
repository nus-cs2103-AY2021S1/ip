import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Duke {

    public static void readAndEcho(List<String> list) {
        String intro1 = "Hello! I'm Duke \n";
        String intro2 = "What can I do for you? \n";


        String greeting = addDividers(formatString(intro1) + formatString(intro2));
        System.out.println(greeting);


        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printList(list);
            } else {
                list.add(input);
                String inputText = "added: " + input + '\n';
                String echo = addDividers(formatString(inputText));
                System.out.println(echo);
            }
            input = sc.nextLine();
        }
        String goodbye = "Bye. Hope to see you again soon! \n";
        System.out.println(addDividers(formatString(goodbye)));
    }

    private static void printList(List<String> list) {
        String printedList = "";
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                String lineItem = (i + 1) + ". " + list.get(i) + "\n";
                printedList = printedList + formatString(lineItem);
            }
        } else {
            String emptyString = "List is empty \n";
            printedList = formatString(emptyString);
        }
        System.out.println(addDividers(printedList));
    }

    private static String addDividers(String s) {
        String divider = "___________________________\n";
        String dividerFormatted = String.format("%" + (5 + divider.length()) + "s", divider);
        String added = dividerFormatted + s + dividerFormatted;
        return added;
    }

    private static String formatString(String s) {
        return String.format("%" + (6 + s.length()) + "s", s);
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
