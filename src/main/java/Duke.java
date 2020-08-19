import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo +"Hello! I'm Duke\n" + "What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        while (!text.equals("bye")) {
            System.out.print(text);
            text = sc.nextLine();
        }
        sc.close();

        System.out.print("Bye. Hope to see you again soon!");
    }
}
