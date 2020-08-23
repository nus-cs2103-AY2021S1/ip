import java.util.Scanner;

public class CommandScanner {
  private Scanner sc;

  public CommandScanner() {
    this.sc = new Scanner(System.in);
  }

  public Command nextCommand() throws DukeException {
    return Parser.parseCommandString(this.sc.nextLine());
  }
}
