import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String lines = "    ____________________________________________________________";
        String default_Greeting = lines + "\n" + "     Hello! I'm Duke \n" + "     What can I do for you?\n" + lines + "\n";
        System.out.println(default_Greeting);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")){
            String response = lines + "\n      " + input + "\n" + lines + "\n";
            System.out.println(response);
            input = sc.nextLine();
        }
        String end_Greeting = lines + "\n" + "      Bye. Hope to see you again soon!\n" + lines;
        System.out.println(end_Greeting);

    }
}
