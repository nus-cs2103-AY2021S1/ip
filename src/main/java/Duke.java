import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {
    private static List<Task> taskList = new ArrayList<>();
    public static void intro() {
        System.out.println("\tHi handsome! My name is Duck. What can I do for you?");
    }
    public static void addTask(Task task) {
        taskList.add(task);
        System.out.println("\tGot it. I've added this task:" + "\n\t\t" + task);
        System.out.println(String.format("\tNow you have %d tasks in the list.", taskList.size()));
    }
    public static void printList() {
        System.out.println("\tHere are the tasks in your list:");
        for(int i = 0; i < taskList.size(); i++) {
            System.out.println(String.format("\t%d. %s", i + 1, taskList.get(i)));
        }
    }

    public static void markTaskDone(String task) throws DukeIndexOutOfBoundsException{
        if (task.length() <= 5) {
            throw new DukeIndexOutOfBoundsException("The task you want to mark is invalid");
        }
        int taskNo = Character.getNumericValue(task.charAt(5));
        if (taskNo < 1 || taskNo > taskList.size()) {
            throw new DukeIndexOutOfBoundsException("The task you want to mark is invalid");
        }
        System.out.println("\tNice! I've marked this task as done:");
        int index = taskNo - 1;
        Task t = taskList.remove(index).doneTask();
        System.out.println("\t" + t);
        taskList.add(index, t);
    }

    public static void deleteTask(String task) throws DukeIndexOutOfBoundsException{
        if (task.length() <= 7) {
            throw new DukeIndexOutOfBoundsException("The task you want to delete is invalid");
        }
        int taskNo = Character.getNumericValue(task.charAt(7));
        if (taskNo < 1 || taskNo > taskList.size()) {
            throw new DukeIndexOutOfBoundsException("The task you want to delete is invalid");
        }
        int index = taskNo - 1;
        Task t = taskList.remove(index);
        System.out.println("\tNoted. I've removed this task:" + "\n\t\t" + t);
        System.out.println(String.format("\tNow you have %d tasks in the list.", taskList.size()));
    }

    public static void handleToDo(String task) throws DukeInvalidCommandException{
        if (task.trim().equals("todo")) {
            throw new DukeInvalidCommandException("The command is incomplete handsome :D");
        }
        task = task.replace("todo ", "");
        Duke.addTask(new ToDos(task));
    }

    public static void handleDeadLine(String task) throws DukeInvalidCommandException{
        task = task.replace("deadline ", "");
        String[] stringArr = task.split("/by", 2);
        if (stringArr.length != 2) {
            throw new DukeInvalidCommandException("The command is incomplete handsome :D");
        }
        task = stringArr[0];
        String by = stringArr[1];
        Duke.addTask(new Deadlines(task, by));
    }

    public static void handleEvent(String task) throws DukeInvalidCommandException{
        task = task.replace("event ", "");
        String[] stringArr = task.split("/at", 2);
        if (stringArr.length != 2) {
            throw new DukeInvalidCommandException("The command is incomplete handsome :D");
        }
        task = stringArr[0];
        String at = stringArr[1];
        Duke.addTask(new Events(task, at));
    }

    public static void main(String[] args) throws DukeRunTimeException{
        Scanner sc = new Scanner(System.in);
        Duke.intro();
        while (sc.hasNextLine()) {
            String task = sc.nextLine();
            switch(task) {
                case "bye" :
                    System.out.print("\tBye. Hope to see you again soon!");
                    return;
                case "list" :
                    Duke.printList();
                    break;
                default :
                    try {
                        if (task.startsWith("done")) {
                            markTaskDone(task);
                        } else if (task.startsWith("todo")) {
                            handleToDo(task);
                        } else if (task.startsWith("deadline")) {
                            handleDeadLine(task);
                        } else if (task.startsWith("event")) {
                            handleEvent(task);
                        } else if (task.startsWith("delete")) {
                            deleteTask(task);
                        } else {
                            throw new DukeInvalidCommandException("Sorry handsome but I'm not sure about this command :)");
                        }
                        break;
                    } catch (DukeInvalidCommandException err) {
                        System.out.println("\t" + err.getLocalizedMessage());
                    } catch (DukeIndexOutOfBoundsException err) {
                        System.out.println("\t" + err.getMessage());
                    } catch (DukeIncompleteCommandException err) {
                        System.out.println("\t" + err.getMessage());
                    }

            }
        }
        sc.close();
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
    }
}
