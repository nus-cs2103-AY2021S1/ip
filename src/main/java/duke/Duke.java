package duke;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private UserInterface ui;
    private Storage storage;
    private Parser parser;
    private TaskList tasksList;
    private Scheduler scheduler;

    // empty constructor needed for javaFX
    public Duke() {}

    /**
     * Constructor for a duke object
     * @param filePath the filepath that stores data of the duke obj
     */
    public Duke(String filePath) {
        this.ui = new UserInterface();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        this.tasksList = new TaskList(new ArrayList<>());
        this.scheduler = new Scheduler();
        // this method copy the task data to the taskList
        this.startup();
    }

    private void startup() {
        try {
            storage.populateToLstOfTask(tasksList.getLstOfTask());
        } catch (IOException e) {
            System.out.println(e.toString() + " Error trying to load tasks");
        }
    }

    public String greetDukeUser() {
        return ui.greetUser();
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
                    result = ui.listTask(tasksList.getLstOfTask());
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
                case SCHEDULE:
                    result = schedule(parsedUserInput);
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
        storage.saveTaskContents(tasksList.getLstOfTask());
        assert tasksList != null;
    }

    private String done(String[] parsedUserInput) {
        try {
            String doneTask = parsedUserInput[1];
            int doneTaskNumber = Integer.parseInt(doneTask);
            int identifierNumberInArrayList = doneTaskNumber - 1;
            Task task = tasksList.getLstOfTask()
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
            List<Task> lstOfTask = tasksList.getLstOfTask();
            Task task = lstOfTask
                    .get(identifierNumberInArrayList);
            lstOfTask.remove(identifierNumberInArrayList);
            return ui.showDeleteTaskMessage(task, tasksList.getNumOfTask());
        } catch (IndexOutOfBoundsException e1) {
            return ui.showInvalidDeleteCommand();
        }
    }

    private String addToDo(String[] parsedUserInput) {
        try {
            //think of a way to avoid this test for exception
            //currently this test if user input to do command
            //w/o any description
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
            tasksList.add(td);
            return ui.showAddedTaskMessage(td, tasksList.getNumOfTask());
        } catch (IndexOutOfBoundsException e1) {
            return ui.showInvalidTodoCommand();
        }
    }

    private String addEvent(String[] parsedUserInput) {
        try {
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

            LocalDateTime localDateTime = parser.parseDateAndTime(date);
            Event event = new Event(description, localDateTime);
            tasksList.add(event);
            return ui.showAddedTaskMessage(event, tasksList.getNumOfTask());
        } catch (DateTimeParseException e) {
            return ui.showInvalidDateFormatGiven();
        }
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
            tasksList.add(deadline);
            return ui.showAddedTaskMessage(deadline, tasksList.getNumOfTask());
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

            List<Task> lst = tasksList.getLstOfTask();
            List<Task> resultList = new ArrayList<>();
            for (int j = 0; j < tasksList.getNumOfTask(); j++) {
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

    private String schedule(String[] parsedUserInput){
        try {
            scheduler.instantiateTasksList(this.tasksList);

            // can add other types of scheduling
            String typeOfSchedule = parsedUserInput[1];
            String dateString = parsedUserInput[2];
            String timeString = parsedUserInput[3];
            LocalDateTime date = parser.parseDateAndTime(dateString + " " + timeString);

            List<Task> tasksBeforeDate = scheduler.getTaskByDate(date);

            return ui.listTask(tasksBeforeDate);

        } catch (NullPointerException | DateTimeParseException e2) {
            return ui.showInvalidDateFormatGiven();
        }

    }

    /**
     * This is the main entry of of the duke if app is run
     * on CLI
     * @param args
     */
    public static void main(String[] args) {
        Duke duke = new Duke("Tasks.txt");
        Scanner sc = new Scanner(System.in);
        System.out.println(duke.greetDukeUser());

        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] parsedString = duke.parser.parseString(input);
            System.out.println(duke.processCommand(parsedString));
            if (input.equals("bye")) {
                break;
            }
        }
    }

    /**
     * This method returns a response to user input
     * @param input String input by user
     * @return the String output by duke
     */
    public String getResponse(String input) {
        String[] parsedString = this.parser.parseString(input);
        return this.processCommand(parsedString);
    }



}
