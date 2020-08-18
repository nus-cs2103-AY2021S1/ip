import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Duke {
    public static void main(String[] args) throws DukeException {
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
            if (splitString[0].equals("bye")) {
                System.out.println("\u263A Bye. Hope to see you again soon!");
                break;
            }
            switch(splitString[0]) {
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
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty. ");
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
                        throw new DukeException("☹ OOPS!!! The description of a deadline task cannot be empty. ");
                    } else {
                        String deadline = Arrays.stream(splitString).dropWhile(e -> !e.equals("/by")).skip(1).collect(Collectors.joining(" "));
                        if (deadline.equals("")) {
                            throw new DukeException("☹ OOPS!!! The time of a deadline task cannot be empty. Specify time after /by");
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
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty. ");
                    } else {
                        String eventTime = Arrays.stream(splitString).dropWhile(e -> !e.equals("/at")).skip(1).collect(Collectors.joining(" "));
                        if (eventTime.equals("")) {
                            throw new DukeException("☹ OOPS!!! The time of an event cannot be empty. Specify time after /at");
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
                    if (splitString.length <= 1) {
                        throw new DukeException("Oops, please enter the task number that you want to mark done!!");
                    }
                    try {
                        int toBeDoneTask = Integer.parseInt(splitString[1]);
                        if (toBeDoneTask <= 0 || toBeDoneTask > inputStore.size()) {
                            throw new DukeException("Oops, enter a task number that already exists in the list.");
                        } else {
                            Task task = inputStore.get(Integer.parseInt(splitString[1]) - 1);
                            task.markDone();
                            returnString = "\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0" +
                                    "\n\t Nice! I've marked this task as done: " +
                                    "\n\t   " + task.toString() +
                                    "\n\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0";
                        }
                    } catch(NumberFormatException e) {
                        throw new DukeException("Task Number to be marked done must be an Integer!");
                    }
                    break;

                case "delete" :
                    if (splitString.length <= 1) {
                        throw new DukeException("Oops, please enter the task number that you want to delete!");
                    }
                    try {
                        int toBeDeletedTask = Integer.parseInt(splitString[1]);
                        if (toBeDeletedTask <= 0 || toBeDeletedTask > inputStore.size()) {
                            throw new DukeException("Oops, enter a task number that already exists in the list.");
                        } else {
                            Task deletedTask = inputStore.get(toBeDeletedTask - 1);
                            inputStore.remove(toBeDeletedTask - 1);
                            returnString = "\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0" +
                                    "\n\t Noted. I've removed this task: " +
                                    "\n\t   " + deletedTask.toString() +
                                    "\n\t Now you have " + inputStore.size() + " tasks in the list." +
                                    "\n\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0";
                        }
                    }
                    catch(NumberFormatException e) {
                        throw new DukeException("Task Number to be deleted must be an Integer!");
                    }
                    break;

                case "" :
                    throw new DukeException("Please enter something!");

                default :
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    //returnString = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

            }
            System.out.println(returnString);
        }
    }
}
