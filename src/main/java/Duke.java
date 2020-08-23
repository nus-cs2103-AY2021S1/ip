import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    public static void main(String[] args) {
        Duke dk = new Duke();
        Storage storage = new Storage();
        try {
            ArrayList<Task> tasks = storage.readFile();
            dk.greet();
            Scanner sc = new Scanner( System.in );
            while (sc.hasNext()) {
                try {
                    System.out.println("____________________________________________________________");
                    String input = sc.nextLine();
                    dk.respond(input, tasks);
                } catch (CommandNotFoundException | EmptyTaskException | EmptyTimeException ex) {
                    System.out.println(ex.getMessage());
                }
                System.out.println("____________________________________________________________");
            }
        } catch (PathNoFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public enum Command {
        BYE, LIST, DONE, DELETE, TODO, DEADLINE, EVENT
    }

    public void respond(String input, ArrayList<Task> tasks) throws CommandNotFoundException, EmptyTaskException,
    EmptyTimeException {
        String[] parseArray = input.trim().split(" ", 2);
        String type = parseArray[0].toUpperCase();
        try {
            Command command = Command.valueOf(type);
            switch (command) {
            case BYE:
                endConversation(tasks);
                break;
            case LIST:
                showTask(tasks);
                break;
            case DONE:
            case DELETE:
                if (parseArray.length == 1) {
                    throw new EmptyTaskException("Please specify task index. (´∀`)");
                } else {
                    String rest = parseArray[1];
                    int index = Integer.parseInt(rest) - 1;
                    switch (command) {
                        case DONE:
                            Task newTask = tasks.get(index).markAsDone();
                            tasks.set(index, newTask);
                            break;
                        case DELETE:
                            Task.reduceOneTasks();
                            String message = tasks.get(index).deleteMessage();
                            System.out.println(message);
                            tasks.remove(index);
                            break;
                    }
                }
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                if (parseArray.length == 1) {
                    throw new EmptyTaskException("Please specify task description. (´∀`)");
                } else {
                    String rest = parseArray[1];
                    switch (command) {
                        case TODO:
                            Todo newTodo = new Todo(rest);
                            tasks.add(newTodo);
                            System.out.println(newTodo);
                            break;
                        case DEADLINE:
                            if (rest.split("/").length == 1) {
                                throw new EmptyTimeException("Please specify deadline using \"/by\". (´∀`)");
                            } else {
                                String description = rest.split(" /")[0];
                                try{
                                    String time = rest.split(" /")[1].split(" ", 2)[1];
                                    Deadline newDeadline = new Deadline(description, time);
                                    tasks.add(newDeadline);
                                    System.out.println(newDeadline);
                                } catch (ArrayIndexOutOfBoundsException ex) {
                                    throw new EmptyTimeException("Please don't leave the deadline blank~ (´∀`)");
                                }

                            }
                            break;
                        case EVENT:
                            if (rest.split("/").length == 1) {
                                throw new EmptyTimeException("Please specify event time using \"/at\". (´∀`)");
                            } else {
                                String description = rest.split(" /")[0];
                                try{
                                    String time = rest.split(" /")[1].split(" ", 2)[1];
                                    Event newEvent = new Event(description, time);
                                    tasks.add(newEvent);
                                    System.out.println(newEvent);
                                } catch (ArrayIndexOutOfBoundsException ex) {
                                    throw new EmptyTimeException("Please don't leave the event time blank~ (´∀`)");
                                }
                            }
                            break;
                    }
                }
                break;
            default:
                    throw new CommandNotFoundException();
            }
        } catch (IllegalArgumentException e) {
            throw new CommandNotFoundException();
        }

    }



    public void greet(){
        System.out.println("Hello! I am YURINA Chan.\nWhat can I do for you? ᕕ( ᐛ )ᕗ");
    }

    public void showTask(ArrayList<Task> tasks){
        if (tasks.size() == 0) {
            System.out.println("This is no task in your task list yet. Add one now! (/^▽^)/");
        }

        int no = 1;
        for (Task task : tasks) {
            String state = "[" + task.getStatusIcon() + "] ";
            System.out.println(state + no + ". " + task.description);
            no++;
        }
    }

    public void endConversation(ArrayList<Task> tasks){
        Storage storage = new Storage();
        storage.saveFile(tasks);
        System.out.println("Bye~ Hope to see you again soon! ∠( ᐛ 」∠)＿");
        System.out.println("____________________________________________________________");
        System.exit(0);
    }


}
