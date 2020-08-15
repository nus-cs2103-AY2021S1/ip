import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        intro();

        String input = "";
        do {
            input = sc.nextLine();
            processEcho(input);
        } while (!input.equals("bye"));

    }

    /**
     * Prints output enclosed in top and bottom horizontal lines
     * @param message message to be output
     */
    private static void println(String[] message) {
        System.out.println("\t____________________________________________________________");
        for (String s : message) {
            System.out.println("\t" + s);
        }
        System.out.println("\t____________________________________________________________");
    }

    private static void intro() {
        String[] msg = new String[] {"Hello! I'm KING!", "What can I do for you?"} ;
        println(msg);
    }

    private static void exit() {
        String[] msg = new String[] { "Bye. Hope to see you again soon!" };
        println(msg);
    }

    private static void processEcho(String msg) {
        if (msg.equals("bye"))
            exit();
        else
            println(new String[] {msg});
    }
}
