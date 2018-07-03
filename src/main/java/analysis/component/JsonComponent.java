package analysis.component;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class JsonComponent {
    private StringProperty idComponent;
    private StringProperty duration;
    private StringProperty status;

    public JsonComponent(String idComponent, String duration, String status) {
        this.idComponent = new SimpleStringProperty(idComponent);
        this.duration = new SimpleStringProperty(duration);
        this.status = new SimpleStringProperty(status);
    }

    public StringProperty getId() {
        return idComponent;
    }


    public StringProperty getDuration() {
        return duration;
    }

    public StringProperty getStatus() {
        return  status;
    }

}
