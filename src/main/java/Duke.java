import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files


public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner nextCommand = new Scanner(System.in);
        Duke.checkDirectory();
        Duke.createFile();
        ArrayList<Task> tasks = Duke.readFile();
        while (true) {
            String command = nextCommand.nextLine();
            boolean taskAdded = false;
            try {
                if (command.equals("bye")) {
                    break;
                }
                //if command is list
                if (command.equals("list")) {
                    for (int i = 0; i < tasks.size(); i++) {
                        Task nextTask = tasks.get(i);
                        System.out.println((i + 1) + ". " + nextTask.toString());
                    }
                    //else if command is done
                } else if (command.startsWith("done")){
                    int completedIndex = Character.getNumericValue(command.charAt(5));
                    Task currentTask = tasks.get(completedIndex - 1);
                    currentTask.setComplete(true);
                    System.out.println("Nice! I've marked this task as done: [✓] " + currentTask.getTaskName());

                    //delete task
                } else if (command.startsWith("delete")) {
                    int deleteIndex = Character.getNumericValue(command.charAt(7));
                    Task deletedTask = tasks.get(deleteIndex - 1);
                    tasks.remove(deleteIndex - 1);
                    System.out.println("Noted. I've removed this task:" + deletedTask.toString()
                    + "\nNow you have " +  tasks.size() +  " tasks in the list.");
                    //save the tasks in hard disk
                    Duke.writeFile(tasks);
                }
                else {
                    //Add a todo
                    if (command.startsWith("todo")) {
                        try {
                            if (command.length() == 4) {
                                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                            }
                            Task newTask = new Todo(command, false);
                            tasks.add(newTask);
                            taskAdded = true;
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }

                        //event
                    } else if (command.startsWith("event")) {
                        try {
                            if (command.length() == 5) {
                                throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                            }
                            Task newTask = new Event(command, false);
                            tasks.add(newTask);
                            taskAdded = true;
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }

                        //deadline
                    } else if (command.startsWith("deadline")) {
                        try {
                            if (command.length() == 8) {
                                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                            }
                            Task newTask = new Deadline(command, false);
                            tasks.add(newTask);
                            taskAdded = true;
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }

                    } else {
                        throw new DukeException("Sorry, I don't know what that means");
                    }
            }
               if (taskAdded) {
                   Task addedTask = tasks.get(tasks.size() - 1);
                   System.out.println("Got it. I've added this task: \n" + addedTask.toString() +
                           "\nNow you have " +  tasks.size() +  " tasks in the list.");
                   //save the tasks in hard disk
                   Duke.writeFile(tasks);
               }

            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        }

        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void checkDirectory() {
        String PATH = "./data";
        File directory = new File(PATH);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }
    public static void createFile() {
        try {

            File myObj = new File("data/filename.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            }
        } catch (IOException e) {
            System.out.println("An error has occured");
            e.printStackTrace();
        }
    }
    public static ArrayList<Task> readFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File myObj = new File("data/filename.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] taskBreakdown = data.split("\\| ");
                String taskType = taskBreakdown[0].trim();
                boolean isTaskCompleted = taskBreakdown[1].trim().equals("1");
                String taskMessage = taskBreakdown[2].trim();
                String taskDate = taskBreakdown.length > 3 ? taskBreakdown[3].trim(): "";

                if (taskType.equals("T")) {
                    tasks.add(new Todo("todo " + taskMessage, isTaskCompleted));
                }
                else if (taskType.equals("E")) {
                    tasks.add(new Event("event " + taskMessage + "/at " + taskDate, isTaskCompleted));
                }
                else if (taskType.equals("D")) {
                    tasks.add(new Deadline("deadline" + taskMessage + "/by " + taskDate, isTaskCompleted));
                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred. File is not found.");
        }
        return tasks;
    }
    public static void writeFile(ArrayList<Task> tasks) {
        try {
            FileWriter myWriter = new FileWriter("data/filename.txt");
            for (int i = 0; i < tasks.size(); i++) {
                Task nextTask = tasks.get(i);
                myWriter.write(( nextTask.toFormattedString()) + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
