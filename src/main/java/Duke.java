import java.util.Scanner;

public class Duke {

    static Scanner input = new Scanner(System.in);
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        repeat();
    }

    private static void repeat() {
        String line = "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-";

        System.out.println("Hello from\n" + logo + "\n How may I help you today? :)");

        String nextInput = input.nextLine();
        boolean bye = nextInput.equals("bye");

        while (!bye) {
            System.out.println(line + "\n" + nextInput + "\n" + line);
            nextInput = input.nextLine();
            bye = nextInput.equals("bye");
        }

        System.out.println("Bye! I'll see you again next time!");

    }

}