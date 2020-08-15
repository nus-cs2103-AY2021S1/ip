import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "________________________________";

        Scanner sc = new Scanner(System.in);

        System.out.println("Hello from");
        System.out.println(logo);
        System.out.println("What can I do for you?");
        System.out.println(line);

        String output = sc.next();

        while (!output.equals("bye")) {

            System.out.println(line);
            System.out.println(output);
            System.out.println(line);

            output = sc.next();
        }

        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
