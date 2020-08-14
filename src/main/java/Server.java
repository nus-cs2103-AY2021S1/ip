public class Server {
    public String formatOut(String s) {
        return String.format("  %s\n    %s\n  %s\n",hor_line(),s,hor_line());
    }

    private String hor_line() {
        return "-------------------------------------";
    }
}
