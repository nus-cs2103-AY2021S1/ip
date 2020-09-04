package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Deals with making sense of the user command
 */
public class Parser {
    private String command;
    private String[] commandParts;
    private final String horizontalLine = "_______________________________________________________";

    public int parseCommand(String command) {
        this.command = command;
        this.commandParts = command.split("\\s", 2);

        if (commandParts[0].contains("todo")) {
            return 1;
        } else if (commandParts[0].contains("deadline")) {
            return 2;
        } else if (commandParts[0].contains("event")) {
            return 3;
        } else if (commandParts[0].contains("done")) {
            return 4;
        } else if (commandParts[0].contains("list")) {
            return 5;
        } else if (commandParts[0].contains("bye")) {
            return 6;
        } else if (commandParts[0].contains("delete")) {
            return 7;
        } else if (commandParts[0].contains("find")) {
            return 8;
        } else {
            return -1;
        }
    }

    public Task createTask(int commandNumber) throws MissingTaskDescriptionException {
        Task task = null;
//        try {
        if (commandNumber == 1) {
            task = createNewToDo();
        } else if (commandNumber == 2) {
            task = createNewDeadline();
        } else if (commandNumber == 3) {
            task = createNewEvent();
        }
//        } catch (MissingTaskDescriptionException e) {
//            System.out.println(e.getMessage());
//        }
        return task;
    }

    public Task createNewToDo() throws MissingTaskDescriptionException {
        try {

            Task newToDoTask = new ToDo(commandParts[1]);
            return newToDoTask;

        } catch (Exception e) {
            throw new MissingTaskDescriptionException(horizontalLine
                    + "\r\n"
                    + "Oops! The description cannot be empty :("
                    + "\r\n"
                    + horizontalLine);
        }
    }

    public Task createNewDeadline() throws MissingTaskDescriptionException {
        try {

            String[] deadlineParts = commandParts[1].split("/by");
            LocalDate deadline = LocalDate.parse(deadlineParts[1].trim());
            String afterDateTimeFormat = deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
            Task newDeadlineTask = new Deadline(deadlineParts[0].trim(), afterDateTimeFormat);
            return newDeadlineTask;

        } catch (Exception e) {

            throw new MissingTaskDescriptionException(horizontalLine
                    + "\r\n"
                    + "Oops! The description cannot be empty :("
                    + "\r\n"
                    + horizontalLine);
        }
    }

    public Task createNewEvent() throws MissingTaskDescriptionException {
        try {

            String[] eventParts = commandParts[1].split("/at");
            LocalDate event = LocalDate.parse(eventParts[1].trim());
            String afterDateTimeFormat = event.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
            Task newEventTask = new Event(eventParts[0].trim(), afterDateTimeFormat);
            return newEventTask;

        } catch (Exception e) {

            throw new MissingTaskDescriptionException(horizontalLine
                    + "\r\n"
                    + "Oops! The description cannot be empty :("
                    + "\r\n"
                    + horizontalLine);
        }
    }

    public int doneTask() throws MissingTaskNumberException {

        int taskNumber = -1;
        taskNumber = markDoneTask();
        return taskNumber;
    }
        
    
    public int markDoneTask() throws MissingTaskNumberException {
        
        try {
            int taskNumber = Integer.parseInt(commandParts[1].trim()) - 1;
            return taskNumber;

        } catch (Exception e) {

            throw new MissingTaskNumberException(horizontalLine
                    + "\r\n"
                    + "Oops! The task number cannot be missing :("
                    + "\r\n"
                    + horizontalLine);
        }
    }

    public int deleteTask() {
        int taskNumber = -1;

        try {
            taskNumber = deleteTaskFromList();
        } catch (MissingTaskNumberException e) {
            System.out.println(e.getMessage());
        }
        return taskNumber;
    }

    public int deleteTaskFromList() throws MissingTaskNumberException {
        try {
            int taskNumber = Integer.parseInt(commandParts[1].trim()) - 1;
            return taskNumber;

        } catch (Exception e) {

            throw new MissingTaskNumberException(horizontalLine
                    + "\r\n"
                    + "Oops! The task number cannot be missing :("
                    + "\r\n"
                    + horizontalLine);
        }
    }

    public ArrayList<Task> getMatchingTasks(TaskList tasks) throws MissingTaskDescriptionException {
        try {
            ArrayList<Task> matchingTasks = new ArrayList<>();
            boolean doesTaskMatch = false;
            
            String descriptionToFind = commandParts[1];;
            
            for (int i = 0; i < tasks.getSize(); i++) {
                ArrayList<Task> listOfTasks = tasks.getTaskList();
                Task task = listOfTasks.get(i);
                String descriptionOfTask = task.getDescription();
                String[] taskDescriptionParts = descriptionOfTask.split("\\s");
                
                for (int j = 0; j < taskDescriptionParts.length; j++) {
                    if (!doesTaskMatch) {
                        if (taskDescriptionParts[j].contains(descriptionToFind)) {
                            doesTaskMatch = true;
                            break;
                        }
                    }
                }
                
                if (doesTaskMatch) {
                    matchingTasks.add(task);
                }
                
                doesTaskMatch = false;
            }
            return matchingTasks;
            
        } catch (Exception e) {

            throw new MissingTaskDescriptionException(horizontalLine
                    + "\r\n"
                    + "Oops! The description cannot be empty :("
                    + "\r\n"
                    + horizontalLine);
        }
    }
}