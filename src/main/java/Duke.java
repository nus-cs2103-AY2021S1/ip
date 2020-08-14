import java.util.Scanner;

public class Duke {

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
                for (int i = 0; i < storageCount; i++) {
                    System.out.println(Integer.toString(i + 1) + "." + taskStorage[i]);
                }
            } else if (command.substring(0, 4).equals("done") && !command.equals("done")) {
                int taskNumber = Integer.parseInt(command.substring(5, 6));
                taskStorage[taskNumber - 1].markAsDone();
                System.out.println("I have marked this task as done: "
                        + "\n" + taskStorage[taskNumber - 1]);
            } else {
                Task task = new Task(command);
                taskStorage[storageCount] = task;
                System.out.println(">" + "added: " + command + "<");
                storageCount++;
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        Duke.botStart();
    }
}
