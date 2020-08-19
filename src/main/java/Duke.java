import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        PrintFunctions.printGreeting();
        String userInput;
        TaskList userTaskList = new TaskList();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            userInput = scanner.nextLine();

            if (UserCommands.isExitCommand(userInput)) {
                break;
            } else if (UserCommands.isListCommand(userInput)) {
                userTaskList.printTaskList();
            } else if (UserCommands.isDoneCommand(userInput)) {
                try {
                    int doneIndex = UserCommands.getDoneIndex(userInput);
                    String[] doneMessages = userTaskList.markTaskDoneAtIndex(doneIndex);
                    PrintFunctions.printMessagesBetweenLines(doneMessages);
                } catch (UserCommands.InvalidCommandException | TaskList.InvalidIndexException exception) {
                    PrintFunctions.printMessageBetweenLines(exception.getMessage());
                }

            } else if (!userInput.isBlank()){
                userTaskList.addTask(userInput);
            }
        }

        PrintFunctions.printMessageBetweenLines(StringConstants.EXIT_MESSAGE);
    }
}
