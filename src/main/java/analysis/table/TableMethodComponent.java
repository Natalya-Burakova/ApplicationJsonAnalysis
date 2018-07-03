package analysis.table;

import analysis.component.JsonComponent;
import analysis.component.JsonMethodComponent;
import analysis.JsonParce;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TableMethodComponent {

    public TableView<JsonMethodComponent> tableMethod;
    public Button home = new Button();

    private JsonParce json;

    public TableMethodComponent(JsonParce json){
        this.json = json;
    }

    public void setTable(JsonComponent component) {
        tableMethod = new TableView<JsonMethodComponent>();
        tableMethod.setPadding(new Insets(0, 0, 1, 0));

        TableColumn<JsonMethodComponent, String> bigTypeCol = new TableColumn<JsonMethodComponent, String>("bigType");
        TableColumn<JsonMethodComponent, String> typeCol = new TableColumn<JsonMethodComponent, String>("name");
        TableColumn<JsonMethodComponent, String> durCol = new TableColumn<JsonMethodComponent, String>("duration");
        TableColumn<JsonMethodComponent, String> countCol = new TableColumn<JsonMethodComponent, String>("count");
        TableColumn<JsonMethodComponent, String> minCol = new TableColumn<JsonMethodComponent, String>("min");
        TableColumn<JsonMethodComponent, String> maxCol = new TableColumn<JsonMethodComponent, String>("max");
        TableColumn<JsonMethodComponent, String> successCol = new TableColumn<JsonMethodComponent, String>("success");
        TableColumn<JsonMethodComponent, String> exceptionCol = new TableColumn<JsonMethodComponent, String>("exception");

        tableMethod.getColumns().addAll(bigTypeCol, typeCol, durCol, countCol, minCol, maxCol, successCol, exceptionCol);

        bigTypeCol.setCellValueFactory(cellData -> cellData.getValue().getBigType());
        typeCol.setCellValueFactory(cellData -> cellData.getValue().getType());
        durCol.setCellValueFactory(cellData -> cellData.getValue().getDuration());
        countCol.setCellValueFactory(cellData -> cellData.getValue().getCount());
        minCol.setCellValueFactory(cellData -> cellData.getValue().getMin());
        maxCol.setCellValueFactory(cellData -> cellData.getValue().getMax());
        successCol.setCellValueFactory(cellData -> cellData.getValue().getSuccess());
        exceptionCol.setCellValueFactory(cellData -> cellData.getValue().getException());


        Callback<TableColumn<JsonMethodComponent, String>, TableCell<JsonMethodComponent, String>> cellFactory
                = (final TableColumn<JsonMethodComponent, String> param) -> {
            final TableCell<JsonMethodComponent, String> cell = new TableCell<JsonMethodComponent, String>() {
                final HBox hbCenter = new HBox(10);

                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        if (getTableView().getItems().get(getIndex()).getException().getValue().equals("null")) ;
                        else {
                            final Button btn = new Button("View stacktrace");
                            btn.setVisible(true);
                            btn.setAlignment(Pos.CENTER);
                            hbCenter.getChildren().addAll(btn);

                            btn.setOnAction(
                                    new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent event) {
                                            VBox root = new VBox();
                                            StackPane layout = new StackPane();
                                            TextArea text = new TextArea();
                                            text.setMaxHeight(300);
                                            text.setMinHeight(300);
                                            text.setWrapText(true);
                                            ObservableList<JsonMethodComponent> list = (ObservableList<JsonMethodComponent>) json.store.get(component);
                                            for (int j = 0; j < list.size(); j++) {
                                                String s = getTableView().getItems().get(getIndex()).getException().getValue();
                                                 text.setText(s);
                                            }
                                            layout.getChildren().add(text);
                                            root.getChildren().add(layout);
                                            root.setAlignment(Pos.CENTER);
                                            Scene newScene = new Scene(root, 410, 300);
                                            Stage newWindow = new Stage();
                                            newWindow.setTitle("Exception");
                                            newWindow.setScene(newScene);
                                            newWindow.show();

                                        }
                                    });
                            hbCenter.setAlignment(Pos.CENTER);
                            setGraphic(hbCenter);
                            setText(null);
                        }
                    }
                }
            };
            return cell;
        };

        exceptionCol.setCellFactory(cellFactory);

        tableMethod.setItems((ObservableList<JsonMethodComponent>) json.store.get(component));

        home.setText("<-");
        home.setFont(Font.font(35));
        home.setPadding(new Insets(1, 1, 1, 1));

    }
}
