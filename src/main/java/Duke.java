import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    //Selective print list
    private static void printList(ArrayList<Task> lst, boolean printAll) {
        for (int i = 1; i <= lst.size(); i++) {
            Task task = lst.get(i - 1);
            if (printAll || !task.getStatus()) {
                System.out.println("\t" + i + ". " + task);
            }
        }
    }

    public static String[] command(String text) throws DukeException {
        String commandList = "bye|list|(done|delete)[\\s]+[\\d]+|todo.*|deadline.*|event.*";
        if (text.matches(commandList)) {
            String[] commandParaPair = text.split(" ", 2);
            return commandParaPair;
        } else {
            throw new DukeException("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static int[] execute(String[] commandParaPair, ArrayList<Task> list, boolean running, int activeTasks)
            throws DukeException {
        switch (commandParaPair[0]) {
            case "bye":
                System.out.println("\tIt was my pleasure assisting you.\n\tSee you next Time!");
                running = false;
                break;
            case "list":
                System.out.println("\tHere are the tasks in your list:");
                printList(list, true);
                break;
            case "done":
            case "delete":
                int taskNo = Integer.parseInt(commandParaPair[1].replaceAll("[\\s]+", ""));
                if (taskNo > list.size()) { //If requested index is out of bound.
                    throw new DukeException("\tYou don't have that many tasks!!!");
                } else { //If index can be found, set the task to be done and move it to the last.
                    Task task = list.get(taskNo - 1);
                    list.remove(task);
                    if (commandParaPair[0].equals("done")) {
                        task.setDone();
                        list.add(task);
                        activeTasks--;
                        System.out.println("\tYou have finished this task!\n\t" + task +
                                "\n\tLet's move on to the next one!");
                        printList(list, false);
                    } else {
                        System.out.println("\tYou have deleted this task!\n\t" + task +
                                "\n\tHere are other tasks on your list:");
                        printList(list, true);
                    }
                }
                break;
            case "todo":
            case "deadline":
            case "event":
                if (commandParaPair.length < 2) {
                    throw new DukeException("\t☹ OOPS!!! The description of a task cannot be empty.");
                } else {
                    Task newTask;
                    if (commandParaPair[0].equals("todo")) {
                        newTask = new Todo(commandParaPair[1]);
                    } else {
                        String para = commandParaPair[1];
                        String[] contentTimePair = para.split(" /by | /at ", 2);
                        if (contentTimePair.length < 2) {
                            throw new DukeException("\t☹ OOPS!!! The time of a deadline / event cannot be empty.");
                        } else {
                            if (commandParaPair[0].equals("deadline")) {
                                newTask = new Deadline(contentTimePair[0], contentTimePair[1]);
                            } else {
                                newTask = new Event(contentTimePair[0], contentTimePair[1]);
                            }
                        }
                    }

                    list.add(activeTasks, newTask);
                    activeTasks++;
                    System.out.println("\tadded: " + newTask);
                    System.out.println("\tYou have " + list.size() + " tasks, " + activeTasks + " undone!");
                    printList(list, true);
                }
                break;
        }
        int[] pair = new int[]{running ? 1 : 0, activeTasks};
        return pair;
    }

    public static void main(String[] args) {
        //To check if the conversation has ended.
        boolean running = true;
        //Greetings.
        System.out.println("\tHello!\n\tI am Baymax, your personal idle time companion." +
                "\n\tHow may I help you?");
        //Add List
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        //Keep track of number of tasks undone
        int noOfTasks = 0;

        while (running) {
            String text = scanner.nextLine();
            try {
                int[] pair = execute(command(text), list, running, noOfTasks);
                running = pair[0] == 1;
                noOfTasks = pair[1];
            } catch (DukeException error) {
                System.out.println(error.getMessage());
            }
        }
    }
}
