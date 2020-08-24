// Database.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura;

import java.util.List;
import java.util.Optional;
import java.nio.file.Path;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.function.Function;
import java.time.format.DateTimeFormatter;

import ikura.task.Task;
import ikura.task.Todo;
import ikura.task.Event;
import ikura.task.Deadline;
import ikura.task.DatedTask;

import ikura.util.Either;
import ikura.util.InvalidInputException;
import ikura.util.InvalidDatabaseException;

public class Database {

    private static final int DATABASE_VERSION = 1;

    private final String databasePath;

    public Database(String path) {
        this.databasePath = path;
    }

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

        var ret = xs.subList(1, xs.size())
            .stream()
            // java is a bad language.
            .map(new Function<String, Either<String, Task>>() {

                @Override
                public Either<String, Task> apply(String line) {
                    /*
                        format:
                        <type> <doneness> [date string] "|" <description>
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

                    Task task = null;
                    if (type == 'T') {

                        var desc = line.substring(line.indexOf('|') + 1);
                        task = new Todo(desc);

                    } else if (type == 'D' || type == 'E') {

                        try {
                            var date = DatedTask.parseDate(line.substring(2, line.indexOf('|')));
                            var desc = line.substring(line.indexOf('|') + 1);

                            if (type == 'D') {
                                task = new Deadline(desc, date);
                            } else {
                                task = new Event(desc, date);
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
                        if (task instanceof Todo) {

                            return String.format("%c%c|%s", 'T',
                                task.isDone() ? '1' : '0',
                                task.getName());

                        } else if (task instanceof Event) {

                            return String.format("%c%c%s|%s", 'E',
                                task.isDone() ? '1' : '0',
                                ((Event) task).getEventDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                task.getName());

                        } else if (task instanceof Deadline) {

                            return String.format("%c%c%s|%s", 'D',
                                task.isDone() ? '1' : '0',
                                ((Deadline) task).getDeadline().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                task.getName());
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
