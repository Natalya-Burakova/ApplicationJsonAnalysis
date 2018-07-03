package analysis;

import com.google.gson.*;
import analysis.component.JsonComponent;
import analysis.component.JsonMethodComponent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.lang.reflect.Array;
import java.util.HashMap;

public class JsonParce {

    public ObservableList<JsonComponent> listJsonComponent;
    public HashMap store;

    public void parce(String jsonLine) {
            listJsonComponent = FXCollections.observableArrayList();
            store = new HashMap();
            Gson gson = new Gson();
            JsonArray data = gson.fromJson(jsonLine, JsonObject.class).getAsJsonObject().get("invocationsData").getAsJsonArray();

            for (int i = 0; i<data.size(); i++) {
                String idComponent = data.get(i).getAsJsonObject().get("componentId").getAsString();
                String duration = data.get(i).getAsJsonObject().get("duration").getAsString();
                String status = "successfully";

                ObservableList<JsonMethodComponent> listJsonMethodComp = FXCollections.observableArrayList();
                JsonArray dataMethod = data.get(i).getAsJsonObject().get("invocations").getAsJsonArray();

                String bigType = data.get(i).getAsJsonObject().get("type").getAsString();
                for (int j=0; j< dataMethod.size(); j++) {
                    JsonObject invocations = dataMethod.get(j).getAsJsonObject();
                    String dur = invocations.get("duration").getAsString();
                    String count = invocations.get("count").getAsString();
                    String min = invocations.get("min").getAsString();
                    String max =  invocations.get("max").getAsString();
                    String success = invocations.get("success").getAsString();
                    String exception = invocations.get("exception").toString();

                    if (success.equals("true"))
                        success = "successfully";
                    else {
                        success = "unsuccessful";
                        status = "unsuccessful";
                    }

                    String type;
                    if (bigType.equals("SQL")) {
                        type = gson.fromJson(jsonLine, JsonObject.class).getAsJsonObject().get("sqlDictionary").getAsJsonArray().get(j).getAsJsonObject().get("name").getAsString();
                        if (type.length() >= 50)
                            type = type.substring(0, 50);
                    }
                    else
                        type = invocations.get("uri").getAsString();

                    JsonMethodComponent newJsonMethodComp = new JsonMethodComponent(bigType, type, dur, count, min, max, success, exception);
                    listJsonMethodComp.add(newJsonMethodComp);
                }

                JsonComponent jsonComponent = new JsonComponent(idComponent, duration, status);
                listJsonComponent.add(jsonComponent);
                store.put(jsonComponent, listJsonMethodComp);
            }
    }

}
