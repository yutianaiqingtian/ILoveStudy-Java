package top.ilovestudy.data.extraction;

import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Store results objects (page models) to files in JSON format.<br>
 * Use model.getKey() as file name if the model implements HasKey.<br>
 * Otherwise use SHA1 as file name.
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.2.0
 */
public class CSVFilePageModelPipeline<T> extends FilePersistentBase implements PageModelPipeline<T> {

  String path;

  public CSVFilePageModelPipeline() {
    this.path = "./";
  }

  public CSVFilePageModelPipeline(String path) {
    if (!path.endsWith(File.pathSeparator)) {
      this.path = path + File.pathSeparator;
    }
    this.path = path;
  }

  @Override
  public void process(T t, Task task) {

    Field[] declaredFields = t.getClass().getDeclaredFields();
    List<String> records = new ArrayList<>(declaredFields.length);
    for (Field declaredField : declaredFields) {
      declaredField.setAccessible(true);
      try {
        records.add((String) declaredField.get(t));
      } catch (IllegalAccessException e) {
        records.add("");
      }
    }
    String record = String.join(",", records);
    PrintWriter printWriter = null;
    try {
      printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(new File(path + t.getClass().getSimpleName() + ".csv"), true), UTF_8));
    } catch (IOException e) {
      e.printStackTrace();
    }
    assert printWriter != null;
    printWriter.write(record + "\n");
    printWriter.close();
  }
}
