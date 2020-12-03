package me.dev.oliver.youtubesns;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

//@SpringBootTest
class mytubeApplicationTests {

  @Test
  void contextLoads() {
  }

  @Test
  void serializationAndDeserializationLocalDateTimeInstance() throws IOException {
    final ObjectMapper mapper = new ObjectMapper()
        .registerModule(new JavaTimeModule());

    final Path path = Paths.get("localDateTime.json");
    final LocalDateTime now = LocalDateTime.now();
    mapper.writeValue(Files.newBufferedWriter(path), now);

    final LocalDateTime localDateTime = mapper
        .readValue(Files.newBufferedReader(path), LocalDateTime.class);
    System.out.println("localDateTime = " + localDateTime);
    // no exception thrown
  }

}
