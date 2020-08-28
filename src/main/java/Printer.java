import java.util.ArrayList;

public class Printer {

  private String GREETING = "     Hello! I'm Duke\n" +
      "     What can I do for you?";
  private String GOODBYE = "     Bye. Hope to see you again soon!";
  private String LINE = "    ____________________________________________________________";
  private String GOT_IT = "     Got it. I've added this task: ";
  private String WHITE_SPACE_SEVEN = "       ";

  public Printer() {
  }

  /**
   * Prints the greeting message
   */
  protected void greeting() {
    String s = "";
    s = LINE + "\n" + GREETING + "\n" + LINE;
    System.out.println(s);
  }

  /**
   * Prints the farewell message
   */
  protected void farewell() {
    String s = "";
    s = LINE + "\n" + GOODBYE + "\n" + LINE;
    System.out.println(s);
  }

  /**
   * Prints the listing size message
   */
  protected void printListing(Listing l, int size) {
    String s = "";
    s = LINE + "\n" + GOT_IT + "\n" + WHITE_SPACE_SEVEN + l.toString() +
        "\n" + "     Now you have " + size + " tasks in the list." + "\n" + LINE;
    System.out.println(s);
  }

  /**
   * Prints the undefinedExceptionMessage message
   */
  protected void undefinedExceptionMessage() {
    System.out
        .println(LINE + "\n" + "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-("
            + "\n" + LINE);
  }

  /**
   * Prints the noExceptionMessage message
   */
  protected void noDescriptionMessage(String s) {
    System.out
        .println(LINE + "\n" + "     ☹ OOPS!!! The description of a " + s + " cannot be empty."
            + "\n" + LINE);
  }

  /**
   * Prints the delete message
   */
  protected void deleteMessage(int size, String listing) {
    System.out.println(LINE + "\n" + "     Noted. I've removed this task: \n" + WHITE_SPACE_SEVEN +
        listing + "\n" + "     Now you have " + size + " tasks in the list.\n" + LINE);
  }

  /**
   * Prints the done message
   */
  protected void doneMessage(String s) {
    System.out.println(LINE + "\n" + "     Nice! I've marked this task as done: \n" + "     "
        + s + "\n" + LINE);
  }

}
