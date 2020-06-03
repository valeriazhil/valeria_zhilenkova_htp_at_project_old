package runners.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.booking.junit.BookingParisTest;
import tests.booking.junit.BookingMoscowTest;
import tests.booking.junit.BookingOsloTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({BookingParisTest.class, BookingMoscowTest.class, BookingOsloTest.class})


public class BookingRunner {
}
