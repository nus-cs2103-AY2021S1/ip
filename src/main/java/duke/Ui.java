package duke;

public class Ui {
    private static final String SPACING = "         ";
    private static final String DIVIDER = "_______________________________________________________";


    public void greeting() {
        String logo = "Dash";
        System.out.println("Hello from\n" + logo);
        System.out.println("How can I help you today?");
        System.out.println(DIVIDER);
    }

    public void getUserCommand() {
        System.out.println("Please enter a command:");

    }

    public void printMessage(String... messages) {
        for (String message : messages) {
            System.out.println(SPACING + message);
        }
        System.out.println(DIVIDER);
    }
}
