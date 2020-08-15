import java.util.Scanner;

public class Duke {

    private static final int DONE_INDEX = 5;
    private static final int TODO_INDEX = 5;
    private static final int DEADLINE_INDEX = 9;
    private static final int EVENT_INDEX = 6;

    private static void botStart() {
        Scanner sc = new Scanner(System.in);
        Task[] taskStorage = new Task[100];
        int storageCount = 0;
        System.out.println("=========================================="
                + "\nHi, my name is Duke."
                + "\nWhat can I do for you today?"
                + "\n==========================================");
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("Thanks for chatting with me, see you soon!"
                        + "\n==========================================");
                break;
            } else if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < storageCount; i++) {
                    System.out.println(i + 1 + "." + taskStorage[i]);
                }
            } else if (command.startsWith("done") && !command.equals("done")) {
                int taskNumber = Integer.parseInt(command.substring(DONE_INDEX));
                taskStorage[taskNumber - 1].markAsDone();
                System.out.println("I have marked this task as done: "
                        + "\n" + taskStorage[taskNumber - 1]);
            } else {
                Task task;
                if (command.startsWith("todo")) {
                    task = new Todo(command.substring(TODO_INDEX));
                } else {
                    String date = command.substring(command.indexOf("/") + 4);
                    if (command.startsWith("deadline")) {
                        task = new Deadline(command.substring(DEADLINE_INDEX, command.indexOf("/") - 1), date);
                    } else {
                        task = new Event(command.substring(EVENT_INDEX, command.indexOf("/") - 1), date);
                    }
                }
                taskStorage[storageCount] = task;
                System.out.println(">" + "added: " + task + "<");
                storageCount++;
                System.out.println("You now have " + storageCount + " task(s) in your list.");
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        Duke.botStart();
    }
}
