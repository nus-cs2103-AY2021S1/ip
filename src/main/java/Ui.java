import java.util.Scanner;

public class Ui {

    public static final String line = "____________________________________________________________";
    private final String botName;

    public Ui(String botName) {
        this.botName = botName;
    }

    private void printLogo() {
        System.out.println("Greetings, human. I am");
        System.out.println(" ______      ___   _____       ___    _________");
        System.out.println("|_   _ \\   .'   `.|_   _|    .'   `. |  _   _  |");
        System.out.println("  | |_) | /  .-.  \\ | |     /  .-.  \\|_/ | | \\_|");
        System.out.println(" _| |__) |\\  `-'  /_| |__/ |\\  `-'  /   _| |_");
        System.out.println("|_______/  `.___.'|________| `.___.'   |_____|");
    }

    private void greet() {
        System.out.println(line);
        System.out.println("Hello! I am " + botName + ", your personal chat-bot companion.");
        System.out.println("How may I help you?");
        System.out.println(line);
    }

    public void showWelcome() {
        printLogo();
        greet();
    }

    public void bye() {
        System.out.println(line);
        System.out.println("Bye! Thank you for chatting with " + botName + "!");
        System.out.println("Hope to see you again soon!");
        System.out.println(line);
    }

    public void showLine() {
        System.out.println(line);
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.hasNextLine() ? sc.nextLine() : null;
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
