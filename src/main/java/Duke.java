package main.java;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private enum TaskType {
        TODO, DEADLINE, EVENT;
    }
    private List<Task> tasks;
    private String indentation = "  ";
    private String BYE_COMMAND = "bye";
    private String LIST_COMMAND = "list";
    private String DONE_COMMAND = "done";
    private String DELETE_COMMAND = "delete";
    private String TODO_COMMAND = "todo";
    private String DEADLINE_COMMAND = "deadline";
    private String EVENT_COMMAND = "event";
    private String TASK_AFTER_COMMAND = "taskafter";

    Duke() {
        tasks = new ArrayList<>();
    }

    private void addTask(String task, TaskType taskType) {
        Task newTask;
        String message;

        try {
            switch (taskType) {
            case TODO:
                if (task == null) {
                    throw new ToDoException();
                }
                newTask = new ToDoTask(task);
                break;
            case DEADLINE:
                if (task == null) {
                    throw new DeadlineException();
                }

                String[] arrForDeadline = task.split("/by", 2);

                if (arrForDeadline.length == 1) {
                    throw new DeadlineException();
                }

                String taskForDeadline = arrForDeadline[0].trim();
                String dateForDeadline = arrForDeadline[1].trim();
                LocalDate deadlineDate = LocalDate.parse(dateForDeadline);
                newTask = new DeadlineTask(taskForDeadline, deadlineDate);
                break;
            case EVENT:
                if (task == null) {
                    throw new EventException();
                }
                String[] arrForEvent = task.split("/at", 2);

                if (arrForEvent.length == 1) {
                    throw new EventException();
                }
                String taskForEvent = arrForEvent[0].trim();
                String dateForEvent = arrForEvent[1].trim();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                LocalDateTime eventDate = LocalDateTime.parse(dateForEvent, formatter);
                newTask = new EventTask(taskForEvent, eventDate);
                break;
            default:
                newTask = new Task(task);
                break;
            }
            tasks.add(newTask);
            message = "Okay. I will add this task:\n" + indentation + newTask + "\n" +
                    "Now you have " + tasks.size() + " " + (tasks.size() == 1 ? "task " : "tasks ") + "in the list.";
        } catch (ToDoException | DeadlineException | EventException e) {
            message = e.getMessage();
        } catch (DateTimeParseException e) {
            message = "Invalid date format! Please put it something " +
                    "like 2020-12-31 for deadline and 2020-12-31 1800 for event.";
        }

        sendMessage(message);
    }

    private void welcome() {
        String message = "Hi! My name is Duke.\nWhat do you want me to do?";
        sendMessage(message);
    }

    private void exit() {
        String message = "Bye. Thank you for using me!";
        sendMessage(message);
    }

    private void showAllTask() {
        String message;

        if (tasks.isEmpty()) {
            message = "You haven't add any task!";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here is the tasks in your list:\n");

            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1) + ". " + tasks.get(i) + "\n");
            }
            message = sb.toString().trim();
        }

        sendMessage(message);
    }

    private void doneTask(String taskNumber) {
        String message;
        try {
            if (taskNumber == null) {
                throw new DoneException();
            }
            int taskNum = Integer.parseInt(taskNumber);
            if (taskNum < 1 || taskNum > tasks.size()) {
                message = "Invalid task number!";
            } else {
                Task task = tasks.get(taskNum - 1);
                task.setStatusToDone();
                message = "Sucessfully marked this task as done: \n" + indentation + task.toString();
            }
        } catch (NumberFormatException e) {
            message = "Please put a number!";
        } catch (DoneException e) {
            message = e.getMessage();
        }
        sendMessage(message);
    }

    private void deleteTask(String taskNumber) {
        String message;
        try {
            if (taskNumber == null) {
                throw new DeleteException();
            }
            int taskNum = Integer.parseInt(taskNumber);
            if (taskNum < 1 || taskNum > tasks.size()) {
                message = "Invalid task number!";
            } else {
                Task task = tasks.remove(taskNum - 1);
                message = "Okay. I will delete this task:\n" + indentation + task + "\n" +
                        "Now you have " + tasks.size() + " " + (tasks.size() == 1 ? "task " : "tasks ") + "in the list.";
            }
        } catch (NumberFormatException e) {
            message = "Please put a number!";
        } catch (DeleteException e) {
            message = e.getMessage();
        }
        sendMessage(message);
    }

    private void taskAfter(String date) {
        String message;
        try {
            LocalDate parsedDate = LocalDate.parse(date);
            StringBuilder sb = new StringBuilder();
            sb.append("Here is the tasks after "
                    + parsedDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":\n");
            int count = 1;
            for (Task task : tasks) {
                if (task instanceof ToDoTask) {
                    continue;
                } else if (task instanceof DeadlineTask) {
                    DeadlineTask deadlineTask = (DeadlineTask) task;
                    if (parsedDate.isBefore(deadlineTask.getDate())) {
                        sb.append(count + ". " + task + "\n");
                        count++;
                    }
                } else if (task instanceof EventTask) {
                    EventTask eventTask = (EventTask) task;
                    if (parsedDate.isBefore(eventTask.getDate().toLocalDate())) {
                        sb.append(count + ". " + task + "\n");
                        count++;
                    }
                }
            }
            message = sb.toString().trim();
        } catch (DateTimeParseException e) {
            message = "Invalid date format! Please put it something like 2020-12-31!";
        }

        sendMessage(message);
    }

    private void takeUserInput() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                String userInput = sc.nextLine();
                String[] userInputArr = userInput.split("\\s", 2);
                String command = userInputArr[0];
                String arg = null;


                if (userInputArr.length != 1) {
                    arg = userInputArr[1];
                }

                if (command.equals(BYE_COMMAND)) {
                    break;
                } else if (command.equals(LIST_COMMAND)) {
                    showAllTask();
                } else if (command.equals(DONE_COMMAND)) {
                    doneTask(arg);
                } else if (command.equals(DELETE_COMMAND)) {
                    deleteTask(arg);
                } else if (command.equals(TASK_AFTER_COMMAND)) {
                    taskAfter(arg);
                } else if (command.equals(TODO_COMMAND)) {
                    addTask(arg, TaskType.TODO);
                } else if (command.equals(DEADLINE_COMMAND)) {
                    addTask(arg, TaskType.DEADLINE);
                } else if (command.equals(EVENT_COMMAND)) {
                    addTask(arg, TaskType.EVENT);
                } else {
                    throw new NotACommandException();
                }
            } catch (NotACommandException e) {
                sendMessage(e.getMessage());
            }
        }
    }

    private void sendMessage(String sendMessage) {
        System.out.println(createLine(sendMessage));
    }

    private String createLine(String response) {
        Scanner sc = new Scanner(response);
        String equalSign = "=";
        int width = 75;
        StringBuilder sb = new StringBuilder();

        while (sc.hasNext()) {
            String textLine = indentation + sc.nextLine();
            sb.append(textLine + "\n");
            width = Math.max(width, textLine.length() + 2);
        }

        String line = equalSign.repeat(width);

        return line + "\n" + sb.toString() + line;
    }

    public static void main(String[] args) {
        Duke duke = new Duke();

        duke.welcome();
        duke.takeUserInput();
        duke.exit();
    }
}