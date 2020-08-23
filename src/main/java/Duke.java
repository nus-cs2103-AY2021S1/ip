public class Duke {
    public static void run() {
        UI.summonSupremeLeaderAndGreet();
        Parser.run();
        UI.farewell();
    }

    public static void main(String[] args) {
        Duke.run();
    }
}
