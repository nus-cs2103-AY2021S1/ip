import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printWithWrapper(new String[]{"Hello, my name is \n" + logo, "How may I help you?"});
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            printWithWrapper(new String[]{input});
            input = sc.nextLine();
        }
        printWithWrapper(new String[]{"Bye bye! Hope to see you again soon!"});
    }

    /**
     * Wraps and prints the strArr with indentation and top and bottom borders
     */
    private static void printWithWrapper(String[] strArr) {
        String BORDER = "=======================================";
        String INDENT = "    ";
        System.out.println(BORDER);
        for (String str : strArr) {
            System.out.println(INDENT + str);
        }
        System.out.println(BORDER);
    }
}
