import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String receiveInput() {
        String input = scanner.nextLine();
        return input;
    }

    public void greet() {
        System.out.println("Duke: Hello! How may I help you?");
    }

    public void goodbye() {
        System.out.println("Duke: Bye. Hope to see you again!");
    }

    public void say(String something) {
        System.out.println("Duke: " + something);
    }

    public void getError(Exception e) {
        System.out.println("Duke: Error! " + e.getMessage());
    }
}