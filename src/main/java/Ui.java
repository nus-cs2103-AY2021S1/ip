import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLoadingError() {
        System.out.println(">> No data storage found. Creating new task list.");
    }

    public void greet() {
        System.out.println(">> Beep Boop. I am Aq-bot.\n>> How can I help?");
    }

    public void bye() {
        System.out.println(">> Bye! Hope I helped!");
    }

    public void invalidDateError() {
        System.out.println(">> Please format your date in YYYY-MM-DD format!");
    }

    public void descriptionError(Constants.TaskTypes type) {
        System.out.println(">> Oh no!!! A " + type + " must have a description!");
    }

    public void conditionError(Constants.TaskTypes type) {
        System.out.println(">> Oh no!!! A " + type + " must have an associated date!");
    }

    public void deleteError() {
        System.out.println(">> Oh no!!! That task does not exist!");
    }
}
