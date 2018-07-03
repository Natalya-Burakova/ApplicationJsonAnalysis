package analysis.table;

import analysis.component.JsonComponent;
import analysis.JsonParce;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.text.Font;

public class TableComponent {


    public TableView<JsonComponent> table;
    public Button ok = new Button();

    private JsonParce json;

    public TableComponent(JsonParce json){
            this.json = json;
    }

    public void setTable() {
        table = new TableView<JsonComponent>();
        table.setPadding(new Insets(0, 0, 1, 0));

        TableColumn<JsonComponent, String> idComponentCol = new TableColumn<JsonComponent, String>("idComponent");
        TableColumn<JsonComponent, String> durationCol = new TableColumn<JsonComponent, String>("duration");
        TableColumn<JsonComponent, String> statusCol = new TableColumn<JsonComponent, String>("status");

        table.getColumns().addAll(idComponentCol, durationCol, statusCol);

        idComponentCol.setCellValueFactory((TableColumn.CellDataFeatures<JsonComponent, String> cellData) -> cellData.getValue().getId());
        durationCol.setCellValueFactory((TableColumn.CellDataFeatures<JsonComponent, String> cellData) -> cellData.getValue().getDuration());
        statusCol.setCellValueFactory((TableColumn.CellDataFeatures<JsonComponent, String> cellData) -> cellData.getValue().getStatus());


        table.setItems(json.listJsonComponent);

        ok.setText("ok");
        ok.setFont(Font.font(25));
        ok.setPadding(new Insets(1, 1, 1, 1));
    }
}

