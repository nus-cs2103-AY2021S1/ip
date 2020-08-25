import java.util.Scanner;

public class Ui {
  private static String duke = "Duke> ";
  private static String cmd = ">> ";
  private static String logo =
      " ____        _        \n"
          + "|  _ \\ _   _| | _____ \n"
          + "| | | | | | | |/ / _ \\\n"
          + "| |_| | |_| |   <  __/\n"
          + "|____/ \\__,_|_|\\_\\___|\n";
  private Scanner sc;

  public Ui() {
    sc = new Scanner(System.in);
  }

  public void startupMsg() {
    System.out.println(logo);
    System.out.println(duke + "Hi I'm Duke!");
    System.out.println(duke + "What can I do for you?");
  }

  public String readInput() {
    System.out.print(cmd);
    return sc.nextLine();
  }

  public void showHelp() {
    String s = "Here's what I can do:\n";
    String msg =
        "Available Commands: \n"
            + "'todo' \n"
            + "'deadline' \n"
            + "'event' \n"
            + "'list' \n"
            + "'delete' \n"
            + "'bye'";
    System.out.println(s + msg);
  }

  public void showError(Exception e) {
    System.out.println(duke + e.getMessage());
  }
}
