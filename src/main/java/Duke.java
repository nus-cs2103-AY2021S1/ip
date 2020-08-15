import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<String> previousInst;
    private static int instCounter;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        previousInst = new ArrayList<>();
        instCounter = 0;


        intro();

        String input = "";
        do {
            input = sc.nextLine();
            process(input);
            previousInst.add(++instCounter + ". " + input);
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

    private static void list() {
        println(previousInst.toArray(new String[instCounter]));
    }

    private static void process(String msg) {
        if (msg.equals("bye"))
            exit();
        else if (msg.equals("list"))
            list();
        else
            println(new String[] {"added: " + msg});
    }
}
