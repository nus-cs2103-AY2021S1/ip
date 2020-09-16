import java.util.ArrayList;

/**
 * The TaskList class manages the adding, completion, finding, deletion and printing of the listings
 * using an ArrayList.
 */

public class TaskList {

    public ArrayList<Listing> list;

    /**
     * Takes in a new Arraylist<Listing> and initialises list.
     *
     * @return a new TaskList object
     */
    public TaskList(ArrayList<Listing> l) {
        this.list = l;
    }

    /**
     * Prints the toString() value of each listing in the list.
     */
    protected String printReturns() {
        String s = "";
        s += "    ____________________________________________________________\n"
                + "     Here are the tasks in your list:\n";
        for (int i = 0; i < list.size(); i++) {
            s += "     " + (i + 1) + "." + list.get(i).toString() + "\n";
        }
        s += "    ____________________________________________________________";
        System.out.println(s);
        return s;
    }

    /**
     * Modifies the done boolean in the listing objects inside the list. Print the correct message
     * through printer * and updates storage through the storage input.
     *
     * @param value   used to find the
     *                listing *
     * @param printer to print the object message
     * @param storage to update Duke.txt
     */
    public String doneListings(Integer value, Printer printer, Storage storage) {
        String output = "";
        Listing item = list.get(value - 1);
        item.complete();
        storage.save(list);
        output = printer.doneMessage(item.toString());
        System.out.println(output);
        return output;
    }

    /**
     * Deletes the listing object from the list . Print the correct message through printer and
     * updates storage through the storage input
     *
     * @param num     used to find the listing
     * @param printer to print the object message
     * @param storage to update Duke.txt
     */
    public String deleteListing(Integer num, Printer printer, Storage storage) {
        String output = "";
        output = printer.deleteMessage(list.size() - 1, list.get(num).toString());
        list.remove((int) num);
        storage.save(list);
        System.out.println(output);
        return output;
    }

    /**
     * Tags the listing object from the list . Print the correct message through printer and
     * updates storage through the storage input
     *
     * @param tagNum  used to find the listing
     * @param printer to print the object message
     * @param storage to update Duke.txt
     */
    public String tagListing(Integer tagNum, String tagDetail, Printer printer, Storage storage) {
        String output = "";
        output = printer.tagMessage(tagDetail, list.get(tagNum).toString());
        list.get((int) tagNum).tags.add(tagDetail);
        storage.save(list);
        System.out.println(output);
        return output;
    }


    /**
     * Adds the listing object to the list . Print the correct message through printer and updates
     * storage through the storage input
     *
     * @param details used to provide the details to the listing
     * @param printer to print the object message
     * @param storage to update Duke.txt
     */
    public String addListings(String[] details, Printer printer, Storage storage) {
        int size = list.size() + 1;
        String output = "";
        String taskInfo = details[1];
        String dateInfo = details[2];
        switch (details[0]) {
            case ("todo"):
                ToDo todo = new ToDo(taskInfo);
                list.add(todo);
                output = printer.printListing(todo, size);
                break;
            case ("deadline"):
                Deadline deadline = new Deadline(taskInfo, dateInfo);
                list.add(deadline);
                output = printer.printListing(deadline, size);
                break;
            case ("event"):
                Event event = new Event(taskInfo, dateInfo);
                list.add(event);
                output = printer.printListing(event, size);
                break;
        }
        storage.save(list);
        System.out.println(output);
        return output;
    }

    /**
     * Takes in a string and loops the list for the message (inside listing.title) using
     * String.get(message) then prints out the corresponding messages.
     *
     * @param message the message requested by the user.
     */
    public String find(String message) {
        String output = "";
        output += "    ____________________________________________________________\n"
                + "     Here are the matching tasks and their corresponding order!:\n";
        for (int i = 0; i < list.size(); i++) {
            Listing listing = list.get(i);
            if (list.get(i).title.contains(message) || list.get(i).tags.contains(message)) {
                output += "     " + (i + 1) + "." + listing.toString() + "\n";
            }
        }
        output += "    ____________________________________________________________\n";
        System.out.println(output);
        return output;
    }

}
