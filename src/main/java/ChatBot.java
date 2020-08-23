package main.java;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;

public class ChatBot {
    public enum Action {
        BYE("bye"), LIST("list"), DONE("done"), TODO("todo"), DEADLINE("deadline"), EVENT("event"), DELETE("delete");
        public String value;
        Action(String value) {
            this.value = value;
        }
    }
    
    public void run() throws FileNotFoundException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    ______________________________________");
        System.out.println("        Hello! I'm Duke");
        System.out.println("        What can I do for you?");
        System.out.println("    ______________________________________");
        
        IOHardDisk hardDiskDatabase = new IOHardDisk();

        Scanner sc = new Scanner(System.in);
        List<Task> list = hardDiskDatabase.getAllTasksFromHardDisk();

        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals(Action.BYE.value)) {
                System.out.println("    _______________________________________________________________________________");
                System.out.println("        Bye. Hope to see you again soon!");
                System.out.println("    _______________________________________________________________________________");
                break;

            } else if (userInput.equals(Action.LIST.value)) {
                System.out.println("    _______________________________________________________________________________");
                System.out.println("        Here are the tasks in your list:");
                for(int i=0; i<list.size(); i++) {
                    System.out.println("            " + (i+1) + "." + list.get(i));
                }
                System.out.println("    _______________________________________________________________________________");

            }  else if (userInput.startsWith(Action.DONE.value)) {
                try {
                    int taskPosition = Integer.parseInt(userInput.substring(5));
                    list.get(taskPosition - 1).markAsDone();
                    System.out.println("    _______________________________________________________________________________");
                    System.out.println("        Nice! I've marked this task as done:");
                    System.out.println("            " + list.get(taskPosition - 1));
                    System.out.println("    _______________________________________________________________________________");
                    hardDiskDatabase.saveTasksInHardDisk(list);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("    _______________________________________________________________________________");
                    System.out.println("        ☹ OOPS!!! The description of a done cannot be empty or in wrong format");
                    System.out.println("    _______________________________________________________________________________");
                } catch (NumberFormatException e) {
                    System.out.println("    _______________________________________________________________________________");
                    System.out.println("        ☹ OOPS!!! The description of a done cannot be empty or in wrong format");
                    System.out.println("    _______________________________________________________________________________");
                }

            } else if (userInput.startsWith(Action.TODO.value)) {
                try {
                    String description = userInput.trim().substring(5);
                    Task toDo = new Todo(description);
                    list.add(toDo);
                    System.out.println("    _______________________________________________________________________________");
                    System.out.println("        Got it. I've added this task:");
                    System.out.println("            " + toDo);
                    System.out.println("        Now you have " + list.size() + " tasks in the list.");
                    System.out.println("    _______________________________________________________________________________");
                    hardDiskDatabase.saveTasksInHardDisk(list);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("    _______________________________________________________________________________");
                    System.out.println("        ☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println("    _______________________________________________________________________________");
                }

            } else if (userInput.startsWith(Action.DEADLINE.value)) {
                try {
                    int spacePosition = userInput.indexOf(" ");
                    int keywordPosition = userInput.indexOf("/by");
                    String description = userInput.substring(spacePosition + 1, keywordPosition - 1);
                    String by = userInput.substring(keywordPosition + 4);
                    Task deadline = new Deadline(description, by);
                    list.add(deadline);
                    System.out.println("    _______________________________________________________________________________");
                    System.out.println("        Got it. I've added this task:");
                    System.out.println("            " + deadline);
                    System.out.println("        Now you have " + list.size() + " tasks in the list.");
                    System.out.println("    _______________________________________________________________________________");
                    hardDiskDatabase.saveTasksInHardDisk(list);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("    _______________________________________________________________________________");
                    System.out.println("        ☹ OOPS!!! The description of a deadline cannot be empty or in wrong format.");
                    System.out.println("    _______________________________________________________________________________");
                }
            } else if (userInput.startsWith(Action.EVENT.value)) {
                try {
                    int spacePosition = userInput.indexOf(" ");
                    int keywordPosition = userInput.indexOf("/at");
                    String description = userInput.substring(spacePosition + 1, keywordPosition - 1);
                    String at = userInput.substring(keywordPosition + 4);
                    Task event = new Event(description, at);
                    list.add(event);
                    System.out.println("    _______________________________________________________________________________");
                    System.out.println("        Got it. I've added this task:");
                    System.out.println("            " + event);
                    System.out.println("        Now you have " + list.size() + " tasks in the list.");
                    System.out.println("    _______________________________________________________________________________");
                    hardDiskDatabase.saveTasksInHardDisk(list);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("    _______________________________________________________________________________");
                    System.out.println("        ☹ OOPS!!! The description of an event cannot be empty or in wrong format.");
                    System.out.println("    _______________________________________________________________________________");
                }

            } else if (userInput.startsWith(Action.DELETE.value)) {
                try {
                    int taskPosition = Integer.parseInt(userInput.substring(7));
                    System.out.println("    _______________________________________________________________________________");
                    System.out.println("        Noted. I've removed this task:");
                    System.out.println("            " + list.remove(taskPosition - 1));
                    System.out.println("        Now you have " + list.size() + " tasks in the list.");
                    System.out.println("    _______________________________________________________________________________");
                    hardDiskDatabase.saveTasksInHardDisk(list);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("    _______________________________________________________________________________");
                    System.out.println("        ☹ OOPS!!! The description of a delete cannot be empty or in wrong format.");
                    System.out.println("    _______________________________________________________________________________");
                }

            } else if (userInput.startsWith("filter")) {
                String[] inputArray = userInput.split(" ");
                QueryOnTasks queryOnTasks = new QueryOnTasks();
                if (inputArray[1].equals("date")) {
                    List<Task> queryList = queryOnTasks.filterByDate(list,inputArray[2]);
                    System.out.println("    _______________________________________________________________________________");
                    System.out.println("        Here are the tasks in your query list:");
                    for(int i=0; i<queryList.size(); i++) {
                        System.out.println("            " + (i+1) + "." + queryList.get(i));
                    }
                    System.out.println("    _______________________________________________________________________________");
                } else if (inputArray[1].equals("month")) {
                    List<Task> queryList = queryOnTasks.filterByMonth(list,inputArray[2]);
                    System.out.println("    _______________________________________________________________________________");
                    System.out.println("        Here are the tasks in your query list:");
                    for(int i=0; i<queryList.size(); i++) {
                        System.out.println("            " + (i+1) + "." + queryList.get(i));
                    }
                    System.out.println("    _______________________________________________________________________________");
                } else if (inputArray[1].equals("year")) {
                    List<Task> queryList = queryOnTasks.filterByYear(list,inputArray[2]);
                    System.out.println("    _______________________________________________________________________________");
                    System.out.println("        Here are the tasks in your query list:");
                    for(int i=0; i<queryList.size(); i++) {
                        System.out.println("            " + (i+1) + "." + queryList.get(i));
                    }
                    System.out.println("    _______________________________________________________________________________");
                }
                
            } else {
                System.out.println("    _______________________________________________________________________________");
                System.out.println("        ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("    _______________________________________________________________________________");
            }
        }
    }
}
