package duke.runner;

import duke.command.Command;
import duke.duke.Duke;
import duke.exception.ParserException;
import duke.input.Parser;
import duke.view.cli.CLI;
import java.util.Scanner;

/**
 * Class that handles the "event loop" of the CLI program, terminating
 * when a termination command is detected.
 */
public class Runner {

  public static void main(String[] args) {
    run();
  }

  /**
   * Prints hello, goodbye, nd output of command executions.
   * Also runs the event loop.
   */
  public static void run() {
    Scanner sc = new Scanner(System.in);
    Duke.getInstance().addObserver(new CLI());

    String input;
    while (!Parser.isExitCommand(input = sc.nextLine().trim())) {
      try {
        Command command = Parser.parseCommand(input);
        command.execute(Duke.getInstance());
      } catch (ParserException e) {
        System.out.println(e.getMessage());
      }
    }

    System.out.println("Bye. Hope to see you again.");
  }
}
