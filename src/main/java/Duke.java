import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Famed, of course, for my unique red skin and unparalleled skills as a general of the House of War, I, the Red Prince, was raised within the vast palaces of the fabled Forbidden City. I was destined to become the next emperor, but my ambitions suffered a bit of a setback when I fell from grace for cavorting with demons. Now I'm exiled and hunted by assassins, but I assure you: I remain undaunted - and as determined as ever to claim my rightful throne!");

        Scanner sc = new Scanner(System.in);
        List<Task> taskList = new ArrayList<>();

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("Here's the extent of our list so far:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i));
                }
            } else if (input.startsWith("done")) {
                try {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]);
                    Task markedTask = taskList.get(taskIndex - 1); // shown list is base 1, implemented list is base 0
                    markedTask.markDone();
                    System.out.println("Right. This task is now marked as done:" + markedTask);
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Hmm? Please mention \"done\" followed by the number of the task we're marking as done.");
                }
            } else {
                Task newTask = new Task(input);
                taskList.add(newTask);
                System.out.println("Fine. I added the following to the list: " + newTask);
            }
            input = sc.nextLine();
        }

        System.out.println("*You take your leave.*");
    }
}
