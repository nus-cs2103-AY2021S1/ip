public class Parser {

    public Parser(TaskList tasks, Ui ui, Storage storage) {

    }
//    public static void processInputs(String input, String[] inputWords) {
//        try {
//            if (input.equals("format")) {
//                System.out.println("Formats for the three task types Todo, Deadline and Event,"
//                        + " are shown below.\n"
//                        + "Todo: 'todo <task description>'"
//                        + " (e.g. todo visit new theme park)\n"
//                        + "Deadline: 'deadline <task description>"
//                        + " /by <yyyy-mm-dd hhmm>' (e.g. deadline submit report /by 2019-11-10 1500)\n"
//                        + "Event: 'event <task description>"
//                        + " /at <yyyy-mm-dd hhmm-hhmm>' "
//                        + "(e.g. event team project meeting /at 2019-10-02 1400-1500)\n"
//                        + "\nAdditional Information:"
//                        + "\nTo mark a task as done, input 'done <task number>'."
//                        + "\nTo delete a task from your list, input 'delete <task number>'.");
//            } else if (input.equals("list")) {
//                this.tasks.printList();
//            } else if (inputWords[0].equals("done") && inputWords.length == 2
//                    && inputWords[1].matches("[0-9]+")) {
//                // condition checks that user input is in the format "done X" where X is a numeric
//                this.tasks.markDone(this.storage, input);
//            } else if (inputWords[0].equals("delete") && inputWords.length == 2
//                    && inputWords[1].matches("[0-9]+")) {
//                // condition checks that user input is in the format "delete X" where X is a numeric
//                this.tasks.deleteTask(this.storage, input);
//            } else {
//                // Dino adds task to list
//                this.tasks.addTask(this.storage, input, inputWords);
//            }
//
//        } catch(DukeException e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println("____________________________________________________________");
//    }
}
