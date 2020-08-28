import java.util.ArrayList;

public class TaskList {

  public ArrayList<Listing> list;

  /**
   * Takes in a new Arraylist<Listing> and initialises list
   */
  public TaskList(ArrayList<Listing> l) {
    this.list = l;
  }

  /**
   * Prints the toString() value of each listing in the list
   */
  protected void printReturns() {
    System.out.println("    ____________________________________________________________");
    System.out.println("     Here are the tasks in your list:");
    for (int i = 0; i < list.size(); i++) {
      String s = "     " + (i + 1) + "." + list.get(i).toString();
      System.out.println(s);
    }
    System.out.println("    ____________________________________________________________");
  }

  /**
   * Modifies the done boolean in the listing objects inside the list. Print the correct message
   * through printer * and updates storage through the storage input * @param num used to find the
   * listing * @param Printer  to print the object message * @param  Sttorage to update Duke.txt
   */
  public void doneListings(Integer value, Printer printer, Storage storage) {
    Listing item = list.get(value - 1);
    item.complete();
    printer.doneMessage(item.toString());
    storage.save(list);
  }

  /**
   * Deletes the listing object from the list . Print the correct message through printer and
   * updates storage through the storage input
   *
   * @param num     used to find the listing
   * @param printer to print the object message
   * @param storage to update Duke.txt
   */
  public void deleteListing(Integer num, Printer printer, Storage storage) {
    printer.deleteMessage(list.size() - 1, list.get(num).toString());
    list.remove((int) num);
    storage.save(list);
  }

  /**
   * Adds the listing object to the list . Print the correct message through printer and updates
   * storage through the storage input
   *
   * @param details used to provide the details to the listing
   * @param printer to print the object message
   * @param storage to update Duke.txt
   */
  public void addListings(String[] details, Printer printer, Storage storage) {
    int size = list.size() + 1;
    String taskInfo = details[1];
    String dateInfo = details[2];
    switch (details[0]) {
      case ("todo"):
        ToDo todo = new ToDo(taskInfo);
        list.add(todo);
        printer.printListing(todo, size);
        break;
      case ("deadline"):
        Deadline deadline = new Deadline(taskInfo, dateInfo);
        list.add(deadline);
        printer.printListing(deadline, size);
        break;
      case ("event"):
        Event event = new Event(taskInfo, dateInfo);
        list.add(event);
        printer.printListing(event, size);
        break;
    }
    storage.save(list);// <----- change this
  }

  /**
   * Takes in a string and loops the list for the message (inside listing.title) using
   * String.get(message) then prints out the corresponding messages.
   *
   * @param message the message requested by the user.
   */
  public void find(String message) {
    System.out.println("    ____________________________________________________________");
    System.out.println("     Here are the matching tasks and their corresponding order!:");
    for (int i = 0; i < list.size(); i++) {
      Listing listing = list.get(i);
      if (list.get(i).title.contains(message)) {
        System.out.println("     " + (i + 1) + "." + listing.toString());
      }
    }
    System.out.println("    ____________________________________________________________");
  }

}
