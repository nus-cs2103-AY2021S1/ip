import java.util.Scanner;

public class Duke {

    private static final String DIVIDER = "    ________________________________________________________\n";
    private static final String LIST_COMMAND = "list";
    private static final String EXIT_COMMAND = "bye";

    private static void greet() {
        String welcomeMessage = "     Konnichiwa!\n"
                + "     What can I do for you?\n";
        System.out.println(DIVIDER + welcomeMessage + DIVIDER);
    }

    private static void exit() {
        String exitMessage = "     Ja ne!\n";
        System.out.println(DIVIDER + exitMessage + DIVIDER);
    }

    private static void chat() {
        Store storage = new Store();
        Scanner sc = new Scanner(System.in);
        String input = "";
        input = sc.nextLine();
        while (!input.equals(EXIT_COMMAND)) {
            System.out.print(DIVIDER);
            if (input.equals(LIST_COMMAND)) {
                storage.listItems();
            } else {
                storage.add(input);
            }
            System.out.println(DIVIDER);
            input = sc.nextLine();
        }
    }

    public static void main(String[] args) {
        greet();
        chat();
        exit();
    }
}
