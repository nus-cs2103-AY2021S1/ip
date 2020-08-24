public class Bye extends Command{

    Bye(){
        this.name = "bye";
        this.usage = "bye";
        this.description = "Duke Chatbot will power down";
    }

    public String response(){
        return "Bye. Hope to see you again soon!";
    }

    public void endBot(){
        ChatBot.ended = true;
    }
}
