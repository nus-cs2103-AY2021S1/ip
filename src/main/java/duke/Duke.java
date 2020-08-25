package duke;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Ui.printGreeting();
        String userInput;
        TaskList userTaskList = new TaskList();
        Scanner scanner = new Scanner(System.in);
        loop: while (true) {
            userInput = scanner.nextLine();
            try {
                UserCommandType userCommandType = Parser.parseUserCommand(userInput);
                switch (userCommandType) {
                    case EXIT:
                        Ui.printMessageBetweenLines(StringConstants.EXIT_MESSAGE);
                        break loop;
                    case LIST:
                        userTaskList.printTaskList();
                        break;
                    case DONE:
                        int doneIndex = Parser.getTaskIndex(userInput);
                        String[] doneMessages = userTaskList.markTaskDoneAtIndex(doneIndex);
                        Ui.printMessagesBetweenLines(doneMessages);
                        userTaskList.saveTaskList();
                        break;
                    case DELETE:
                        int deleteIndex = Parser.getTaskIndex(userInput);
                        String[] deleteMessages = userTaskList.deleteTaskAtIndex(deleteIndex);
                        Ui.printMessagesBetweenLines(deleteMessages);
                        userTaskList.saveTaskList();
                        break;
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        userTaskList.addTask(userInput, userCommandType);
                        userTaskList.saveTaskList();
                        break;
                }
            } catch (Parser.InvalidCommandException | TaskList.InvalidIndexException exception) {
                Ui.printExceptionBetweenLines(exception);
            }
        }
    }
}
