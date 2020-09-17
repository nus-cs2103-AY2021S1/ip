package duke;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Ui {
    private boolean exited = false;
    private TaskList tl;
    private Storage s;
    private Stack<String> actions;

    Ui(TaskList tl, Storage s) {
        this.tl = tl;
        this.s = s;
        this.actions = new Stack<>();
    }

    /**
     * Starts the scanner and takes in the commands
     */
    public void start() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n" + "I can be your friend who manages your task!");

        try {
            while (!exited) {
                Scanner sc = new Scanner(System.in);
                String command = sc.nextLine();
                Parser.processCommand(command, tl, this);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Stops the scanner when the user inputs in "bye"
     */
    public void stop() {
        exited = true;
    }

    /**
     * Prints out a string everytime the user puts up a task
     * @param arr temporary list of tasks
     * @param t the task to print out
     */
    public static String print(ArrayList<Task> arr, Task t) {
        String keyword = "";
        String toPrint = "";
        if (t instanceof Deadline) {
            keyword = "by";
            toPrint = " (" + keyword + ": " + t.getTime() + ")";
        } else if (t instanceof Event) {
            keyword = "at";
            toPrint = " (" + keyword + ": " + t.getTime() + ")";
        }
        return "Got it. I've added this task:" + "\n" + t.getIndicator()
                + t.getIcon() + t.getName() + toPrint + "\n" + "Now you have " + arr.size() + " tasks in the list.";
    }

    /**
     * Prints out a list of tasks that match the searchword
     * @param arr temporary list of tasks
     */
    public static String printForFind(ArrayList<Task> arr) {
        if (arr.size() == 0) {
            return "There is no task that matches your searchword";
        } else {
            String str = "Here are the matching tasks in your list:";
            int counter = 1;
            for (Task t: arr) {
                String keyword = "";
                String toPrint = "";
                if (t instanceof Deadline) {
                    keyword = "by";
                    toPrint = " (" + keyword + ": " + t.getTime() + ")";
                } else if (t instanceof Event) {
                    keyword = "at";
                    toPrint = " (" + keyword + ": " + t.getTime() + ")";
                }
                str += "\n" + counter + ". " + t.getIndicator() + t.getIcon() + t.getName() + toPrint;
                counter++;
            }
            return str;
        }
    }

    /**
     * Prints out a string everytime the user indicates a task as done
     * @param arr arraylist of tasks
     */
    public static String printForDone(ArrayList<Task> arr, Task t) {
        String keyword = "";
        String toPrint = "";
        if (t instanceof Deadline) {
            keyword = "by";
            toPrint = " (" + keyword + ": " + t.getTime() + ")";
        } else if (t instanceof Event) {
            keyword = "at";
            toPrint = " (" + keyword + ": " + t.getTime() + ")";
        }
        return "Got it. Duke has marked this task as done:" + "\n" + t.getIndicator()
                + t.getIcon() + t.getName() + toPrint + "\n" + "Now you have " + arr.size() + " tasks in the list.";
    }

    /**
     * Prints out a string everytime the user indicates a task as not done
     * @param arr arraylist of tasks
     */
    public static String printForUnDone(ArrayList<Task> arr, Task t) {
        String keyword = "";
        String toPrint = "";
        if (t instanceof Deadline) {
            keyword = "by";
            toPrint = " (" + keyword + ": " + t.getTime() + ")";
        } else if (t instanceof Event) {
            keyword = "at";
            toPrint = " (" + keyword + ": " + t.getTime() + ")";
        }
        return "Got it. Duke has marked this task as not done:" + "\n" + t.getIndicator()
                + t.getIcon() + t.getName() + toPrint + "\n" + "Now you have " + arr.size() + " tasks in the list.";
    }

    /**
     * Prints out a string everytime the user deletes a task
     * @param arr temporary list of tasks
     * @param t the task to print out
     */
    public static String printForDelete(ArrayList<Task> arr, Task t) {
        String keyword = "";
        String toPrint = "";
        if (t instanceof Deadline) {
            keyword = "by";
            toPrint = " (" + keyword + ": " + t.getTime() + ")";
        } else if (t instanceof Event) {
            keyword = "at";
            toPrint = " (" + keyword + ": " + t.getTime() + ")";
        }
        return "Got it. Duke has removed this task:" + "\n" + t.getIndicator()
                + t.getIcon() + t.getName() + toPrint + "\n" + "Now you have " + arr.size() + " tasks in the list.";
    }

    /**
     * Prints out the bye statement
     */
    public String respondToBye() {
        this.stop();
        return "Bye. Please come back soon :(";
    }

    /**
     * Prints out a string saying that the list is empty
     */
    public String respondToEmptyList() {
        return "You currently do not have any todos.";
    }

    /**
     * Prints out the list of tasks when the user inputs in "list"
     * @throws Exception
     */
    public String respondToList(ArrayList<Task> arr) throws Exception {
        int counter = 1;
        String str = "Here are the tasks in your list: ";
        for (Task t: arr) {
            String keyword = "";
            String toPrint = "";
            if (t instanceof Deadline) {
                keyword = "by";
                toPrint = " (" + keyword + ": " + t.getTime() + ")";
            } else if (t instanceof Event) {
                keyword = "at";
                toPrint = " (" + keyword + ": " + t.getTime() + ")";
            }
            String taskStr = t.getIndicator() + t.getIcon() + t.getName() + toPrint;
            str += "\n" + counter + ". " + taskStr;
            counter++;
        }
        return str;
    }

    /**
     * Prints out a statement when the user forgets to put the searchword.
     */
    public String respondToFindWrongSyntax() {
        return "Provide a number of the todo that you want to mark as done!";
    }

    /**
     * Prints the filtered list.
     * @param searchWord the word on which the user wants to base their search.
     * @throws Exception
     */
    public String respondToFind(String searchWord) throws Exception {
        ArrayList<Task> temp = new ArrayList<>();
        for (Task t: tl.getArr()) {
            if (t.getName().contains(searchWord)) {
                temp.add(t);
            }
        }
        return printForFind(temp);
    }

    /**
     * Prints out a string whenever the user inputs in an invalid task number to mark as done
     */
    public String respondToDoneFail() {
        return "The specified todo does not exist!";
    }

    /**
     * Prints out a string whenever the user forgets to input in the task number
     */
    public String respondToDoneWrongSyntax() {
        return "Provide a number of the todo that you want to mark as done!";
    }

    /**
     * Prints out the statement saying that task has been marked as done and updates the list
     */
    public String respondToDone(int taskNumber) throws Exception {
        ArrayList<Task> arr = tl.getArr();
        int position = taskNumber - 1;
        Task t = arr.get(position);
        t.taskIsDone();
        actions.push("done" + " " + position);
        s.listWriter(arr);
        return printForDone(arr, t);
    }

    /**
     * Prints out an error message when the user inputs in the wrong syntax for todo
     */
    public String respondToTodoWrongSyntax() {
        TodoException te = new TodoException();
        return te.getErrorMessage();
    }

    /**
     * Prints out the statement saying that todo has been added and updates the list
     */
    public String respondToTodo(String name) throws Exception {
        ArrayList<Task> arr = tl.getArr();
        Todo t = new Todo(name);
        tl.addTask(t);
        int position = arr.size() - 1;
        actions.push("todo " + position);
        s.listWriter(arr);
        return print(arr, t);
    }

    /**
     * Prints out an error message when the user forgets to input the deadline details
     */
    public String respondToEventFail() {
        EventException ee = new EventException();
        return ee.getErrorMessage();
    }

    /**
     * Prints out a statement saying that an event has been added to the list and updates the list
     * @param name name of the event
     * @param time deadline of the event
     * @throws Exception
     */
    public String respondToEvent(String name, String time) throws Exception {
        ArrayList<Task> arr = tl.getArr();
        Event e = new Event(name, time);
        tl.addTask(e);
        int position = arr.size() - 1;
        actions.push("event" + " " + position);
        s.listWriter(arr);
        return print(arr, e);
    }

    /**
     * Prints out an error message when the user inputs in the wrong syntax for delete
     */
    public String respondToDeleteWrongSyntax() {
        return "Provide the number of the todo that you want to delete!";
    }

    /**
     * Prints out a statement saying that the specified task number does not exists
     */
    public String respondToDeleteFail() {
        return "The specified todo does not exist!";
    }

    /**
     * Prints out a statement saying that the task has been deleted and updates the list
     * @param taskNumber task number of the task that is being deleted
     * @throws Exception
     */
    public String respondToDelete(int taskNumber) throws Exception {
        ArrayList<Task> arr = tl.getArr();
        int position = taskNumber - 1;
        Task t = arr.get(position);
        tl.removeTask(t);
        if (t instanceof Todo) {
            actions.push("T" + " " + position + " " + t.getName());
        } else if (t instanceof Deadline) {
            actions.push("D" + " " + position + " " + t.getName() + " " + t.getTime());
        } else {
            actions.push("E" + " " + position + " " + t.getName() + " " + t.getTime());
        }
        s.listWriter(arr);
        return printForDelete(arr, t);
    }

    /**
     * Prints out an error message when the user forgets to input the deadline details
     */
    public String respondToDeadlineFail() {
        DeadlineException de = new DeadlineException();
        return de.getErrorMessage();
    }

    /**
     * Prints out a statement saying that a deadline has been added to the list and updates the list
     * @param name name of the event
     * @param time deadline of the event
     * @throws Exception
     */
    public String respondToDeadline(String name, String time) throws Exception {
        ArrayList<Task> arr = tl.getArr();
        Deadline d = new Deadline(name, time);
        tl.addTask(d);
        int position = arr.size() - 1;
        actions.push("deadline" + " " + position);
        s.listWriter(arr);
        return print(arr, d);
    }

    /**
     * Prints out a statement saying that the command does not exist
     */
    public String respondToCommandDoesNotExist() {
        return "Command does not exist!" + "\n";
    }

    public String popFromStack() {
        return actions.pop();
    }

    public String respondToUndo() throws Exception {
        if (actions.empty()) {
            return "Nothing to undo!";
        }
        ArrayList<Task> arr = tl.getArr();
        String action = popFromStack();
        String[] splits = action.split(" ");
        String act = splits[0];
        if (act.equals("todo") || act.equals("deadline") || act.equals("event")) {
            int position = Integer.parseInt(splits[1]);
            Task t = arr.get(position);
            tl.removeTask(t);
            s.listWriter(arr);
            return printForDelete(arr, t);
        } else if (act.equals("delete")) {
            int position = Integer.parseInt(splits[1]);
            String type = splits[2];
            if (type.equals("E")) {
                Event e = new Event(splits[3], splits[4]);
                arr.add(position, e);
                s.listWriter(arr);
                return print(arr, e);
            } else if (type.equals("D")) {
                Deadline d = new Deadline(splits[3], splits[4]);
                arr.add(position, d);
                s.listWriter(arr);
                return print(arr, d);
            } else {
                Todo t = new Todo(splits[3]);
                arr.add(position, t);
                s.listWriter(arr);
                return print(arr, t);
            }
        } else if (act.equals("done")) {
            int index = Integer.parseInt(splits[1]);
            Task t = arr.get(index);
            t.taskIsNotDone();
            s.listWriter(arr);
            return printForUnDone(arr, t);
        } else {
            return "Wrong command";
        }
    }
}
