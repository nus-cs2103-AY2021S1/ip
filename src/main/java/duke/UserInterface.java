package duke;

import java.util.List;

public class UserInterface {
    private static final String INDENT = "    ";
    private static final String UPPER_LINE = INDENT + "___________________________________________________" + "\n";
    private static final String LOWER_LINE =  INDENT + "___________________________________________________" +"\n";

    private static final String DONE_TASK_MARKED_MESSAGE = "    Nice! I've marked this task as done:";
    private static final String DELETE_TASK_MARKED_MESSAGE = "    Noted. I've removed this task:";

    private static final String NO_TASK_MESSAGE = "    Sorry the task does not exists";
    private static final String ADDED_TASK_MESSAGE = "    Got it. I've added this task:";

    private static final String INVALID_COMMAND_MESSAGE = "    Sorry couldn't recognise command";
    private static final String INVALID_DONE_MESSAGE = "    Sorry done cannot be empty ";
    private static final String INVALID_TODO_MESSAGE = "    Sorry todo cannot be empty ";
    private static final String INVALID_DELETE_MESSAGE = "    Sorry delete cannot be empty ";

    private static final String GREET_USER_LINE_1 = "Hello! I'm duke.Duke";
    private static final String GREET_USER_LINE_2 = "What can I do for you?";

    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    private static final String TASK_LEFT_MESSAGE_PART_1 = "Now you have ";
    private static final String TASK_LEFT_MESSAGE_PART_2 = " tasks in the list.";

    private static final String INVALID_DATE_FORMAT = "Please give a valid date";


    private static final String SPACE = " ";

    public UserInterface() {

    }

    //Given contents output line by line between the output lines
    public void outputUi(String... contents) {
        StringBuilder result = new StringBuilder(UPPER_LINE);
        for (String s: contents) {
            result.append(INDENT).append(s).append("\n");
        }
        result.append(LOWER_LINE);
        System.out.println(result.toString());
    }

    public void greetUser() {
        outputUi(GREET_USER_LINE_1, GREET_USER_LINE_2);
    }

    public void showInvalidCommandMessage() {
         outputUi(INVALID_COMMAND_MESSAGE);
    }

    public void showInvalidTodoCommand() {
        outputUi(INVALID_TODO_MESSAGE);
    }

    public void showInvalidDoneCommand() {
        outputUi(INVALID_DONE_MESSAGE);
    }

    public void showInvalidDeleteCommand() {
        outputUi(INVALID_DELETE_MESSAGE);
    }

    public void showInvalidTaskNumber() {
        outputUi(NO_TASK_MESSAGE);
    }

    public void showMarkedTaskDoneMessage(Task task) {
        outputUi(DONE_TASK_MARKED_MESSAGE, INDENT + task.getStatusIcon() + SPACE + task.getDescription());
    }

    public void showDeleteTaskMessage(Task task, int numOfTaskInList) {
        outputUi(DELETE_TASK_MARKED_MESSAGE, INDENT + task.toString(), numOfTaskInList(numOfTaskInList));
    }

    public void showAddedTaskMessage(Task task, int numOfTaskInList) {
        outputUi(ADDED_TASK_MESSAGE, INDENT + task.toString(), numOfTaskInList(numOfTaskInList));
    }

    private String numOfTaskInList(int numOfTaskInList) {
        return TASK_LEFT_MESSAGE_PART_1 + numOfTaskInList + TASK_LEFT_MESSAGE_PART_2;
    }

    public void showExitMessage() {
        outputUi(BYE_MESSAGE);
    }

    public void showInvalidDateFormatGiven() {
        outputUi(INVALID_DATE_FORMAT);
    }

    public void listTask(List<Task> lstOfTask) {
        String outputIndent = "    ";
        StringBuilder concat = new StringBuilder();
        for (int i = 0; i < lstOfTask.size(); i++) {
            Task task = lstOfTask.get(i);
            int taskNumber = i + 1;
            String s="";
            if (i == lstOfTask.size() -1) {
                s = outputIndent + taskNumber + "." + task.toString();
            } else {
                s = outputIndent + taskNumber + "." + task.toString() + "\n";
            }
            concat.append(s);
        }
        outputUi(concat.toString());
    }

}
