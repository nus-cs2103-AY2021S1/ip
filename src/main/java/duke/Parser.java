package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Parser class comprehends and carries out the user commands
 * It is used in the Ui class
 */
public class Parser {
    public  Parser(){}

    public static void printLine() {
        System.out.println("-------------------------------");
    }

    /**
     * Constructor for loading deadlines using switch and case
     * @param sc            the scanner used to take in the user commands
     * @param todoList      the array list the stores all the todos
     * @param store         the Storage that handles the loading and saving of tasks into the schedule text file
     */
    public void parse(Scanner sc, ArrayList<Task> todoList, Storage store) throws IOException {
        String command = sc.next();
        switch (command) {
            case "bye":
                printLine();
                System.out.println("See you later alligator ");
                printLine();
                System.exit(0);
                break;
            case "list":
                printLine();
                System.out.println("Here's what you got: ");
                if (todoList.size() == 0) {
                    System.out.println("You are free!!");
                }
                for (Task task :
                        todoList) {
                    System.out.println(todoList.indexOf(task) + 1 + " " + task.toString());
                }
                printLine();
                break;
            case "done":
                printLine();
                int taskID = sc.nextInt() - 1;
                Task task = todoList.get(taskID);
                task.markAsDone();
                System.out.println("Gratz, you finished this dawg :");
                System.out.println(task.toString());
                printLine();
                break;
            case "todo":
                printLine();
                String name = sc.nextLine();
                try {
                    if (name.isEmpty() || name == " ") {
                        throw new DukeException("no task indicated");
                    } else {
                        Todo td = new Todo(name);
                        todoList.add(td);
                        System.out.println("Aight new task for you: \n" + td.toString());
                        System.out.println("Now you got " + todoList.size()
                                + " task(s) waiting man");
                        store.write(td);
                    }
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                printLine();
                break;
            case "deadline":
                printLine();
                String fullDL = sc.nextLine();

                try {
                    String dlName = fullDL.split("/by")[0];
                    LocalDate dlTime = LocalDate.parse(fullDL.split("/by ")[1]);
                    Deadline dl = new Deadline(dlName, dlTime);
                    todoList.add(dl);
                    System.out.println("Aight new task for you: \n" + dl.toString());
                    System.out.println("Now you got " + todoList.size() + " task(s) waiting man");
                    store.write(dl);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("check you entered both a description and a deadline time");;
                    e.printStackTrace();
                }
                printLine();
                break;
            case "event":
                printLine();
                String fullE = sc.nextLine();
                try {
                    String eventName = fullE.split("/at")[0];
                    LocalDate eventTime = LocalDate.parse(fullE.split("/at ")[1]);
                    Event e = new Event(eventName, eventTime);
                    todoList.add(e);
                    System.out.println("Aight new task for you: \n" + e.toString());
                    System.out.println("Now you got " + todoList.size() + " task(s) waiting man");
                    store.write(e);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("check you entered both a description and an event time");
                    e.printStackTrace();
                }
                printLine();
                break;
            case "delete":
                printLine();
                try {
                    int deleteID = sc.nextInt() - 1;
                    Task deleted = todoList.get(deleteID);
                    todoList.remove(deleteID);
                    store.overwrite(new TaskList(todoList));
                    System.out.println("Gotchu, I am removing \n" + deleted.toString());
                    System.out.println("Now you got " + todoList.size() + " task(s) waiting man");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("the item you are trying to delete does not exist");
                    e.printStackTrace();
                }
                printLine();
                break;
            case "on":
                printLine();
                LocalDate checkOn = LocalDate.parse(sc.nextLine().trim());
                System.out.println("On this day, you have: ");
                int eventCount = 0;
                for (Task checkEvent: todoList) {
                    if (checkEvent instanceof Event && ((Event) checkEvent).at.equals(checkOn)) {
                        System.out.println(checkEvent.toString());
                        eventCount++;
                    }
                }
                if (eventCount > 0) {
                    System.out.println("   [ A total of " + eventCount + " event(s)]");
                } else {
                    System.out.println("   [ You have no events on this day ]");
                }
                printLine();
                break;
            case "by":
                printLine();
                LocalDate checkBy = LocalDate.parse(sc.nextLine().trim());
                System.out.println("By this day, you have: ");
                int deadlineCount = 0;
                for (Task checkDead: todoList) {
                    if (checkDead instanceof Deadline
                            && (((Deadline) checkDead).by.isBefore(checkBy)
                            || ((Deadline) checkDead).by.equals(checkBy))) {
                        System.out.println(checkDead.toString());
                        deadlineCount++;
                    }
                }
                if (deadlineCount > 0) {
                    System.out.println("   [ A total of " + deadlineCount + " deadline(s)]");
                } else {
                    System.out.println("   [ You have no deadlines by this day ]");
                }
                printLine();
                break;
            case "find":
                printLine();
                String keyword = sc.nextLine();
                int findCount = 0;
                System.out.println("Here are your tasks with the keyword: " + keyword + "\n");
                for (Task find: todoList) {
                    if (find.description.contains(keyword)) {
                        System.out.println(findCount + 1 + ". " + find.toString());
                        findCount++;
                    }
                }
                if (findCount == 0) {
                    System.out.println("No related tasks found");
                }
                printLine();
                break;
            default:
                printLine();
                System.out.println("sorry i don't get you");
                printLine();
        }
    }
}
