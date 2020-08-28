public class Listing {

  String title;
  Boolean isDone = false;

  public Listing(String s) {
    this.title = s;
  }

  public void complete() {
    isDone = true;
  }

  public String doneness() {
    if (isDone) {
      return "[✓]";
    } else {
      return "[✗]";
    }
  }

  public void checkDoneness(String s) {
    if (s.equals("1")) {
      this.isDone = true;
    } else {
      this.isDone = false;
    }
  }

  public String[] toArray() {
    return new String[2];
  }

  public String toString() {
    return this.title;
  }
}
