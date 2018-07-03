package analysis.component;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class JsonMethodComponent {
    private StringProperty bigType;
    private StringProperty type;
    private StringProperty duration;
    private StringProperty count;
    private StringProperty min;
    private StringProperty max;
    private StringProperty success;
    private StringProperty exception;


    public JsonMethodComponent(String bigType, String type, String duration, String count, String min, String max, String success, String exception) {
        this.bigType = new SimpleStringProperty(bigType);
        this.type = new SimpleStringProperty(type);
        this.duration = new SimpleStringProperty(duration);
        this.count = new SimpleStringProperty(count);
        this.min = new SimpleStringProperty(min);
        this.max = new SimpleStringProperty(max);
        this.success = new SimpleStringProperty(success);
        this.exception = new SimpleStringProperty(exception);
    }

    public StringProperty getBigType() { return  bigType; }

    public StringProperty getType() {
        return type;
    }

    public StringProperty getDuration() {
        return duration;
    }

    public StringProperty getCount() { return count; }

    public StringProperty getMin() { return  min; }

    public StringProperty getMax() { return max; }

    public StringProperty getSuccess() {
        return  success;
    }

    public StringProperty getException() { return exception; }
}
