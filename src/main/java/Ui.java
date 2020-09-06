import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    final private String line = "-------------------------------------";
    final private String loadFailMsg = "Sorry, I forgot you existed!";
    final private String addedMsg = "Alright, I've added a new order: ";
    final private String doneMsg = "Great choice! I have taken your order: ";
    final private String deleteMsg = "Too bad. I'll remove the following order: ";
    final private String saveMsg = "Aright, I have remembered your sins :)";
    final private String retListMsg = "Here's what you have ordered...";
    final private String defaultError = "Wat talking you?";
    final private String byeMsg = "Bye! Please come again!";
    final private String newLine = "\n";

    private Scanner s;

    public Ui() {
        this.s = new Scanner(System.in);
    }

    /**
     * Reads the next line of input by user.
     *
     * @return the next command
     */
    public String readCommand() {
        return s.nextLine();
    }

    public static String welcome() {
        return "Welcome to Hyu's drive-in!" + "\n" + "What would you like to have?" + "\n";
    }

    public void showLine() {
        System.out.println(line);
    }

    public String addedItem(Task curr, int size) {
         return addedMsg + newLine + curr + newLine + "You have ordered " + size + " items." + newLine;
    }

    public String showError(String ex) {
        return ex + newLine;
    }

    public String doneItem(Task curr) {
        return doneMsg + newLine + curr;
    }

    public String deleteItem(Task curr) {
        return deleteMsg + newLine + curr;
    }

    public String returnList(ArrayList<Task> curr) {
        String temp = "";
        for (int k = 0; k < curr.size(); k++) {
            temp += ((k + 1) + ": " + curr.get(k)) + newLine;
        }
        return retListMsg + newLine + temp;
    }

    public String save() {
        return saveMsg + newLine;
    }

    public String defaultError() {
        return defaultError + newLine;
    }

    public String bye() {
        return byeMsg + newLine;
    }

    public String showLoadingError() {
        return loadFailMsg + newLine;
    }
}
