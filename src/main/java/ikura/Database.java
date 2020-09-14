// Database.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura;

import java.util.List;
import java.util.Arrays;
import java.util.Optional;
import java.nio.file.Path;
import java.util.ArrayList;
import java.nio.file.Files;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.function.Function;
import java.time.format.DateTimeFormatter;

import ikura.task.Task;
import ikura.task.Todo;
import ikura.task.Event;
import ikura.util.Either;
import ikura.task.Deadline;
import ikura.task.TaskParser;

import java.io.IOException;
import ikura.util.InvalidInputException;
import ikura.util.InvalidDatabaseException;


/**
 * A class encapsulating the serialisation and deserialisation of the list of tasks.
 */
public class Database {

    private static final int DATABASE_VERSION = 2;

    private final String databasePath;

    /**
     * Constructs a new database, using the given path as the backing file. The file does
     * not have to exist, but it should be writeable.
     *
     * @param path the path to the file.
     */
    public Database(String path) {
        this.databasePath = path;
    }


    /**
     * Split the input by the delimiter '|', accounting for
     * escaped | in the actual input.
     *
     * @param input the input line. this should contain at most 2 pipes.
     * @return the list of components: first one can be discarded, second one is the title, third one
     *         is the description (if it exists -- empty otherwise);
     */
    public static String[] splitTitleAndDescription(String input) {

        // the input is something like this. so we can just make a an array of 3.
        // <type, doneness and date> | <title> | [description]

        var currentPart = 0;
        var ret = new StringBuilder[3];
        for (var i = 0; i < 3; i++) {
            ret[i] = new StringBuilder();
        }

        char prev = 0;
        for (int i = 0; i < input.length(); i++) {

            char ch = input.charAt(i);

            if (ch == '\\') {
                if (i + 1 < input.length() && input.charAt(i + 1) == '|') {
                    // append the pipe, then skip the next char (the pipe)
                    ret[currentPart].append("|");
                    i += 1;
                } else {
                    ret[currentPart].append("\\");
                }
            } else if (ch == '|') {
                currentPart++;
            } else {
                ret[currentPart].append(input.charAt(i));
            }
        }

        return Arrays.stream(ret)
            .map(x -> x.toString())
            .collect(Collectors.toList())
            .toArray(new String[0]);
    }



