import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        PrintFunctions.printGreeting();
        String userInput;
        TaskList userTaskList = new TaskList();
        Scanner scanner = new Scanner(System.in);
        loop: while (true) {
            userInput = scanner.nextLine();
            try {
                UserCommandType userCommandType = UserCommands.parseUserCommand(userInput);
                switch (userCommandType) {
                    case EXIT:
                        PrintFunctions.printMessageBetweenLines(StringConstants.EXIT_MESSAGE);
                        break loop;
                    case LIST:
                        userTaskList.printTaskList();
                        break;
                    case DONE:
                        int doneIndex = UserCommands.getDoneIndex(userInput);
                        String[] doneMessages = userTaskList.markTaskDoneAtIndex(doneIndex);
                        PrintFunctions.printMessagesBetweenLines(doneMessages);
                        break;
                    case TODO:
                        userTaskList.addTask(userInput);
                        break;
                }
            } catch (UserCommands.InvalidCommandException | TaskList.InvalidIndexException  exception) {
                PrintFunctions.printMessageBetweenLines(exception.getMessage());
            }
        }
    }
}
