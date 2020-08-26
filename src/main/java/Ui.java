import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readUserInput() {
        // prompt
        System.out.print(" > ");

        return sc.nextLine();
    }

    public String getConfirmation(String question) {
        System.out.println(question);
        displayLine();
        return readUserInput();
    }

    public void displayLine() {
        System.out.println("____________________________________________________________");
    }

    public void displayWelcomeMsg() {
        String logo = " _____  _     _____ _____  _____\n" +
                "/  _  \\| |   |_   _/  __ \\|  ___|\n" +
                "| |_| || |     | | | /  \\/| |__\n" +
                "|  _  || |     | | | |    |  __|\n" +
                "| | | || |_____| |_| \\__/\\| |___\n" +
                "\\_| |_/\\_____/\\___/ \\____/\\____/\n";

        System.out.println(logo +
                "\nHello! I'm Alice\n" +
                "How can I help you today?");
    }

    public void displayLoadSuccess() {
        displayOutput("File successfully loaded!");
    }

    public void displayLoadError(String filePath) {
        displayError("Cannot load/create file at " + filePath);
    }

    public void displayWarning(String warningMessage) {
        System.out.println("!! " + warningMessage);
    }

    public void displayError(String errorMessage) {
        System.out.println("ERROR: " + errorMessage);
    }

    public void displayOutput(String outputMessage) {
        System.out.println(outputMessage);
    }
}
