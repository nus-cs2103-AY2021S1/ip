

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;





public class Duke {

    public interface Printable{
        String print();
    }

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        List<Task> tasks = FileSaver.loadFile();
        System.out.println("Hello from\n" + logo);
        Printable greeting = () -> "Hello! I'm Duke \nWhat can I do for you?";
        Printable goodbye = () -> "Bye. Hope to see you again soon!";
        speak(greeting);
        Printable input;
        Scanner sc = new Scanner(System.in);

        do {
            try {
                input = getUserInput(sc);
                String command = input.print();
                if (command.toLowerCase().equals("bye")) {
                    FileSaver.writeToFile(tasks);
                    speak(goodbye);
                    break;
                } else if (command.toLowerCase().equals("list")) {
                    listTasks(tasks);
                } else if (command.split("\\s+")[0].equals("done")) {
                    Task task = tasks.get(Integer.parseInt(command.split("\\s+")[1]) - 1);
                    task.setDone();
                    speak(() -> "Nice! I've marked this task as done:\n[✓] " + task.toString());
                }else if (command.split("\\s+")[0].equals("delete")){
                    if (command.split("\\s+").length != 2) {
                        throw new IllegalUserInputException("PLease specify the correct argument number");
                    }
                    try {
                        int i = Integer.parseInt(command.split("\\s+")[1]);
                        Task.decrementTask();
                        Task task = tasks.get(i-1);
                        tasks.remove(i-1);
                        speak(() -> String.format("Noted. I've removed this task:\n" +
                                "%s%s %s\n" +
                                "Now you have %d tasks in the list.",task.getTaskSymbol(),task.getSymbol(),task.toString(),Task.remainingTasks()));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Please choose a valid task number to delete");
                        continue;
                    }

                } else {
                    storeInput(command, tasks);
                }
            } catch (IllegalArgumentException | IOException e) {
                System.out.println(e);
                continue;
            }
        } while (true);
    }

    public static void speak(Printable printable) {
        System.out.println("------------------------------------------------------");
        System.out.println(printable.print() + "\n");
        System.out.println("------------------------------------------------------");
    }

    public static Printable getUserInput(Scanner sc) {
        return () -> sc.nextLine();
    }

    public static void storeInput(String command, List<Task> tasks) {
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
            String taskSymbol = task.getTaskSymbol();
            String symbol = task.getSymbol();
            String toString = task.toString();
            Printable userReply = () -> String.format("Got it. i've added this task:\n %s%s %s\n" +
                    "Now you have %d tasks in the list.", taskSymbol, symbol, toString, Task.remainingTasks());
            speak(userReply);
        }
        return;
    }

    public static void listTasks (List<Task> tasks) {
        int i;
        StringBuilder sb = new StringBuilder();
        for (i = 0 ; i < tasks.size() ; i++) {
            Task task = tasks.get(i);
            if (i==tasks.size()-1) {
                sb.append(String.format("%d.%s %s",i+1,task.getSymbol(),task));
            } else {
                sb.append(String.format("%d.%s %s\n", i+1,task.getSymbol(), task));
            }
        }
        Printable task = () -> sb.toString();
        speak(task);
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
