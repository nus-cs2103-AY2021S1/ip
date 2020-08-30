import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class NewIoHandler {
    private static NewUI ui = new NewUI();
    private Scanner sc = new Scanner(System.in);
    private static TaskManager taskManager = new TaskManager(new ArrayList<>());
    private static TaskSaveAndLoadManager taskSaveAndLoadManager = new TaskSaveAndLoadManager();
    // private NewUI ui = new NewUI();

    private static String[] splitReply(String input) {
        String[] replyArr;
        // index 4 is excluded
        if (input.length() >= 4) {
            if (input.substring(0, 4).equals("done")) {
                replyArr = input.split(" ");
            } else if (input.contains("/")) {
                replyArr = input.split("/");
            } else {
                replyArr = input.split(" ");
            }
        } else {
            // printMessage(new UnexpectedInputException().toString());
            replyArr = null;
        }
        return replyArr;
    }

    private static DateAndTime handleTime(String timeFormat) {
        if (!timeFormat.contains(" ")) {
            LocalDate localDate = LocalDate.parse(timeFormat);
            return new DateAndTime(localDate);
        } else {
            String[] split = timeFormat.split(" ");
            LocalTime localTime = LocalTime.parse(split[0].trim());
            LocalDate localDate = LocalDate.parse(split[1].trim());
            return new DateAndTime(localDate, localTime);
        }
    }

    private static String handleUserInput(String input) {
        String fullReply;
        ArrayList<String> botReplyHeading = ui.botReplyHeading(input);
        if (input.equals("list")) {
            String botReply = botReplyHeading.get(0);
            String resultList = ui.formatBotReplyBody(taskManager.convertTaskListToString(taskManager.getTaskList()));
            fullReply = botReply + resultList;
        } else if (input.trim().startsWith("done")) {
            String[] replyArr = input.split(" ");
            int taskIndex = Integer.parseInt(replyArr[1]) - 1;
            taskManager.markTaskAsDone(taskIndex);
            String botReply = botReplyHeading.get(0);
            String taskDone = ui.formatBotReplyBody(taskManager.getTask(taskIndex).toString());
            fullReply = botReply + taskDone;
        } else if (input.trim().startsWith("delete")) {
            String[] replyArr = input.split(" ");
            int taskIndex = Integer.parseInt(replyArr[1]) - 1;
            Task cacheTask = taskManager.getTask(taskIndex);
            taskManager.removeTask(taskIndex);
            String botReply = botReplyHeading.get(0);
            String taskDone = ui.formatBotReplyBody(cacheTask.toString());
            fullReply = botReply + taskDone;
        } else if (input.trim().startsWith("find")) {
            String[] replyArr = input.split(" ");
            ArrayList<Task> foundTasks = taskManager.findTaskThatHasKeyword(replyArr[1]);
            String botReply = "";
            if (foundTasks.size() == 0) {
                botReply = botReplyHeading.get(0);
            } else {
                botReply = botReplyHeading.get(1);
            }
            String resultList = ui.formatBotReplyBody(taskManager.convertTaskListToString(foundTasks));
            fullReply = botReply + resultList;
            // adding task to the list
        } else {
            Task newTask = null;
            if (input.trim().startsWith("todo")) {
                input = input.replace("todo ", "");
                newTask = new ToDoTask(input, false);
            } else if (input.trim().startsWith("deadline")) {
                input = input.replace("deadline ", "");
                input = input.replace("/by", "/");
                String[] tempArr = splitReply(input);
                newTask = new DeadlineTask(tempArr[0], false, handleTime(tempArr[1].trim()));
            } else if (input.trim().startsWith("event")) {
                input = input.replace("event ", "");
                input = input.replace("/at", "/");
                String[] tempArr = splitReply(input);
                newTask = new EventTask(tempArr[0], false, handleTime(tempArr[1].trim()));
            }
            taskManager.addTask(newTask);
            String botReply = botReplyHeading.get(0);
            assert newTask != null;
            String addedTask = ui.formatBotReplyBody(newTask.toString() + "\n");
            String totalTask = ui.formatBotReplyBody("Now you have a grand total of " +
                    taskManager.getTotalNoOfTasks() + " tasks!");
            fullReply = botReply + addedTask + totalTask;
        }
        return fullReply;
    }

    public static String handleInputAndReply(String input) throws DukeException, IOException {
        taskSaveAndLoadManager.saveTaskManager(taskManager);
        if (taskSaveAndLoadManager.loadTaskManager() != null) {
            taskManager = taskSaveAndLoadManager.loadTaskManager();
        }
        String output = "";
        try {
            output = DukeExceptionHandler.checkForException_new(input);
        } catch (DukeException e) {
            return e.toString();
        }
        if (output.equals("NO EXCEPTION")) {
            output = handleUserInput(input);
        } else {
            output = DukeExceptionHandler.checkForException_new(input);
        }
        return output;
    }
}
