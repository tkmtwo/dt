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
public class ZonedDateTimesTest {
  
  private static final ZonedDateTime REFERENCE_DT = ZonedDateTime.of(1981, 8, 3, 4, 5, 5, 333, ZoneId.of("Z"));
  
  @Test
  public void test0010Floor() {
    //           ZonedDateTime.of(1981, 8, 3, 4, 5, 5, 333);
    assertEquals(ZonedDateTime.of(1981, 8, 3, 4, 5, 5, 0, ZoneId.of("Z")),
                 ZonedDateTimes.floor(REFERENCE_DT, ChronoUnit.SECONDS));
    assertEquals(ZonedDateTime.of(1981, 8, 3, 4, 5, 0, 0, ZoneId.of("Z")),
                 ZonedDateTimes.floor(REFERENCE_DT, ChronoUnit.MINUTES));
    assertEquals(ZonedDateTime.of(1981, 8, 3, 4, 0, 0, 0, ZoneId.of("Z")),
                 ZonedDateTimes.floor(REFERENCE_DT, ChronoUnit.HOURS));
    assertEquals(ZonedDateTime.of(1981, 8, 3, 0, 0, 0, 0, ZoneId.of("Z")),
                 ZonedDateTimes.floor(REFERENCE_DT, ChronoUnit.DAYS));
    assertEquals(ZonedDateTime.of(1981, 8, 1, 0, 0, 0, 0, ZoneId.of("Z")),
                 ZonedDateTimes.floor(REFERENCE_DT, ChronoUnit.MONTHS));
    assertEquals(ZonedDateTime.of(1981, 1, 1, 0, 0, 0, 0, ZoneId.of("Z")),
                 ZonedDateTimes.floor(REFERENCE_DT, ChronoUnit.YEARS));
  }
  
  @Test
  public void test0010Ceiling() {
    //           ZonedDateTime.of(1981, 8, 3, 4, 5, 5, 333);
    assertEquals(ZonedDateTime.of(1981, 8, 3, 4, 5, 6, 0, ZoneId.of("Z")),
                 ZonedDateTimes.ceiling(REFERENCE_DT, ChronoUnit.SECONDS));
    assertEquals(ZonedDateTime.of(1981, 8, 3, 4, 6, 0, 0, ZoneId.of("Z")),
                 ZonedDateTimes.ceiling(REFERENCE_DT, ChronoUnit.MINUTES));
    assertEquals(ZonedDateTime.of(1981, 8, 3, 5, 0, 0, 0, ZoneId.of("Z")),
                 ZonedDateTimes.ceiling(REFERENCE_DT, ChronoUnit.HOURS));
    assertEquals(ZonedDateTime.of(1981, 8, 4, 0, 0, 0, 0, ZoneId.of("Z")),
                 ZonedDateTimes.ceiling(REFERENCE_DT, ChronoUnit.DAYS));
    assertEquals(ZonedDateTime.of(1981, 9, 1, 0, 0, 0, 0, ZoneId.of("Z")),
                 ZonedDateTimes.ceiling(REFERENCE_DT, ChronoUnit.MONTHS));
    assertEquals(ZonedDateTime.of(1982, 1, 1, 0, 0, 0, 0, ZoneId.of("Z")),
                 ZonedDateTimes.ceiling(REFERENCE_DT, ChronoUnit.YEARS));
    
  }
  
  
  @Test
  public void test0030Calculate() {
    ZonedDateTime dt = ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneId.of("Z"));
    
    assertEquals("1970-01-01T00:00:00", IsoDateTimeFormatters.LOCAL_DATETIME_EXTENDED.format(dt));
    assertEquals(ZonedDateTime.of(1970, 1, 1, 1, 0, 0, 0, ZoneId.of("Z")),
                 ZonedDateTimes.calculate(dt, "+1hours"));
    assertEquals(ZonedDateTime.of(1970, 1, 1, 1, 0, 0, 0, ZoneId.of("Z")),
                 ZonedDateTimes.calculate(dt, " +1hours"));
    assertEquals(ZonedDateTime.of(1970, 1, 1, 1, 0, 0, 0, ZoneId.of("Z")),
                 ZonedDateTimes.calculate(dt, "+1hours "));
    assertEquals(ZonedDateTime.of(1970, 1, 1, 1, 0, 0, 0, ZoneId.of("Z")),
                 ZonedDateTimes.calculate(dt, " +1hours "));
    
    assertEquals(ZonedDateTime.of(1970, 1, 1, 1, 30, 0, 0, ZoneId.of("Z")),
                 ZonedDateTimes.calculate(dt, "+1hours+30minutes"));
    assertEquals(ZonedDateTime.of(1970, 1, 1, 0, 30, 0, 0, ZoneId.of("Z")),
                 ZonedDateTimes.calculate(dt, "+1hours-30minutes"));
    
    assertEquals(ZonedDateTime.of(1970, 1, 1, 1, 0, 0, 0, ZoneId.of("Z")),
                 ZonedDateTimes.calculate(dt, "+60minutes"));
    assertEquals(ZonedDateTime.of(1969, 12, 25, 1, 0, 0, 0, ZoneId.of("Z")),
                 ZonedDateTimes.calculate(dt, " -7days +1hours "));
    
    assertEquals(ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneId.of("Z")),
                 ZonedDateTimes.calculate(dt, "-1hours+60minutes-3600seconds+3600000millis"));
    assertEquals(ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneId.of("Z")),
                 ZonedDateTimes.calculate(dt, "   -1hours +60minutes -3600seconds +3600000millis    "));
    
    
    
    ZonedDateTime dtHour = ZonedDateTimes.floor(ChronoUnit.HOURS);
    assertEquals(dtHour,
                 ZonedDateTimes.calculate("hours"));
    assertEquals(dtHour,
                 ZonedDateTimes.calculate(" hours "));
    assertEquals(dtHour,
                 ZonedDateTimes.calculate("hours -1hours +3600seconds"));
    assertEquals(dtHour,
                 ZonedDateTimes.calculate("  hours +1hours -3600seconds "));
  }
  
  
  
}
