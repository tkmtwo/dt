package io.github.tkmtwo.dt.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


/**
 *
 */
public final class DateTimeModule
  extends SimpleModule {
  
  private static final long serialVersionUID = 1L;
  
  
  public DateTimeModule() {
    addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
    addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
    
    addSerializer(ZonedDateTime.class, new ZonedDateTimeSerializer());
    addDeserializer(ZonedDateTime.class, new ZonedDateTimeDeserializer());
    
  }
  
  
}
