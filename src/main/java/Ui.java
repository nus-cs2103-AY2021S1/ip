public class Ui {
    private String line = "-------------------------------------";
    private String addedMsg = "Alright, I've added a new order: ";
    private String doneMsg = "Great choice! I have taken your order: ";
    private String deleteMsg = "Too bad. I'll remove the following order: ";
    private String retListMsg = "Here's what you have ordered so far...";
    private String defaultError = "Wat talking you?";

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

    public void addedItem(Task curr) {
        System.out.println(line);
        System.out.println(addedMsg);
        System.out.println(curr);
        System.out.println("You have ordered " + Duke.list.size() + " items.");
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

    public void returnList() {
        System.out.println(line);
        System.out.println(retListMsg);
        for (int k = 0; k < Duke.list.size(); k++) {
            System.out.println((k + 1) + ": " + Duke.list.get(k));
        }
        System.out.println((line));
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
