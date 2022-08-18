import java.util.Scanner;

public class Duke {
    private static String line = "___________________________________";

    public static void greet() {
        System.out.println(line);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public static void echo(String word) {
        if (word.equals("bye") || word.equals("Bye")) {
            System.out.println(line);
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(line);
            System.exit(0);
        } else {
            System.out.println(line);
            System.out.println(word);
            System.out.println(line);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String nextWord = sc.next();
            echo((nextWord));
        }
    }
}
