package storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import commands.Deadline;
import commands.Event;
import commands.Task;
import exceptions.DukeException;
import ui.DateConverter;

/**
 * Storage.Storage.TaskList manages the list of tasks and implements the iterable interface
 */
public class TaskList implements Iterable<Task> {


    private ArrayList<Task> listOfItems;
    private ArrayList<Task> listOfKeyWordItems = new ArrayList<>();


    public TaskList() {
        this.listOfItems = new ArrayList<>();
    }

    /**
     * adds a task to the list of items
     *
     * @param item task that is being added
     * @return String output stating its has been added
     */
    public String add(Task item) {
        this.listOfItems.add(item);

        return String.format("\nGot it. I've added this task:\n  %s\nNow you have %d tasks in your list.\n",
                item.toString(),
                this.listOfItems.size());
    }

    public ArrayList<Task> getListOfItems() {
        return listOfItems;
    }

    /**
     * marks a task in the list with [✓] to state it is done
     *
     * @param index position of the task to be marked in the list
     * @return String output stating that the task has been marked completed
     * @throws DukeException if the number given is not on the list
     */
    public String markCompleted(int index) throws DukeException {
        try {
            Task item = this.getListOfItems().get(index);
            item.markAsDone();

            return String.format("\nNice! I've marked this task as done:\n  %s\n", item.toString());

        } catch (IndexOutOfBoundsException e) {
            return (new DukeException("List does not contain the number specified")).toString();
        }
    }

    /**
     * removes a task form the list at the specified position
     *
     * @param index
     * @return String output stating that the task has been removed
     * @throws DukeException if the number given is not on the list
     */
    public String deleteTask(int index) throws DukeException {

        try {

            Task item = this.getListOfItems().remove(index);

            return String.format("\nNoted. I've removed this task:\n  %s\nNow you have %d tasks in your list.\n",
                    item.toString(),
                    this.getListOfItems().size());
        } catch (IndexOutOfBoundsException e) {
            return (new DukeException("List does not contain the number specified")).toString();
        }
    }

    /**
     * reschedule either a deadline or event with a new date and time
     * @param details the details to change the date to
     * @return String withe the new date and time
     */
    public String rescheduleTask(String details) {
        if (details == null) {
            throw new DukeException("I need something to work with.");
        }
        try {
            String[] detailsArray = details.split("/to", 2);

            String indexString = detailsArray[0].trim();
            int index = Integer.parseInt(indexString) - 1;
            Task temp = this.getListOfItems().get(index);
            String dateTimeString = detailsArray[1].trim();
            LocalDateTime dateTime = DateConverter.parseString(dateTimeString);

            if (temp instanceof Deadline) {
                Task item = new Deadline(temp.getDescription(), dateTime);
                if (temp.getIsDone()) {
                    item.markAsDone();
                }

                this.getListOfItems().set(index, item);
                return String.format("\nNoted. I have now rescheduled %s to :\n "
                                + " %s\nYou still have %d tasks in your list.\n", temp.toString(), item.toString(),
                        this.getListOfItems().size());
            } else if (temp instanceof Event) {
                Task item = new Event(temp.getDescription(), dateTime);
                if (temp.getIsDone()) {
                    item.markAsDone();
                }
                this.getListOfItems().set(index, item);
                return String.format("\nNoted. I have now rescheduled %s to :\n"
                                + "  %s\nYou still have %d tasks in your list.\n", temp.toString(), item.toString(),
                        this.getListOfItems().size());
            } else {
                return (new DukeException("Commands.ToDo Commands.Task detected. Unable to reschedule")).toString();
            }
        } catch (Exception e) {
            return (new DukeException("Unable reschedule the specified Commands.Task")).toString();
        }
    }

    /**
     * searches the listOF Items for any task that contains the key word
     * @param keyWord word being looked for
     */
    public void findTask(String keyWord) {
        assert keyWord.length() > 0;

        listOfKeyWordItems.clear();
        for (Task item : getListOfItems()) {
            if (item.toString().indexOf(keyWord) != -1) {
                listOfKeyWordItems.add(item);
            }
        }
    }

    /**
     * prints out the list of items that have the keyword contained
     * @return a string of the list of key words tasks
     */
    public String printOutKeyWordList() {
        String list = "\nHere are the matching tasks in your list:\n";
        for (int i = 0; i < listOfKeyWordItems.size(); i++) {
            list += String.format("%d.%s\n", i + 1, listOfKeyWordItems.get(i).toString());
        }
        return list;
    }

    /**
     * prints out the list of all tasks
     * @return string output of the list of items
     */
    public String printOutList() {
        String list = "\nHere are the tasks in your list:\n";
        for (int i = 0; i < this.getListOfItems().size(); i++) {
            list += String.format("%d.%s\n", i + 1, this.listOfItems.get(i).toString());
        }
        return list;
    }

    /**
     * iterates over the list of items
     *
     * @return iterator with generic T as Commands.Task
     */
    @Override
    public Iterator<Task> iterator() {
        return this.listOfItems.iterator();
    }

}
