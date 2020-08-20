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
            if (text.matches("bye")) { //If user wants to leave the chat bot.
                System.out.println("\tIt was my pleasure assisting you.\n\tSee you next Time!");
                running = false;
            } else if (text.matches("list")) { //If user requests for the list of tasks.
                System.out.println("\tHere are the tasks in your list:");
                printList(list, true);
            } else if (text.matches("(done)([\\s]+[\\d]+)")) { //If user wants to set a task to be done
                int taskNo = Integer.parseInt(text.replaceAll("done[\\s]+", ""));
                if (taskNo > list.size()) { //If requested index is out of bound.
                    System.out.println("\tYou don't have that many tasks!!!");
                } else { //If index can be found, set the task to be done and move it to the last.
                    Task task = list.get(taskNo - 1);
                    list.remove(task);
                    task.setDone();
                    System.out.println("\tYou have finished this task!\n\t" + task + "\n\tLet's move on to the next one!");
                    printList(list, false);
                    list.add(task);
                    noOfTasks--;
                }
            } else { //Echo
                Task newTask = new Task(text);
                list.add(noOfTasks, newTask);
                noOfTasks++;
                System.out.println("\tadded: " + newTask);
            }
        }
    }
}
