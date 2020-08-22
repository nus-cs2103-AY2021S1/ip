import java.io.CharArrayReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
//go through string.equals

//main loop of input
// 1. read input
// 2. decide whether valid or invalid
// 3. if invalid, the bot terminates, if valid it will decide the category
// 4. based on the category, produce an output
// 5. continue again

enum Category {
    TODO, DEADLINE, EVENT, LIST, BYE, INVALID, DONE, DELETE
}

public class Duke {
    //2/ valid or invalid
    //todo read book
    //deadline read book /by 6pm
    public static Command decideCategory(String input) {
        String[] wordsArray = input.split(" ", 2);
        String categoryWord = wordsArray[0];
        //System.out.println(wordsArray[0] + wordsArray[1]);
        if (wordsArray.length == 2) {
            switch (categoryWord) {
                case "todo":
                    return new Command(Category.TODO, wordsArray[1]);
                case "deadline":
                    return new Command(Category.DEADLINE, wordsArray[1]);
                case "event":
                    return new Command(Category.EVENT, wordsArray[1]);
                case "done":
                    return new Command(Category.DONE, wordsArray[1]);
                case "delete":
                    return new Command(Category.DELETE, wordsArray[1]);
                default:
                    return new Command(Category.INVALID);
            }
        } else {
            switch (categoryWord) {
                case "list":
                    return new Command(Category.LIST);
                case "bye":
                    return new Command(Category.BYE);
                default:
                    return new Command(Category.INVALID);
            }
        }
    }

    public static void processCommand(Command command, TaskList taskList) {
        int taskNumber;
        switch (command.getCategory()) {
            case TODO:
                Todo newTask = new Todo(command.getDescription());
                taskList.addTask(newTask);
                System.out.println(newTask);
                break;
            case DEADLINE:
                Deadline newDeadline = new Deadline(command.getDescription());
                taskList.addTask(newDeadline);
                System.out.println(newDeadline);
                break;
            case EVENT:
                Event newEvent = new Event(command.getDescription());
                taskList.addTask(newEvent);
                System.out.println(newEvent);
                break;
            case LIST:
                taskList.showList();
                break;
            case DONE:
                taskNumber = Integer.parseInt(command.getDescription());
                Task doneTask = taskList.getTask(taskNumber);
                doneTask.markAsDone();
                System.out.println("ok done");
                break;
            case DELETE:
                taskNumber = Integer.parseInt(command.getDescription());
                Task deletedTask = taskList.getTask(taskNumber);
                taskList.removeTask(deletedTask);
                System.out.println("deleted");
                break;
            case BYE: //bye
                System.out.println("bye!");
                System.exit(0);
                break;
            case INVALID:
                System.out.println("invalid");
                break;
        }
    }

