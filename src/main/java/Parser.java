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
            String[] arr = split.split(" ", 2);
            String keyword = arr[0];
            Commands command = Commands.findCommand(keyword);
            switch (command) {
            case EXIT:

                Storage.save(taskList, Storage.FILE_PATH);
                return ui.addLines("Bye. Hope to see you again soon!");

            case LIST:
                Storage.save(taskList, Storage.FILE_PATH);
                return ui.addLines(taskList.printOutList());

            case DONE:
                try {
                    Storage.save(taskList, Storage.FILE_PATH);
                    int index = Integer.parseInt(arr[1]) - 1;
                    return ui.addLines(taskList.markCompleted(index));

                } catch (Exception e) {
                    return (new DukeException("Integer not detected")).toString();
                }
            case DEADLINE:
                try {
                    Storage.save(taskList, Storage.FILE_PATH);
                    return ui.addLines(taskList.add(Deadline.createDeadline(arr[1])));

                } catch (ArrayIndexOutOfBoundsException e) {
                    return (new DukeException("OOPS!!! The description of a deadline is emptye.", e))
                            .toString();
                }
            case TODO:
                try {
                    Storage.save(taskList, Storage.FILE_PATH);
                    ToDo item = new ToDo(arr[1]);
                    return ui.addLines(taskList.add(item));

                } catch (Exception e) {
                    return (new DukeException("OOPS!!! The description of a todo cannot be empty.", e)).toString();
                }
            case EVENT:
                try {
                    Storage.save(taskList, Storage.FILE_PATH);
                    return ui.addLines(taskList.add(Event.createEvent(arr[1])));

                } catch (ArrayIndexOutOfBoundsException e) {
                    return (new DukeException("OOPS!!! The description of an event is empty.", e))
                            .toString();
                }
            case DELETE:
                try {
                    Storage.save(taskList, Storage.FILE_PATH);
                    int index2 = Integer.parseInt(arr[1]) - 1;
                    return ui.addLines(taskList.deleteTask(index2));

                } catch (Exception e) {
                    return (new DukeException("OOPS!!! There is no task at that list number to delete!", e))
                             .toString();
                }
            case FIND:
                try {
                    Storage.save(taskList, Storage.FILE_PATH);
                    String findWord = arr[1];
                    taskList.findTask(findWord);
                    return ui.addLines(taskList.printOutKeyWordList());
                } catch (Exception e) {
                    return (new DukeException("OOPS!!! Please specify a keyword to search for!", e))
                            .toString();
                }
            case RESCHEDULE:
                try {
                    Storage.save(taskList, Storage.FILE_PATH);
                    String taskToChange = arr[1];
                    return ui.addLines(taskList.rescheduleTask(taskToChange));
                } catch (Exception e) {
                    return (new DukeException("OOPS!!! The description to reschedule a task cannot be empty", e))
                                .toString();
                }
            default: return "Unable to parse";
            }


        } catch (DukeException e) {
            System.out.println(e);
            return e.toString();
        }
    }


}
