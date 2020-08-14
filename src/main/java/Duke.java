import java.util.Scanner;

public class Duke {

    private static void botStart() {
        Scanner sc = new Scanner(System.in);
        String[] storage = new String[100];
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
                    System.out.println(storage[i]);
                }
            } else {
                storage[storageCount] = Integer.toString(storageCount + 1) + ". " + command;
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
