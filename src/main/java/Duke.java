import task.*;

public class Duke {
    private static final String FILEPATH = "duke.txt";
    private TaskList taskList;
    /**
     * Calculates a response from the given command.
     * @return A response.
     */
    public String getResponse(String input) {
        String reply = "";
        try {
            String[] command = Parser.parseCommand(input);

            if(command[0].contentEquals("bye")){
                return "Bye. Hope to see you again soon!";
            }
            else if(command[0].contentEquals("list")){
                StringBuilder replyBuilder = new StringBuilder();

                for(int i = 0; i < taskList.size(); i++){
                    replyBuilder.append(i + 1).append(". ").append(taskList.get(i)).append("\n");
                }
                return replyBuilder.toString();
            }
            else if(command[0].contentEquals("remove")){
                String indexStr = input.replaceAll("[^0-9]", "");
                int index = Integer.parseInt(indexStr) - 1;
                Task t = taskList.remove(index);
                reply = "Noted. I've removed this task:\n"
                        +"\t" + t + "\n"
                        + "\t" + "Now you have " + taskList.size() + " tasks in the list.";

                Storage.saveTasks(FILEPATH, taskList);
                return reply;
            }
            else if(command[0].contentEquals("done")){
                String indexStr = input.replaceAll("[^0-9]", "");
                int index = Integer.parseInt(indexStr) - 1;
                taskList.get(index).setDone();
                reply = "Nice! I've marked this task as done:\n"
                        + taskList.get(index);
                Storage.saveTasks(FILEPATH, taskList);
                return reply;
            }
            else if(command[0].contentEquals("find") ){
                TaskList foundList = taskList.find(command[1]);
                StringBuilder replyBuilder = new StringBuilder();

                for(int i = 0; i < foundList.size(); i++){
                    replyBuilder.append(i + 1).append(". ").append(foundList.get(i)).append("\n");
                }
                return replyBuilder.toString();
            }
            else if(command[0].contentEquals("todo") ){
                try {
                    Task newTask = new Todo(command[1]);
                    taskList.add(newTask);
                    reply = "Got it. I've added this task:\n"
                            + "\t" + newTask;
                    Storage.saveTasks(FILEPATH, taskList);
                    return reply;
                }
                catch(EmptyStringException e){
                    return "Todo cannot be empty.";
                }
            }
            else if(command[0].contentEquals("deadline")){
                try {
                    Task newTask = new Deadline(command[1]);
                    taskList.add(newTask);
                    reply = "Got it. I've added this task:\n"
                            + "\t" + newTask;
                    Storage.saveTasks(FILEPATH, taskList);
                    return reply;
                }
                catch(EmptyStringException e){
                    return "Deadline cannot be empty.";
                }
            }
            else if(command[0].startsWith("event")){
                try {
                    Task newTask = new Event(command[1]);
                    taskList.add(newTask);
                    reply = "Got it. I've added this task:\n"
                            + "\t" + newTask;
                    Storage.saveTasks(FILEPATH, taskList);
                    return reply;
                }
                catch(EmptyStringException e){
                    return "Event cannot be empty.";
                }
            }
            else{
                return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
            }
        }
        catch(Exception e){
            reply = reply + "\n" + e.getMessage();
            return reply;
        }
    }

    public void startDuke() {
        taskList = Storage.loadTasks(FILEPATH);
    }
}