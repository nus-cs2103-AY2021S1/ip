import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Encapsulates a Parser
 * Deals with making sense of the user command
 */

public class Parser {

    protected Storage storage;
    protected Ui ui;
    protected TaskList tasks;

    /**
     * Constructor
     *
     * @param storage is the storage that stores the data file
     * @param ui is the user interface used with duke
     * @param list is the TaskList that will be updated
     */
    public Parser(Storage storage, Ui ui, TaskList list) {
        this.storage = storage;
        this.ui = ui;
        this.tasks = list;
    }

    /**
     * Processes the user's inputs
     *
     * @param input is the user's input in String form
     * @throws DukeException
     */
    protected String processInput(String input) throws DukeException {
        int stringLength = input.length();
        assert stringLength >= 1 : "Input cannot be empty";
        if (input.equals("list")) {
            return this.provideList();
        } else if (input.startsWith("done")) {
            return this.markAsDone(input);
        } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
            return this.newTaskEntry(input);
        } else if (input.equals("bye")) {
            return this.end();
        } else if (input.startsWith("delete")) {
            return this.delete(input);
        } else if (input.startsWith("find")) {
            return this.find(input);
        } else if (input.equals("remove duplicates")) {
            return this.removeDupes();
        } else {
            return new InputNotRecognisedException().toString();
        }
    }

    /**
     * Compares two tasks
     *
     * @return a boolean denoting if they are equal
     */
    public boolean areEqualTasks(Task t1, Task t2) {
        if (t1 instanceof Todo && t2 instanceof Todo) {
            Todo thisTask = (Todo) t1;
            Todo otherTask = (Todo) t2;
            return t1.equals(t2);
        } else if (t1 instanceof Deadline && t2 instanceof Deadline) {
            Deadline thisTask = (Deadline) t1;
            Deadline otherTask = (Deadline) t2;
            return t1.equals(t2);
        } else if (t1 instanceof Event && t2 instanceof Event) {
            Event thisTask = (Event) t1;
            Event otherTask = (Event) t2;
            return t1.equals(t2);
        } else {
            return false;
        }
    }



    /**
     * Finds and remove duplicated Tasks
     *
     * @return a String message
     */
    private String removeDupes() {
        boolean needsUpdate = false;
        for (int i = 0; i < tasks.length() - 1; i++) {
            Task focalTask = tasks.get(i);
            for (int j = 0; j < tasks.length(); j++) {
                if (j != i) {
                    Task otherTask = tasks.get(j);
                    if (areEqualTasks(focalTask, otherTask)) {
                        needsUpdate = true;
                        tasks.remove(otherTask);
                    }
                }
            }
        }
        if (needsUpdate) {
            this.storage.saveListToData(tasks.get());
            return this.ui.removedDupes();
        } else {
            return this.ui.noDupes();
        }
    }


    /**
     * Finds and prints out Tasks with the keywords provided by the user
     *
     * @param input the user's input in String form
     * @return a String Message
     */
    private String find(String input) {
        int stringLength = input.length();
        assert stringLength >= 1 : "Input cannot be empty";
        String keyword = input.substring(5);
        ArrayList<Task> temp = new ArrayList<>();
        for (int i = 0; i < tasks.length(); i++) {
            Task currentTask = tasks.get(i);
            System.out.println(currentTask);
            if (currentTask.getDescription().contains(keyword)) {
                temp.add(currentTask);
            }
        }
        String output = "";
        for (int i = 0; i < temp.size(); i++) {
            Task task = temp.get(i);
            String stringedIndex = Integer.toString(temp.indexOf(task) + 1);
            String outputLine = stringedIndex + ". " + task;
            output = output + outputLine + "\n";
        }
        return ui.findMsg() + "\n"
                        + output;
    }

    /**
     * Prints out the TaskList
     *
     * @return a String Message
     */
    private String provideList() {
        String items = "";
        for (int i = 0; i < tasks.length(); i++) {
            Task task = tasks.get(i);
            String stringedIndex = Integer.toString(i + 1);
            String outputLine = stringedIndex + ". " + task;
            items = items + outputLine + "\n";
        }
        storage.saveListToData(tasks.get());
        return ui.listMsg() + "\n" + items;
    }

    /**
     * Marks a Task on the TaskList as done
     *
     * @param input is the user's input in String form
     * @return a String Message
     */
    private String markAsDone(String input) {
        int stringLength = input.length();
        assert stringLength >= 1 : "Input cannot be empty";
        String stringIndex = input.substring(5, input.length());
        int index = Integer.parseInt(stringIndex);
        Task chosen = this.tasks.get(index - 1);
        chosen.finish();
        storage.saveListToData(this.tasks.get());
        return ui.markAsDoneMsg(chosen);
    }

    /**
     * Checks the type of Task the user is trying to create
     *
     * @param input is the User's input in String form
     * @throws EmptyDescriptionException
     * @throws WrongFormatException
     * @return a String Message
     */
    private String newTaskEntry(String input) throws EmptyDescriptionException, WrongFormatException {
        int stringLength = input.length();
        assert stringLength >= 1 : "Input cannot be empty";
        if (input.contains("todo")) {
            return this.createAndAddTodo(input);
        } else if (input.contains("deadline")) {
            return this.createAndAddDeadline(input);
        } else {
            return this.createAndAddEvent(input);
        }
    }

    /**
     * Creates and adds a Task with type "Todo" to the TaskList
     *
     * @param input is the description of the task as given by user
     * @throws EmptyDescriptionException
     * @throws WrongFormatException
     * @return a String Message
     */
    private String createAndAddTodo(String input) throws EmptyDescriptionException, WrongFormatException {
        int stringLength = input.length();
        assert stringLength >= 1 : "Input cannot be empty";
        if (input.length() < 5 || input.substring(5).replaceAll("\\s", "").equals("")) {
            return new EmptyDescriptionException("todo").toString();
        } else if (!Character.toString(input.charAt(4)).equals(" ")) {
            return new WrongFormatException("todo").toString();
        } else {
            Task task = new Todo(input.substring(5, input.length()));
            return this.addTaskToTasklist(task);
        }
    }

    /**
     * Creates and adds a Task with type "Deadline" to the TaskList
     *
     * @param input is the description of the task as given by user
     * @throws EmptyDescriptionException
     * @throws WrongFormatException
     * @return a String Message
     */
    private String createAndAddDeadline(String input) throws EmptyDescriptionException, WrongFormatException {
        int stringLength = input.length();
        assert stringLength >= 1 : "Input cannot be empty";
        if (input.length() < 9 || input.substring(8, input.indexOf("/")).replaceAll("\\s", "").equals("")) {
            return new EmptyDescriptionException("deadline").toString();
        } else if (input.contains("/by")
                && Character.toString(input.charAt(8)).equals(" ")
                && Character.toString(input.charAt(input.indexOf("/") + 3)).equals(" ")
                && Character.toString(input.charAt(input.indexOf("/") - 1)).equals(" ")) {
            String desc = input.substring(9, input.indexOf("/") - 1);
            String by = input.substring(input.indexOf("/") + 4, input.length());
            if (by.matches(".*[a-zA-Z]+.*")) {
                return new WrongFormatException("deadline").toString();
            }
            try {
                LocalDate date = LocalDate.parse(by);
                Task task = new Deadline(desc, date);
                return this.addTaskToTasklist(task);
            } catch (Exception e) {
                return e.toString();
            }

        } else {
            return new WrongFormatException("deadline").toString();
        }
    }

    /**
     * Adds a Task to the TaskList
     *
     * @param task is the Task to be added to the TaskList
     * @throws EmptyDescriptionException
     * @throws WrongFormatException
     * @return a String Message
     */
    private String addTaskToTasklist(Task task) {
        this.tasks.add(task);
        int listCount = this.tasks.length();
        storage.saveListToData(this.tasks.get());
        return ui.addTaskToTasklistMsg(task, listCount);
    }

    /**
     * Creates and adds a Task with type "Event" to the TaskList
     *
     * @param input is the description of the task as given by user
     * @throws EmptyDescriptionException
     * @throws WrongFormatException
     * @return a String Message
     */
    private String createAndAddEvent(String input) throws EmptyDescriptionException, WrongFormatException {
        int stringLength = input.length();
        assert stringLength >= 1 : "Input cannot be empty";
        if (input.length() < 6 || input.substring(5).replaceAll("\\s", "").equals("")) {
            throw new EmptyDescriptionException("event");
        } else if (input.contains("/at")
                && Character.toString(input.charAt(5)).equals(" ")
                && Character.toString(input.charAt(input.indexOf("/") + 3)).equals(" ")
                && Character.toString(input.charAt(input.indexOf("/") - 1)).equals(" ")) {
            String desc = input.substring(6, input.indexOf("/") - 1);
            String at = input.substring(input.indexOf("/") + 4, input.length());
            Task task = new Event(desc, at);
            return this.addTaskToTasklist(task);
        } else {
            return new WrongFormatException("event").toString();
        }
    }

    /**
     * Exit sequence of the Application
     * @return a String Message
     */
    private String end() {
        storage.saveListToData(this.tasks.get());
        return ui.farewell();
    }

    /**
     * Deletes a Task in the TaskList based on user input
     *
     * @param input is the position of the Task to be deleted
     * @throws EmptyListException
     * @throws InvalidListIndexException
     * @return a String Message
     */
    private String delete(String input) throws EmptyListException, InvalidListIndexException {
        int stringLength = input.length();
        assert stringLength >= 1 : "Input cannot be empty";
        String stringIndex = input.substring(7, input.length());
        if (stringIndex.matches(".*[a-zA-Z]+.*")) {
            return new InvalidListIndexException().toString();
        } else {
            int index = Integer.parseInt(stringIndex);
            System.out.println(index);
            if (this.tasks.isEmpty()) {
                return new EmptyListException().toString();
            } else if (index > 0 && index <= this.tasks.length()) {
                Task chosen = this.tasks.get(index - 1);
                this.tasks.remove(chosen);
                int listLength = this.tasks.length();
                storage.saveListToData(this.tasks.get());
                return ui.returnDeleteMsg(listLength, chosen);
            } else {
                return new InvalidListIndexException().toString();
            }
        }
    }
}
