package io.github.tkmtwo.dt;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import io.github.tkmtwo.dt.format.IsoDateTimeFormatters;




@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalDateTimesTest {
  
  private static final LocalDateTime REFERENCE_LDT = LocalDateTime.of(1981, 8, 3, 4, 5, 5, 333);
  
  @Test
  public void test0010Floor() {
    //           LocalDateTime.of(1981, 8, 3, 4, 5, 5, 333);
    assertEquals(LocalDateTime.of(1981, 8, 3, 4, 5, 5, 0),
                 LocalDateTimes.floor(REFERENCE_LDT, ChronoUnit.SECONDS));
    assertEquals(LocalDateTime.of(1981, 8, 3, 4, 5, 0, 0),
                 LocalDateTimes.floor(REFERENCE_LDT, ChronoUnit.MINUTES));
    assertEquals(LocalDateTime.of(1981, 8, 3, 4, 0, 0, 0),
                 LocalDateTimes.floor(REFERENCE_LDT, ChronoUnit.HOURS));
    assertEquals(LocalDateTime.of(1981, 8, 3, 0, 0, 0, 0),
                 LocalDateTimes.floor(REFERENCE_LDT, ChronoUnit.DAYS));
    assertEquals(LocalDateTime.of(1981, 8, 1, 0, 0, 0, 0),
                 LocalDateTimes.floor(REFERENCE_LDT, ChronoUnit.MONTHS));
    assertEquals(LocalDateTime.of(1981, 1, 1, 0, 0, 0, 0),
                 LocalDateTimes.floor(REFERENCE_LDT, ChronoUnit.YEARS));
  }
  
  @Test
  public void test0010Ceiling() {
    //           LocalDateTime.of(1981, 8, 3, 4, 5, 5, 333);
    assertEquals(LocalDateTime.of(1981, 8, 3, 4, 5, 6, 0),
                 LocalDateTimes.ceiling(REFERENCE_LDT, ChronoUnit.SECONDS));
    assertEquals(LocalDateTime.of(1981, 8, 3, 4, 6, 0, 0),
                 LocalDateTimes.ceiling(REFERENCE_LDT, ChronoUnit.MINUTES));
    assertEquals(LocalDateTime.of(1981, 8, 3, 5, 0, 0, 0),
                 LocalDateTimes.ceiling(REFERENCE_LDT, ChronoUnit.HOURS));
    assertEquals(LocalDateTime.of(1981, 8, 4, 0, 0, 0, 0),
                 LocalDateTimes.ceiling(REFERENCE_LDT, ChronoUnit.DAYS));
    assertEquals(LocalDateTime.of(1981, 9, 1, 0, 0, 0, 0),
                 LocalDateTimes.ceiling(REFERENCE_LDT, ChronoUnit.MONTHS));
    assertEquals(LocalDateTime.of(1982, 1, 1, 0, 0, 0, 0),
                 LocalDateTimes.ceiling(REFERENCE_LDT, ChronoUnit.YEARS));
    
  }
  
  
  @Test
  public void test0030Calculate() {
    LocalDateTime ldt = LocalDateTime.of(1970, 1, 1, 0, 0, 0, 0);
    
    assertEquals("1970-01-01T00:00:00", IsoDateTimeFormatters.LOCAL_DATETIME_EXTENDED.format(ldt));
    assertEquals(LocalDateTime.of(1970, 1, 1, 1, 0, 0, 0),
                 LocalDateTimes.calculate(ldt, "+1hours"));
    assertEquals(LocalDateTime.of(1970, 1, 1, 1, 0, 0, 0),
                 LocalDateTimes.calculate(ldt, " +1hours"));
    assertEquals(LocalDateTime.of(1970, 1, 1, 1, 0, 0, 0),
                 LocalDateTimes.calculate(ldt, "+1hours "));
    assertEquals(LocalDateTime.of(1970, 1, 1, 1, 0, 0, 0),
                 LocalDateTimes.calculate(ldt, " +1hours "));
    
    assertEquals(LocalDateTime.of(1970, 1, 1, 1, 30, 0, 0),
                 LocalDateTimes.calculate(ldt, "+1hours+30minutes"));
    assertEquals(LocalDateTime.of(1970, 1, 1, 0, 30, 0, 0),
                 LocalDateTimes.calculate(ldt, "+1hours-30minutes"));
    
    assertEquals(LocalDateTime.of(1970, 1, 1, 1, 0, 0, 0),
                 LocalDateTimes.calculate(ldt, "+60minutes"));
    assertEquals(LocalDateTime.of(1969, 12, 25, 1, 0, 0, 0),
                 LocalDateTimes.calculate(ldt, " -7days +1hours "));
    
    assertEquals(LocalDateTime.of(1970, 1, 1, 0, 0, 0, 0),
                 LocalDateTimes.calculate(ldt, "-1hours+60minutes-3600seconds+3600000millis"));
    assertEquals(LocalDateTime.of(1970, 1, 1, 0, 0, 0, 0),
                 LocalDateTimes.calculate(ldt, "   -1hours +60minutes -3600seconds +3600000millis    "));
    
    
    
    LocalDateTime ldtHour = LocalDateTimes.floor(ChronoUnit.HOURS);
    assertEquals(ldtHour,
                 LocalDateTimes.calculate("hours"));
    assertEquals(ldtHour,
                 LocalDateTimes.calculate(" hours "));
    assertEquals(ldtHour,
                 LocalDateTimes.calculate("hours -1hours +3600seconds"));
    assertEquals(ldtHour,
                 LocalDateTimes.calculate("  hours +1hours -3600seconds "));
  }
  
  
  
}
