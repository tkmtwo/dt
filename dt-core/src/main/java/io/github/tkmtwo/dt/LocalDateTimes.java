package io.github.tkmtwo.dt;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.emptyToNull;

import com.google.common.base.CharMatcher;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.EnumSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocalDateTimes {
  
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
  
  public static LocalDateTime floor() { return floor(ChronoUnit.SECONDS); }
  public static LocalDateTime floorSeconds() { return floor(ChronoUnit.SECONDS); }
  public static LocalDateTime floorMinutes() { return floor(ChronoUnit.MINUTES); }
  public static LocalDateTime floorHours() { return floor(ChronoUnit.HOURS); }
  public static LocalDateTime floorDays() { return floor(ChronoUnit.DAYS); }
  public static LocalDateTime floorMonths() { return floor(ChronoUnit.MONTHS); }
  public static LocalDateTime floorYears() { return floor(ChronoUnit.YEARS); }
  
  
  public static LocalDateTime ceiling() { return ceiling(ChronoUnit.SECONDS); }
  public static LocalDateTime ceilingSeconds() { return ceiling(ChronoUnit.SECONDS); }
  public static LocalDateTime ceilingMinutes() { return ceiling(ChronoUnit.MINUTES); }
  public static LocalDateTime ceilingHours() { return ceiling(ChronoUnit.HOURS); }
  public static LocalDateTime ceilingDays() { return ceiling(ChronoUnit.DAYS); }
  public static LocalDateTime ceilingMonths() { return ceiling(ChronoUnit.MONTHS); }
  public static LocalDateTime ceilingYears() { return ceiling(ChronoUnit.YEARS); }
  
  
  
  public static LocalDateTime floor(LocalDateTime dt) {
    return floor(dt, ChronoUnit.SECONDS);
  }
  public static LocalDateTime floor(ChronoUnit cu) {
    return floor(LocalDateTime.now(), cu);
  }
  
  public static LocalDateTime floor(LocalDateTime dt, ChronoUnit cu) {
    checkNotNull(dt, "LocalDateTime is null.");
    checkNotNull(cu, "ChronoUnit is null.");
    
    LocalDateTime rdt = null;
    
    //private static final LocalDateTime REFERENCE_DT = LocalDateTime.of(1981, 8, 3, 4, 5, 5, 333);
    switch (cu) {
    case SECONDS:
      rdt = LocalDateTime.of(dt.getYear(), dt.getMonth(), dt.getDayOfMonth(), dt.getHour(), dt.getMinute(), dt.getSecond(), 0);
      break;
    case MINUTES:
      rdt = LocalDateTime.of(dt.getYear(), dt.getMonth(), dt.getDayOfMonth(), dt.getHour(), dt.getMinute(), 0, 0);
      break;
    case HOURS:
      rdt = LocalDateTime.of(dt.getYear(), dt.getMonth(), dt.getDayOfMonth(), dt.getHour(), 0, 0, 0);
      break;
    case DAYS:
      rdt = LocalDateTime.of(dt.getYear(), dt.getMonth(), dt.getDayOfMonth(), 0, 0, 0, 0);
      break;
    case MONTHS:
      rdt = LocalDateTime.of(dt.getYear(), dt.getMonth(), 1, 0, 0, 0, 0);
      break;
    case YEARS:
      rdt = LocalDateTime.of(dt.getYear(), 1, 1, 0, 0, 0, 0);
      break;
    default:
      throw new UnsupportedTemporalTypeException("ChronoUnit " + cu.name() + " not supported for ceiling/floor operations.");
    }
      
    return rdt;
  }
  
  
  
  
  
  
  
  
  
  
  public static LocalDateTime ceiling(LocalDateTime dt) {
    return ceiling(dt, ChronoUnit.SECONDS);
  }
  public static LocalDateTime ceiling(ChronoUnit cu) {
    return ceiling(LocalDateTime.now(), cu);
  }
  public static LocalDateTime ceiling(LocalDateTime dt, ChronoUnit cu) {
    LocalDateTime floor = floor(dt, cu);
    return floor.plus(1L, cu);
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  public static LocalDateTime calculate(String s) {
    LocalDateTime dt = LocalDateTime.now();
    String spec = CharMatcher.whitespace().trimFrom(checkNotNull(emptyToNull(s), "String spec is empty."));
    
    Pattern basePattern = Pattern.compile("[a-zA-Z]+");
    Matcher baseMatcher = basePattern.matcher(spec);
    if (baseMatcher.lookingAt()) {
      dt = floor(dt, valueOfChronoUnit(baseMatcher.group(0)));
    }
    
    return calculate(dt, spec);
  }
  
  public static LocalDateTime calculate(LocalDateTime dt, String s) {
    LocalDateTime rdt = checkNotNull(dt, "LocalDateTime is null.");
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
