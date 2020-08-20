public class GreetCommand extends Command {
    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        sb.append(logo).append("\n")
                .append("hello! i'm duke :-)\n")
                .append("how may i help you?\n")
                .append("--------------------------------------------------------------");
        return sb.toString();
    }
}
