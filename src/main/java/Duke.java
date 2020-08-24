import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private String indent = "    ";
    private String outputIndent = "    ";
    public String upperLine = indent + "___________________________________________________" + "\n";
    public String lowerLine =  indent + "___________________________________________________" +"\n";
    private final String INVALID_COMMAND_MESSAGE = "    Sorry couldn't recognise command";
    private final String DONE_TASK_MARKED_MESSAGE = "    Nice! I've marked this task as done:";
    private final String DELETE_TASK_MARKED_MESSAGE = "    Noted. I've removed this task:";

    private final String NO_TASK_MESSAGE = "    Sorry the task does not exists";    
    private final String ADDED_TASK_MESSAGE = "    Got it. I've added this task:";
    private final String INVALID_DONE_MESSAGE = "    Sorry done cannot be empty ";
    private final String INVALID_TODO_MESSAGE = "    Sorry todo cannot be empty ";
    private final String INVALID_DELETE_MESSAGE = "    Sorry delete cannot be empty ";

    private List<Task> lstOfTask = new ArrayList<>();

    private String greetUser() {
        return upperLine + indent + "Hello! I'm Duke" + "\n"
                + indent + "What can I do for you?" +"\n"
                + lowerLine;
    }

    private String[] parseString(String input) {
        String[] tokens = input.split(" ");
        return tokens;
    }

    private String processCommand(String[] parsedUserInput) {
        try {
            String cmd = parsedUserInput[0];
            Command checkedCommand = Command.valueOfUserCommand(cmd);
            String resultString;

            if (checkedCommand == null) {
                resultString = INVALID_COMMAND_MESSAGE;
            } else {
                switch (checkedCommand) {
                    case LIST:
                        resultString = listTask();
                        break;
                    case BYE:
                        resultString = exit();
                        break;
                    case DONE:
                        resultString = done(parsedUserInput);
                        break;
                    case TODO:
                        resultString = addToDo(parsedUserInput);
                        break;
                    case EVENT:
                        resultString = ADDED_TASK_MESSAGE + "\n" + outputIndent + addEvent(parsedUserInput) + "\n"
                                + getNumOfTaskMessage();
                        break;
                    case DEADLINE:
                        resultString = ADDED_TASK_MESSAGE + "\n" + outputIndent + addDeadline(parsedUserInput) + "\n"
                                + getNumOfTaskMessage();
                        break;
                    case DELETE:
                        resultString = delete(parsedUserInput);
                        break;
                    default:
                        resultString = "";
                        break;
                }
            }
            return upperLine + resultString + "\n" + lowerLine;
        } catch (IndexOutOfBoundsException e) {
            return upperLine + INVALID_COMMAND_MESSAGE + "\n" + lowerLine;
        }
    }

    private String listTask() {
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
        return concat.toString();
    }

    private String exit() {
        return outputIndent + "Bye. Hope to see you again soon!" + "\n";
    }

    private String done(String[] parsedUserInput) {

        try {
            String doneTask = parsedUserInput[1];
            int doneTaskNumber = Integer.parseInt(doneTask);
            int identifierNumberInArrayList = doneTaskNumber - 1;
            Task task = this.lstOfTask.get(identifierNumberInArrayList);
            task.markAsDone();
            return DONE_TASK_MARKED_MESSAGE + "\n"
                    + outputIndent + outputIndent + task.toString();
        } catch (IndexOutOfBoundsException e1) {
            return INVALID_DONE_MESSAGE;
        }
    }

    private String delete(String[] parsedUserInput) {
        try {
            String deleteTask = parsedUserInput[1];
            int doneTaskNumber = Integer.parseInt(deleteTask);
            int identifierNumberInArrayList = doneTaskNumber - 1;
            Task task = this.lstOfTask.get(identifierNumberInArrayList);
            lstOfTask.remove(identifierNumberInArrayList);
            return DELETE_TASK_MARKED_MESSAGE + "\n"
                    + outputIndent + outputIndent + task.toString() + "\n"
                    + getNumOfTaskMessage();
        } catch (IndexOutOfBoundsException e1) {
            return INVALID_DELETE_MESSAGE;
        }

    }

    private String addToDo(String[] parsedUserInput) {
        try {
            String test = parsedUserInput[1];
            String taskDescription = "";
            for (int i = 1; i < parsedUserInput.length; i++) {
                if (i == parsedUserInput.length -1) {
                    taskDescription += parsedUserInput[i];
                } else {
                    taskDescription += parsedUserInput[i] + " ";
                }
            }
            ToDo td = new ToDo(taskDescription);
            lstOfTask.add(td);
            return ADDED_TASK_MESSAGE + "\n"
                    + outputIndent + outputIndent + td.toString() + "\n"
                    + getNumOfTaskMessage();
        } catch (IndexOutOfBoundsException e1) {
            return INVALID_TODO_MESSAGE;
        }
    }

    private String addEvent(String[] parsedUserInput) {
        String taskDescription ="";
        for(int i = 1; i < parsedUserInput.length; i++) {
            if (i == parsedUserInput.length-1) {
             taskDescription += parsedUserInput[i];
            } else {
                taskDescription += parsedUserInput[i] + " ";
            }
        }

        String[] eventArray = taskDescription.split(" /at ");
        String description = eventArray[0];
        String date = eventArray[1];

        Event event = new Event(description, date.trim());
        lstOfTask.add(event);
        return outputIndent+event.toString();
    }

    private String addDeadline(String[] parsedUserInput) {
        String taskDescription ="";
        for(int i = 1; i < parsedUserInput.length; i++) {
            if (i == parsedUserInput.length-1) {
                taskDescription += parsedUserInput[i];
            }
            taskDescription += parsedUserInput[i] + " ";
        }

        String[] deadlineArray = taskDescription.split(" /by ");
        String description = deadlineArray[0];
        String date = deadlineArray[1];

        Deadline deadline = new Deadline(description, date.trim());
        lstOfTask.add(deadline);
        return outputIndent + deadline.toString();
    }

    public int getNumOfTask() {
        return lstOfTask.size();
    }

    private String getNumOfTaskMessage() {
        return "    Now you have " + getNumOfTask() + " tasks in the list";
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);

        System.out.println(duke.greetUser());;

        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] parsedString = duke.parseString(input);
            String output = duke.processCommand(parsedString);
            System.out.println(output);
        }

    }
}
