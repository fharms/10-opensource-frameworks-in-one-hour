package dk.presentation.flogger;

import com.google.common.flogger.FluentLogger;
import com.google.common.flogger.LoggerConfig;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.stream.IntStream;

import static com.google.common.flogger.LazyArgs.lazy;
import static java.util.concurrent.TimeUnit.SECONDS;

public class FloggerTest {

    private static final FluentLogger logger = FluentLogger.forEnclosingClass();


    @Test
    public void testSimpleInfoLogging() {
        logger
            .atInfo()
            .log("Log Info message with: %s", "flogger");
    }

    @Test
    public void testSimpleLogging() {
        logger
            .atFine()
            .log("Log message with: %s", "flogger");
    }


    @Test
    public void testExtensiveLogging() {
        IntStream.range(0, 30).forEach(value -> {
           logger.atSevere()
                .atMostEvery(5, SECONDS)
                .log("Value: %s", lazy(() -> "¯\\_(ツ)_//¯"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        });
    }

    @Test
    public void testCondition() {
        IntStream.range(0, 20)
            .forEach(value -> logger.atInfo().every(5).log("My log message %s", "with condition"));
    }

    @Test
    public void testStacktraceLogging() {
        LoggerConfig.of(logger).setLevel(Level.SEVERE);
        logger.atSevere()
            .withCause(new IllegalArgumentException())
            .log("¯\\_(ツ)_//¯");
    }
}
