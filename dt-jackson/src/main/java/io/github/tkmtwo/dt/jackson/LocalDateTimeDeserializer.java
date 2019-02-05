package io.github.tkmtwo.dt.jackson;

import static io.github.tkmtwo.dt.format.IsoDateTimeFormatters.LOCAL_DATETIME_EXTENDED;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 *
 */
public final class LocalDateTimeDeserializer
  extends StdScalarDeserializer<LocalDateTime> {
  
  private static final long serialVersionUID = 1L;
  private transient DateTimeFormatter dateTimeFormatter = LOCAL_DATETIME_EXTENDED;

  public LocalDateTimeDeserializer() { super(LocalDateTime.class); }
  
  public DateTimeFormatter getDateTimeFormatter() {
    if (dateTimeFormatter == null) {
      dateTimeFormatter = LOCAL_DATETIME_EXTENDED;
    }
    
    return dateTimeFormatter;
  }

  
  public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
    throws IOException, JsonProcessingException {
    if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING) {
      String dtText = jsonParser.getText();
      //if (isBlank(dtText)) { return null; }

      //return getDateTimeFormatter().parseDateTime(jsonParser.getText());
      ////return LocalDateTime.parse(dtText, getDateTimeFormatter());
      //return getDateTimeFormatter().parse(dtText);
      return io.github.tkmtwo.dt.format.IsoDateTimeFormatters.parseLocalDateTime(dtText);
    }
    
    throw deserializationContext.mappingException("Expected JSON Text");
  }
  
}
