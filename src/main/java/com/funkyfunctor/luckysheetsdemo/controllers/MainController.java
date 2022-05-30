package com.funkyfunctor.luckysheetsdemo.controllers;

import com.funkyfunctor.luckysheetsdemo.json.InitialLoadData;
import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@RestController
public class MainController {
  @GetMapping("/")
  public String index() {
    return HTML_PAGE;
  }

  @PostMapping("/data/initialdata.json")
  public String initialJsonData(HttpServletRequest httpServletRequest) {
    val parameters = HashMap.ofAll(httpServletRequest.getParameterMap());
    val paramsLog = listAllParameters(parameters);
    //System.out.printf("Called '/data/initialdata.json' \n\n%s\n\n", paramsLog);
    return InitialLoadData.getWorksheetData();
  }

  @PostMapping("/data/sheetData")
  public String sheetData(HttpServletRequest httpServletRequest) {
    val parameters = HashMap.ofAll(httpServletRequest.getParameterMap());
    val paramsLog = listAllParameters(parameters);
    System.out.printf("Called '/data/sheetData' \n\n%s\n\n", paramsLog);
    val workbookId = parameters.getOrElse("gridKey", new String[0]);
    val worksheetId = parameters.getOrElse("index", new String[0]);

    return InitialLoadData.getData(
            mkString(workbookId),
            mkString(worksheetId)
    );
  }

  public static String listAllParameters(HashMap<String, String[]> params) {
    return params.foldLeft(new StringBuffer("| Key\t| Value\t|\n"), (buffer, tuple) -> {
      val valuesList = mkString(tuple._2);
      return buffer.append("| ").append(tuple._1).append("\t| ").append(valuesList).append("\t|\n");
    }).toString();
  }

  public static String mkString(String[] values) {
    return List.ofAll(Arrays.asList(values)).mkString(", ");
  }

  public static String HTML_PAGE = """
          <!DOCTYPE html>
          <html>
          <title>Grimlibot - LuckySheet Example</title>
          <meta name="viewport" content="width=device-width, initial-scale=1">
          <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
          <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/luckysheet@2.1.2/dist/plugins/css/pluginsCss.css' />
          <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/luckysheet@2.1.2/dist/plugins/plugins.css' />
          <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/luckysheet@2.1.2/dist/css/luckysheet.css' />
          <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/luckysheet@2.1.2/dist/assets/iconfont/iconfont.css' />
          <script src="https://cdn.jsdelivr.net/npm/luckysheet@2.1.2/dist/plugins/js/plugin.js"></script>
          <script src="https://cdn.jsdelivr.net/npm/luckysheet@2.1.2/dist/luckysheet.umd.js"></script>

          <script type="text/javascript">
            $(function () {
                luckysheet.create({
                  container: 'luckysheet',
                  //showinfobar:false,
                  loadUrl: '/data/initialdata.json',
                  loadSheetUrl: '/data/sheetData'
                });
            });
            
            function btnClick() {
              console.log(JSON.stringify(luckysheet.getSheetData()));
            }
          </script>

          <body>
            <div id="luckysheet" style="margin:0px;padding:0px;position:absolute;width:100%;height:100%;left: 0px;top: 0px;"></div>
          </body>
          </html>
          """;
}