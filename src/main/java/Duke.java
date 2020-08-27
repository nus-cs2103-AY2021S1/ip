import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void handleInput(TaskData data) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            try {
                String input = sc.nextLine();

                if (input.toLowerCase().equals("bye")) {
                    replyBye();
                    sc.close();
                    break;
                } else if (input.toLowerCase().equals("help")) {
                    replyHelp();
                } else if (input.toLowerCase().equals("list")) {
                    replyList(data.getTasks(), Task.totalTasks);
                } else if (input.split(" ").length == 0) {
                    throw new DukeException("Sorry, I don't understand what you are saying! D=\n" +
                            "Type \"help\" to view the list of commands you can use!");
                } else if (input.toLowerCase().split(" ")[0].equals("done")) {
                    replyDone(input, data.getTasks());
                    data.updateData();
                } else if (input.toLowerCase().split(" ")[0].equals("delete")) {
                    replyDelete(input, data.getTasks());
                    data.updateData();
                } else {
                    String inputTask;
                    Task task;
                    if (input.toLowerCase().split(" ")[0].equals("todo")) {
                        if (input.split(" ").length == 1) {
                            throw new DukeException("You need to specify your todo task!\n"
                                + "eg todo read book");
                        }

                        inputTask = input.substring(5);
                        task = new Todo(inputTask);
                        data.getTasks().add(task);
                        replyTask(task);
                        data.updateData();
                    } else if (input.toLowerCase().split(" ")[0].equals("event")) {
                        if (input.split(" ").length == 1) {
                            throw new DukeException("You need to specify your event!\n"
                                    + "eg event project meeting /at 2019-10-15 1200");
                        }

                        inputTask = input.substring(6);
                        String[] arr = inputTask.split(" /at ");
                        if (arr.length == 1) {
                            throw new DukeException("You need to use the proper format!\n"
                                    + "eg event project meeting /at 2019-10-15 1200");
                        }

                        task = new Event(arr[0], arr[1]);
                        data.getTasks().add(task);
                        replyTask(task);
                        data.updateData();
                    } else if (input.toLowerCase().split(" ")[0].equals("deadline")) {
                        if (input.split(" ").length == 1) {
                            throw new DukeException("You need to specify your deadline!\n"
                                    + "eg deadline return book /by 2019-10-15 2359");
                        }

                        inputTask = input.substring(9);
                        String[] arr = inputTask.split(" /by ");
                        if (arr.length == 1) {
                            throw new DukeException("You need to use the proper format!\n"
                                    + "eg deadline return book /by 2019-10-15 2359");
                        }

                        task = new Deadline(arr[0], arr[1]);
                        data.getTasks().add(task);
                        replyTask(task);
                        data.updateData();
                    } else {
                        throw new DukeException("Sorry, I don't understand what you are saying! D=\n" +
                                "Type \"help\" to view the list of commands you can use!");
                    }
                }
            } catch (DukeException ex) {
                System.out.println(ex);
            }
        }
    }

    public static void replyBye() {
        String exitMessage = "BYEEE!! SEE YOU AGAIN!!! >O<";
        printReply(exitMessage);
    }

    public static void replyDone(String input, ArrayList<Task> list) throws DukeException{
        String doneMessage = "Nicee!! You've completed this task!\n";
        if (input.length() <= 5) {
            throw new DukeException("You need to specify which task to mark done! \n" +
                    "eg done 1");
        }

        if (Task.totalTasks == 0) {
            throw new DukeException("You don't have any tasks yet!");
        }

        int i = input.charAt(5) - 48;

        if (i == 0 || i > Task.totalTasks) {
            throw new DukeException("You don't have a task with that number! ><\n" +
                "Can you try a different number?");
        } else {
            list.get(i - 1).markDone();
            doneMessage += list.get(i - 1);
            printReply(doneMessage);
        }
    }

    public static void replyDelete(String input, ArrayList<Task> list) throws DukeException{
        String deleteMessage = "Oki! I've removed this task!\n";
        if (input.length() <= 7) {
            throw new DukeException("You need to specify which task to delete!\n" +
                    "eg delete 1");
        }

        if (Task.totalTasks == 0) {
            throw new DukeException("You don't have any tasks yet!");
        }

        int i = input.charAt(7) - 48;

        if (i == 0 || i > Task.totalTasks) {
            throw new DukeException("You don't have a task with that number! ><\n" +
                    "Can you try a different number?");
        } else {
            Task.totalTasks--;
            deleteMessage += list.get(i - 1) + "\n" + "Now you have " +
                    Task.totalTasks + " tasks in your list!";;
            list.remove(i - 1);
            printReply(deleteMessage);
        }
    }

    public static void replyHelp() {
        System.out.println("---------------------------------------------------------");
        System.out.println("Here are the list of commands you can use! =D");
        System.out.println("help: displays the list of commands available\n");
        System.out.println("list: displays the list of tasks you have\n");
        System.out.println("todo *task description*: adds a task without " +
                "any\ndate/time attached to it\n" + "eg todo read book\n");
        System.out.println("deadline *task description* /by *date+time*: " +
                "adds a\ntask that needs to be done before a specific date and time\n" +
                "(date and time to be written in yyyy-mm-dd HHMM format)\n" +
                "eg deadline return book /by 2019-10-15 2359\n");
        System.out.println("event *task description* /at *date+time*: " +
                "adds a task that\nstarts at a specific time and ends at a specific time\n" +
                "(date and time to be written in yyyy-mm-dd HHMM format)\n" +
                "eg event meeting /at 2019-10-15 1200\n");
        System.out.println("done *task number*: marks the task with that number as\n" +
                "done eg done 1\n");
        System.out.println("delete *task number*: deletes the task with that number\n" +
                "from the list eg delete 1\n");
        System.out.println("bye: ends the session");
        System.out.println("---------------------------------------------------------");
    }

    public static void replyList(ArrayList<Task> list, int end) throws DukeException {
        String numberedList = "Here are your tasks! JIAYOU! =D";

        if (end == 0) {
            throw new DukeException("You don't have any tasks yet!");
        }

        for (int i = 0; i < end; i++) {
            int num = i + 1;
            numberedList += "\n" + num + ". " + list.get(i);
        }

        printReply(numberedList);
    }

    public static void replyTask(Task task) {
        String addedMessage = "Oki! I have added this task:\n" +
                task + "\n" + "Now you have " + Task.totalTasks +
                " tasks in your list!";
        printReply(addedMessage);
    }

    public static void printReply(String reply) {
        System.out.println("---------------------------------------------------------");
        System.out.println(reply);
        System.out.println("---------------------------------------------------------");
    }

    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("hElLoOOoOOoO! Welcome to\n" + logo);
        System.out.println("How can I help you today? : D");
        System.out.println("Type \"help\" to view the list of commands you can use!");

        TaskData data = new TaskData();
        handleInput(data);
    }
}
