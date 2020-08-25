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
                        int doneIndex = UserCommands.getTaskIndex(userInput);
                        String[] doneMessages = userTaskList.markTaskDoneAtIndex(doneIndex);
                        PrintFunctions.printMessagesBetweenLines(doneMessages);
                        userTaskList.saveTaskList();
                        break;
                    case DELETE:
                        int deleteIndex = UserCommands.getTaskIndex(userInput);
                        String[] deleteMessages = userTaskList.deleteTaskAtIndex(deleteIndex);
                        PrintFunctions.printMessagesBetweenLines(deleteMessages);
                        userTaskList.saveTaskList();
                        break;
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        userTaskList.addTask(userInput, userCommandType);
                        userTaskList.saveTaskList();
                        break;
                }
            } catch (UserCommands.InvalidCommandException | TaskList.InvalidIndexException exception) {
                PrintFunctions.printMessageBetweenLines(exception.getMessage());
            }
        }
    }
}
