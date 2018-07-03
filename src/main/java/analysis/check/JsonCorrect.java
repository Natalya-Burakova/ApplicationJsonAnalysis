package analysis.check;

import com.google.gson.Gson;

import java.io.*;

public class JsonCorrect {

    private final Gson gson = new Gson();

    public boolean isJSONValid(String jsonInString) {
        try {
            String json = readJson(jsonInString);
            if (json!=null){
                gson.fromJson(json, Object.class);
                return true;
            }
            else
                return false;
        } catch(Exception ex) {
            return false;
        }
    }

    public String readJson(String json) {
        StringBuilder newJson = new StringBuilder();
        String ls = System.getProperty("line.separator");
        try (FileInputStream f = new FileInputStream(json);
             BufferedReader br = new BufferedReader(new InputStreamReader(f));) {
            String strLine;
            while ((strLine = br.readLine()) != null) {
                newJson.append(strLine);
                newJson.append(ls);
            }
            if (newJson.toString().length()!=0)
                return newJson.toString();
            else
                return null;
        }
        catch (IOException e) {
            return null;
        }
    }

}
