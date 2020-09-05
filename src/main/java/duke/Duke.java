package duke;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke  {

    private UserInterface ui;
    private Storage storage;
    private Parser parser;
    private TaskList taskList;

    public Duke() {} // empty constructor needed for javaFX

    /**
     * Constructor for a duke object
     * @param filePath the filepath that stores data of the duke obj
     */
    public Duke(String filePath) {
        this.ui = new UserInterface();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        this.taskList = new TaskList(new ArrayList<>());
        this.startup();// copy the task data
    }

    private void startup() {
        try {
            storage.populateToLstOfTask(taskList.getLstOfTask());
            ui.greetUser();
        } catch (IOException e) {
            System.out.println(e.toString() + " Error trying to load tasks");
        }
    }

    private String processCommand(String[] parsedUserInput) {
        try {
            String result = "";
            String cmd = parsedUserInput[0];
            Command checkedCommand = Command.valueOfUserCommand(cmd);
            if (checkedCommand == null) {
                return ui.showInvalidCommandMessage();
            } else {
                switch (checkedCommand) {
                case LIST:
                    result = ui.listTask(taskList.getLstOfTask());
                    break;
                case BYE:
                    exit();
                    result = ui.showExitMessage();
                    break;
                case DONE:
                    result = done(parsedUserInput);
                    break;
                case TODO:
                    result = addToDo(parsedUserInput);
                    break;
                case EVENT:
                    result = addEvent(parsedUserInput);
                    break;
                case DEADLINE:
                    result = addDeadline(parsedUserInput);
                    break;
                case DELETE:
                    result = delete(parsedUserInput);
                    break;
                case FIND:
                    result = search(parsedUserInput);
                    break;
                default:
                    break;
                }

            }
            return result;

        } catch (IndexOutOfBoundsException e) {
            return ui.showInvalidCommandMessage();
        }
    }

    private void exit() {
        storage.saveTaskContents(taskList.getLstOfTask());
    }

    private String done(String[] parsedUserInput) {

        try {
            String doneTask = parsedUserInput[1];
            int doneTaskNumber = Integer.parseInt(doneTask);
            int identifierNumberInArrayList = doneTaskNumber - 1;
            Task task = taskList.getLstOfTask()
                    .get(identifierNumberInArrayList);
            task.markAsDone();
            return ui.showMarkedTaskDoneMessage(task);
        } catch (IndexOutOfBoundsException e1) {
            return ui.showInvalidDoneCommand();
        }
    }

    private String delete(String[] parsedUserInput) {
        try {
            String deleteTask = parsedUserInput[1];
            int doneTaskNumber = Integer.parseInt(deleteTask);
            int identifierNumberInArrayList = doneTaskNumber - 1;
            List<Task> lstOfTask = taskList.getLstOfTask();
            Task task = lstOfTask
                    .get(identifierNumberInArrayList);
            lstOfTask.remove(identifierNumberInArrayList);
            return ui.showDeleteTaskMessage(task, taskList.getNumOfTask());
        } catch (IndexOutOfBoundsException e1) {
            return ui.showInvalidDeleteCommand();
        }
    }

    private String addToDo(String[] parsedUserInput) {
        try {
            String test = parsedUserInput[1];
            String taskDescription = "";
            for (int i = 1; i < parsedUserInput.length; i++) {
                if (i == parsedUserInput.length - 1) {
                    taskDescription += parsedUserInput[i];
                } else {
                    taskDescription += parsedUserInput[i] + " ";
                }
            }
            ToDo td = new ToDo(taskDescription);
            taskList.add(td);
            return ui.showAddedTaskMessage(td, taskList.getNumOfTask());
        } catch (IndexOutOfBoundsException e1) {
            return ui.showInvalidTodoCommand();
        }
    }

    private String addEvent(String[] parsedUserInput) {
        String taskDescription = "";
        for (int i = 1; i < parsedUserInput.length; i++) {
            if (i == parsedUserInput.length - 1) {
                taskDescription += parsedUserInput[i];
            } else {
                taskDescription += parsedUserInput[i] + " ";
            }
        }

        String[] eventArray = taskDescription.split(" /at ");
        String description = eventArray[0];
        String date = eventArray[1];

        Event event = new Event(description, date.trim());
        taskList.add(event);
        return ui.showAddedTaskMessage(event, taskList.getNumOfTask());
    }

    private String addDeadline(String[] parsedUserInput) {
        try {
            String taskDescription = "";
            for (int i = 1; i < parsedUserInput.length; i++) {
                if (i == parsedUserInput.length - 1) {
                    taskDescription += parsedUserInput[i];
                } else {
                    taskDescription += parsedUserInput[i] + " ";
                }
            }


            String[] deadlineArray = taskDescription.split(" /by ");
            String description = deadlineArray[0];
            String date = deadlineArray[1];


            LocalDateTime d1 = parser.parseDateAndTime(date);
            Deadline deadline = new Deadline(description, d1);
            taskList.add(deadline);
            return ui.showAddedTaskMessage(deadline, taskList.getNumOfTask());
        } catch (DateTimeParseException e) {
            return ui.showInvalidDateFormatGiven();
        }
    }

    private String search(String[] parsedUserInput) {
        try {
            StringBuilder keyword = new StringBuilder(parsedUserInput[1]);
            for (int i = 2; i < parsedUserInput.length; i++) {
                keyword.append(parsedUserInput[i]);
            }

            List<Task> lst = taskList.getLstOfTask();
            List<Task> resultList = new ArrayList<>();
            for (int j = 0; j < taskList.getNumOfTask(); j++) {
                Task t = lst.get(j);
                String description = t.getDescription();
                if (description.contains(keyword)) {
                    resultList.add(t);
                }

            }

            if (resultList.isEmpty()) {
                return ui.showNoSearchResult();
            } else {
                return ui.showSearchResults(resultList);
            }

        } catch (IndexOutOfBoundsException e) {
            return ui.showInvalidSearchCommand();
        }

    }

    public static void main(String[] args) {
        Duke duke = new Duke("Tasks.txt");
        Scanner sc = new Scanner(System.in);

        //duke.startup(); // startup alr run when instantiating duke

        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] parsedString = duke.parser.parseString(input);
            duke.processCommand(parsedString);
            if (input.equals("bye")) {
                break;
            }
        }


    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String[] parsedString = this.parser.parseString(input);
        return this.processCommand(parsedString);
    }



}
