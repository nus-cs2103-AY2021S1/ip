public class Ui {
    private static final int PADDING_LEFT_LENGTH = 5;
    private static final int DIVIDER_LENGTH = 60;
    private static String PADDING_LEFT = "";
    private static String DIVIDER = "";

    public void generateLeftPadding() {
        if (PADDING_LEFT.equals("")) {
            for (int i = 0; i < PADDING_LEFT_LENGTH; i++) {
                PADDING_LEFT += " ";
            }
        }
        System.out.print(PADDING_LEFT);
    }

    public void generateDivider() {
        if (DIVIDER.equals("")) {
            for (int j = 0; j < DIVIDER_LENGTH; j++) {
                DIVIDER += "_";
            }
        }
        System.out.println(DIVIDER);
    }

    public void printWelcomeMessage() {
        String logo = "    _____                 ________  .__\n"
                + "   /     \\_______  ______ \\______ \\ |__| ____   ____\n"
                + "  /  \\ /  \\_  __ \\/  ___/  |    |  \\|  |/    \\ /  _ \\ \n"
                + " /    Y    \\  | \\/\\___ \\   |    |   \\  |   |  (  <_> ) \n"
                + " \\____|__  /__|  /____  > /_______  /__|___|  /\\____/ \n"
                + "         \\/           \\/          \\/        \\/ \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Welcome!");
        System.out.println("What can Mrs Dino do for you?");
    }

    public void printBye() {
        generateDivider();
        generateLeftPadding();
        System.out.println("Bye. Hope to see you again soon!");
        generateDivider();
    }

    public void printDone(String message) {
        generateDivider();
        System.out.println("Nice! I've marked this task as done:");
        generateLeftPadding();
        System.out.println(message);
        generateDivider();
    }

    public void printTaskAdded(String message, int size) {
        generateDivider();
        System.out.println("Got it. I've added this task:");
        generateLeftPadding();
        System.out.println(message);
        System.out.println("Now you have " + size + " tasks in the list.");
        generateDivider();
    }

    public void printTaskDeleted(String message, int size) {
        generateDivider();
        System.out.println("Noted. I've removed this task:");
        generateLeftPadding();
        System.out.println(message);
        System.out.println("Now you have " + size + " tasks in the list.");
        generateDivider();
    }

}
