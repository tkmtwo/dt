package io.github.tkmtwo.dt.format;

import static com.google.common.base.Strings.emptyToNull;
import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.DAY_OF_WEEK;
import static java.time.temporal.ChronoField.DAY_OF_YEAR;
import static java.time.temporal.ChronoField.HOUR_OF_DAY;
import static java.time.temporal.ChronoField.MINUTE_OF_HOUR;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;
import static java.time.temporal.ChronoField.NANO_OF_SECOND;
import static java.time.temporal.ChronoField.SECOND_OF_MINUTE;
import static java.time.temporal.ChronoField.YEAR;

import com.google.common.base.CharMatcher;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.format.SignStyle;
import java.util.Locale;

public final class IsoDateTimeFormatters {
  
  
  ////
  //// EXTENDED
  ////
  public static final DateTimeFormatter LOCAL_DATE_EXTENDED;
  static {
    DateTimeFormatter localDateExtended = new DateTimeFormatterBuilder()
      .appendValue(YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
      .appendLiteral('-')
      .appendValue(MONTH_OF_YEAR, 2)
      .appendLiteral('-')
      .appendValue(DAY_OF_MONTH, 2)
      .toFormatter();
    
    LOCAL_DATE_EXTENDED =
      localDateExtended
      .withResolverStyle(ResolverStyle.STRICT)
      .withChronology(IsoChronology.INSTANCE)
      .withZone(ZoneId.of("Z"));
  }
  
  public static final DateTimeFormatter LOCAL_TIME_EXTENDED;
  static {
    DateTimeFormatter localTimeExtended = new DateTimeFormatterBuilder()
      .appendValue(HOUR_OF_DAY, 2)
      .appendLiteral(':')
      .appendValue(MINUTE_OF_HOUR, 2)
      //.optionalStart()
      .appendLiteral(':')
      .appendValue(SECOND_OF_MINUTE, 2)
      //.optionalStart() //x
      //.appendFraction(NANO_OF_SECOND, 0, 9, true) //x
      .toFormatter();
    
    LOCAL_TIME_EXTENDED =
      localTimeExtended
      .withResolverStyle(ResolverStyle.STRICT)
      .withChronology(IsoChronology.INSTANCE)
      .withZone(ZoneId.of("Z"));
  }
  
  
  public static final DateTimeFormatter LOCAL_DATETIME_EXTENDED;
  static {
    DateTimeFormatter localDateTimeExtended = new DateTimeFormatterBuilder()
      .parseCaseInsensitive()
      .append(LOCAL_DATE_EXTENDED)
      .appendLiteral('T')
      .append(LOCAL_TIME_EXTENDED)
      .toFormatter();
    
    LOCAL_DATETIME_EXTENDED =
      localDateTimeExtended
      .withResolverStyle(ResolverStyle.STRICT)
      .withChronology(IsoChronology.INSTANCE)
      .withZone(ZoneId.of("Z"));
  }
  
  public static final DateTimeFormatter OFFSET_DATETIME_EXTENDED;
  static {
    DateTimeFormatter offsetDateTimeExtended = new DateTimeFormatterBuilder()
      .parseCaseInsensitive()
      .append(LOCAL_DATETIME_EXTENDED)
      .appendOffsetId()
      .toFormatter();
    
    OFFSET_DATETIME_EXTENDED = 
      offsetDateTimeExtended
      .withResolverStyle(ResolverStyle.STRICT)
      .withChronology(IsoChronology.INSTANCE)
      .withZone(ZoneId.of("Z"));
    
  }
  
  public static final DateTimeFormatter ZONED_DATETIME_EXTENDED;
  static {
    DateTimeFormatter zonedDateTimeExtended = new DateTimeFormatterBuilder()
      .parseCaseInsensitive()
      .append(LOCAL_DATETIME_EXTENDED)
      //.appendLiteral(' ')
      .appendZoneId()
      .toFormatter();
    
    ZONED_DATETIME_EXTENDED =
      zonedDateTimeExtended
      .withResolverStyle(ResolverStyle.STRICT)
      .withChronology(IsoChronology.INSTANCE)
      .withZone(ZoneId.of("Z"));
    
  }
  
  
  
  
  ////
  //// BASIC
  ////
  public static final DateTimeFormatter LOCAL_DATE_BASIC;
  static {
    DateTimeFormatter localDateBasic = new DateTimeFormatterBuilder()
      .appendValue(YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
      .appendValue(MONTH_OF_YEAR, 2)
      .appendValue(DAY_OF_MONTH, 2)
      .toFormatter();
    
    LOCAL_DATE_BASIC =
      localDateBasic
      .withResolverStyle(ResolverStyle.STRICT)
      .withChronology(IsoChronology.INSTANCE)
      .withZone(ZoneId.of("Z"));
  }
  
  public static final DateTimeFormatter LOCAL_TIME_BASIC;
  static {
    DateTimeFormatter localTimeBasic = new DateTimeFormatterBuilder()
      .appendValue(HOUR_OF_DAY, 2)
      .appendValue(MINUTE_OF_HOUR, 2)
      //.optionalStart()
      .appendValue(SECOND_OF_MINUTE, 2)
      //.optionalStart() //x
      //.appendFraction(NANO_OF_SECOND, 0, 9, true) //x
      .toFormatter();
    
    LOCAL_TIME_BASIC =
      localTimeBasic
      .withResolverStyle(ResolverStyle.STRICT)
      .withChronology(IsoChronology.INSTANCE)
      .withZone(ZoneId.of("Z"));
  }
  
  
  public static final DateTimeFormatter LOCAL_DATETIME_BASIC;
  static {
    DateTimeFormatter localDateTimeBasic = new DateTimeFormatterBuilder()
      .parseCaseInsensitive()
      .append(LOCAL_DATE_BASIC)
      .appendLiteral('T')
      .append(LOCAL_TIME_BASIC)
      .toFormatter();
    
    LOCAL_DATETIME_BASIC =
      localDateTimeBasic
      .withResolverStyle(ResolverStyle.STRICT)
      .withChronology(IsoChronology.INSTANCE)
      .withZone(ZoneId.of("Z"));
  }
  
  public static final DateTimeFormatter OFFSET_DATETIME_BASIC;
  static {
    DateTimeFormatter offsetDateTimeBasic = new DateTimeFormatterBuilder()
      .parseCaseInsensitive()
      .append(LOCAL_DATETIME_BASIC)
      .appendOffsetId()
      .toFormatter();
    
    OFFSET_DATETIME_BASIC = 
      offsetDateTimeBasic
      .withResolverStyle(ResolverStyle.STRICT)
      .withChronology(IsoChronology.INSTANCE)
      .withZone(ZoneId.of("Z"));
    
  }
  
  public static final DateTimeFormatter ZONED_DATETIME_BASIC;
  static {
    DateTimeFormatter zonedDateTimeBasic = new DateTimeFormatterBuilder()
      .parseCaseInsensitive()
      .append(LOCAL_DATETIME_BASIC)
      //.appendLiteral(' ')
      .appendZoneId()
      .toFormatter();
    
    ZONED_DATETIME_BASIC =
      zonedDateTimeBasic
      .withResolverStyle(ResolverStyle.STRICT)
      .withChronology(IsoChronology.INSTANCE)
      .withZone(ZoneId.of("Z"));
    
  }
  


  private static final CharMatcher ASCII_DIGITS =
    CharMatcher.inRange('0', '9');
  private static final CharMatcher DATE_TIME_SEPARATOR =
    CharMatcher.anyOf("T ");
  private static final CharMatcher OFFSET_SEPARATOR =
    CharMatcher.anyOf("+-");
  private static final CharMatcher ZONE_SEPARATOR =
    CharMatcher.anyOf(" ");
  

  public static DateTimeFormatter pickParser(String s) {
    String ts = emptyToNull(s);
    if (ts == null) { return null; }
    
    int tsLength = ts.length();

    //  yyyy-MM-ddTHH:mm:ss+00:00      ISO Extended with offset
    //  yyyy-MM-ddTHH:mm:ss zone       ISO Extended with zone
    //  yyyy-MM-ddTHH:mm:ss            ISO Extended with offset (local)
    //  yyyy-MM-ddTHH:mm:ssZ           ISO Extended zero offset

    //  yyyyMMddTHHmmss+00:00      ISO Extended with offset
    //  yyyyMMddTHHmmss zone       ISO Extended with zone
    //  yyyyMMddTHHmmss            ISO Extended with offset (local)
    //  yyyyMMddTHHmmssZ           ISO Extended zero offset


    //counting
    //  25 yyyy-MM-ddTHH:mm:ss+00:00      ISO Extended with offset
    //  21 yyyy-MM-ddTHH:mm:ss zone       ISO Extended with zone
    //  19 yyyy-MM-ddTHH:mm:ss            ISO Extended with offset (local)
    //  20 yyyy-MM-ddTHH:mm:ssZ           ISO Extended zero offset

    //  21 yyyyMMddTHHmmss+00:00      ISO Extended with offset
    //  17 yyyyMMddTHHmmss zone       ISO Extended with zone
    //  15 yyyyMMddTHHmmss            ISO Extended with offset (local)
    //  16 yyyyMMddTHHmmssZ           ISO Extended zero offset


    //counting sorted
    //  x 15 yyyyMMddTHHmmss            ISO Extended with offset (local)
    //  x 16 yyyyMMddTHHmmssZ           ISO Extended zero offset
    //  x 17 yyyyMMddTHHmmss zone       ISO Extended with zone
    //  x 19 yyyy-MM-ddTHH:mm:ss            ISO Extended with offset (local)
    //  x 20 yyyy-MM-ddTHH:mm:ssZ           ISO Extended zero offset
    //  x 21 yyyy-MM-ddTHH:mm:ss zone       ISO Extended with zone
    //  x 21 yyyyMMddTHHmmss+00:00      ISO Extended with offset
    //  x 25 yyyy-MM-ddTHH:mm:ss+00:00      ISO Extended with offset
    
    
    // 25 yyyy-MM-ddTHH:mm:ss+00:00    OFFSET_DATETIME_EXTENDED
    // 21 yyyyMMddTHHmmss+00:00        OFFSET_DATETIME_BASIC
    // 21 yyyy-MM-ddTHH:mm:ss zone     ZONED_DATETIME_EXTENDED
    // 20 yyyy-MM-ddTHH:mm:ssZ         OFFSET_DATETIME_EXTENDED (Z)
    // 19 yyyy-MM-ddTHH:mm:ss          LOCAL_DATETIME_EXTENDED
    // 17 yyyyMMddTHHmmss zone         ZONED_DATETIME_BASIC
    // 16 yyyyMMddTHHmmssZ             OFFSET_DATETIME_BASIC (Z)
    // 15 yyyyMMddTHHmmss              LOCAL_DATETIME_BASIC
    


    //    
    if (tsLength >= 25 && OFFSET_SEPARATOR.matches(ts.charAt(19))) {
      //
      // 25 yyyy-MM-ddTHH:mm:ss+00:00  OFFSET_DATETIME_EXTENDED
      //
      //return (ts.charAt(10) == 'T') ? OFFSET_DATETIME_EXTENDED : OFFSET_DATETIME_EXTENDED_NOTEE;
      return OFFSET_DATETIME_EXTENDED;
      
    } else if (tsLength >= 21 && OFFSET_SEPARATOR.matches(ts.charAt(15))) {
      //
      // 21 yyyyMMddTHHmmss+00:00  OFFSET_DATETIME_BASIC
      //
      //return (ts.charAt(8) == 'T') ? OFFSET_DATETIME_BASIC : OFFSET_DATETIME_BASIC_NOTEE;
      return OFFSET_DATETIME_BASIC;
      
    } else if (tsLength >= 21 && ZONE_SEPARATOR.matches(ts.charAt(19))) {
      //
      // 21 yyyy-MM-ddTHH:mm:ss zone    ZONED_DATETIME_EXTENDED
      //      
      //return (ts.charAt(10) == 'T') ? ZONED_DATETIME_EXTENDED : ZONED_DATETIME_EXTENDED_NOTEE;
      return ZONED_DATETIME_EXTENDED;
      
    } else if (tsLength == 20 && DATE_TIME_SEPARATOR.matches(ts.charAt(10))) {
      //
      // 20 yyyy-MM-ddTHH:mm:ssZ OFFSET_DATETIME_EXTENDED (Z)
      //
      //return (ts.charAt(10) == 'T') ? OFFSET_DATETIME_EXTENDED : OFFSET_DATETIME_EXTENDED_NOTEE;
      return OFFSET_DATETIME_EXTENDED;
      
    } else if (tsLength == 19 && DATE_TIME_SEPARATOR.matches(ts.charAt(10))) {
      //
      // 19 yyyy-MM-ddTHH:mm:ss  LOCAL_DATETIME_EXTENDED
      //
      //return (ts.charAt(10) == 'T') ? LOCAL_DATETIME_EXTENDED : LOCAL_DATETIME_EXTENDED_NOTEE;
      return LOCAL_DATETIME_EXTENDED;
      
    } else if (tsLength >= 17 && DATE_TIME_SEPARATOR.matches(ts.charAt(8))) {
      //
      // 17 yyyyMMddTHHmmss zone   ZONED_DATETIME_BASIC
      //
      //return (ts.charAt(8) == 'T') ? ZONED_DATETIME_BASIC : ZONED_DATETIME_BASIC_NOTEE;
      return ZONED_DATETIME_BASIC;
      
    } else if (tsLength == 16 && DATE_TIME_SEPARATOR.matches(ts.charAt(8))) {
      //
      // 16 yyyyMMddTHHmmssZ  OFFSET_DATETIME_BASIC (Z)
      //
      //return (ts.charAt(8) == 'T') ? OFFSET_DATETIME_BASIC : OFFSET_DATETIME_BASIC_NOTEE;
      return OFFSET_DATETIME_BASIC;
      
    } else if (tsLength == 15 && DATE_TIME_SEPARATOR.matches(ts.charAt(8))) {
      //
      // 15 yyyyMMddTHHmmss LOCAL_DATETIME_BASIC
      //
      //return (ts.charAt(8) == 'T') ? LOCAL_DATETIME_BASIC : LOCAL_DATETIME_BASIC_NOTEE;
      return LOCAL_DATETIME_BASIC;
    }
    
    return null;

  }
  
  private static Long getLong(String s) {
    if (ASCII_DIGITS.matchesAllOf(s)) {
      return Long.parseLong(s);
    }
    return null;
  }
  
  public static LocalDateTime parseLocalDateTime(String s) {
    DateTimeFormatter dtf = pickParser(s);
    if (dtf != null) {
      return LocalDateTime.parse(s, dtf);
    }
    
    Long epochSecs = getLong(s);
    if (epochSecs != null) {
      Instant instant = Instant.ofEpochSecond(epochSecs);
      return LocalDateTime.ofInstant(instant, ZoneId.of("Z"));
    }
    
    throw new IllegalArgumentException(String.format("Could not parse LocalDateTime from '%s'",
                                                     s));
  }
    

  public static OffsetDateTime parseOffsetDateTime(String s) {
    DateTimeFormatter dtf = pickParser(s);
    if (dtf != null) {
      return OffsetDateTime.parse(s, dtf);
    }
    
    Long epochSecs = getLong(s);
    if (epochSecs != null) {
      Instant instant = Instant.ofEpochSecond(epochSecs);
      return OffsetDateTime.ofInstant(instant, ZoneId.of("Z"));
    }
    
    throw new IllegalArgumentException(String.format("Could not parse OffsetDateTime from '%s'",
                                                     s));
  }

  public static ZonedDateTime parseZonedDateTime(String s) {
    DateTimeFormatter dtf = pickParser(s);
    if (dtf != null) {
      return ZonedDateTime.parse(s, dtf);
    }
    
    Long epochSecs = getLong(s);
    if (epochSecs != null) {
      Instant instant = Instant.ofEpochSecond(epochSecs);
      return ZonedDateTime.ofInstant(instant, ZoneId.of("Z"));
    }
    
    throw new IllegalArgumentException(String.format("Could not parse ZonedDateTime from '%s'",
                                                     s));
  }
  
  
  
  
}
