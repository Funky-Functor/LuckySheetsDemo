package com.funkyfunctor.luckysheetsdemo.json;

import lombok.val;

public class InitialLoadData {

  public static String getWorksheetData() {
    return """
             [
               {
                 "name": "My 1st Funky Sheet",
                 "config": {},
                 "index": "1",
                 "status": "1",
                 "order": "0",
                 "luckysheet_select_save": [
                   { "row": [0, 0], "column": [4, 4], "sheetIndex": 1 }
                 ],
                 "zoomRatio": 1,
                 "showGridLines": "1",
                 "defaultColWidth": 80,
                 "defaultRowHeight": 20,
                 "celldata": """ + getCellData("", "0") + """
                 ,
                 "calcChain": []
               },
                              {
                                "name": "My 2nd Funky Sheet",
                                "config": {},
                                "index": "2",
                                "status": "1",
                                "order": "1",
                                "zoomRatio": 1,
                                "showGridLines": "1",
                                "defaultColWidth": 80,
                                "defaultRowHeight": 20,
                                "calcChain": []
                              }
             ]
            """;
  }

  public static String getCellData(String workbookId, String sheetId) {
    return """
           [
            {
             "r": 0,
             "c": 0,
             "v": 'Hello Funky World'
            },
            {
             "r": 1,
             "c": 1,
             "v": 'This is the B2 cell'
            },

           ]
           """;
  }

  public static String getData(String workbookId, String sheetId) {
    if (sheetId.isEmpty())
      return "{}";

    val data = """
             [
              {
               r: 1,
               c: 1,
               v: {
                 ct: {t: "g", fa: "General"},
                 v: "This is a test",
                 m: "This is a test"
                }
              }
             ]
            """;

    return ("{" + sheetId + ":" + data + "}");
  }

}
