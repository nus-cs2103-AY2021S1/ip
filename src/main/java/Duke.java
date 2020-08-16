import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Duke {
    public static void main(String[] args)  {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ \uD83D\uDD34 \uD83D\uDD34 \\\n"
                + "| |_| | |_| |   <  \\__/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm\n" + logo);

        System.out.println("\nWhat can I do for you?");

        ArrayList<Task> inputStore = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            String[] splitString = userInput.split(" ");
            String returnString = "";
            switch(splitString[0]) {
                case "bye" :
                    returnString = "\u263A Bye. Hope to see you again soon!";
                    break;

                case "list" :
                    returnString = "\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0" +
                                    "\n\t Here are the tasks in your list:";
                    for (int i = 0; i < inputStore.size(); i++) {
                        returnString += "\n\t " + (i + 1) + "." +  inputStore.get(i).toString();
                    }
                    returnString += "\n\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0";
                    break;

                case "todo" :
                    String todo =  Arrays.stream(splitString).skip(1).collect(Collectors.joining(" "));
                    if (todo.equals("")) {
                        returnString = "\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0" +
                                "\n\t ☹ OOPS!!! The description of a todo cannot be empty. " +
                                "\n\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0";
                    } else {
                        Todo newTodo = new Todo(todo);
                        inputStore.add(newTodo);
                        returnString = "\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0" +
                                "\n\t Got it. I've added this task: " + "\n\t  " + newTodo.toString() +
                                "\n\t Now you have " + inputStore.size() + " tasks in the list." +
                                "\n\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0";
                    }

                    break;

                case "deadline" :
                    String taskName =  Arrays.stream(splitString).skip(1).takeWhile(e -> !e.equals("/by")).collect(Collectors.joining(" "));
                    if (taskName.equals("")) {
                        returnString = "\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0" +
                                "\n\t ☹ OOPS!!! The description of a deadline task cannot be empty. " +
                                "\n\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0";
                    } else {
                        String deadline = Arrays.stream(splitString).dropWhile(e -> !e.equals("/by")).skip(1).collect(Collectors.joining(" "));
                        if (deadline.equals("")) {
                            returnString = "\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0" +
                                    "\n\t ☹ OOPS!!! The deadline of a deadline task cannot be empty. " +
                                    "\n\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0";
                        } else {
                            Deadline newDeadline = new Deadline(taskName, deadline);
                            inputStore.add(newDeadline);
                            returnString = "\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0" +
                                    "\n\t Got it. I've added this task: " + "\n\t  " + newDeadline.toString() +
                                    "\n\t Now you have " + inputStore.size() + " tasks in the list." +
                                    "\n\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0";
                        }
                    }
                    break;

                case "event" :
                    String eventName =  Arrays.stream(splitString).skip(1).takeWhile(e -> !e.equals("/at")).collect(Collectors.joining(" "));
                    if (eventName.equals("")) {
                        returnString = "\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0" +
                                "\n\t ☹ OOPS!!! The description of an event task cannot be empty. " +
                                "\n\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0";
                    } else {
                        String eventTime = Arrays.stream(splitString).dropWhile(e -> !e.equals("/at")).skip(1).collect(Collectors.joining(" "));
                        if (eventTime.equals("")) {
                            returnString = "\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0" +
                                    "\n\t ☹ OOPS!!! The time of an event task cannot be empty. " +
                                    "\n\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0";
                        } else {
                            Event newEvent = new Event(eventName, eventTime);
                            inputStore.add(newEvent);
                            returnString = "\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0" +
                                    "\n\t Got it. I've added this task: " + "\n\t  " + newEvent.toString() +
                                    "\n\t Now you have " + inputStore.size() + " tasks in the list." +
                                    "\n\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0";
                        }
                    }
                    break;

                case "done" :
                    Task task = inputStore.get(Integer.parseInt(splitString[1]) - 1);
                    task.markDone();
                    returnString = "\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0" +
                            "\n\t Nice! I've marked this task as done: " +
                            "\n\t   " + task.toString() +
                            "\n\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0";
                    break;

                case "delete" :
                    Task deletedTask = inputStore.get(Integer.parseInt(splitString[1]) - 1);
                    inputStore.remove(Integer.parseInt(splitString[1]) - 1);
                    returnString = "\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0" +
                            "\n\t Noted. I've removed this task: " +
                            "\n\t   " + deletedTask.toString() +
                            "\n\t Now you have " +  inputStore.size()  + " tasks in the list." +
                            "\n\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0";
                    break;

                default :
                    returnString = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

            }

            System.out.println(returnString);

        }
    }
}
