package graph;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class GraphTestRunner {
    public static void main(String [] args) {
        Result result = JUnitCore.runClasses(GraphUnitTests.class);
        result.getFailures().forEach((failure) -> {
            System.out.println(failure.toString());
        });
        System.out.println(result.wasSuccessful());
    }
}

