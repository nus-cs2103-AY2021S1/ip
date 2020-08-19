import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String divider = "___________________________________";
        System.out.println(divider);
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println(divider);
        Scanner sc = new Scanner(System.in);

        String input = "";

        while(!input.toLowerCase().equals("bye")) {
            input = sc.nextLine();
            System.out.println(divider);
            System.out.println(input);
            System.out.println(divider);

        }

        System.out.println("Bye! See you around :)");
    }
}
