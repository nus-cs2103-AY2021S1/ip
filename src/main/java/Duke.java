import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner scan1 = new Scanner(System.in);

        while (scan1.hasNext()) {
            String input = scan1.next();
            if (!input.equals("bye")) {
                System.out.println(input);
            } else {
                String bye = "Bye. Hope to see you again soon!";
                System.out.println(bye);
                scan1.close();
                break;
            }
        }
    }
}
