import java.io.*;
import java.util.Scanner;

public class IOHandler {

    Scanner sc = new Scanner(System.in);
    String text = sc.nextLine();

    public boolean hasText(String text) {
        return text != null;
    }

    TaskManager taskManager = new TaskManager();

    public void handleIO() {

        try {
            while (!text.equals("bye")) {

                String fileName = "data/duke.txt";
                String error = DukeExceptionHandler.handleException(text);
                System.out.println(text + "1");
                if (error != null) {
                    System.out.print(text + "2");
                    System.out.println(error);

                } else {
                    if (text.equals("list")) {
                        System.out.print(taskManager);

                    } else if (text.contains("done")) {
                        String[] textArray = text.split(" ", 2);
                        int taskNum = Integer.parseInt(textArray[1]);
                        taskManager.setTaskDone(taskNum);

                        System.out.println("Nice! I've marked this task as done:\n"
                                + taskManager.getTask(taskNum - 1));

                        Task doneTask = taskManager.getTask(taskNum - 1);
                        replaceDone(fileName, doneTask.getDescription());

                    } else if (text.contains("delete")) {
                        String[] textArray = text.split(" ", 2);
                        int taskNum = Integer.parseInt(textArray[1]);
                        Task deletedTask = taskManager.getTask(taskNum - 1);
                        taskManager.removeTask(taskNum);

                        System.out.println("Noted. I've removed this task:\n"
                                + deletedTask + "\nNow you have " + taskManager.getNumTasks()
                                + " tasks in the list");

                    } else if (text.length() > 0) {

                        if (text.contains("todo")) {
                            String[] textArray = text.split(" ", 2);
                            Todo todo = new Todo(textArray[1]);
                            taskManager.addTask(todo);
                            System.out.println("Got it. I've added this task:\n"
                                    + todo + "\nNow you have " + taskManager.getNumTasks()
                                    + " tasks in the list");

                            writeToFile(fileName, new Todo(textArray[1]));

                        } else if (text.contains("deadline")) {
                            String[] textArray = text.split(" ", 2);
                            Deadline deadline = new Deadline(textArray[1]);
                            taskManager.addTask(deadline);
                            System.out.println("Got it. I've added this task:\n"
                                    + deadline + "\nNow you have " + taskManager.getNumTasks()
                                    + " tasks in the list");

                            writeToFile(fileName, new Deadline(textArray[1]));

                        } else if (text.contains("event")) {
                            String[] textArray = text.split(" ", 2);
                            Event event = new Event(textArray[1]);
                            taskManager.addTask(event);
                            System.out.println("Got it. I've added this task:\n"
                                    + event + "\nNow you have " + taskManager.getNumTasks()
                                    + " tasks in the list");

                            writeToFile(fileName, new Event(textArray[1]));
                        }
                    }
                }


                text = sc.nextLine();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        catch (IOException e) {
            System.out.println("OOPS something went wrong!");
        }
        sc.close();
    }

    public static void writeToFile(String file, Task task) throws IOException {

        FileWriter writer = new FileWriter(file, true);

        if (task instanceof Todo) {
            writer.write("T | " +  (task.getDone() ? 1 : 0) + " | " + task.getDescription() + "\n");
        }

        if (task instanceof Deadline) {
            writer.write("D | " + (task.getDone() ? 1 : 0) + " | " + ((Deadline) task).getDescription() + " | " + ((Deadline) task).getTime() + "\n");
        }

        if (task instanceof Event) {
            writer.write("E | " + (task.getDone() ? 1 : 0) + " | " + ((Event) task).getDescription() + " | " + ((Event) task).getTime() + "\n");
        }

        writer.close();
    }

    public static void replaceDone(String filePath, String replaceWith) {
        try {
            // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader(filePath));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();
            String inputStr = inputBuffer.toString();

            inputStr = inputStr.replace("| 0 | " + replaceWith, "| 1 | " + replaceWith);


            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream(filePath);
            fileOut.write(inputStr.getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }
}







