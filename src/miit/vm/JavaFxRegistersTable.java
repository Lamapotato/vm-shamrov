package miit.vm;

import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class JavaFxRegistersTable extends Application {

    public static final String ColumnForRegisters = "регистры";
    public static final String ColumnForNames = "Номер";

    public static void start(String[] args) {
        launch(args);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("ЦПЭ");
        stage.setWidth(280);
        stage.setHeight(450);

        TableColumn<Map, String> nameCol = new TableColumn<>("");

        nameCol.setCellValueFactory(new MapValueFactory(ColumnForNames));
        nameCol.setMinWidth(75);
        TableColumn<Map, String> regCol = new TableColumn<>("Регистры");

        regCol.setCellValueFactory(new MapValueFactory(ColumnForRegisters));
        regCol.setMinWidth(75);

        TableView table_view = new TableView<>(generateDataInMap());

        table_view.setEditable(true);
        table_view.getSelectionModel().setCellSelectionEnabled(true);
        table_view.getColumns().setAll(nameCol,regCol);
        Callback<TableColumn<Map, String>, TableCell<Map, String>>
            cellFactoryForMap = new Callback<TableColumn<Map, String>,
                TableCell<Map, String>>() {
                    @Override
                    public TableCell call(TableColumn p) {
                        return new TextFieldTableCell(new StringConverter() {
                            @Override
                            public String toString(Object t) {
                                return t.toString();
                            }
                            @Override
                            public Object fromString(String string) {
                                return string;
                            }
                        });
                    }
        };
        regCol.setCellFactory(cellFactoryForMap);

        final VBox vbox = new VBox();

        vbox.setSpacing(0);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table_view);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);

        stage.show();
    }

    @SuppressWarnings("rawtypes")
	private ObservableList<Map> generateDataInMap() {
        int max = 16;
        ObservableList<Map> allData = FXCollections.observableArrayList();
        for (int i = 1; i < max; i++) {
            Map<String, String> dataRow = new HashMap<>();

            String value1 = "0000";

            dataRow.put(ColumnForRegisters, value1);
            dataRow.put(ColumnForNames, "R" + i);

            allData.add(dataRow);
        }
        return allData;
    }
}
