import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException, FileNotFoundException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String taskName = sc.nextLine();
        ArrayList<Task> list = new ArrayList<>();

        while (!taskName.equals("bye")) {
            if (taskName.equals("list")) {
                if (list.isEmpty()) {
                    System.out.println("List is empty.");
                } else {
                    StringBuilder listOutput = new StringBuilder();
                    for (int j = 0; j < list.size(); j++) {
                        int num = j + 1;
                        Task task = list.get(j);
                        listOutput.append(num + "." + task.toString() + "\n");
                    }
                    System.out.println(listOutput);
                }
            } else {
                if (taskName.contains(" ")) {
                    int i = taskName.indexOf(" ");
                    String firstWord = taskName.substring(0, i);
                    if (firstWord.equals("done")) {
                        // what if task is not in the list
                        int num = Integer.parseInt(taskName.substring(i+1));
                        if (list.size() < num) {
                            throw new DukeException("List does not have that item.");
                        } else {
                            Task taskToSetToDone = list.get(num-1);
                            taskToSetToDone.setDone();
                            System.out.println("Nice! I've marked this task as done:" + "\n" + taskToSetToDone.toString());
                            saveTaskToTxtFile(list);
                        }
                    } else if (firstWord.equals("delete")) {
                        // what if task is not in the list
                        int num = Integer.parseInt(taskName.substring(i+1));
                        if (list.size() < num) {
                            throw new DukeException("List does not have that item.");
                        } else {
                            Task taskToDelete = list.get(num-1);
                            list.remove(num-1);
                            System.out.println("Noted. I've removed this task:" + "\n" + taskToDelete.toString() + "\n" + "Now you have " + list.size() +
                                    " tasks in the list.");
                            saveTaskToTxtFile(list);
                        }
                    } else if (firstWord.equals("todo")) {
                        String todoName = taskName.substring(i+1);
                        Todo newTodo = new Todo(todoName);
                        addTasktoList(list, newTodo);
                        saveTaskToTxtFile(list);
                    } else if (firstWord.equals("deadline")) {
                        int index = taskName.indexOf("/");
                        String deadlineName = taskName.substring(i+1, index);
                        String deadlineTime = taskName.substring(index+4);
                        Deadline newDeadline = new Deadline(deadlineName, deadlineTime);
                        addTasktoList(list, newDeadline);
                        saveTaskToTxtFile(list);
                    } else if (firstWord.equals("event")) {
                        int index = taskName.indexOf("/");
                        String eventName = taskName.substring(i+1, index);
                        String eventTime = taskName.substring(index+4);
                        Event newEvent = new Event(eventName, eventTime);
                        addTasktoList(list, newEvent);
                        saveTaskToTxtFile(list);
                    } else {
                        // throw exception
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means. Please enter your task with the " +
                                "starting keyword \"todo\" or \"deadline\" or \"event\".");
                    }
                } else {
                    // throw exception
                    if (taskName.equals("todo")) {
                        // throw empty todo exception
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    } else if (taskName.equals("deadline")) {
                        // throw empty deadline
                        throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                    } else if (taskName.equals("event")) {
                        // throw empty deadline
                        throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                    } else {
                        // throw idk what it means exception
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means. Please enter your task with the " +
                                "starting keyword \"todo\" or \"deadline\" or \"event\".");
                    }
                }
            }
            taskName = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void addTasktoList(ArrayList<Task> list, Task task) {
        list.add(task);
        String strToPrint = "Got it. I've added this task:" + "\n" + task.toString() + "\n" + "Now you have " + list.size() + " task in the list.";
        System.out.println(strToPrint);
    }

    private static void saveTaskToTxtFile(ArrayList<Task> list) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("data/duke.txt");
        StringBuilder listOutput = new StringBuilder();
        for (int j = 0; j < list.size(); j++) {
            int num = j + 1;
            Task task = list.get(j);
            listOutput.append(num + "." + task.toString() + "\n");
        }
        String text = listOutput.toString();
        out.println(text);
        out.close();
    }
}
