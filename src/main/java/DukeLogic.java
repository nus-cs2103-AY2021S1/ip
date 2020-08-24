import java.time.LocalDate;
import java.util.ArrayList;

public class DukeLogic {
    private ArrayList<Task> taskList;

    public DukeLogic(){
        this.taskList = new ArrayList<>();
    }

    public void executeCommand(String command) {
        command = command.trim();
        try {
            if(command.equals("list")){
                printTaskList();
            } else if (command.length() >= 4 && command.substring(0, 4).equals("done")){
                completeTask(command);
            } else if (command.length() >= 6 && command.substring(0, 6).equals("delete")){
                deleteTask(command);
            } else if (validAddTaskCommand(command)){
                addTask(command);
            } else {
                throw new UnknownCommandException(command);
            }
        } catch (UnknownCommandException e) {
            System.out.println("    ____________________________________________________________");
            System.out.println("    Oh noes! I'm not sure what that means ;A;");
            System.out.println("    ____________________________________________________________");
        } catch (IncompleteTaskException e) {
            System.out.println("    ____________________________________________________________");
            System.out.println("    Oh dear! Your task description seems to be incomplete :<");
            System.out.println("    ____________________________________________________________");
        } catch (InvalidTaskException e) {
            System.out.println("    ____________________________________________________________");
            System.out.println("    Oh noes! I don't think you specified a valid task index :<");
            System.out.println("    ____________________________________________________________");
        }
    }

    private void printTaskList(){
        System.out.println("    ____________________________________________________________");
        System.out.println("    Here are the tasks in your list!!");
        int index = 1;
        for(Task task: this.taskList) {
            System.out.println("    " + index + ". " + task);
            index++;
        }
        System.out.println("    ____________________________________________________________");
    }

    private void completeTask(String command) throws InvalidTaskException  {
        if (command.length() < 5) {
            throw new InvalidTaskException("No task index specified");
        }
        try {
            int index = Integer.parseInt(command.substring(5));

            if(index > this.taskList.size() || index <= 0) {
                throw new InvalidTaskException("Invalid task index specified");
            }

            Task task = this.taskList.get(index - 1).markAsDone();
            System.out.println("    ____________________________________________________________");
            System.out.println("     Yay! I've marked this task as done :3");
            System.out.println("       " + task);
            System.out.println("    ____________________________________________________________");
        } catch (NumberFormatException e) {
            throw new InvalidTaskException("Invalid task index specified");
        }
    }

    private void deleteTask(String command) throws InvalidTaskException {
        if (command.length() < 7) {
            throw new InvalidTaskException("No task index specified");
        }
        try {
            int index = Integer.parseInt(command.substring(7));

            if(index > this.taskList.size() || index <= 0) {
                throw new InvalidTaskException("Invalid task index specified");
            }

            Task task = this.taskList.get(index - 1);
            this.taskList.remove(index - 1);
            System.out.println("    ____________________________________________________________");
            System.out.println("     Got it! I'll remove this task :>");
            System.out.println("       " + task);
            System.out.println("     Only " + this.taskList.size() + " tasks left!!");
            System.out.println("    ____________________________________________________________");
        } catch (NumberFormatException e) {
            throw new InvalidTaskException("Invalid task index specified");
        }
    }

    private boolean validAddTaskCommand(String command){
        return command.split(" ")[0].equals("todo") || command.split(" ")[0].equals("deadline") ||
                command.split(" ")[0].equals("event");
    }

    private void addTask(String command) throws IncompleteTaskException {
        String typeOfTask = command.split(" ")[0];
        switch (typeOfTask) {
            case "todo":
                if (command.length() <= 4) {
                    throw new IncompleteTaskException("Incomplete Todo description");
                }
                this.taskList.add(new Todo(command.substring(5)));
                break;
            case "event": {
                if (!command.contains("/at") || (command.indexOf("event ") + 6 > command.indexOf(" /at"))) {
                    throw new IncompleteTaskException("Incomplete Event description");
                }
                String taskName = command.substring(command.indexOf("event ") + 6, command.indexOf(" /at"));
                String taskDate = command.substring(command.indexOf("/at ") + 4);
                this.taskList.add(new Event(taskName, LocalDate.parse(taskDate)));
                break;
            }
            case "deadline": {
                if (!command.contains("/by") || (command.indexOf("deadline ") + 9 > command.indexOf(" /by"))) {
                    throw new IncompleteTaskException("Incomplete Deadline description");
                }
                String taskName = command.substring(command.indexOf("deadline ") + 9, command.indexOf(" /by"));
                String taskDate = command.substring(command.indexOf("/by ") + 4);
                this.taskList.add(new Deadline(taskName, LocalDate.parse(taskDate)));
                break;
            }
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     Okies! I've added this task~");
        System.out.println("       " + this.taskList.get(this.taskList.size() - 1));
        System.out.println("     Now you have " + this.taskList.size() + " tasks in the list uwu");
        System.out.println("    ____________________________________________________________");
    }
}
