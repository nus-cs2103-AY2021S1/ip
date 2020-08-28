import java.util.ArrayList;

public class TaskList {

  public ArrayList<Listing> list;

  public TaskList(ArrayList<Listing> l) {
    this.list = l;
  }

  protected void printReturns() {
    System.out.println("    ____________________________________________________________");
    System.out.println("     Here are the tasks in your list:");
    for (int i = 0; i < list.size(); i++) {
      String s = "     " + (i + 1) + "." + list.get(i).toString();
      System.out.println(s);
    }
    System.out.println("    ____________________________________________________________");
  }

  public void doneListings(Integer value, Printer printer, Storage storage) {
    Listing item = list.get(value - 1);
    item.complete();
    printer.doneMessage(item.toString());
    storage.save(list);
  }

  public void deleteListing(Integer num, Printer printer, Storage storage) {
    printer.deleteMessage(list.size() - 1, list.get(num).toString());
    list.remove((int) num);
    storage.save(list);
  }

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

}
