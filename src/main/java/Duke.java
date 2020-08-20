package main.java;

import java.util.Scanner;

public class Duke {

    public static void greeting() {
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";

        String underscore = "    ____________________________________________________________";

        System.out.println(underscore);
        System.out.println(logo + "\n" + "    Hello! I'm Duke" + "\n" + "    What can I do for you?");
        System.out.println(underscore);
    }

    public static void commandTask(String input, TaskList newList) throws NoDescriptionException, InvalidCommandException, InvalidTaskException {

        String[] splitString = input.split(" ");

        if (splitString[0].equals("todo")) {
            if(splitString.length == 1) {
                throw new NoDescriptionException("    ☹ OOPS!!! The description of a todo cannot be empty.");
            } else {
                String info = splitString[1];
                Todos newTodo = new Todos(info);
                newList.addTask(newTodo);
            }

        } else if (splitString[0].equals("deadline")) {
            if(splitString.length == 1) {
                throw new NoDescriptionException("    ☹ OOPS!!! The description of a deadline cannot be empty.");
            } else {
                String info = splitString[1];
                String[] information = input.split("/by ");
                if (information.length == 1) {
                    throw new InvalidTaskException("    ☹ OOPS!!! Please specify the deadline time");
                } else {
                    String description = information[0];
                    String by = information[1];
                    Deadlines newDeadline = new Deadlines(description, by);

                    newList.addTask(newDeadline);
                }
            }

        } else if (splitString[0].equals("event")) {
            if(splitString.length == 1) {
                throw new NoDescriptionException("    ☹ OOPS!!! The description of an event cannot be empty.");
            } else {
                String info = splitString[1];
                String[] information = input.split("/at ");

                if (information.length == 1) {
                    throw new InvalidTaskException("    ☹ OOPS!!! Please state the event time");
                } else {
                    String description = information[0];
                    String at = information[1];
                    Events newEvent = new Events(description, at);

                    newList.addTask(newEvent);
                }
            }

        } else {
            throw new InvalidCommandException("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void commandHandler(String input, TaskList newList) throws InvalidDoneDeleteException {
        String[] splitInput = input.split(" ");

        if (splitInput[0].equals("list")) {
            newList.listTasks();

        } else if (splitInput[0].equals("done")) {
            if (splitInput.length == 1) {
                throw new InvalidDoneDeleteException("    ☹ OOPS!!! Please specify which task is done");
            } else {
                String taskNumberString = splitInput[1];
                try {
                    int taskNumberInt = Integer.parseInt(taskNumberString) - 1;

                    if (taskNumberInt + 1 > newList.getLength()) {
                        throw new InvalidDoneDeleteException("    ☹ OOPS!!! Your task number is out of bounds");
                    } else {
                        newList.get(taskNumberInt).markDone();
                    }
                } catch (NumberFormatException e) {
                    throw new InvalidDoneDeleteException("    ☹ OOPS!!! Invalid task number.");

                }
            }
        } else if (splitInput[0].equals("delete")) {
            if (splitInput.length == 1) {
                throw new InvalidDoneDeleteException("    ☹ OOPS!!! Please specify which task you want to delete");
            } else {
                String taskNumberString = splitInput[1];
                try {
                    int taskNumberInt = Integer.parseInt(taskNumberString) - 1;

                    if (taskNumberInt + 1 > newList.getLength()) {
                        throw new InvalidDoneDeleteException("    ☹ OOPS!!! Your task number is out of bounds");
                    } else {
                        newList.removeTask(taskNumberInt);
                    }
                } catch (NumberFormatException e) {
                    throw new InvalidDoneDeleteException("    ☹ OOPS!!! Invalid task number.");
                }
            }
        } else {
            try {
                commandTask(input,newList);
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            } catch (NoDescriptionException e) {
                System.out.println(e.getMessage());
            } catch (InvalidTaskException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public static void main(String[] args) {
        String underscore = "    ____________________________________________________________";
        greeting();

        Scanner sc = new Scanner(System.in);
        TaskList newList = new TaskList();

        while(sc.hasNext()) {
            String input = sc.nextLine();
            System.out.println(underscore);
            try {
                String[] splitInput = input.split(" ");
                if (splitInput[0].equals("bye")) {
                    System.out.println("     Bye. Hope to see you again soon!");
                    return;
                } else {
                    commandHandler(input, newList);
                }
            } catch (InvalidDoneDeleteException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(underscore);
        }
        sc.close();
    }
}

