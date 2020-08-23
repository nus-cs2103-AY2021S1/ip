import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


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
                } catch (CommandNotFoundException | EmptyTaskException | EmptyTimeException
                        | WrongDateFormatException ex) {
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
    EmptyTimeException, WrongDateFormatException {
        String[] parseArray = input.trim().split(" ", 2);
        String type = parseArray[0].toUpperCase();
        try {
            Command command = Command.valueOf(type);
            switch (command) {
                case BYE:
                    endConversation(tasks);
                    break;
                case LIST:
                    if (parseArray.length == 1) {
                        showTask(tasks);
                    } else {
                        LocalDate date = LocalDate.parse(parseArray[1]);
                        showTasksOnSpecificDay(date, tasks);
                    }
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
                                        LocalDate date = LocalDate.parse(time);
                                        Deadline newDeadline = new Deadline(description, date);
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
                                        LocalDate date = LocalDate.parse(time);
                                        Event newEvent = new Event(description, date);
                                        tasks.add(newEvent);
                                        System.out.println(newEvent);
                                    } catch (ArrayIndexOutOfBoundsException ex) {
                                        throw new EmptyTimeException("Please don't leave the event time blank~ (´∀`)");
                                    }
                                }
                            }
                            break;
                    }
                }
        } catch (IllegalArgumentException ex) {
            throw new CommandNotFoundException();
        } catch (DateTimeParseException ex) {
            throw new WrongDateFormatException();
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

    public void showTasksOnSpecificDay(LocalDate date, ArrayList<Task> tasks){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String dateString = date.format(formatter);
        ArrayList<Task> filtered = tasks.stream().filter(Task::getHasTime).filter(task -> task.getTime().equals(date))
                .collect(Collectors.toCollection(ArrayList::new));
        if (filtered.size() == 0) {
            System.out.println("No tasks on this day! Chill Chill~ ٩(˘◡˘)۶");
        } else {
            System.out.println("On " + dateString + ", you have the following tasks:");
            showTask(filtered);
        }
    }


}
