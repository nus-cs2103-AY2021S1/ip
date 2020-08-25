import java.util.Scanner;

public class Ui {
    Scanner sc = new Scanner(System.in);

    public void greetings() {
        showLine();
        String greetings = "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println(greetings);
        showLine();
    }

    public void goodbye() {
        // print goodbye message
        String goodbye = "Bye. Hope to see you again soon!";
        System.out.println(goodbye);
        showLine();
        // exits program
        System.exit(0);
    }

    public void showLine() {
        System.out.println("____________________________________________________________\n");
    }

    public String readCommand() {
        return sc.nextLine().trim(); // get rid of leading and trailing spaces
    }
}
