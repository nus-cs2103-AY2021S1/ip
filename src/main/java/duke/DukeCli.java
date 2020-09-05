package duke;

import task.*;

import java.io.*;

public class DukeCli {

    private static final String FILEPATH = "duke.txt";
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor for duke.Duke class.
     * @param filePath Path for the duke.Duke save file.
     */
    public DukeCli(String filePath) {
        //storage = new duke.Storage(filePath);
        taskList = Storage.loadTasks(FILEPATH);
        ui = new Ui();
    }

    /**
     * Method to run the duke.Duke program.
     */
    public void run() {
        Ui.printWelcome();
        while(true){
            try {
                String input = ui.getInput();
                String[] command = Parser.parseCommand(input);

                if(command[0].contentEquals("bye")){
                    Ui.printBye();
                    break;
                }
                else if(command[0].contentEquals("list")){
                    Ui.printTaskList(taskList);
                }
                else if(command[0].contentEquals("remove")){
                    String indexStr = input.replaceAll("[^0-9]", "");
                    int index = Integer.parseInt(indexStr) - 1;
                    Task t = taskList.remove(index);
                    Ui.printRemovedTask(t, taskList.size());
                }
                else if(command[0].contentEquals("done")){
                    String indexStr = input.replaceAll("[^0-9]", "");
                    int index = Integer.parseInt(indexStr) - 1;
                    taskList.get(index).setDone();
                    Ui.printDoneTask(taskList.get(index));
                }
                else if(command[0].contentEquals("find") ){
                    TaskList foundList = taskList.find(command[1]);
                    Ui.printFoundList(foundList);
                }
                else if(command[0].contentEquals("todo") ){
                    try {
                        Task newTask = new Todo(command[1]);
                        taskList.add(newTask);
                        Ui.printAddedTask(newTask);
                    }
                    catch(EmptyStringException e){
                        Ui.printException("Todo cannot be empty.");
                    }
                }
                else if(command[0].contentEquals("deadline")){
                    try {
                        Task newTask = new Deadline(command[1]);
                        taskList.add(newTask);
                        Ui.printAddedTask(newTask);
                    }
                    catch(EmptyStringException e){
                        Ui.printException("Deadline cannot be empty.");
                    }
                }
                else if(command[0].startsWith("event")){
                    try {
                        Task newTask = new Event(command[1]);
                        taskList.add(newTask);
                        Ui.printAddedTask(newTask);
                    }
                    catch(EmptyStringException e){
                        Ui.printException("Event cannot be empty.");
                    }
                }
                else{
                    Ui.printException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    continue;
                }

                try {
                    Storage.saveTasks(FILEPATH, taskList);
                }
                catch (IOException e){
                    Ui.printException("Unable to save to file.");
                }
            }
            catch(Exception e){
                Ui.printException(e.getMessage());
            }
        }
    }

    /**
     * duke.Main method.
     * @param args List of arguments.
     */
    public static void main(String[] args) {
        new DukeCli(FILEPATH).run();
    }
}