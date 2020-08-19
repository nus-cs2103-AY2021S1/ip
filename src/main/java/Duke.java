import java.util.Scanner;

public class Duke {
    public static void greet() {
        System.out.println("Hello! I'm Jia Le :)");
        System.out.println("How may I be of assistance to you?");
    }

    public static void echo() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line =scanner.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye! Hope to see you again ;)");
                break;
            }
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
        echo();
    }
}
