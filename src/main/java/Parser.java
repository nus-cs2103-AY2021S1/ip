import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Parser {
    public  Parser(){}

    public static void printLine() {
        System.out.println("-------------------------------");
    }

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
                    System.out.println(todoList.indexOf(task) + 1 + ". " + task.toString());
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
                        System.out.println("Now you got " + todoList.size() + " task(s) waiting man");
                        store.write(td);
                    }} catch (DukeException e){
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                printLine();
                break;
            case "deadline":
                printLine();
                String fullDL = sc.nextLine();

                try {
                    String DLname = fullDL.split("/by")[0];
                    LocalDate DLtime = LocalDate.parse(fullDL.split("/by ")[1]);
                    Deadline dl = new Deadline(DLname, DLtime);
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
                    String EventName = fullE.split("/at")[0];
                    LocalDate EventTime = LocalDate.parse(fullE.split("/at ")[1]);
                    Event e = new Event(EventName, EventTime);
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
                int eCount = 0;
                for (Task checkEvent: todoList) {
                    if (checkEvent instanceof Event && ((Event) checkEvent).at.equals(checkOn)) {
                        System.out.println(checkEvent.toString());
                        eCount ++;
                    }
                }
                if (eCount > 0) {
                    System.out.println("   [ A total of " + eCount + " event(s)]");
                } else {
                    System.out.println("   [ You have no events on this day ]");
                }
                printLine();
                break;
            case "by":
                printLine();
                LocalDate checkBy = LocalDate.parse(sc.nextLine().trim());
                System.out.println("By this day, you have: ");
                int dCount = 0;
                for (Task checkDead: todoList) {
                    if (checkDead instanceof Deadline && (((Deadline) checkDead).by.isBefore(checkBy) ||
                            ((Deadline) checkDead).by.equals(checkBy))) {
                        System.out.println(checkDead.toString());
                        dCount ++;
                    }
                }
                if (dCount > 0) {
                    System.out.println("   [ A total of " + dCount + " deadline(s)]");
                } else {
                    System.out.println("   [ You have no deadlines by this day ]");
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
