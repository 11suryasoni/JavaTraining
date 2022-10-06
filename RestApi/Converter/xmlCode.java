/*
Copyright (c) 2022. -> All Rights Reserved
Unauthorized Copying or Redistribution of this file in Source or Class file
format is Strictly Prohibited.

This xml code class is used to convert json to xml file.

@Author -> suryaps (Surya prakash soni)
 */

package RestApi;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class xmlCode {

    static final Logger logg = Logger.getLogger(xmlCode.class);
    /**
     * This function convert Json String to Xml.
     */
    static int k = 0;

    public static String convertToXML(String jsonString, String root) throws JSONException {

        logg.info("CONVERT TO XML -INVOKED");
        JSONObject jsonObject = new JSONObject(jsonString);
        logg.info("STRING RETURNED");
        logg.debug("XML -DATA - " + XML.toString(jsonObject));
        if (k == 0)
            return "<?xml version=\"1.0\" encoding=\"ISO-8859-15\"?>\n<roots>\n<" + root + ">" + XML.toString(jsonObject) + "</" + root + ">";
        else
            return XML.toString(jsonObject);
    }

    public static void main(String[] args) {

        logg.info("XML PROGRAM INVOKED");
        logg.warn("JSON FILE PATH ENTERED MANUALLY");
        String loc = "/Users/azuga/Desktop/test3.json";

        logg.debug("PATH - " + loc);
        String result;
        try {

            result = new String(Files.readAllBytes(Paths.get(loc)));
            String newJson = result.replaceAll("}\\{", "}},{{");
            String[] loopObj = newJson.split("},\\{");

            logg.debug("XML -DATA - " + newJson);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 6; i++, k++) {
                String xmlvalue = convertToXML(loopObj[i], "root");
                sb.append(xmlvalue.replaceAll("><", ">\n<"));
            }
            sb.append("\n</roots>");
            String res = sb.toString();

            logg.warn("XML -XML FILE PATH ENTERED MANUALLY");
            FileWriter file = new FileWriter("/Users/azuga/Desktop/test3XMLData.xml");

            logg.debug("XML - FILE PATH - " + file);
            file.write(res);
            file.flush();
            file.close();
            logg.info("XML -FILE  CLOSED SUCCESSFULLY");

        } catch (IOException e1) {
            logg.error("Input Error");
            e1.printStackTrace();
        } catch (JSONException e2) {
            logg.error("JSON FILE ERROR");
            e2.printStackTrace();
        }
    }
}