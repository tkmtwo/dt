package io.github.tkmtwo.dt;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.emptyToNull;

import com.google.common.base.CharMatcher;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.EnumSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZonedDateTimes {
  
  public static Set<ChronoUnit> CHRONO_UNITS = EnumSet.allOf(ChronoUnit.class);
  
  public static ChronoUnit valueOfChronoUnit(String s) {
    //String cuName = checkNotBlank(s, "Provided chrono unit name is blank.");
    String cuName = checkNotNull(emptyToNull(s), "ChronoUnit name was empty");
    return ChronoUnit.valueOf(cuName.toUpperCase());
  }
  
  public static ChronoUnit verifyChronoUnit(String s) {
    checkNotNull(s, "Need a string.");
    
    for (ChronoUnit cu : CHRONO_UNITS) {
      if (s.equalsIgnoreCase(cu.name())) {
        return cu;
      }
    }
    
    throw new IllegalArgumentException("ChronoUnit with name " + s + " not found.");
  }
  
  public static ZonedDateTime floor() { return floor(ChronoUnit.SECONDS); }
  public static ZonedDateTime floorSeconds() { return floor(ChronoUnit.SECONDS); }
  public static ZonedDateTime floorMinutes() { return floor(ChronoUnit.MINUTES); }
  public static ZonedDateTime floorHours() { return floor(ChronoUnit.HOURS); }
  public static ZonedDateTime floorDays() { return floor(ChronoUnit.DAYS); }
  public static ZonedDateTime floorMonths() { return floor(ChronoUnit.MONTHS); }
  public static ZonedDateTime floorYears() { return floor(ChronoUnit.YEARS); }
  
  
  public static ZonedDateTime ceiling() { return ceiling(ChronoUnit.SECONDS); }
  public static ZonedDateTime ceilingSeconds() { return ceiling(ChronoUnit.SECONDS); }
  public static ZonedDateTime ceilingMinutes() { return ceiling(ChronoUnit.MINUTES); }
  public static ZonedDateTime ceilingHours() { return ceiling(ChronoUnit.HOURS); }
  public static ZonedDateTime ceilingDays() { return ceiling(ChronoUnit.DAYS); }
  public static ZonedDateTime ceilingMonths() { return ceiling(ChronoUnit.MONTHS); }
  public static ZonedDateTime ceilingYears() { return ceiling(ChronoUnit.YEARS); }
  
  
  
  public static ZonedDateTime floor(ZonedDateTime dt) {
    return floor(dt, ChronoUnit.SECONDS);
  }
  public static ZonedDateTime floor(ChronoUnit cu) {
    return floor(ZonedDateTime.now(), cu);
  }
  public static ZonedDateTime floor(ZonedDateTime dt, ChronoUnit cu) {
    checkNotNull(dt, "ZonedDateTime is null.");
    checkNotNull(cu, "ChronoUnit is null.");
    
    ZonedDateTime rdt = null;
    
    //private static final ZonedDateTime REFERENCE_DT = ZonedDateTime.of(1981, 8, 3, 4, 5, 5, 333);
    switch (cu) {
    case SECONDS:
      rdt = ZonedDateTime.of(dt.getYear(), dt.getMonth().getValue(), dt.getDayOfMonth(), dt.getHour(), dt.getMinute(), dt.getSecond(), 0, dt.getZone());
      break;
    case MINUTES:
      rdt = ZonedDateTime.of(dt.getYear(), dt.getMonth().getValue(), dt.getDayOfMonth(), dt.getHour(), dt.getMinute(), 0, 0, dt.getZone());
      break;
    case HOURS:
      rdt = ZonedDateTime.of(dt.getYear(), dt.getMonth().getValue(), dt.getDayOfMonth(), dt.getHour(), 0, 0, 0, dt.getZone());
      break;
    case DAYS:
      rdt = ZonedDateTime.of(dt.getYear(), dt.getMonth().getValue(), dt.getDayOfMonth(), 0, 0, 0, 0, dt.getZone());
      break;
    case MONTHS:
      rdt = ZonedDateTime.of(dt.getYear(), dt.getMonth().getValue(), 1, 0, 0, 0, 0, dt.getZone());
      break;
    case YEARS:
      rdt = ZonedDateTime.of(dt.getYear(), 1, 1, 0, 0, 0, 0, dt.getZone());
      break;
    default:
      throw new UnsupportedTemporalTypeException("ChronoUnit " + cu.name() + " not supported for ceiling/floor operations.");
    }
      
    return rdt;
  }

  public static ZonedDateTime ceiling(ZonedDateTime dt) {
    return ceiling(dt, ChronoUnit.SECONDS);
  }
  public static ZonedDateTime ceiling(ChronoUnit cu) {
    return ceiling(ZonedDateTime.now(), cu);
  }
  public static ZonedDateTime ceiling(ZonedDateTime dt, ChronoUnit cu) {
    ZonedDateTime floor = floor(dt, cu);
    return floor.plus(1L, cu);
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  public static ZonedDateTime calculate(String s) {
    ZonedDateTime dt = ZonedDateTime.now();
    String spec = CharMatcher.whitespace().trimFrom(checkNotNull(emptyToNull(s), "String spec is empty."));
    
    Pattern basePattern = Pattern.compile("[a-zA-Z]+");
    Matcher baseMatcher = basePattern.matcher(spec);
    if (baseMatcher.lookingAt()) {
      dt = floor(dt, valueOfChronoUnit(baseMatcher.group(0)));
    }
    
    return calculate(dt, spec);
  }
  
  public static ZonedDateTime calculate(ZonedDateTime dt, String s) {
    ZonedDateTime rdt = checkNotNull(dt, "ZonedDateTime is null.");
    String spec = CharMatcher.whitespace().trimFrom(checkNotNull(emptyToNull(s), "String spec is empty."));
    
    Pattern p = Pattern.compile("([+\\-])(\\d+)([a-zA-Z]+)");
    Matcher m = p.matcher(spec);
    while (m.find()) {
      String op = m.group(1);
      String num = m.group(2);
      String unit = m.group(3);
      
      if ("+".equals(op)) {
        rdt = rdt.plus(Long.parseLong(num), valueOfChronoUnit(unit));
      } else if ("-".equals(op)) {
        rdt = rdt.minus(Long.parseLong(num), valueOfChronoUnit(unit));
      }
      
    }
    
    return rdt;
  }
  
  
}
