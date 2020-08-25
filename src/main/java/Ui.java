import java.util.ArrayList;

public class Ui {
    final private String line = "-------------------------------------";
    final private String addedMsg = "Alright, I've added a new order: ";
    final private String doneMsg = "Great choice! I have taken your order: ";
    final private String deleteMsg = "Too bad. I'll remove the following order: ";
    final private String saveMsg = "Aright, I have remembered your sins :)";
    final private String retListMsg = "Here's what you have ordered so far...";
    final private String defaultError = "Wat talking you?";

    public Ui() {

    }

    public void showWelcome() {
        System.out.println(line);
        System.out.println("Welcome to mel's drive-in!");
        System.out.println("What would you like to have?");
        System.out.println(line);
    }

    public void showLine() {
        System.out.println(line);
    }

    public void addedItem(Task curr, int size) {
        System.out.println(line);
        System.out.println(addedMsg);
        System.out.println(curr);
        System.out.println("You have ordered " + size + " items.");
        System.out.println(line);
    }

    public void showError(String ex) {
        System.out.println(line);
        System.out.println(ex);
        System.out.println(line);
    }

    public void doneItem(Task curr) {
        System.out.println(line);
        System.out.println(doneMsg);
        System.out.println(curr);
        System.out.println(line);
    }

    public void deleteItem(Task curr) {
        System.out.println(line);
        System.out.println(deleteMsg);
        System.out.println(curr);
        System.out.println(line);
    }

    public void returnList(ArrayList<Task> curr) {
        System.out.println(line);
        System.out.println(retListMsg);
        for (int k = 0; k < curr.size(); k++) {
            System.out.println((k + 1) + ": " + curr.get(k));
        }
        System.out.println((line));
    }
    public void save() {
        System.out.println(line);
        System.out.println(saveMsg);
        System.out.println(line);
    }
    public void defaultError() {
        System.out.println(line);
        System.out.println(defaultError);
        System.out.println(line);
    }

    public void bye() {
        System.out.println(line);
        System.out.println("Bye! Please come again!");
        System.out.println(line);
    }
}
