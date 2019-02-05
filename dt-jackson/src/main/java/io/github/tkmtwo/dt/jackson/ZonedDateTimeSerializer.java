package io.github.tkmtwo.dt.jackson;


import static io.github.tkmtwo.dt.format.IsoDateTimeFormatters.ZONED_DATETIME_EXTENDED;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


//import org.joda.time.DateTime;
//import org.joda.time.format.DateTimeFormatter;
//import org.joda.time.format.ISODateTimeFormat;


/**
 *
 */
public final class ZonedDateTimeSerializer
  extends StdScalarSerializer<ZonedDateTime> {
  
  private DateTimeFormatter dateTimeFormatter = ZONED_DATETIME_EXTENDED;

  public ZonedDateTimeSerializer() { super(ZonedDateTime.class); }
  public ZonedDateTimeSerializer(DateTimeFormatter dtf) {
    super(ZonedDateTime.class);
    dateTimeFormatter = dtf;
  }
  
  public DateTimeFormatter getDateTimeFormatter() {
    if (dateTimeFormatter == null) {
      dateTimeFormatter = ZONED_DATETIME_EXTENDED;
    }
    
    return dateTimeFormatter;
  }
  public void setDateTimeFormatter(DateTimeFormatter dtf) {
    dateTimeFormatter = dtf;
  }


  public void serialize(ZonedDateTime value, JsonGenerator jgen, SerializerProvider provider)
    throws IOException, JsonProcessingException {
    jgen.writeString(getDateTimeFormatter().format(value));
  }
  
  
}
