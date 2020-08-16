import java.util.Scanner;

public class Duke {

    public static String divider = "__________________________________________________________";

    public static void messageEcho(String word) {
        System.out.println(divider);
        System.out.println(word);
        System.out.println(divider + "\n");
    }

    public static void main(String[] args) {
        messageEcho("Hello! I'm Duke\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String word  = sc.nextLine();
            if (word.equals("bye")) {
                break;
            }
            messageEcho(word);
        }

        messageEcho("Bye. Hope to see you again soon!");
    }
}
