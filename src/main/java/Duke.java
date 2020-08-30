

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;





public class Duke {
    
    public static void main(String[] args) throws IOException {
        UI userInterface = new UI();
        List<Task> tasks = Storage.loadFile();
        userInterface.greetUser();
        Printable input;
        Scanner sc = new Scanner(System.in);

        do {
            try {
                input = getUserInput(sc);
                String command = input.print();
                if (command.toLowerCase().equals("bye")) {
                    Storage.writeToFile(tasks);
                    userInterface.goodbye();
                    break;
                } else if (command.toLowerCase().equals("list")) {
                    userInterface.listTasks(tasks);
                } else if (command.split("\\s+")[0].equals("done")) {
                    Task task = tasks.get(Integer.parseInt(command.split("\\s+")[1]) - 1);
                    task.setDone();
                    userInterface.taskCompletedMessage(task);
                }else if (command.split("\\s+")[0].equals("delete")){
                    if (command.split("\\s+").length != 2) {
                        throw new IllegalUserInputException("PLease specify the correct argument number");
                    }
                    try {
                        int i = Integer.parseInt(command.split("\\s+")[1]);
                        Task.decrementTask();
                        Task task = tasks.get(i-1);
                        tasks.remove(i-1);
                        userInterface.taskDeletedMessage(task);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Please choose a valid task number to delete");
                        continue;
                    }

                } else {
                    storeInput(command, tasks, userInterface);
                }
            } catch (IllegalArgumentException | IOException e) {
                System.out.println(e);
                continue;
            }
        } while (true);
    }
    

    public static Printable getUserInput(Scanner sc) {
        return () -> sc.nextLine();
    }

    public static void storeInput(String command, List<Task> tasks,UI userInterface) {
        String cmd1 = command.split("\\s+")[0];
        String cmd2 ;
        Task task =null;
        if (cmd1.equals("deadline")) {
            cmd2 =  command.substring(cmd1.length()+1);
            try {
                task = parseDeadline(cmd2);
            }catch (Exception e) {
               System.out.println( "Please specify time in format YYYY-MM-DD hh:hh \n for instance : 2019-10-15 18:00");
            }
        } else if (cmd1.equals("todo")) {
            if (command.split("\\s+").length == 1) {
                throw new IllegalUserInputException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            cmd2 =  command.substring(cmd1.length()+1);
            task = new Todo(cmd2,false);
        } else if (cmd1.equals("event")) {
            cmd2 =  command.substring(cmd1.length()+1);
            task = parseEvent(cmd2);
        } else {
            throw new IllegalUserInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        if (task != null) {
            tasks.add(task);
            userInterface.taskAddedMessage(task);
        }
        return;
    }
    

     private static Deadline parseDeadline(String input) {
        String[] arr = input.split("\\s+");
        String description="";
        String deadline="";
        boolean flag = false;
        for (int i = 0; i<arr.length;i++) {
            if (arr[i].equals("/by")) {
                flag = true;
                continue;
            }
            if (!flag) {
                description += arr[i] + " ";
            } else {
                deadline += arr[i] + " ";
            }
        }

        return new Deadline (description.substring(0,description.length()-1),false,deadline.substring(0,deadline.length()-1));
     }

     private static Event parseEvent (String input) {
         String[] arr = input.split("\\s+");
         String description="";
         String duration="";
         boolean flag = false;
         for (int i = 0; i<arr.length;i++) {
             if (arr[i].equals("/at")) {
                 flag = true;
                 continue;
             }
             if (!flag) {
                 description += arr[i] + " ";
             } else {
                 duration += arr[i] + " ";
             }
         }

         return new Event (description.substring(0,description.length()-1),false,duration.substring(0,duration.length()-1));
     }
}
