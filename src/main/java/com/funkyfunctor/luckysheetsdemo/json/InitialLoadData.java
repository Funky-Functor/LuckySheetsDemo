package com.funkyfunctor.luckysheetsdemo.json;

import lombok.val;

public class InitialLoadData {

  public static String getWorksheetData() {
    return "[\n" +
            "  {\n" +
            "    \"name\": \"My 1st Funky Sheet\",\n" +
            "    \"config\": {},\n" +
            "    \"index\": \"1\",\n" +
            "    \"status\": \"1\",\n" +
            "    \"order\": \"0\",\n" +
            "    \"luckysheet_select_save\": [\n" +
            "      { \"row\": [0, 0], \"column\": [4, 4], \"sheetIndex\": 1 }\n" +
            "    ],\n" +
            "    \"zoomRatio\": 1,\n" +
            "    \"showGridLines\": \"1\",\n" +
            "    \"defaultColWidth\": 80,\n" +
            "    \"defaultRowHeight\": 20,\n" +
            "    \"celldata\": " + getCellData("", "0") + ",\n" +
            "    \"calcChain\": []\n" +
            "  }\n" +
            "]\n";
  }

  public static String getCellData(String workbookId, String sheetId) {
    return "[\n" +
            "      {\n" +
            "        \"r\": 0,\n" +
            "        \"c\": 0,\n" +
            "        \"v\": 'Hello Funky World - " + sheetId + "'" +
            "      },\n" +
            "      {\n" +
            "        \"r\": 1,\n" +
            "        \"c\": 1,\n" +
            "        \"v\": 'This is the B2 column'" +
            "      }\n" +
            "    ]";
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
