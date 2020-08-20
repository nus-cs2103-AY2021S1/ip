public class Responder {
    public static void responder() {
        String input = ReadIn.readIn();
        String firstWord = input.split(" ")[0];

        while (true) {
            StringBuilder sb = new StringBuilder();
            sb.append("--------------------------------------------------------------\n");

            try {
                if (firstWord.equals("bye")) {
                    sb.append(new ByeCommand().execute());
                    sb.append("\n--------------------------------------------------------------");
                    System.out.println(sb.toString());
                    break;
                } else if (firstWord.equals("list")) {
                    sb.append(new ListCommand().execute());
                } else if (firstWord.equals("done")) {
                    int i = Integer.valueOf(input.substring(5));
                    sb.append(new DoneCommand(i).execute());
                } else if (firstWord.equals("delete")) {
                    int i = Integer.valueOf(input.substring(7));
                    sb.append(new DeleteCommand(i).execute());
                } else if (firstWord.equals("todo")) {
                    sb.append(new ToDoCommand(input).execute());
                } else if (firstWord.equals("deadline")) {
                    sb.append(new DeadlineCommand(input).execute());
                } else if (firstWord.equals("event")) {
                    sb.append(new EventCommand(input).execute());
                } else {
                    throw new DukeException("oops! im sorry, but i do not know what that means :-(");
                }
            } catch (EmptyDescriptionException e) {
                sb.append(e.getMessage());
            } catch (DukeException e) {
                sb.append(e.getMessage());
            }
            sb.append("\n--------------------------------------------------------------");
            System.out.println(sb.toString());
            input = ReadIn.readIn();
            firstWord = input.split(" ")[0];
        }
    }
}
