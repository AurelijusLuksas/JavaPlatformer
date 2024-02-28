package misc;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

/**
 * @author Aurelijus Lukšas 5 group.
 * Class used for reading JSON file.
 * JSON PARSER
 */
public class ReadJSON {
    private static int [][] solid, back, front;

    /**
     * Method for reading levels data from json file.
     * @param filename
     * @param state
     * @return
     */
    public static int[][] readLevel(String filename, int state) {

        try (FileReader fileReader = new FileReader("src/main/java/levels/" + filename)) {

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);

            JSONArray solidArray = (JSONArray) jsonObject.get("solid");
            JSONArray backArray = (JSONArray) jsonObject.get("back");
            JSONArray frontArray = (JSONArray) jsonObject.get("front");

            solid = convertJsonToArray(solidArray);
            back = convertJsonToArray(backArray);
            front = convertJsonToArray(frontArray);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        if (state == 0)
            return solid;
        if (state == 1)
            return back;
        if (state == 2)
            return front;
        return null;
    }

    /**
     * Method for converting jsonArray to an array.
     * @param jsonArray
     * @return
     */
    private static int[][] convertJsonToArray(JSONArray jsonArray) {
        int[][] array = new int[jsonArray.size()][((JSONArray) jsonArray.get(0)).size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONArray rowArray = (JSONArray) jsonArray.get(i);
            for (int j = 0; j < rowArray.size(); j++) {
                array[i][j] = ((Long) rowArray.get(j)).intValue();
            }
        }
        return array;
    }
}

