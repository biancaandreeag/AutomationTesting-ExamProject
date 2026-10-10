package logger;

import com.aventstack.chaintest.plugins.ChainTestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

// toate logurile trec pe aici: fiecare test are fisierul lui in target/logs/tests,
// iar la final se unesc in AllTests.log (configurarea e in log4j2.xml)
public class LoggerUtility {

    private static final String testLogsPath = "target/logs/tests/";
    private static final String allLogsPath = "target/logs/AllTests.log";
    private static final Logger logger = LogManager.getLogger();

    // numele testului ajunge in log4j2.xml (Routing), de acolo stie in ce fisier sa scrie
    public static synchronized void startTestCase(String testName) {
        ThreadContext.put("testName", testName);
        logger.info("===== " + testName + " started =====");
    }

    public static synchronized void endTestCase(String testName) {
        logger.info("===== " + testName + " ended =====");
        ThreadContext.remove("testName");
    }

    // pasul apare si in log, si in raportul ChainTest
    public static synchronized void infoTest(String message) {
        logger.info(getCaller() + " - " + message);
        ChainTestListener.log(message);
    }

    public static synchronized void errorLog(String message) {
        logger.error(getCaller() + " - " + message);
        ChainTestListener.log("ERROR: " + message);
    }

    // clasa si metoda care au cerut logul; [3] pentru ca [1] e getCaller si [2] e infoTest / errorLog
    private static String getCaller() {
        StackTraceElement caller = Thread.currentThread().getStackTrace()[3];
        String className = caller.getClassName().substring(caller.getClassName().lastIndexOf('.') + 1);
        return className + "." + caller.getMethodName();
    }

    // la finalul suitei lipesc toate logurile intr-unul singur, ca sa nu deschid zeci de fisiere
    public static void mergeTestLogs() {
        File[] files = new File(testLogsPath).listFiles();
        if (files == null) {
            return;
        }
        try (PrintWriter writer = new PrintWriter(allLogsPath)) {
            for (File file : files) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        writer.println(line);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Logurile nu au putut fi unite: " + e.getMessage());
        }
    }
}
