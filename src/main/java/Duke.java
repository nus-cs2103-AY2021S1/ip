import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Duke dk = new Duke();
        ArrayList<Task> tasks = new ArrayList<Task>();

        dk.greet();
        Scanner sc = new Scanner( System.in );

        while (sc.hasNext()) {
            System.out.println("____________________________________________________________");
            String input = sc.nextLine();
            try {
                dk.respond(input, tasks);
            } catch (CommandNotFoundException ex) {
                System.out.println(ex);
            } catch (EmptyTaskException ex) {
                System.out.println(ex);
            } catch (EmptyTimeException ex) {
                System.out.println(ex);
            }
            System.out.println("____________________________________________________________");
        }
    }

    public void respond(String input, ArrayList<Task> tasks) throws CommandNotFoundException, EmptyTaskException,
    EmptyTimeException {
        String[] parseArray = input.split(" ", 2);
        String type = parseArray[0];
        if (input.equals("bye")) {
            endConversation();
        } else if (input.equals("list")) {
            showTask(tasks);
        } else if (type.equals("done")||type.equals("delete")||
                type.equals("todo")||type.equals("deadline")||type.equals("event")) {
            if (parseArray.length == 1 && (type.equals("done")||type.equals("delete"))){
                throw new EmptyTaskException("Please specify task index. (´∀`)");
            } else if (parseArray.length == 1 ){
                throw new EmptyTaskException("Please specify task description. (´∀`)");
            } else {
                String rest = parseArray[1];
                String description = "";
                String time = "";

                if (type.equals("done")) {
                    int index = Integer.parseInt(rest) - 1;
                    Task newTask = tasks.get(index).markAsDone();
                    tasks.set(index, newTask);
                } else if (type.equals("delete")) {
                    int index = Integer.parseInt(rest) - 1;
                    Task.reduceOneTasks();
                    String message = tasks.get(index).deleteMessage();
                    System.out.println(message);
                    tasks.remove(index);

                } else {
                    switch (type) {
                        case "todo":
                            Todo newTodo = new Todo(rest);
                            tasks.add(newTodo);
                            System.out.println(newTodo);
                            break;
                        case "deadline":
                            if (rest.split("/").length == 1 ) {
                                throw new EmptyTimeException("Please specify deadline using \"/by\". (´∀`)");
                            }
                            description = rest.split(" /")[0];
                            time = rest.split(" /")[1].split(" ", 2)[1];
                            Deadline newDeadline = new Deadline(description, time);
                            tasks.add(newDeadline);
                            System.out.println(newDeadline);
                            break;
                        case "event":
                            if (rest.split("/").length == 1 ) {
                                throw new EmptyTimeException("Please specify deadline using \"/at\". (´∀`)");
                            }
                            description = rest.split(" /")[0];
                            time = rest.split(" /")[1].split(" ", 2)[1];
                            Event newEvent = new Event(description, time);
                            tasks.add(newEvent);
                            System.out.println(newEvent);
                            break;
                    }
                }
                System.out.println("Now you have " + Task.numberOfTasks + " tasks in the list.");
            }
        } else {
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

    public void endConversation(){
        System.out.println("Bye~ Hope to see you again soon! ∠( ᐛ 」∠)＿");
        System.out.println("____________________________________________________________");
        System.exit(0);
    }


}
