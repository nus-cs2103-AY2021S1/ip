import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.File;

public class Bill {
    
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    
    private static final String horizontal_line = "------------------------------------------";
    private static final List<Task> list_of_Content = new ArrayList<>();
    private static final String filePath = "./data/bill.txt";
    
    private static void welcome_message() {
        System.out.println("Hello from Bill \n" + logo);
        System.out.println(horizontal_line);
        System.out.println("Hello! I'm Bill ");
        System.out.println("What Can I do for you? ");
        System.out.println(horizontal_line);
    }
    
    private static void session_end() {
        System.out.println(horizontal_line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontal_line);
    }
    
    private static void print_the_list() {
        
        int no_of_tasks = list_of_Content.size();

        System.out.println(horizontal_line);
        if (no_of_tasks == 0) {
            System.out.println("There is no tasks in the list, please add some tasks first ");
        } else {
            for (int i = 0; i < list_of_Content.size(); i = i + 1) {
                String counter = Integer.toString(i + 1) + ". ";
                System.out.println(counter + list_of_Content.get(i));
            }
        }
        System.out.println(horizontal_line);
    }
    
    public static void main(String[] args) throws IOException {
        
        welcome_message();
           
        fileManager openFile = new fileManager(filePath);
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            try {
                String input = sc.nextLine();
                if (input.equals("bye")) {
                    session_end();
                    sc.close();
                    break;
                } else {
                    
                    int length = input.length();
                    String[] isDone = input.split(" ");
                    String firstChar = isDone[0];
                    if (input.equals("list")) {
                        System.out.println("Here are the tasks in your list: ");
                        print_the_list();
                    } else if (firstChar.equals("done")) {

                        if (isDone.length == 1) {
                            throw new InvalidDoneException("OOPS!!! please provide me with the task to be marked as done");
                        }
                        
                        if (isDone.length > 2) {
                            throw new InvalidDoneException("OOPS!!! I can only mark one task as done at a time");
                        }
                        String lastChar = isDone[isDone.length - 1];
                        int index = Integer.parseInt(lastChar);
                        
                        if (index > list_of_Content.size()) {
                            int no_of_tasks = list_of_Content.size();
                            throw new InvalidException("There are only " + no_of_tasks + " tasks in the list; Please restate" +
                                    " the task to be mark as done");
                        }
                        
                        Task current = list_of_Content.get(index - 1);
                        current.set_Task_As_Done();
                        System.out.println(horizontal_line);
                        System.out.println("Nice! I've marked this task as done: ");
                        System.out.println(current);
                        System.out.println(horizontal_line);

                    } else if (firstChar.equals("delete")) {
                        
                        if (isDone.length == 1) {
                            throw new InvalidDeleteException("OOPS!!! please provide me with the task to be deleted");
                        }

                        if (isDone.length > 2) {
                            throw new InvalidDeleteException("OOPS!!! I can only delete one task at a time");
                        }

                        String lastChar = isDone[isDone.length - 1];
                        int index = Integer.parseInt(lastChar);
//                        System.out.println(index);
//                        System.out.println(isDone.length);

                        if (index > list_of_Content.size()) {
                            int no_of_tasks = list_of_Content.size();
                            throw new InvalidException("There are only " + no_of_tasks + " tasks in the list; Please restate" +
                                    " the task to be deleted");
                        }
                        Task toBeRemove = list_of_Content.get(index - 1);
                        list_of_Content.remove(index - 1);
                        int no_of_tasks_left = list_of_Content.size();
                        System.out.println(horizontal_line);
                        System.out.println(" Noted. I've removed this task:  ");
                        System.out.println(toBeRemove);
                        System.out.println(" Now you have " + no_of_tasks_left + " tasks in the list. ");
                        System.out.println(horizontal_line);
                    } else {
                        if (firstChar.equals("todo")) {
                            
                            if (isDone.length == 1) {
                                throw new InvalidTodoException("OOPS!!! The description of a todo cannot be empty." +
                                        "please provide me with the task to be completed");
                            }
                            ToDo new_task = new ToDo(input.substring(firstChar.length() + 1));
                            list_of_Content.add(new_task);
                            System.out.println(horizontal_line);
                            System.out.println("Got it. I've added this task: ");
                            System.out.println(new_task);
                            System.out.println("Now you have " + list_of_Content.size() + " tasks in the list.");
                            System.out.println(horizontal_line);
                        } else if (firstChar.equals("deadline")) {

                            if (isDone.length == 1) {
                                throw new InvalidDeadlineException("OOPS!!! The description of a task cannot be empty." +
                                        "please provide me with the name and duration of the task to be completed");
                            }

                            if (input.split("/by").length < 2) {
                                throw new InvalidDeadlineException("OOPS!!! please provide me with the" +
                                        " duration of the task to be completed");
                            }
                            int index = input.indexOf("/by");
                            String task = input.substring(firstChar.length() + 1, index);
                            String time = input.substring(index + 4);
                            Deadline deadline = new Deadline(task, time);
                            list_of_Content.add(deadline);
                            System.out.println(horizontal_line);
                            System.out.println("Got it. I've added this task: ");
                            System.out.println(deadline);
                            System.out.println("Now you have " + list_of_Content.size() + " tasks in the list.");
                            System.out.println(horizontal_line);
                        } else if (firstChar.equals("event")) {

                            if (isDone.length == 1) {
                                throw new InvalidDeadlineException("OOPS!!! The description of a task cannot be empty." +
                                        "please provide me with the name and time of the task");
                            }

                            if (input.split("/at").length < 2) {
                                throw new InvalidDeadlineException("OOPS!!! please provide me with the" +
                                        " time of the task to be completed");
                            }
                            
                            int index = input.indexOf("/at");
                            String task = input.substring(firstChar.length() + 1, index);
                            String duration = input.substring(index + 4);
                            Event event = new Event(task, duration);
                            list_of_Content.add(event);
                            System.out.println(horizontal_line);
                            System.out.println("Got it. I've added this task: ");
                            System.out.println(event);
                            System.out.println("Now you have " + list_of_Content.size() + " tasks in the list.");
                            System.out.println(horizontal_line);
                        } else {
//                            Task task = new Task(input);
//                            list_of_Content.add(task);
//                            System.out.println(horizontal_line);
//                            System.out.println("Got it. I've added this task: " + task);
//                            System.out.println("Now you have " + list_of_Content.size() + " tasks in the list.");
//                            System.out.println(horizontal_line);
                            throw new InvalidException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }
                    }
                    
                }
                
            } catch (InvalidException e){

                System.out.println(horizontal_line);
                System.out.println(e.getMessage());
                System.out.println(horizontal_line);
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        openFile.write(list_of_Content);
    }
}
