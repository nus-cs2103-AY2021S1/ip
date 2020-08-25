import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void greet() {
        System.out.println("Hello! How may I help you?");
    }

    public String receive() {
        String input = scanner.nextLine();
        return input;
    }

    public void goodbye() {
        System.out.println("Bye. Hope to see you again!");
    }

    public void say(String something) {
        System.out.println(something);
    }

    public void getError(Exception e) {
        System.out.println(e.getMessage());
    }
}
