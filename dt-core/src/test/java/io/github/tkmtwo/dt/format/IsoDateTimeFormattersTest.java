package io.github.tkmtwo.dt.format;


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
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IsoDateTimeFormattersTest {

  /*
   * On Saturday, August 1, 1981, at 12:01 a.m. Eastern Time
   * MTV launched with the words "Ladies and gentlemen, rock and roll."
   *
   * We'll use 1981-08-01T00:01:01.123456789 in America/New_York time
   *
   */

  //This is 1981-08-01T04:01:01Z  
  private static long EPOCH_SECS = 365486461L;
  private static long EPOCH_MILLIS = 365486461000L;
  private static long EPOCH_NANOS = 365486461000000L;

  private static final LocalDate localDate = LocalDate.of(1981, 8, 1);
  private static final LocalTime localTime = LocalTime.of(4, 1, 1);
  private static final LocalDateTime localDateTime = LocalDateTime.of(1981, 8, 1, 4, 1, 1);
  private static final OffsetDateTime offsetDateTime = OffsetDateTime.of(1981, 8, 1, 4, 1, 1, 0, ZoneOffset.UTC);
  private static final ZonedDateTime zonedDateTime = ZonedDateTime.of(1981, 8, 1, 4, 1, 1, 0, ZoneId.of("Z"));
  
  
  @Test
  public void test0000Launch() {
    ZonedDateTime zdtEastern = ZonedDateTime.of(1981, 8, 1, 0, 1, 1, 0, ZoneId.of("America/New_York"));
    ZonedDateTime zdtZ       = ZonedDateTime.of(1981, 8, 1, 4, 1, 1, 0, ZoneId.of("Z"));
    assertEquals(zdtEastern.toInstant(), zdtZ.toInstant());
    assertEquals(zdtEastern, zdtZ.withZoneSameInstant(ZoneId.of("America/New_York")));
    assertEquals(zdtZ, zdtEastern.withZoneSameInstant(ZoneId.of("Z")));
    
    assertEquals(zdtEastern.toInstant(), Instant.ofEpochSecond(EPOCH_SECS));
  }
  
  @Test
  public void test0100LocalDateExtended() {
    assertEquals(localDate, LocalDate.parse("1981-08-01", IsoDateTimeFormatters.LOCAL_DATE_EXTENDED));
    assertEquals("1981-08-01", IsoDateTimeFormatters.LOCAL_DATE_EXTENDED.format(localDate));
  }
  
  @Test
  public void test0110LocalDateBasic() {
    assertEquals(localDate, LocalDate.parse("19810801", IsoDateTimeFormatters.LOCAL_DATE_BASIC));
    assertEquals("19810801", IsoDateTimeFormatters.LOCAL_DATE_BASIC.format(localDate));
  }
  
  
  
  
  
  
  @Test
  public void test0200LocalTimeExtended() {
    assertEquals(localTime, LocalTime.parse("04:01:01", IsoDateTimeFormatters.LOCAL_TIME_EXTENDED));
    assertEquals("04:01:01", IsoDateTimeFormatters.LOCAL_TIME_EXTENDED.format(localTime));
  }
  
  
  @Test
  public void test0210LocalTimeBasicBasic() {
    assertEquals(localTime, LocalTime.parse("040101", IsoDateTimeFormatters.LOCAL_TIME_BASIC));
    assertEquals("040101", IsoDateTimeFormatters.LOCAL_TIME_BASIC.format(localTime));
  }

  
  
  
  @Test
  public void test0300LocalDateTimeExtended() {
    assertEquals(localDateTime, LocalDateTime.parse("1981-08-01T04:01:01", IsoDateTimeFormatters.LOCAL_DATETIME_EXTENDED));
    assertEquals("1981-08-01T04:01:01", IsoDateTimeFormatters.LOCAL_DATETIME_EXTENDED.format(localDateTime));
  }
  
  @Test
  public void test0310LocalDateTimeBasic() {
    assertEquals(localDateTime, LocalDateTime.parse("19810801T040101", IsoDateTimeFormatters.LOCAL_DATETIME_BASIC));
    assertEquals("19810801T040101", IsoDateTimeFormatters.LOCAL_DATETIME_BASIC.format(localDateTime));
  }
  
  
  
  

  
  @Test
  public void test0400OffsetDateTimeExtended() {
    
    assertEquals(offsetDateTime, OffsetDateTime.parse("1981-08-01T04:01:01Z",      IsoDateTimeFormatters.OFFSET_DATETIME_EXTENDED));
    assertEquals(offsetDateTime.toInstant(),
                 ZonedDateTime.of(1981, 8, 1, 0, 1, 1, 0, ZoneId.of("America/New_York")).toInstant());
    
    assertEquals(offsetDateTime, OffsetDateTime.parse("1981-08-01T04:01:01+00:00", IsoDateTimeFormatters.OFFSET_DATETIME_EXTENDED));
    assertEquals(offsetDateTime, OffsetDateTime.parse("1981-08-01T04:01:01-00:00", IsoDateTimeFormatters.OFFSET_DATETIME_EXTENDED));

    assertEquals(offsetDateTime.toInstant(), OffsetDateTime.parse("1981-08-01T05:01:01+01:00", IsoDateTimeFormatters.OFFSET_DATETIME_EXTENDED).toInstant());
    assertEquals(offsetDateTime.toInstant(), OffsetDateTime.parse("1981-08-01T05:31:01+01:30", IsoDateTimeFormatters.OFFSET_DATETIME_EXTENDED).toInstant());

    assertEquals(offsetDateTime.toInstant(), OffsetDateTime.parse("1981-08-01T03:01:01-01:00", IsoDateTimeFormatters.OFFSET_DATETIME_EXTENDED).toInstant());
    assertEquals(offsetDateTime.toInstant(), OffsetDateTime.parse("1981-08-01T02:31:01-01:30", IsoDateTimeFormatters.OFFSET_DATETIME_EXTENDED).toInstant());

    assertEquals(offsetDateTime.toInstant(), OffsetDateTime.parse("1981-08-01T09:01:01+05:00", IsoDateTimeFormatters.OFFSET_DATETIME_EXTENDED).toInstant());
    assertEquals(offsetDateTime.toInstant(), OffsetDateTime.parse("1981-08-01T09:31:01+05:30", IsoDateTimeFormatters.OFFSET_DATETIME_EXTENDED).toInstant());

    assertEquals(offsetDateTime.toInstant(), OffsetDateTime.parse("1981-07-31T23:01:01-05:00", IsoDateTimeFormatters.OFFSET_DATETIME_EXTENDED).toInstant());
    assertEquals(offsetDateTime.toInstant(), OffsetDateTime.parse("1981-07-31T22:31:01-05:30", IsoDateTimeFormatters.OFFSET_DATETIME_EXTENDED).toInstant());

  }
  
  
  @Test
  public void test0410OffsetDateTimeBasic() {
    
    assertEquals(offsetDateTime, OffsetDateTime.parse("19810801T040101Z",      IsoDateTimeFormatters.OFFSET_DATETIME_BASIC));
    
    assertEquals(offsetDateTime, OffsetDateTime.parse("19810801T040101+00:00", IsoDateTimeFormatters.OFFSET_DATETIME_BASIC));
    assertEquals(offsetDateTime, OffsetDateTime.parse("19810801T040101-00:00", IsoDateTimeFormatters.OFFSET_DATETIME_BASIC));

    assertEquals(offsetDateTime.toInstant(), OffsetDateTime.parse("19810801T050101+01:00", IsoDateTimeFormatters.OFFSET_DATETIME_BASIC).toInstant());
    assertEquals(offsetDateTime.toInstant(), OffsetDateTime.parse("19810801T053101+01:30", IsoDateTimeFormatters.OFFSET_DATETIME_BASIC).toInstant());

    assertEquals(offsetDateTime.toInstant(), OffsetDateTime.parse("19810801T030101-01:00", IsoDateTimeFormatters.OFFSET_DATETIME_BASIC).toInstant());
    assertEquals(offsetDateTime.toInstant(), OffsetDateTime.parse("19810801T023101-01:30", IsoDateTimeFormatters.OFFSET_DATETIME_BASIC).toInstant());

    assertEquals(offsetDateTime.toInstant(), OffsetDateTime.parse("19810801T090101+05:00", IsoDateTimeFormatters.OFFSET_DATETIME_BASIC).toInstant());
    assertEquals(offsetDateTime.toInstant(), OffsetDateTime.parse("19810801T093101+05:30", IsoDateTimeFormatters.OFFSET_DATETIME_BASIC).toInstant());

    assertEquals(offsetDateTime.toInstant(), OffsetDateTime.parse("19810731T230101-05:00", IsoDateTimeFormatters.OFFSET_DATETIME_BASIC).toInstant());
    assertEquals(offsetDateTime.toInstant(), OffsetDateTime.parse("19810731T223101-05:30", IsoDateTimeFormatters.OFFSET_DATETIME_BASIC).toInstant());

  }
  
  
  
  
  
  
  
  
  @Test
  public void test0500ZonedDateTimeExtended() {
    assertEquals(zonedDateTime, ZonedDateTime.parse("1981-08-01T04:01:01Z", IsoDateTimeFormatters.ZONED_DATETIME_EXTENDED));

    assertEquals(zonedDateTime.toInstant(), ZonedDateTime.parse("1981-08-01T04:01:01Z", IsoDateTimeFormatters.ZONED_DATETIME_EXTENDED).toInstant());

    assertEquals(zonedDateTime.toInstant(), ZonedDateTime.parse("1981-08-01T00:01:01America/New_York", IsoDateTimeFormatters.ZONED_DATETIME_EXTENDED).toInstant());
    assertEquals(zonedDateTime.toInstant(), ZonedDateTime.parse("1981-08-01T00:01:01US/Eastern", IsoDateTimeFormatters.ZONED_DATETIME_EXTENDED).toInstant());

    assertEquals(zonedDateTime.toInstant(), ZonedDateTime.parse("1981-07-31T23:01:01America/Chicago", IsoDateTimeFormatters.ZONED_DATETIME_EXTENDED).toInstant());
    assertEquals(zonedDateTime.toInstant(), ZonedDateTime.parse("1981-07-31T23:01:01US/Central", IsoDateTimeFormatters.ZONED_DATETIME_EXTENDED).toInstant());
    
  }
  
  
  @Test
  public void test0510ZonedDateTimeBasic() {

    assertEquals(zonedDateTime, ZonedDateTime.parse("19810801T040101Z", IsoDateTimeFormatters.ZONED_DATETIME_BASIC));

    assertEquals(zonedDateTime.toInstant(), ZonedDateTime.parse("19810801T040101Z", IsoDateTimeFormatters.ZONED_DATETIME_BASIC).toInstant());

    assertEquals(zonedDateTime.toInstant(), ZonedDateTime.parse("19810801T000101America/New_York", IsoDateTimeFormatters.ZONED_DATETIME_BASIC).toInstant());
    assertEquals(zonedDateTime.toInstant(), ZonedDateTime.parse("19810801T000101US/Eastern", IsoDateTimeFormatters.ZONED_DATETIME_BASIC).toInstant());

    assertEquals(zonedDateTime.toInstant(), ZonedDateTime.parse("19810731T230101America/Chicago", IsoDateTimeFormatters.ZONED_DATETIME_BASIC).toInstant());
    assertEquals(zonedDateTime.toInstant(), ZonedDateTime.parse("19810731T230101US/Central", IsoDateTimeFormatters.ZONED_DATETIME_BASIC).toInstant());
    
  }


  @Test
  public void test0600PickParser() {
    assertTrue(IsoDateTimeFormatters.pickParser("yyyy-MM-ddTHH:mm:ss+00:00") == IsoDateTimeFormatters.OFFSET_DATETIME_EXTENDED);
    assertTrue(IsoDateTimeFormatters.pickParser("yyyyMMddTHHmmss+00:00")     == IsoDateTimeFormatters.OFFSET_DATETIME_BASIC);
    assertTrue(IsoDateTimeFormatters.pickParser("yyyy-MM-ddTHH:mm:ss zone")  == IsoDateTimeFormatters.ZONED_DATETIME_EXTENDED);
    assertTrue(IsoDateTimeFormatters.pickParser("yyyy-MM-ddTHH:mm:ssZ")      == IsoDateTimeFormatters.OFFSET_DATETIME_EXTENDED);
    assertTrue(IsoDateTimeFormatters.pickParser("yyyy-MM-ddTHH:mm:ss")       == IsoDateTimeFormatters.LOCAL_DATETIME_EXTENDED);
    assertTrue(IsoDateTimeFormatters.pickParser("yyyyMMddTHHmmss zone")      == IsoDateTimeFormatters.ZONED_DATETIME_BASIC);
    assertTrue(IsoDateTimeFormatters.pickParser("yyyyMMddTHHmmssZ")          == IsoDateTimeFormatters.OFFSET_DATETIME_BASIC);
    assertTrue(IsoDateTimeFormatters.pickParser("yyyyMMddTHHmmss")           == IsoDateTimeFormatters.LOCAL_DATETIME_BASIC);
  }
  
  
  
  @Test
  public void test0610PickParserResults() {

    //
    // LOCAL DATE TIME
    //
    //epoch secs
    assertEquals(localDateTime, IsoDateTimeFormatters.parseLocalDateTime(String.valueOf(EPOCH_SECS)));
    
    //extended
    assertEquals(localDateTime, IsoDateTimeFormatters.parseLocalDateTime("1981-08-01T04:01:01"));
    
    //basic
    assertEquals(localDateTime, IsoDateTimeFormatters.parseLocalDateTime("19810801T040101"));
    
    //
    // OFFSET DATE TIME
    //
    //epoch secs
    assertEquals(offsetDateTime, IsoDateTimeFormatters.parseOffsetDateTime(String.valueOf(EPOCH_SECS)));
    
    //extended
    assertEquals(offsetDateTime.toInstant(), IsoDateTimeFormatters.parseOffsetDateTime("1981-08-01T04:01:01+00:00").toInstant());
    assertEquals(offsetDateTime.toInstant(), IsoDateTimeFormatters.parseOffsetDateTime("1981-08-01T05:01:01+01:00").toInstant());
    assertEquals(offsetDateTime.toInstant(), IsoDateTimeFormatters.parseOffsetDateTime("1981-08-01T04:01:01-00:00").toInstant());
    assertEquals(offsetDateTime.toInstant(), IsoDateTimeFormatters.parseOffsetDateTime("1981-08-01T03:01:01-01:00").toInstant());
    assertEquals(offsetDateTime.toInstant(), IsoDateTimeFormatters.parseOffsetDateTime("1981-08-01T04:01:01Z").toInstant());
    
    //basic
    assertEquals(offsetDateTime.toInstant(), IsoDateTimeFormatters.parseOffsetDateTime("19810801T040101+00:00").toInstant());
    assertEquals(offsetDateTime.toInstant(), IsoDateTimeFormatters.parseOffsetDateTime("19810801T050101+01:00").toInstant());
    assertEquals(offsetDateTime.toInstant(), IsoDateTimeFormatters.parseOffsetDateTime("19810801T040101-00:00").toInstant());
    assertEquals(offsetDateTime.toInstant(), IsoDateTimeFormatters.parseOffsetDateTime("19810801T030101-01:00").toInstant());
    assertEquals(offsetDateTime.toInstant(), IsoDateTimeFormatters.parseOffsetDateTime("19810801T040101Z").toInstant());
    
    
    
    //
    // ZONED DATE TIME
    //
    //epoch secs
    assertEquals(zonedDateTime, IsoDateTimeFormatters.parseZonedDateTime(String.valueOf(EPOCH_SECS)));
    
    //extended
    assertEquals(zonedDateTime.toInstant(), IsoDateTimeFormatters.parseZonedDateTime("1981-08-01T04:01:01Z").toInstant());
    
    
    //basic
    assertEquals(zonedDateTime.toInstant(), IsoDateTimeFormatters.parseZonedDateTime("19810801T040101Z").toInstant());
    
    
  }
  
  
  
}
