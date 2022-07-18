package helper.Utilities;

import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtility {

    public static void verifyTheValueAtNode(String json, String field, String key, String keyValue)
    {
        Filter expensiveFilter = Filter.filter(Criteria.where(key).gt(keyValue));
        String expensive = JsonPath.parse(json)
                .read(field, expensiveFilter);
        System.out.println("Result :: "+expensive);
    }

    public static void getFilteredJSONMap(String json, String nodePath, String key, Object value)
    {
        Filter expensiveFilter = Filter.filter(Criteria.where(key).gt(value));
        List<Map<String, Object>> expensive = JsonPath.parse(json)
                .read(nodePath, expensiveFilter);
        System.out.println("Result :: "+expensive);
    }
    public static String setValueAt(String json, String jsonNodePath, String value) {
        DocumentContext doc = JsonPath.parse(json).set(jsonNodePath, value);
        String newJson = doc.jsonString();
        return newJson;

    }

    public static String setValues(String json, HashMap<String, String> pathsAndValues) {

        try {
            for (Map.Entry<String, String> set : pathsAndValues.entrySet()) {
                json = setValueAt(json, set.getKey(), set.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public static String readJsonFileAsString(String filepath) {
        String str=null;
        try {

            str= new String(Files.readAllBytes(Paths.get(filepath)));

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return str;

    }
    public static String getValueAt(String json, String path) {
        try {
            return JsonPath.read(json, path).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    public static JSONArray getArrayAt(String json, String path)
    {
        try {
        return JsonPath.read(json, path);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
    }

    public static long randomNum2(){
        long randomNumber = (long) (Math.random() * 100000000000000L);
        return randomNumber;
    }


}