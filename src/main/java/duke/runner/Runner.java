package duke.runner;

import duke.input.Parser;
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
    System.out.println("Hi. Duke here.\nHow can I help you?");
    Scanner sc = new Scanner(System.in);

    String input;
    while (!Parser.isExitCommand(input = sc.nextLine())) {
      System.out.println(Parser.runCommand(input));
    }

    System.out.println("Bye. Hope to see you again.");
  }
}
