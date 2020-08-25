import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Greet();

        ArrayList<Task> TaskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String file = "data/duke.txt";

<<<<<<< HEAD
       while (!input.equals("bye")) {

           String errorMessage = DukeExceptionHandler.handleException(input);
           if (errorMessage != null) {
               System.out.println(errorMessage);
           }
           else {
               if (!input.contains("done") && !input.contains("list")) {
                   if (input.contains("todo")) {
                       String[] textArray = input.split(" ", 2);
                       Todo todo = new Todo(textArray[1]);
                       TaskList.add(todo);
                       add(todo, TaskList);
                   }
                   if (input.contains("deadline")) {
                       String[] textArray = input.split(" ", 2);
                       Deadline deadline = new Deadline(textArray[1]);
                       TaskList.add(deadline);
                       add(deadline, TaskList);
                   }
                   if (input.contains("event")) {
                       String[] textArray = input.split(" ", 2);
                       Event event = new Event(textArray[1]);
                       TaskList.add(event);
                       add(event, TaskList);
                   }
               } else if (input.equals("list")) {
                   printList(TaskList);
               } else if (input.contains("done")) {
                   String[] textArray = input.split(" ", 2);
                   int taskNum = Integer.parseInt(textArray[1]);
                   Task doneTask = TaskList.get(taskNum - 1);
                   doneTask.markAsDone();
                   System.out.println("Nice! I've marked this task as done:\n" + doneTask);
               }
           }
           input = sc.nextLine();
       }
       Bye();
=======
        StringBuffer buffer = new StringBuffer();
        String fileContent = buffer.toString();

        try {
            while (!input.equals("bye")) {

                String errorMessage = DukeExceptionHandler.handleException(input);
                if (errorMessage != null) {
                    System.out.println(errorMessage);
                } else {
                    if (!input.contains("done") && !input.contains("list")) {
                        if (input.contains("todo")) {
                            String[] textArray = input.split(" ", 2);
                            Todo todo = new Todo(textArray[1]);
                            TaskList.add(todo);
                            add(todo, TaskList);

                            writeToFile(file, new Todo(textArray[1]));
                        }
                        if (input.contains("deadline")) {
                            String[] textArray = input.split(" ", 2);
                            Deadline deadline = new Deadline(textArray[1]);
                            TaskList.add(deadline);
                            add(deadline, TaskList);

                            writeToFile(file, new Deadline(textArray[1]));
                        }
                        if (input.contains("event")) {
                            String[] textArray = input.split(" ", 2);
                            Event event = new Event(textArray[1]);
                            TaskList.add(event);
                            add(event, TaskList);
                            writeToFile(file, new Event(textArray[1]));
                        }
                        if (input.contains("delete")) {
                            String[] textArray = input.split(" ", 2);
                            int taskNum = Integer.parseInt(textArray[1]);
                            Task deletedTask = TaskList.get(taskNum - 1);
                            TaskList.remove(deletedTask);
                            System.out.println("Noted. I've removed this task:\n"
                                    + deletedTask + "\nNow you have " + TaskList.size()
                                    + " tasks in the list");


                        }
                    } else if (input.equals("list")) {
                        printList(TaskList);
                    } else if (input.contains("done")) {
                        String[] textArray = input.split(" ", 2);
                        int taskNum = Integer.parseInt(textArray[1]);
                        Task doneTask = TaskList.get(taskNum - 1);
                        doneTask.markAsDone();
                        System.out.println("Nice! I've marked this task as done:\n" + doneTask);

                        replaceDone(file, doneTask.getDescription());
                    }
                }
                input = sc.nextLine();
            }
            Bye();
        }
       //put 2 catch blocks here
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
>>>>>>> 44cfda02875a8be99bebc91fe334f7fe956e196a

    }
    public static void Greet() {
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void printList(ArrayList<Task> TaskList) {
        System.out.println("    ____________________________________________________________");
        for (Task task : TaskList) {
            System.out.println("     " + (TaskList.indexOf(task) + 1) + "." + task);
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void add(Task task, ArrayList<Task> TaskList) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:\n" + "       " + task);
        System.out.println("     Now you have " + TaskList.size() + " tasks in the list");
        System.out.println("    ____________________________________________________________");
    }

    public static void Bye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    public static void writeToFile(String filePath, Task task) throws IOException, FileNotFoundException {
        FileWriter fw = new FileWriter(filePath, true);
        if (task instanceof Todo) {
            fw.write(((Todo) task).getIdentifier() + " | " + (task.getDone() ? 1 : 0) + " | " + task.getDescription() + "\n");
        }
        else if (task instanceof Deadline) {
            fw.write(((Deadline) task).getIdentifier() + " | " + (task.getDone() ? 1 : 0) + " | " + task.getDescription() + " | " + ((Deadline) task).getDate() + "\n");
        }
        else {
            fw.write(((Event) task).getIdentifier() + " | " + (task.getDone() ? 1 : 0) + " | " + task.getDescription() + " | " + ((Event) task).getDate() + "\n");
        }
        fw.close();
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
