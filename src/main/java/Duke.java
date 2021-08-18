import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String sectionBreak = "------------------------------------------";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("\nHello buddy! I am");
        System.out.println(logo);
        System.out.println("What can I do for you?");
        System.out.println(sectionBreak);

        Scanner sc=new Scanner(System.in);

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(input);
            System.out.println(sectionBreak);
            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you soon!");
        System.out.println(sectionBreak);
    }
}
