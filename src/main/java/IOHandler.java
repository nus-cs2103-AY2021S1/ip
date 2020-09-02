import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Handles the input by the user and generates the respective output.
 */
public class IOHandler {

    private static TaskManager taskManager = new TaskManager();

    /**
     * Handles the input by the user and prints the respective output.
     */
    public static String handleIO(String input) {

//        try {

//            String fileName = "data/duke.txt";
//            File file = new File(fileName);
//
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//
            String error = DukeExceptionHandler.handleException(input);
//            List<String> files = FileHandler.readSavedFile(fileName);

//            for (String value : files) {
//                Task task = TextAndTaskConverter.textConverter(value);
//                taskManager.getTasksList().add(task);
//            }

            while (!input.equals("bye")) {

                if (error != null) {
                    System.out.print(input);
                    System.out.println(error);

                } else {
                    if (input.equals("list")) {
                        return taskManager.toString();

                    } else if (input.contains("done")) {
                        String[] textArray = input.split(" ", 2);
                        int taskNum = Integer.parseInt(textArray[1]);
                        taskManager.setTaskDone(taskNum);

                        return "Nice! I've marked this task as done:\n"
                                + taskManager.getTask(taskNum - 1);

//                        System.out.println("Nice! I've marked this task as done:\n"
//                                + taskManager.getTask(taskNum - 1));

                    } else if (input.contains("delete")) {
                        String[] textArray = input.split(" ", 2);
                        int taskNum = Integer.parseInt(textArray[1]);
                        Task deletedTask = taskManager.getTask(taskNum - 1);
                        taskManager.removeTask(taskNum);

                        return "Noted. I've removed this task:\n"
                                + deletedTask + "\nNow you have " + taskManager.getNumTasks()
                                + " tasks in the list";

//                        System.out.println("Noted. I've removed this task:\n"
//                                + deletedTask + "\nNow you have " + taskManager.getNumTasks()
//                                + " tasks in the list");

                    } else if (input.contains("find")) {
                        String[] textArray = input.split(" ", 2);
                        ArrayList<String> tasksFound = new ArrayList<>();

                        for (int i = 0; i < taskManager.getTasksList().size(); i++) {
                            String found = taskManager.getTasksList().get(i).toString();
                            if (found.contains(textArray[1])) {
                                tasksFound.add(found);
                            }
                        }

                        if (tasksFound.size() > 0) {

                            System.out.println("Here are the matching tasks in your list:");

                            for (int j = 0; j < tasksFound.size(); j++) {

                                return (j + 1) + ". " + tasksFound.get(j);
                                //System.out.println((j + 1) + ". " + tasksFound.get(j));
                            }
                        } else {
                            return "Nothing matches :(";
                            //System.out.println("Nothing matches :(");
                        }

                    } else if (input.length() > 0) {

                        if (input.contains("todo")) {
                            String[] textArray = input.split(" ", 2);
                            Todo todo = new Todo(textArray[1]);
                            taskManager.addTask(todo);

                            return "Got it. I've added this task:\n"
                                    + todo + "\nNow you have " + taskManager.getNumTasks()
                                    + " tasks in the list";

//                            System.out.println("Got it. I've added this task:\n"
//                                    + todo + "\nNow you have " + taskManager.getNumTasks()
//                                    + " tasks in the list");

                        } else if (input.contains("deadline")) {
                            String[] textArray = input.split(" ", 2);
                            String trimText = textArray[1].trim();
                            String task = trimText.replace("/by", "/");

                            String taskName = TextAndTaskConverter.getTaskName(task);
                            LocalDate date = TextAndTaskConverter.getDate(task);

                            Deadline deadline = new Deadline(taskName, date);
                            taskManager.addTask(deadline);

                            return "Got it. I've added this task:\n"
                                    + deadline + "\nNow you have " + taskManager.getNumTasks()
                                    + " tasks in the list";

//                            System.out.println("Got it. I've added this task:\n"
//                                    + deadline + "\nNow you have " + taskManager.getNumTasks()
//                                    + " tasks in the list");

                        } else if (input.contains("event")) {
                            String[] textArray = input.split(" ", 2);

                            String trimText = textArray[1].trim();
                            String task = trimText.replace("/at", "/");

                            String taskName = TextAndTaskConverter.getTaskName(task);
                            LocalDate date = TextAndTaskConverter.getDate(task);
                            LocalTime time = TextAndTaskConverter.getTime(task);

                            Event event = new Event(taskName, date, time);
                            taskManager.addTask(event);

                            return "Got it. I've added this task:\n"
                                    + event + "\nNow you have " + taskManager.getNumTasks()
                                    + " tasks in the list";

//                            System.out.println("Got it. I've added this task:\n"
//                                    + event + "\nNow you have " + taskManager.getNumTasks()
//                                    + " tasks in the list");
                        }
                    }
                }
            }
            //FileHandler.writeToFile(fileName, taskManager);
//        catch(FileNotFoundException e){
//
//            return "File not found!";
//            //System.out.println("File not found!");
//        }
//        catch(IOException e){
//            return "OOPS something went wrong!";
////            System.out.println("OOPS something went wrong!");
//        }
        return "Bye. Hope to see you again soon!";
    }
}







