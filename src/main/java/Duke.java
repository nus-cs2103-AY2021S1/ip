import java.io.IOException;
import java.util.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileWriter;



public class Duke {
    //functions to edit file
    static void todoToFile(String path, ArrayList<Task> list) throws IOException {
        FileWriter clearFile = new FileWriter(path);
        clearFile.write("");
        clearFile.close();
        FileWriter appendFile = new FileWriter(path, true);
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            //System.out.println((i+1) + ". "+ task.toString());
            appendFile.write(task.toString());
            appendFile.write("\n");
        }
        appendFile.close();
    }



    public static void main(String[] args) throws DukeException, IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        ////Beginning of my own code:
        System.out.println("Hello! Duke at your service. Please name your request.");
        Scanner sc = new Scanner(System.in);

        //Object to store the list
        ArrayList<Task> itemList = new ArrayList<>();

        //create file
        String path = "out/todo.txt";
        File file = new File(path);
        if (file.isFile()) {
        } else {
            file.createNewFile();
        }
        Scanner fileSc = new Scanner(file);

        //load data onto itemList
        while (fileSc.hasNextLine()) {
            Task newItem;
            String taskString = fileSc.nextLine();
            if (taskString.startsWith("[T]")) {
                String name = taskString.substring(8);
                if (taskString.charAt(5) == '\u2713') {
                    newItem = new TODO(name, Task.Status.DONE);
                } else {
                    newItem = new TODO(name, Task.Status.PENDING);
                }
                itemList.add(newItem);
            } else if (taskString.startsWith("[D]")) {
                String name = taskString.split(" by: ")[0].substring(8);
                String dueDate = taskString.split(" by: ")[1];
                if (taskString.charAt(5) == '\u2713') {
                    newItem = new Deadline(name, Task.Status.DONE,dueDate);
                } else {
                    newItem = new Deadline(name, Task.Status.PENDING,dueDate);
                }
                itemList.add(newItem);
            } else if (taskString.startsWith("[E]")){
                //System.out.println(taskString.split(" at: ").length);
                String name = taskString.split(" at: ")[0].substring(8);
                String dueDate = taskString.split(" at: ")[1];
                if (taskString.charAt(5) == '\u2713') {
                    newItem = new Event(name, Task.Status.DONE,dueDate);
                } else {
                    newItem = new Event(name, Task.Status.PENDING,dueDate);
                }
                itemList.add(newItem);
            } else {
                System.out.println("error");
            }
        }



        while (sc.hasNextLine()) {
            String userMessage = sc.nextLine();


            //add items to list
            if (!userMessage.equals("bye") && !userMessage.equals("list")
                    && !userMessage.contains("done") && !userMessage.contains("delete")) {
                Task newItem;
                if (userMessage.startsWith("todo")) {
                    String name = userMessage.substring(5);
                    if (!name.isEmpty() && !name.isBlank()) {
                        newItem = new TODO(name, Task.Status.PENDING);
                    } else {
                        throw new DukeException("Oops, tasks cannot be empty");
                    }
                } else if (userMessage.startsWith("deadline")) {
                    if (!userMessage.contains("/by")) {
                        throw new DukeException("Sorry, incorrect format for Deadlines. \n Please specify a Due Date " +
                                "(and task name)");
                    } else {
                        String name = userMessage.split("/by")[0].substring(9);
                        if (name.isEmpty() || name.isBlank()) {
                            throw new DukeException("Oops, tasks cannot be empty");
                        }
                        String dueDate = userMessage.split("/by")[1];
                        newItem = new Deadline(name, Task.Status.PENDING,dueDate);
                    }
                } else if (userMessage.startsWith("event")) {
                    if (!userMessage.contains("/at")) {
                        throw new DukeException("Sorry, incorrect format for Events. \n Please specify a time " +
                                "(and task name)");
                    } else {
                        String name = userMessage.split("/at")[0].substring(6);
                        if (name.isEmpty() || name.isBlank()) {
                            throw new DukeException("Oops, tasks cannot be empty");
                        }
                        String time = userMessage.split("/at")[1];
                        newItem = new Event(name, Task.Status.PENDING,time);
                    }
                } else {
                    throw new DukeException("Sorry, I do not understand this command");
                }
                itemList.add(newItem);
                Duke.todoToFile(path, itemList);
                System.out.println("new task added: " + newItem.toString());
                System.out.println("You now have " + itemList.size() + " tasks in your list!");
            }

            //list down the contents in the list
            if (userMessage.equals("list")) {
                System.out.println("Here is your list: ");
                for (int i = 0; i < itemList.size(); i++) {
                    Task task = itemList.get(i);
                    System.out.println((i+1) + ". "+ task.toString());
                }
            }


            //mark something as done
            if (userMessage.contains("done")) {
                int index = Character.getNumericValue(userMessage.charAt(5)) - 1;
                Task task = itemList.get(index);
                task.markAsDone();
                Duke.todoToFile(path, itemList);
                System.out.println("Good job! You have finished this task!");
                System.out.println(task.toString());
            }

            //delete task
            if (userMessage.contains("delete")) {
                int index = Character.getNumericValue(userMessage.charAt(7)) - 1;
                Task task = itemList.get(index);
                itemList.remove(index);
                Duke.todoToFile(path, itemList);
                System.out.println("I have deleted this task for you: ");
                System.out.println(task.toString());
                System.out.println("You now have " + itemList.size() + " tasks in your list!");
            }

            //exit
            if (userMessage.equals("bye")) {
                System.out.println("Bye! Nice serving you. Hope to see you again soon! :D");
                break;
            }
        }


    }
}
