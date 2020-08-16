import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Store store = new Store();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        StringUtils.printWithWrapper(new String[]{"Hello, my name is \n" + logo, "How may I help you?"}, false);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            processInput(input, store);
            input = sc.nextLine();
        }

        StringUtils.printWithWrapper(new String[]{"Bye bye! Hope to see you again soon!"}, false);
    }

    private static void processInput(String input, Store store) {
        String[] splitInput = input.split(" ");

        if (splitInput[0].equals("done")) {
            store.markTaskAsDone(Integer.parseInt(splitInput[1]));
            return;
        }

        switch(input) {
            case "list":
                store.list();
                break;
            default:
                store.add(input);
        }
    }
}