    public static void printHello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("-------------------------------------------\n" +
                "Hello! I'm Duke\n" + "What can I do for you?\n" +
                "-------------------------------------------\n");
    }

    public static void main(String[] args) throws DukeException, IOException {
        printHello();

        Scanner sc = new Scanner(System.in);
        String input;
        TaskList taskList = new TaskList();

        while (true) {
            input = sc.nextLine(); //1. read input
            Command command = decideCategory(input);
            processCommand(command, taskList);

            //3. deciding category and printing output

            //4. process output

            //5. continue loop

            //Task task = new Task(input); //cannot convert it to task directly (not every input is a task)
//            char[] chars = task.getDescription().toCharArray();
//            if (task.getDescription().equals("bye")) {
//                System.out.println("-------------------------------------------\n" +
//                                   "Bye. Hope to see you again soon!" +
//                                   "-------------------------------------------");
//                System.exit(0);
//
//            } else if (task.getDescription().equals("list")) {
//                System.out.println("-------------------------------------------\n" +
//                                   "Here are the tasks in your list:\n");
//
//                for (int j = 0; j < taskList.size(); j++) {
//                    if (taskList.get(j).getDescription().charAt(0) == todoChar[0]) {
//                        Todo taskTodo = new Todo(taskList.get(j).getDescription());
//                        System.out.println(j + 1 + "." + taskTodo.toString());
//                    } else if (taskList.get(j).getDescription().charAt(0) == deadlineChar[0]) {
//                        String taskName = taskList.get(j).getDescription();
//                        String[] parts = taskName.split("/by ");
//                        //String[] subparts = parts[0].split("deadline");
//                        Deadline taskDeadline = new Deadline(taskList.get(j).getDescription(), parts[1]);
//                        System.out.println(j + 1 + "." + taskDeadline.toString());
//                    } else {
//                        String taskName = taskList.get(j).getDescription();
//                        String[] parts = taskName.split("/at ");
//                        //String[] subparts = parts[0].split("event");
//                        Event taskEvent = new Event(taskList.get(j).getDescription(), parts[1]);
//                        System.out.println(j + 1 + "." + taskEvent.toString());
//                    }
//                }
//                System.out.println(String.format("Now you have %d tasks in the list.\n", taskList.size()) +
//                                   "-------------------------------------------");
//            }
//            // done case
//            else if (chars.length == doneArray.length) {
//                // CODECOMMENT: this seems like a common case (matching two
//                // strings) - let's think about how we can do this without
//                // manually typing out all the different indices in the array
//                if (chars[0] == doneArray[0] && chars[1] == doneArray[1] &&
//                    chars[2] == doneArray[2] && chars[3] == doneArray[3] &&
//                    chars[4] == doneArray[4] && Character.isDigit(chars[5])) {
//
//                    number = Character.getNumericValue(chars[chars.length - 1]);
//                    taskList.get(number - 1).markAsDone();
//                    System.out.println("-------------------------------------------\n" +
//                                       " Nice! I've marked this task as done:\n" + "[" +
//                                       taskList.get(number - 1).getStatusIcon() + "] " +
//                                       taskList.get(number - 1).getDescription());
//                }
//            }
//            // delete method
//            else if (chars.length == deleteArray.length) {
//                if (chars[0] == deleteArray[0] && chars[1] == deleteArray[1] &&
//                    chars[2] == deleteArray[2] && chars[3] == deleteArray[3] &&
//                    chars[4] == deleteArray[4] && chars[5] == deleteArray[5] &&
//                    chars[6] == deleteArray[6] && Character.isDigit(chars[7])) {
//
//                    number = Character.getNumericValue(chars[chars.length - 1]);
//
//                    System.out.println("-------------------------------------------\n" +
//                                       " Noted. I've removed this task:\n" + "[" +
//                                       taskList.get(number - 1).getStatusIcon() + "] " +
//                                       taskList.get(number - 1).getDescription() + "\n" +
//                                       String.format("Now you have %d tasks in the list.\n", taskList.size() -1)
//                                       + "-------------------------------------------------------------");
//
//                    taskList.remove(number - 1);
//                }
//            } else {
//                //if it is a todo
//                try {
//                    if (chars[0] == todoChar[0]) {
//                        try {
//                            taskList.add(i - 1, task);
//                            Todo taskTodo = new Todo(task.getDescription());
//                            System.out.println("-------------------------------------------\n" +
//                                               "Got it. I've added this task:\n" + taskTodo.toString() + "\n" +
//                                               String.format("Now you have %d tasks in the list.\n", taskList.size())
//                                               + "-------------------------------------------");
//                            i++;
//                        } catch (ArrayIndexOutOfBoundsException ex) {
//                            System.out.println("-------------------------------------------\n" +
//                                               "☹ OOPS!!! The description of a todo cannot be empty. Try again!\n" +
//                                               "-------------------------------------------");
//                        }
//                        // if it is a deadline
//                    } else if (chars[0] == deadlineChar[0]) {
//                        try {
//                            taskList.add(i - 1, task);
//                            String taskName = task.getDescription();
//                            String[] parts = taskName.split("/by ");
//
//                            Deadline taskDeadline = new Deadline(task.getDescription(), parts[1]);
//                            System.out.println("-------------------------------------------------------------\n" +
//                                               "Got it. I've added this task:\n" + taskDeadline.toString() + "\n" +
//                                               String.format("Now you have %d tasks in the list.\n", taskList.size())
//                                               + "-------------------------------------------------------------");
//                            i++;
//                            if (chars.length == 8) {
//                                throw new ArrayIndexOutOfBoundsException("Cannot be empty");
//                            }
//                        } catch (ArrayIndexOutOfBoundsException ex) {
//                            System.out.println("-------------------------------------------------------------\n" +
//                                               "☹ OOPS!!! The description of a deadline cannot be empty. Try again!\n" +
//                                               "-------------------------------------------------------------");
//                        }
//                    } else if (chars[0] == eventChar[0]) {
//                        try {
//                            // if it is an event
//                            taskList.add(i - 1, task);
//                            String taskName = task.getDescription();
//                            String[] parts = taskName.split("/at ");
//
//                            Event taskEvent = new Event(task.getDescription(), parts[1]);
//                            System.out.println("-------------------------------------------------------------\n" +
//                                               "Got it. I've added this task:\n" + taskEvent.toString() + "\n" +
//                                               String.format("Now you have %d tasks in the list.\n", taskList.size())
//                                               + "-------------------------------------------------------------");
//                            i++;
//                        } catch (ArrayIndexOutOfBoundsException ex) {
//                            System.out.println("-------------------------------------------------------------\n" +
//                                               "☹ OOPS!!! The description of an event cannot be empty. Try again!\n" +
//                                               "-------------------------------------------------------------");
//                        }
//                    }
//                } catch (ArrayIndexOutOfBoundsException ex) {
//                    System.out.println("-------------------------------------------------------------\n" +
//                                       "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
//                                       "-------------------------------------------------------------");
//                    System.exit(0);
//                }
//            }

        }
    }
}










