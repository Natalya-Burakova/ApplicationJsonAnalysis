package controller;

import analysis.*;
import analysis.check.JsonCorrect;
import analysis.component.JsonComponent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import analysis.table.TableComponent;
import analysis.table.TableMethodComponent;

import java.io.File;


public class Controller {

    public Button btnNext;
    public TextField txtFieldPath;
    public Button btnOpen;
    public Label lblPrint;

    public String pathToFile;
    public  JsonParce jsonParse;

    public void clickOnNext(ActionEvent actionEvent) {
        if (txtFieldPath.getCharacters().length()!= 0) {
            lblPrint.setText("");
            pathToFile = txtFieldPath.getCharacters().toString();
            JsonCorrect jsonCorrect = new JsonCorrect();
            if (jsonCorrect.isJSONValid(pathToFile)){
                String stringJson = jsonCorrect.readJson(pathToFile);
                if (stringJson!=null) {
                    jsonParse = new JsonParce();
                    jsonParse.parce(stringJson);
                    newWindowTableComponent();
                }
            }
            else
                lblPrint.setText("The file is corrupt or does not contain a json object.");
        }
        else
            lblPrint.setText("Error! It is not possible to download the file.");

    }

    public void clickOnOpen(ActionEvent actionEvent) {
        Stage stage = new Stage();
        StackPane layout = new StackPane();
        Scene scene = new Scene(layout, 200, 200);
        stage.setResizable(false);
        stage.setScene(scene);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Json files (*.json)", "*.json");//Расширение
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            lblPrint.setText("");
            txtFieldPath.setText(file.getAbsolutePath());
        }
        else {
            lblPrint.setText("Error! You have not selected a file.");
            txtFieldPath.setText("");
        }
    }

    public void newWindowTableComponent(){
        VBox root = new VBox();
        StackPane layout = new StackPane();

        TableComponent tableComponent = new TableComponent(jsonParse);
        tableComponent.setTable();

        layout.getChildren().add(tableComponent.table);
        root.getChildren().add(layout);
        root.getChildren().add(tableComponent.ok);
        root.setAlignment(Pos.CENTER);

        VBox.setMargin(layout, new Insets(0,0, 1, 0));
        VBox.setMargin(tableComponent.ok, new Insets(1, 1, 1, 1));

        Scene newScene = new Scene(root, 687, 451);
        Stage newWindow = new Stage();
        newWindow.setTitle("Appplication analysis json");
        newWindow.setScene(newScene);
        newWindow.show();

        tableComponent.ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newWindowTableMethodComponent(tableComponent.table.getSelectionModel().getSelectedItem());
            }
        });
    }

    public void newWindowTableMethodComponent(JsonComponent component) {
        VBox root = new VBox();
        StackPane layout = new StackPane();

        TableMethodComponent tableMethodComponent = new TableMethodComponent(jsonParse);
        tableMethodComponent.setTable(component);

        layout.getChildren().add(tableMethodComponent.tableMethod);
        root.getChildren().add(layout);
        root.getChildren().add(tableMethodComponent.home);
        root.setAlignment(Pos.CENTER);

        VBox.setMargin(layout, new Insets(0,0, 1, 0));
        VBox.setMargin(tableMethodComponent.home, new Insets(1, 1, 1, 1));

        Scene newScene = new Scene(root, 687, 451);
        Stage newWindow = new Stage();
        newWindow.setTitle("Appplication analysis json");
        newWindow.setScene(newScene);
        newWindow.show();

        tableMethodComponent.home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newWindow.close();
            }
        });
    }

}
