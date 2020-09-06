/**
 * Parser class handles userinput from UI class and parses throught it to create the Task List
 */
public class Parser {


    /**
     * Takes all the necessary arguments to create the list of tasks
     *
     * @param taskList main list where all tasks are kept on
     * @param ui       scanner that takes user inputs
     */


    public static String parseCode(TaskList taskList, UI ui, String userInput) {

        String echo = userInput;
        try {
            String split = echo;
            String arr[] = split.split(" ", 2);
            String keyword = arr[0];
            Commands command = Commands.findCommand(keyword);
            switch (command) {
                case EXIT:

                    Storage.save(taskList, Storage.FILE_PATH);
                    return ui.addLines("Bye. Hope to see you again soon!");

                case LIST:

                    return ui.addLines(taskList.printOutList());

                case DONE:
                    try {

                        int index = Integer.parseInt(arr[1]) - 1;
                        return ui.addLines(taskList.markCompleted(index));

                    } catch (Exception e) {
                        return (new DukeException("Integer not detected")).toString();
                    }
                case DEADLINE:
                    try {

                        return ui.addLines(taskList.add(Deadline.createDeadline(arr[1])));

                    } catch (ArrayIndexOutOfBoundsException e) {
                        return (new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.", e)).toString();
                    }
                case TODO:
                    try {

                        ToDo item = new ToDo(arr[1]);
                        return ui.addLines(taskList.add(item));

                    } catch (Exception e) {
                        return (new DukeException("☹ OOPS!!! The description of a todo cannot be empty.", e)).toString();
                    }
                case EVENT:
                    try {

                        return ui.addLines(taskList.add(Event.createEvent(arr[1])));

                    } catch (Exception e) {
                        return (new DukeException("☹ OOPS!!! The description of a event cannot be empty.", e)).toString();
                    }
                case DELETE:
                    try {

                        int index2 = Integer.parseInt(arr[1]) - 1;
                        return ui.addLines(taskList.deleteTask(index2));

                    } catch (Exception e) {
                        return (new DukeException("☹ OOPS!!! There is no task at that list number to delete!", e)).toString();
                    }
                case FIND:

                    String findWord = arr[1];
                    taskList.findTask(findWord);
                    return ui.addLines(taskList.printOutKeyWordList());
                
                case RESCHEDULE:
                    try {
                      
                        String taskToChange = arr[1];
                        return ui.addLines(taskList.rescheduleTask(taskToChange));
                      
                    } catch (Exception e) {
                        return (new DukeException("☹ OOPS!!! The description to reschedule a task cannot be empty", e)).toString();
                    }

            }


        } catch (DukeException e) {
            System.out.println(e);
            return e.toString();
        }

        return "Unable to parse";
    }


}
