package duke.classes;

import duke.exceptions.BlahException;
import duke.exceptions.DukeInvalidTimeException;
import duke.exceptions.EmptyDukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * The Duke class implements the DukeChatBot that is wired to store, display, update and delete
 * a variety of tasks.
 *
 * @author Rishi Ravikumar
 */
public class Duke {
    public static List<Task> todoList; //= new ArrayList<>();
    public static String line = "---------------------------------------------------";
    private Data data;
    private Parser parser;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor whereby the duke class loads data from a pre-defined path on the hard disk.
     *
     * @param path File path for local data
     */
    public Duke(String path) {
        try {
            data = new Data(path);
            //System.out.println("Before calling load");
            // todoList = data.loadData();
            this.taskList = new TaskList(data.loadData());
        } catch (IOException | DukeInvalidTimeException | ArrayIndexOutOfBoundsException e) {
            System.out.println("FAILURE: Unable to load data from local drive.");
            //todoList = new ArrayList<>();
            this.taskList = new TaskList();
        }
    }

    /**
     * Main method whereby the Duke chatbot runs.
     *
     * @param args Command to run program
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Void method that abstracts away most the logic behind running the Duke chatbot.
     *
     */
    public void run() {
        this.ui = new Ui();
        this.parser = new Parser();
        String word = this.parser.scan.nextLine();
        while (!word.equals("bye")) {
            int len = word.length();
            Commands currentCommand = this.parser.analyse(word);
            assign(currentCommand, word);
            word = this.parser.scan.nextLine();
        }
        this.ui.endDuke();
        try {
            data.save(this.taskList.todoList);
        } catch (IOException | NullPointerException e) {
            System.out.println("FAILURE: Could not save data to main/data directory.");
        }
    }

    /**
     * Void method that carries the logic behind assigning an action based on the command.
     *
     * @param command Type of command
     * @param task Actual task
     */

    public void assign(Commands command, String task) {
        switch (command) {
            case LIST:
                this.ui.displayList(this.taskList.todoList);
                break;
            case TODO:
            case EVENT:
            case DEADLINE:
                decideTaskType(command, task);
                break;
            case DONE:
                Task todo = this.taskList.markDone(task);
                this.ui.completeTask(todo);
                break;
            case DELETE:
                Task deletedTask = this.taskList.delete(task, this.taskList.todoList);
                this.ui.deleteTask(deletedTask, this.taskList.todoList);
                break;
            case BLAH:
            case TASK:
                assignOtherTasks(task);
                break;
            default:
                break;
        }
    }

    /**
     * Void method that carries the logic behind assigning the unconventional tasks.
     * tasks like normal tasks and blah.
     *
     * @param task Actual task
     */

    public void assignOtherTasks(String task) {
        try {
            this.taskList.storeTask(task);
            this.ui.addOtherTask(task);
        } catch (BlahException e) {
            this.ui.printError(e.toString());
        }
    }

    /**
     * Void method that abstracts away the logic behind assigning a method based on task type.
     *
     * @param commands Commands of TODO, DEADLINE & EVENT
     * @param task Actual activity
     */

    public void decideTaskType(Commands commands, String task) {
        Task todo = null;
        try {
            switch (commands){
                case TODO:
                    todo = taskList.storeTodo(task);
                    break;
                case DEADLINE:
                    todo = taskList.storeDeadline(task);
                    break;
                case EVENT:
                    todo = taskList.storeEvent(task);
                    break;
                default:
                    break;
            }
            this.ui.addTask(todo, this.taskList.todoList);
        } catch (EmptyDukeException | DukeInvalidTimeException e) {
            this.ui.printError(e.toString());
        }
    }

    public void runDuke(){
        Scanner scan = new Scanner(System.in);
        String word = scan.nextLine();
        int len = 0;
        while (!word.equals("bye")) {
            len = word.length();
            if (word.equals("list")) {
                System.out.println(line);
                for (Task task : todoList) {
                    System.out.println(task.getStatusWithIndex());
                }
            } else if (word.startsWith("todo") || word.startsWith("deadline") || word.startsWith("event")) {
                processTask(word);
            } else if (len > 4 && word.substring(0,5).equals("done ")) {
                int taskNo = Character.getNumericValue(word.charAt(5)) - 1;
                Task task = todoList.get(taskNo);
                task.isDone = true;
                System.out.println(line + "\nNice! I have marked this task as done: \n  " + task.toString());
            } else {
                if (len > 6 && word.substring(0,7).equals("delete ")) {
                    int taskNo = Character.getNumericValue(word.charAt(7)) - 1;
                    delete(taskNo);
                } else {
                    try {
                        storeTask(word);
                        System.out.println(line + "\nadded: " + word);
                    } catch (BlahException e){
                        System.out.println(e.toString());
                    }
                }
            }
            word = scan.nextLine();
        }
        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
        try {
            data.save(todoList);
        } catch (IOException | NullPointerException e) {
            System.out.println("FAILURE: Could not save data to an appropriate directory.");
        }
    }

    public static void storeTask(String item) throws BlahException {
        int count = todoList.size() + 1;
        if (count > 100) {
            System.out.println("You have far too many pending tasks!");
        } else if (item.equals("blah")) {
            throw new BlahException();
        } else {
            todoList.add(new Task(item, count));
        }
    }
    public static Todo storeTodo(String todo) throws EmptyDukeException {
        int count = todoList.size() + 1;
        if (count > 100) {
            System.out.println("You have far too many pending tasks!");
            return null;
        } else if (todo.length() <= 4) {
            throw new EmptyDukeException("The description of your todo is empty.");
        } else {
            Todo curr = new Todo(todo.substring(5), count, false);
            todoList.add(curr);
            return curr;
        }
    }

    public static Deadline storeDeadline(String deadline) throws EmptyDukeException, DukeInvalidTimeException {
        int count = todoList.size() + 1;
        if (count > 100) {
            System.out.println("You have far too many pending tasks!");
            return null;
        } else if (deadline.length() <= 8) {
            throw new EmptyDukeException("The description of your deadline is empty.");
        } else {
            Deadline curr = new Deadline(deadline.substring(9), count, false);
            todoList.add(curr);
            return curr;
        }
    }

    public static Event storeEvent(String event) throws EmptyDukeException, DukeInvalidTimeException {
        int count = todoList.size() + 1;
        if (count > 100) {
            System.out.println("You have far too many pending tasks!");
            return null;
        } else if (event.length() <= 5) {
            throw new EmptyDukeException("The description of your event is empty.");
        } else {
            Event curr = new Event(event.substring(6), count, false);
            todoList.add(curr);
            return curr;
        }
    }

    public static void processTask(String word) {
        Task toDo = null;
        try {
            if (word.substring(0,4).equals("todo")) {
                toDo = storeTodo(word);
            } else if (word.substring(0,5).equals("event")) {
                toDo = storeEvent(word);
            } else if (word.substring(0,8).equals("deadline")) {
                toDo = storeDeadline(word);
            }
            System.out.println(toDo == null ? "Failed!" : line + "\nGot it. I've added this task:\n   "
                    + toDo.toString()
                    + "\nNow you have " + todoList.size() + " tasks in the list." );
        } catch (EmptyDukeException | DukeInvalidTimeException e) {
            System.out.println(e.toString());
        }
    }

    public static void delete(int n) {
        Task task = todoList.remove(n);
        for (int i = n; i < todoList.size(); i++) {
            todoList.get(i).index = todoList.get(i).index - 1;
        }
        System.out.println(line + "\nNoted. I've removed this task:\n  " + task.toString()
                + "\nNow you have " + todoList.size() + " tasks in the list.");
    }
}
