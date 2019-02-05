package io.github.tkmtwo.dt.jackson;


import static io.github.tkmtwo.dt.format.IsoDateTimeFormatters.LOCAL_DATETIME_EXTENDED;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


//import org.joda.time.DateTime;
//import org.joda.time.format.DateTimeFormatter;
//import org.joda.time.format.ISODateTimeFormat;


/**
 *
 */
public final class LocalDateTimeSerializer
  extends StdScalarSerializer<LocalDateTime> {
  
  private DateTimeFormatter dateTimeFormatter = LOCAL_DATETIME_EXTENDED;

  public LocalDateTimeSerializer() { super(LocalDateTime.class); }
  public LocalDateTimeSerializer(DateTimeFormatter dtf) {
    super(LocalDateTime.class);
    dateTimeFormatter = dtf;
  }
  
  public DateTimeFormatter getDateTimeFormatter() {
    if (dateTimeFormatter == null) {
      dateTimeFormatter = LOCAL_DATETIME_EXTENDED;
    }
    
    return dateTimeFormatter;
  }
  public void setDateTimeFormatter(DateTimeFormatter dtf) {
    dateTimeFormatter = dtf;
  }


  public void serialize(LocalDateTime value, JsonGenerator jgen, SerializerProvider provider)
    throws IOException, JsonProcessingException {
    jgen.writeString(getDateTimeFormatter().format(value));
  }
  
  
}
