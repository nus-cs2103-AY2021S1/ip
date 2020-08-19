public class Userinput {
    private final String input;
    private boolean terminate;

    public Userinput(String input) {
        this.input = input;
        this.terminate = false;
    }

    String getDukeResponse(){
        String listResponse = "    ____________________________________________________________\n" +
                "     list\n" +
                "    ____________________________________________________________";
        String blahResponse = "    ____________________________________________________________\n" +
                "     blah\n" +
                "    ____________________________________________________________";
        String byeResponse = "    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________";
        if (this.input.equals("list")){
          return listResponse;
        } else if (this.input.equals("blah")){
            return blahResponse;
        } else if (this.input.equals("bye")){
            this.terminate = true;
            return byeResponse;
        } else {
            return  "    ____________________________________________________________\n" +
                    "     Sorry. I do not understand what you have said.\n" +
                    "    ____________________________________________________________";
        }
    }

    boolean getTerminate(){
        return this.terminate;
    }

}
