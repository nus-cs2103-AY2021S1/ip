import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you today? (type: \"help\" to view list of commands)\n" +
                "=========================================================================");

        Scanner scanner = new Scanner(System.in);
        List<Task> pastInputs = new ArrayList<>();

        boolean terminated = false;

        while (!terminated && scanner.hasNext()) {
            try {
                String userInput = scanner.nextLine();
                //determining user input type via the first word
                String[] splitInput = userInput.split(" ");
                String keyWord = getKeyWord(splitInput[0]);

                if (keyWord.equals("bye")) {
                    terminated = true;
                    System.out.println("Duke says: Goodbye and have a nice day! :D");
                    scanner.close();
                } else if (keyWord.equals("help")) {
                    System.out.println("list: displays a sequential view of past inputs\n" +
                            "done <task number>: denotes a task as done by checking it\n" +
                            "delete <task number>: deletes an existing task\n" +
                            "deadline <description> /by <date/time>: adds a deadline with desired date/time\n" +
                            "event <description> /at <date/time>: adds an event with desired date/time\n" +
                            "todo <description>: adds a todo task\n" +
                            "bye: terminates program");
                } else if (keyWord.equals("list")) {
                    if (pastInputs.size() == 0) {
                        System.out.println("Duke says: No past inputs found");
                    } else {
                        System.out.println("Here are your tasks:");
                        for (int i = 1; i <= pastInputs.size(); i++) {
                            System.out.println(i + ". " + pastInputs.get(i - 1));
                        }
                        System.out.println("If you wish to mark a task as completed, input \"done <task number>\"");
                    }
                } else if (keyWord.equals("done")) {
                    //checks the formatting of user input
                    if (splitInput.length <= 2) {
                        try {
                            int taskNumber = Integer.parseInt(splitInput[1]);
                            Task doneTask = pastInputs.get(taskNumber - 1);
                            doneTask.markDone();
                            pastInputs.set(taskNumber - 1, doneTask);
                            System.out.println("Duke says: Good Job! I've marked this task as done:");
                            System.out.println(doneTask);
                        } catch (NumberFormatException ex) {
                            pastInputs.add(new Task(userInput));
                            System.out.println("Duke added into your task list: " + userInput);
                        } catch (Exception ex) {
                            System.out.println("Duke says: Please try again with a valid task number");
                        }
                    } else {
                        pastInputs.add(new Task(userInput));
                        System.out.println("Duke added into your task list:\n" + userInput);
                        System.out.println("You now have " + pastInputs.size() + " task(s) in your list");
                    }
                } else if (keyWord.equals("delete")) {
                    pastInputs = deleteTask(pastInputs, splitInput);
                } else {
                    String[] data = processInput(splitInput);
                    if (keyWord.equals("todo")) {
                        ToDo toDo = new ToDo(data[0]);
                        pastInputs.add(toDo);
                        System.out.println("Duke added into your task list:\n" + toDo);
                    } else if (keyWord.equals("deadline")) {
                        Deadline deadline = new Deadline(data[0], data[1]);
                        pastInputs.add(deadline);
                        System.out.println("Duke added into your task list:\n" + deadline);
                    } else if (keyWord.equals("event")){
                        Event event = new Event(data[0], data[1]);
                        pastInputs.add(event);
                        System.out.println("Duke added into your task list:\n" + event);
                    }
                    System.out.println("You now have " + pastInputs.size() + " task(s) in your list");
                }
                System.out.println("=========================================================================");
            } catch (InvalidKeyWordException ex) {
                System.out.println(ex.getMessage());
                System.out.println("=========================================================================");
            } catch (EmptyTaskException ex) {
                System.out.println(ex.getMessage());
                System.out.println("=========================================================================");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println("=========================================================================");
            }
        }
    }

    //returns a reserved keyword
    public static String getKeyWord(String word) throws InvalidKeyWordException {
        Set<String> reservedKeyWords = new HashSet<>();
        reservedKeyWords.add("help");
        reservedKeyWords.add("list");
        reservedKeyWords.add("done");
        reservedKeyWords.add("delete");
        reservedKeyWords.add("deadline");
        reservedKeyWords.add("event");
        reservedKeyWords.add("todo");
        reservedKeyWords.add("bye");
        
        if (!reservedKeyWords.contains(word)) {
            throw new InvalidKeyWordException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return word;
    }

    //returns an array of description and date/time(if applicable)
    public static String[] processInput(String[] array) throws EmptyTaskException {
        if (array.length <= 1) {
            if (array[0].equals("event")) {
                throw new EmptyTaskException("☹ OOPS!!! The description of a event cannot be empty.");
            } else if (array[0].equals("deadline")) {
                throw new EmptyTaskException("☹ OOPS!!! The description of a deadline cannot be empty.");
            } else if (array[0].equals("todo")) {
                throw new EmptyTaskException("☹ OOPS!!! The description of a todo cannot be empty.");
            } else {
                throw new EmptyTaskException("Unknown error!");
            }
        } else if (array[0].equals("event")) {
            String des = "";
            String dateAndOrTime = "";
            boolean toBreak = false;
            for (int i = 1; i < array.length; i++) {
                if (array[i].equals("/at")) {
                    //sets the breaking point of input
                    toBreak = true;
                } else if (!toBreak) {
                    des += array[i] + " ";
                } else {
                    dateAndOrTime += array[i];
                    if (i != array.length - 1) {
                        dateAndOrTime += " ";
                    }
                }
            }
            //index 0 is description, index 1 is date/time
            return new String[] {des, dateAndOrTime};

        } else if (array[0].equals("deadline")) {
            String des = "";
            String dateAndOrTime = "";
            boolean toBreak = false;
            for (int i = 1; i < array.length; i++) {
                if (array[i].equals("/by")) {
                    //sets the breaking point of input
                    toBreak = true;
                } else if (!toBreak) {
                    des += array[i] + " ";
                } else {
                    dateAndOrTime += array[i];
                    if (i != array.length - 1) {
                        dateAndOrTime += " ";
                    }
                }
            }
            //index 0 is description, index 1 is date/time
            return new String[] {des, dateAndOrTime};
        } else if (array[0].equals("todo")) {
            String des = "";
            for (int i = 1; i < array.length; i++) {
                des += array[i];
                if (i != array.length - 1) {
                    des += " ";
                }
            }
            //index 0 is description
            return new String[] {des};
        }
        return new String[] {};
    }

    public static List<Task> deleteTask(List<Task> current, String[] input) throws DeleteFailureException {
        try {
            if (input.length == 2) {
                List<Task> updated = current;
                int taskNumber = Integer.parseInt(input[1]);
                Task removedTask = current.get(taskNumber - 1);
                current.remove(taskNumber - 1);
                System.out.println("Successfully deleted the task!\n" + removedTask);
                System.out.println("You now have " + updated.size() + " task(s) in your list");
                return updated;
            } else {
                throw new DeleteFailureException("Duke says: Please try again with a valid format.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DeleteFailureException("Duke says: Please try again with a valid number.");
        }
    }
}
