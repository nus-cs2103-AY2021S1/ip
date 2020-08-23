import java.util.Scanner;

public class Ui {
    private static final String LINE = "\t____________________________________________________________";
    private static final Scanner sc = new Scanner(System.in);
    public Ui() {

    }

    public void sendGreeting() {
        String logo = " ____        _        \n\t "
                + "|  _ \\ _   _| | _____ \n\t "
                + "| | | | | | | |/ / _ \\\n\t "
                + "| |_| | |_| |   <  __/\n\t "
                + "|____/ \\__,_|_|\\_\\___|\n\t ";
        System.out.println(LINE + "\n\t " + logo + "\n\t Hello! I'm Duke\n\t What can I do for you?\n" + LINE);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public String readCommand() {
        return sc.nextLine();
    }

   public void showError(Exception e) {
        System.out.println("\t " + e.getMessage());
   }

   public void printMessage(String message) {
        System.out.println("\t " + message);
   }







}