    /**
     * Load the list of tasks from the database.
     *
     * @return the list of tasks loaded from disk.
     * @throws IOException if there was an error reading the file.
     * @throws InvalidDatabaseException if there were malformed lines in the file.
     */
    public List<Task> loadTasks() throws IOException, InvalidDatabaseException {

        /*
            here's the ideal scenario:

            no exceptions. return Pair<List<Task>, List<Integer>>; first is the list of
            parsed tasks, second is the list of failed tasks.

            but that's too much effort given this useless language, so if there's a malformed
            line in the database just abort the whole thing.
        */

        var path = Path.of(this.databasePath);

        if (!Files.exists(path)) {

            // just return an empty list. we'll create the file when we save.
            return new ArrayList<Task>();
        }

        // the file probably exists now.
        // first line indicates version. java streams are dumb, so this will have to do.
        var dbVersion = Files.lines(path)
            .findFirst()
            .flatMap(x -> {
                try {
                    return Optional.of(Integer.parseInt(x));
                } catch (NumberFormatException e) {
                    return Optional.empty();
                }
            })
            .orElse(DATABASE_VERSION);

        if (dbVersion != DATABASE_VERSION) {
            throw new InvalidDatabaseException(String.format(
                "version mismatch; expected '%d', found '%d'", DATABASE_VERSION, dbVersion
            ));
        }

        var xs = Files.lines(path).collect(Collectors.toList());

        // if there's no lines then return an empty list as well.
        if (xs.isEmpty()) {
            return new ArrayList<Task>();
        }

        // TODO: make and use a stream zipper to make this code less obtuse
        // one might note the flagrant use of Collectors.toList() here, it's because java
        // streams suck.

        // TODO: '|' character needs to be escaped
        var ret = xs.subList(1, xs.size())
            .stream()
            // java is a bad language.
            .map(new Function<String, Either<String, Task>>() {

                @Override
                public Either<String, Task> apply(String line) {
                    /*
                        format:
                        <type> <doneness> [date string] "|" <title> "|" <description>
                        <type>       :: char (ascii char)
                                        'T' = todo
                                        'D' = deadline
                                        'E' = event

                        <doneness>   :: char (ascii char)
                                        '1' = done
                                        '0' = rare
                    */

                    var type = line.charAt(0);
                    var asdf = line.charAt(1);

                    var done = false;

                    // time for some 3Head java
                    if (asdf == '1') {
                        done = true;
                    } else if (asdf == '0') {
                        done = false;
                    } else {
                        // ideally we'd tell the user the line number, but given that:
                        // 1. java streams suck, so there's no way to get the index of this line
                        // 2. they're not supposed to edit the file anyway,
                        // let's just not bother.
                        return Either.left(String.format("invalid doneness '%c' (expected either '0' or '1')", asdf));
                    }

                    var bits = splitTitleAndDescription(line);

                    // TODO: clean up this code
                    Task task = null;
                    if (type == 'T') {

                        var title = bits[1];
                        var desc  = bits[2];

                        task = new Todo(title, desc);

                    } else if (type == 'D' || type == 'E') {

                        try {
                            var date = TaskParser.parseDate(bits[0].substring(2));
                            var title = bits[1];
                            var desc  = bits[2];

                            if (type == 'D') {
                                task = new Deadline(title, desc, date);
                            } else {
                                task = new Event(title, desc, date);
                            }
                        } catch (InvalidInputException e) {
                            return Either.left(e.toString());
                        }
                    } else {
                        return Either.left(String.format("invalid type '%c' (expected one of 'T', 'D', 'E')", type));
                    }

                    assert task != null;

                    if (done) {
                        task.markAsDone();
                    }

                    return Either.right(task);
                }
            })
            .collect(Collectors.toList());

        Optional<Either<String, Task>> error = null;
        if ((error = ret.stream().filter(Either::isLeft).findFirst()).isPresent()) {
            throw new InvalidDatabaseException(error.get().fromLeft());
        } else {
            return ret.stream()
                .filter(Either::isRight)
                .map(Either::fromRight)
                .collect(Collectors.toList());
        }
    }



    /**
     * Saves the list of tasks to disk.
     *
     * @param list the list of tasks.
     * @throws IOException if there were errors creating or writing the file.
     */
    public void saveTasks(List<Task> list) throws IOException {

        var path = Path.of(this.databasePath);

        if (!Files.exists(path)) {

            if (!Files.exists(path.getParent())) {
                // this is apparently equivalent to `mkdir -p`
                Files.createDirectories(path.getParent());
            }
            Files.createFile(path);
        }

        Files.write(path,
            Stream.concat(
                Stream.of(String.format("%d", DATABASE_VERSION)),
                list.stream()
                    .map(task -> {

                        var title = task.getTitle();
                        var desc  = task.getDescription();

                        // escape any pipes in the content
                        title = title.replace("|", "\\|");
                        desc  = desc.replace("|", "\\|");

                        if (task instanceof Todo) {

                            return String.format("%c%c|%s|%s", 'T',
                                task.isDone() ? '1' : '0',
                                title, desc);

                        } else if (task instanceof Event) {

                            var event = (Event) task;
                            return String.format("%c%c%s|%s|%s", 'E',
                                task.isDone() ? '1' : '0',
                                event.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                title, desc);

                        } else if (task instanceof Deadline) {

                            var deadline = (Deadline) task;
                            return String.format("%c%c%s|%s|%s", 'D',
                                task.isDone() ? '1' : '0',
                                deadline.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                title, desc);
                        } else {
                            // asdf?!
                            return "";
                        }
                    })
            )
            .collect(Collectors.toList())
        );
    }
}
