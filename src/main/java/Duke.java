import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        //To check if the conversation has ended.
        boolean running = true;
        //Greetings.
        System.out.println("\tHello!\n\tI am Baymax, your personal idle time companion." +
                "\n\tHow may I help you?");
        //Add List
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        while (running) {
            String text = scanner.nextLine();
            if (text.equals("bye")) {
                System.out.println("\tIt was my pleasure assisting you.\n\tSee you next Time!");
                running = false;
            } else if (text.equals("list")) {
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println("\t" + i + ". " + list.get(i - 1));
                }
            } else if (text.contains("done")) {
                int taskNo = Integer.parseInt(text.substring(5));
                if (taskNo > list.size()) {

                }
                Task task = list.get(taskNo - 1);
                task.setDone();
                System.out.println("\tYou have finished this task!\n\t" + task + "\n\tLet's move on to the next one!");
            } else {
                Task newTask = new Task(text);
                list.add(newTask);
                System.out.println("\tadded: " + newTask);
            }
        }
    }
}
