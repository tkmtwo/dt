package io.github.tkmtwo.dt.jackson;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DateTimeModuleTest {
  
  private ObjectMapper newObjectMapper() {
    ObjectMapper om = new ObjectMapper();
    om.registerModule(new DateTimeModule());
    return om;
  }



  private String quoted(String s) {
    return '"' + s + '"';
  }

  @Test
  public void test0010LocalDateTimeSer()
    throws Exception {
    
    ObjectMapper om = newObjectMapper();

    assertEquals(quoted("1970-01-01T00:00:00"),
                 om.writeValueAsString(LocalDateTime.of(1970, 1, 1, 0, 0, 0, 0)));
    assertEquals(quoted("1970-01-01T00:00:03"),
                 om.writeValueAsString(LocalDateTime.of(1970, 1, 1, 0, 0, 3, 0)));
    assertEquals(quoted("1970-01-01T00:00:03"),
                 om.writeValueAsString(LocalDateTime.of(1970, 1, 1, 0, 0, 3, 73)));
                              
    
  }


  @Test
  public void test0011LocalDateTimeDe()
    throws Exception {
    
    LocalDateTime threeSeconds = LocalDateTime.of(1970, 1, 1, 0, 0, 3, 0);
    ObjectMapper om = newObjectMapper();
    
    String[] ss = new String[] {
      "1970-01-01T00:00:03Z",
      "1970-01-01T00:00:03",
      "19700101T000003",
      "3"
    };
    
    for (String s : ss) {
      assertEquals(threeSeconds, om.readValue(quoted(s), LocalDateTime.class));
    }
    
  }
  
  
  
  
  @Test
  public void test0020ZonedDateTimeSer()
    throws Exception {
    
    ObjectMapper om = newObjectMapper();

    assertEquals(quoted("1970-01-01T00:00:00Z"),
                 om.writeValueAsString(ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneId.of("Z"))));
    assertEquals(quoted("1970-01-01T00:00:03Z"),
                 om.writeValueAsString(ZonedDateTime.of(1970, 1, 1, 0, 0, 3, 0, ZoneId.of("Z"))));
    assertEquals(quoted("1970-01-01T00:00:03Z"),
                 om.writeValueAsString(ZonedDateTime.of(1970, 1, 1, 0, 0, 3, 73, ZoneId.of("Z"))));
                              
    
  }
  
  
  
  
  
}
