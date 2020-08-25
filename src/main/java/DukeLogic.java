import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DukeLogic {
    private ArrayList<Task> taskList;
    private File storage;
    private final String filePath = "data/tasks.txt";

    public DukeLogic() {
        this.taskList = new ArrayList<>();
        this.storage = new File(filePath);

        try {
            if(this.storage.exists()){
                // Load into taskList if file is not empty
                Scanner s = new Scanner(this.storage);
                if (this.storage.length() != 0){
                    while (s.hasNext()) {
                        String[] t = s.nextLine().split(" [|] ");
                        // Tasks are stored in the format: type | isCompleted | taskName | date
                        switch (t[0]){
                            case "T":
                                this.taskList.add(Todo.existingTodo(t[2], t[1].equals("1")));
                                break;
                            case "D":
                                this.taskList.add(Deadline.existingDeadline(t[2], t[1].equals("1"), t[3]));
                                break;
                            case "E":
                                this.taskList.add(Event.existingEvent(t[2], t[1].equals("1"), t[3]));
                                break;
                        }
                    }
                }
            } else {
                this.storage.getParentFile().mkdirs();
                this.storage.createNewFile();
            }

            if(!this.storage.exists()){
                 throw new IOException();
            }
        } catch (IOException e) {
            System.out.println("Oh noes! I can't seem to find the tasks you saved previously ;A;");
        }
    }

    public void executeCommand(String command) {
        command = command.trim();
        try {
            if(command.equals("list")){
                printTaskList();
            } else if (command.length() >= 4 && command.substring(0, 4).equals("done")){
                completeTask(command);
                updateFile();
            } else if (command.length() >= 6 && command.substring(0, 6).equals("delete")){
                deleteTask(command);
                updateFile();
            } else if (validAddTaskCommand(command)){
                addTask(command);
                updateFile();
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
        } catch (IOException e) {
            System.out.println("Oh dear! I can't seem to save your task permanently! ;A;");
            System.out.println("Could you try exiting and entering the application before adding it again? m(_ _)m");
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

    private void addTask(String command) throws IncompleteTaskException, IOException {
        String typeOfTask = command.split(" ")[0];
        FileWriter fw = new FileWriter(filePath, true);
        switch (typeOfTask) {
        case "todo":
            if (command.length() <= 4) {
                throw new IncompleteTaskException("Incomplete Todo description");
            }
            Todo newTodo = Todo.newTodo(command.substring(5));
            this.taskList.add(newTodo);
            fw.write(newTodo.toSaveString());
            break;
        case "event":
            if (!command.contains("/at") || (command.indexOf("event ") + 6 > command.indexOf(" /at"))) {
                throw new IncompleteTaskException("Incomplete Event description");
            }
            String eventName = command.substring(command.indexOf("event ") + 6, command.indexOf(" /at"));
            String eventDate = command.substring(command.indexOf("/at ") + 4);
            Event newEvent = Event.newEvent(eventName, eventDate);
            this.taskList.add(newEvent);
            fw.write(newEvent.toSaveString());
            break;
        case "deadline":
            if (!command.contains("/by") || (command.indexOf("deadline ") + 9 > command.indexOf(" /by"))) {
                throw new IncompleteTaskException("Incomplete Deadline description");
            }
            String deadlineName = command.substring(command.indexOf("deadline ") + 9, command.indexOf(" /by"));
            String deadlineDate = command.substring(command.indexOf("/by ") + 4);
            Deadline newDeadline = Deadline.newDeadline(deadlineName, deadlineDate);
            this.taskList.add(newDeadline);
            fw.write(newDeadline.toSaveString());
            break;
        }
        fw.close();
        System.out.println("    ____________________________________________________________");
        System.out.println("     Okies! I've added this task~");
        System.out.println("       " + this.taskList.get(this.taskList.size() - 1));
        System.out.println("     Now you have " + this.taskList.size() + " tasks in the list uwu");
        System.out.println("    ____________________________________________________________");
    }

    private void updateFile() throws IOException {
        String saveString = "";
        for(Task t : this.taskList){
            saveString += t.toSaveString();
        };

        FileWriter fw = new FileWriter(filePath);
        fw.write(saveString);
        fw.close();
    }
}
