import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // create scanner object
        String welcomeMessage = "―――― Serina here, what would you like to do?";
        System.out.println(welcomeMessage); // print welcome message
        int index = 0;
        Task[] tasks = new Task[100];

        while (true) {
            String userInput = sc.nextLine();
            String[] splitInput = userInput.split(" "); // split command by the spaces
            String command = splitInput[0];

            switch (command) {
                case "bye":  // terminate on "bye"
                    System.out.println("――――  Goodbye, call me when you need me.");
                    break;
                case "list":  // list task
                    System.out.println("―――― Your Tasks: ");
                    for (int i = 0; i < tasks.length; i++) {
                        if (tasks[i] == null) {
                            break;
                        } else {
                            int num = i + 1;
                            System.out.println(num + ". " + tasks[i]);
                        }
                    }
                    break;
                case "done":  // mark task as done and print it
                    int taskNum = Integer.parseInt(splitInput[1]) - 1;
                    System.out.println(tasks[taskNum].markAsDone());
                    break;
                default: // add task
                    tasks[index] = new Task(userInput);
                    System.out.println("――――  added: " + userInput);
                    index += 1; // increment index
                    break;
            }
        }
    }
}
