public class Parser {

    public static Command parse(String str) throws DukeException {
        assert str != null : "command should not be null";
        String s = str.split(" ")[0];
        if (s.equals("bye")) {
            return new ByeCommand(str);
        } else if (s.equals("list")) {
            return new ListCommand(str);
        } else if (s.equals("done")) {
            return new DoneCommand(str);
        } else if (s.equals("delete")) {
            return new DeleteCommand(str);
        } else if (s.equals("todo")) {
            return new TodoCommand(str);
        } else if (s.equals("deadline")) {
            return new DeadlineCommand(str);
        } else if (s.equals("event")) {
            return new EventCommand(str);
        } else if (s.equals("find")) {
            return new FindKeywordCommand(str);
        }else if (s.equals("prioritize")) {
            return new PrioritizeCommand(str);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}

