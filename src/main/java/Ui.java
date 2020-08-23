import java.util.LinkedList;
import java.util.Scanner;

public class Ui {

    Scanner sc = new Scanner(System.in);

    public void printList(LinkedList<Task> list) {
        if (list.size() == 0) {
            System.out.println("     You have no tasks in your list now! Type todo, event or deadline to add some!");
        } else {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 1; i <= list.size(); i++) {
                System.out.println("     " + i + "." + list.get(i - 1));
            }
        }
    }

    public void printLine(String line) { System.out.println("     " + line); }

    public String receiveChat() {
        return sc.nextLine();
    }

    public void showLoading() {
        System.out.println("     Data loading...");
    }

    public void showLoaded(int num) {
        System.out.println("     Previous data found!\n     Now you have " + num + " task in the list!");
    }

    public void showLoadedNew() {
        System.out.println("     Did not find any previous stored data and new data file created! Welcome!");
    }

    public void showLoadingError() {
        System.out.println("     Oops! Cannot access your data file and no new data file has been created!");
    }

    public void welcome() {
        printHorizontal();
        System.out.println("    " + " Hello! I'm Duke!\n     What can I do for you?");
        printHorizontal();
    }

    public void printHorizontal() {
        System.out.println("    ____________________________________________________________");
    }
}
