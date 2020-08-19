import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____   ____  \n"
                + "|  _ \\ |  _ \\ \n"
                + "| | | || | | | \n"
                + "| |_| || |_| | \n"
                + "|____/ |____/ \n";
        System.out.println("Hi! I'm\n" + logo + "How can I help you? :)\n"
                + "_________________________________________");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        String bye = "bye";
        String list = "list";

        Task[] taskList = new Task[100];
        int taskSize = 0;

        do {
            if (input.startsWith("done")) {
                String taskStr = input.substring(5);
                int taskNum = 0;

                try {
                    taskNum = Integer.parseInt(taskStr);
                }
                catch (NumberFormatException nfe) {
                    taskNum = 0;
                }

                if (taskNum > 0 && taskNum <= taskSize) {
                    taskList[taskNum - 1].markAsDone();
                    System.out.println("Wow!! Good job!!\n" + taskList[taskNum - 1]);
                }
                else {
                    System.out.println("hmm.. I don't think thats a valid task, try again?");
                }
            }
            else if (input.equals(list)) {
                int curr = 0;
                while (curr < taskSize) {
                    System.out.println((curr + 1) + ". " + taskList[curr]);
                    curr += 1;
                }
            }
            else {
                taskList[taskSize] = new Task(input);
                System.out.println("I have added: " + input);
                taskSize += 1;
            }
            System.out.println("_________________________________________");
            input = sc.nextLine();
        }
        while (!input.equals(bye));

        System.out.println("You're leaving? Bye :( Come back soon!"
                + "\n_________________________________________");
    }
}
