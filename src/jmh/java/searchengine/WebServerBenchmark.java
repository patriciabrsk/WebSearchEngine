package searchengine;

import java.io.IOException;
import java.net.BindException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class WebServerBenchmark {
    WebServer server;
    List<String> hitSearchTerms;
    List<String> missSearchTerms;
    SearchEngine searchList;

    @Param({"/Users/mahv/Documents/IP-project/myproject/data/enwiki-tiny.txt", "/Users/mahv/Documents/IP-project/myproject/data/enwiki-small.txt", "/Users/mahv/Documents/IP-project/myproject/data/enwiki-medium.txt", "/Users/mahv/Documents/IP-project/myproject/data/enwiki-large.txt"})
    String filename;

    @Setup(Level.Trial)
    public void setup() {
        try {
            var rnd = new Random();
            while (server == null) {
                try {
                    server = new WebServer(rnd.nextInt(60000) + 1024, filename);
                } catch (BindException e) {
                    // port in use. Try again
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // @Benchmark
    // public ArrayList<Website> measureAvgTime() throws InterruptedException {
    //     Probably not a good idea to search for the same thing all the time... oh well
    //     SearchEngine searchEngine = new SearchEngine();
    //     return searchEngine.searchList("denmark");
    // }
}
