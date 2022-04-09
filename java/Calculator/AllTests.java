package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestCalculate.class,
        TestWorkers.class,
        TestParser.class
})

final class AllTests {
}
