import java.util.Scanner;

/**
 * UI class.
 * Handles input from user.
 *
 * @author YanCheng
 */
public class Ui {

    public TaskList taskList;
    public Storage storage;
    public Parser parser;

    /**
     * Constructor for Ui class.
     * @param taskList A TaskList object
     * @param storage A Storage object
     * @param parser A Parser object
     */
    public Ui(TaskList taskList, Storage storage, Parser parser) {
        this.taskList = taskList;
        this.storage = storage;
        this.parser = parser;
    }

    /**
     * Prompts user for input and delegates input to Parser.
     */
    public void echo() {

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                taskList.listOut();
            } else if (input.contains("find")) {
                taskList.find(input);
            } else if (input.contains("done")) {
                try {
                    taskList.done(input);
                    storage.save();
                } catch (DukeException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     " + e.getMessage());
                    System.out.println("    ____________________________________________________________");
                    System.out.println();
                }
            } else if (input.contains("delete")) {
                try {
                    taskList.delete(input);
                    storage.save();
                } catch (DukeException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     " + e.getMessage());
                    System.out.println("    ____________________________________________________________");
                    System.out.println();
                }
            } else {

                try {
                    if (input.contains("todo")) {
                        parser.handleToDo(input);
                    } else if (input.contains("deadline")) {
                        parser.handleDeadline(input);
                    } else if (input.contains("event")) {
                        parser.handleEvent(input);
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }

                    System.out.println();
                } catch (DukeException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     " + e.getMessage());
                    System.out.println("    ____________________________________________________________");
                    System.out.println();
                }
            }
        }
        sc.close();
    }
}
