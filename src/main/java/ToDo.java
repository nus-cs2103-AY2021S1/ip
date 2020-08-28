public class ToDo extends Listing {

  /**
   * Creates a ToDo object by passing the string s as the Todo message
   */
  public ToDo(String s) {
    super(s);
  }

  /**
   * Creates a ToDo object by passing a string doneness which gets converted to a boolean and
   * assigned to this.isDone. String s gets passed as the message detail
   *
   * @param doneness a string of either 1 or 0 that gets converted to boolean
   * @param s        the message detail
   */
  public ToDo(String doneness, String s) {
    super(s);
    checkDoneness(doneness);
  }

  /**
   * Summarises the details of the ToDo object into a size 3 string array containing a code "T" that
   * represents ToDO, isDone boolean in the form of a string and the detail message
   */
  public String[] toArray() {
    String[] details = new String[3];
    details[0] = "T";
    if (this.isDone) {
      details[1] = "1";
    } else {
      details[1] = "0";
    }
    details[2] = this.title;
    return details;
  }

  /**
   * Prints the ToDo object in string.
   */
  @Override
  public String toString() {
    return "[T]" + super.doneness() + this.title;
  }
}
