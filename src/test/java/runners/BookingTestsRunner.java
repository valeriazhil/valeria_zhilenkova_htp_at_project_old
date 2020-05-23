package runners;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import allTests.bookingTests.BookingParisTest;




@RunWith(Suite.class)

@Suite.SuiteClasses({BookingParisTest.class})

public class BookingTestsRunner {
}
