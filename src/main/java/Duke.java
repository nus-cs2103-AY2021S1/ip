import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        String next = sc.nextLine();
        while (!next.equals("bye")) {
            System.out.println(next);
            next = sc.nextLine();
        }
        close();
}

    public static void close() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }
}
