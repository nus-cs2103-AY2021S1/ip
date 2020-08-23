public class Save {

    String home = System.getProperty("user.home");

    java.nio.file.Path path = java.nio.file.Paths.get(home, "my", "app", "dir");
    boolean directoryExists = java.nio.file.Files.exists(path);


}
