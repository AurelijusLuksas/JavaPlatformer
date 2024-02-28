package misc;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Aurelijus Lukšas 5 group.
 * Class used for writing levels data to a JSON file.
 */
public class WriteJSON {
    /**
     * Method used for writing level data to a newly created file or writing over an older file.
     * @param solid
     * @param front
     * @param back
     * @param filename
     */
    public static void writeToJSON(int[][] solid, int[][] front, int[][] back, String filename){
        JSONArray solidLayer = arrayToJSONArray(solid);
        JSONArray backLayer = arrayToJSONArray(back);
        JSONArray frontLayer = arrayToJSONArray(front);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("solid", solidLayer);
        jsonObject.put("back", backLayer);
        jsonObject.put("front", frontLayer);

        File outputfile = new File("src/main/java/levels/" + filename);

        if(!outputfile.exists()) {
            try {
                outputfile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try (FileWriter fileWriter = new FileWriter(outputfile)) {
            String jsonString = jsonObject.toJSONString().replaceAll("],","],\n");
            String replaceCommas = jsonString.replaceAll(",", ", ");
            String finalString = replaceCommas.replaceAll(":", ":\n");
            fileWriter.write(finalString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Method used for converting int array to a jsonArray.
     * @param array
     * @return
     */
    private static JSONArray arrayToJSONArray(int[][] array) {
        JSONArray jsonArray = new JSONArray();
        for (int[] row : array) {
            JSONArray rowArray = new JSONArray();
            for (int num : row) {
                rowArray.add(num);
            }
            jsonArray.add(rowArray);
        }
        return jsonArray;
    }
}
