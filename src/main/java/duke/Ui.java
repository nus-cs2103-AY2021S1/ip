package duke;

public class Ui {
    private static final String SPACING = "         ";
    private static final String DIVIDER = "_______________________________________________________";

    public void greeting() {
        String logo = "\n" +
                "\n" +
                "o.OOOo.          o           \n" +
                " O    `o         O           \n" +
                " o      O        o           \n" +
                " O      o        o           \n" +
                " o      O O   o  O  o  .oOo. \n" +
                " O      o o   O  OoO   OooO' \n" +
                " o    .O' O   o  o  O  O     \n" +
                " OooOO'   `OoO'o O   o `OoO' \n" +
                "                             \n" +
                "                             \n" +
                "\n";
        printMessage("Hello from\n" , logo, "How can I help you today?");
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
