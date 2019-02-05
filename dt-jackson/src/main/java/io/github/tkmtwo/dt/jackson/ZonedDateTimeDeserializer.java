package io.github.tkmtwo.dt.jackson;

import static io.github.tkmtwo.dt.format.IsoDateTimeFormatters.ZONED_DATETIME_EXTENDED;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


/**
 *
 */
public final class ZonedDateTimeDeserializer
  extends StdScalarDeserializer<ZonedDateTime> {
  
  private static final long serialVersionUID = 1L;
  private transient DateTimeFormatter dateTimeFormatter = ZONED_DATETIME_EXTENDED;

  public ZonedDateTimeDeserializer() { super(ZonedDateTime.class); }
  
  public DateTimeFormatter getDateTimeFormatter() {
    if (dateTimeFormatter == null) {
      dateTimeFormatter = ZONED_DATETIME_EXTENDED;
    }
    
    return dateTimeFormatter;
  }

  
  public ZonedDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
    throws IOException, JsonProcessingException {
    if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING) {
      String dtText = jsonParser.getText();
      //if (isBlank(dtText)) { return null; }

      //return getDateTimeFormatter().parseDateTime(jsonParser.getText());
      ////return ZonedDateTime.parse(dtText, getDateTimeFormatter());
      //return getDateTimeFormatter().parse(dtText);
      return io.github.tkmtwo.dt.format.IsoDateTimeFormatters.parseZonedDateTime(dtText);
    }
    
    throw deserializationContext.mappingException("Expected JSON Text");
  }
  
}
