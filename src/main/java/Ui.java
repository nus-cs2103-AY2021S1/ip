import java.util.Scanner;

public class Ui {
    protected Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void printGreeting() {
        System.out.println("Yooo, I'm Duke.\nWhat can I do for you today?\nPlease enter dates and times in this format: yyyy-mm-dd hhmm"); //Greeting
    }

    public void printGoodbye() {
        System.out.println("Bye bye!!! See you again next time :)");
    }

    public void printLoadingError(Exception e) {
        System.out.println(e);
    }

    public void printTasks(TaskList list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i).display());
        }
    }
}
