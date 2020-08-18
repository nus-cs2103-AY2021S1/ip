import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "____________________________________________________________\n";
        System.out.println(line + logo + "\nHello, I'm Duke \nWhat can I do for you?\n" + line);

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while(!command.equals("bye")) {
            System.out.println(line + command + "\n" + line);
            command = sc.nextLine();
        }

        System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
    }
}
