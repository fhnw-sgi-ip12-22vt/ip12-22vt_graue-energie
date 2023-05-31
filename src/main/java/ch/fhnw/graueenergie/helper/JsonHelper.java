package ch.fhnw.graueenergie.helper;

import ch.fhnw.graueenergie.exception.JsonHelperReadException;
import com.google.gson.Gson;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public final class JsonHelper {

  private JsonHelper() {
  }

  public static <T> T read(String path, Class<T> classOfT) {
    T jsonObject = null;

    try {
      Reader reader = Files.newBufferedReader(
          Paths.get(Objects.requireNonNull(JsonHelper.class.getResource(path)).getPath()));

      jsonObject = new Gson().fromJson(reader, classOfT);

      reader.close();
    } catch (Exception ignored) {
      throw new JsonHelperReadException();
    }

    return jsonObject;
  }
}
