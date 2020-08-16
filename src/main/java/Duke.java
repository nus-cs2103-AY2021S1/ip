import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        StringUtils.printWithWrapper(new String[]{"Hello, my name is \n" + logo, "How may I help you?"});
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            StringUtils.printWithWrapper(new String[]{input});
            input = sc.nextLine();
        }
        StringUtils.printWithWrapper(new String[]{"Bye bye! Hope to see you again soon!"});
    }


}
