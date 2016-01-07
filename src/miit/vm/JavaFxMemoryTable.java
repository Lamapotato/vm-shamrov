package miit.vm;

import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class JavaFxMemoryTable extends Application {

    public static final String Column7 = "7";
    public static final String Column6 = "6";
    public static final String Column5 = "5";
    public static final String Column4 = "4";
    public static final String Column3 = "3";
    public static final String Column2 = "2";
    public static final String Column1 = "1";
    public static final String Column0 = "0";
    public static final String ColumnName = "name";

    public static void start(String[] args) {
        launch(args);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Память");
        stage.setWidth(750);
        stage.setHeight(500);

        final Label label = new Label("Таблица памяти");
        label.setFont(new Font("Arial", 20));

        TableColumn<Map, String> colNumSeven = new TableColumn<>("7");
        TableColumn<Map, String> colNumSix = new TableColumn<>("6");
        TableColumn<Map, String> colNumFive = new TableColumn<>("5");
        TableColumn<Map, String> colNumFour = new TableColumn<>("4");
        TableColumn<Map, String> colNumThree = new TableColumn<>("3");
        TableColumn<Map, String> colNumTwo = new TableColumn<>("2");
        TableColumn<Map, String> colNumOne = new TableColumn<>("1");
        TableColumn<Map, String> colNumZero = new TableColumn<>("0");
        TableColumn<Map, String> colName = new TableColumn<>("Адрес");

        colNumSeven.setCellValueFactory(new MapValueFactory(Column7));
        colNumSeven.setMinWidth(75);
        colNumSix.setCellValueFactory(new MapValueFactory(Column6));
        colNumSix.setMinWidth(75);
        colNumFive.setCellValueFactory(new MapValueFactory(Column5));
        colNumFive.setMinWidth(75);
        colNumFour.setCellValueFactory(new MapValueFactory(Column4));
        colNumFour.setMinWidth(75);
        colNumThree.setCellValueFactory(new MapValueFactory(Column3));
        colNumThree.setMinWidth(75);
        colNumTwo.setCellValueFactory(new MapValueFactory(Column2));
        colNumTwo.setMinWidth(75);
        colNumOne.setCellValueFactory(new MapValueFactory(Column1));
        colNumOne.setMinWidth(75);
        colNumZero.setCellValueFactory(new MapValueFactory(Column0));
        colNumZero.setMinWidth(75);
        colName.setCellValueFactory(new MapValueFactory(ColumnName));
        colName.setMinWidth(75);

        TableView table_view = new TableView<>(generateDataInMap());

        table_view.setEditable(true);
        table_view.getSelectionModel().setCellSelectionEnabled(true);
        table_view.getColumns().setAll(colName,colNumSeven, colNumSix, colNumFive, colNumFour, colNumThree, colNumTwo, colNumOne, colNumZero);
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
        colNumSeven.setCellFactory(cellFactoryForMap);
        colNumSix.setCellFactory(cellFactoryForMap);
        colNumFive.setCellFactory(cellFactoryForMap);
        colNumFour.setCellFactory(cellFactoryForMap);
        colNumThree.setCellFactory(cellFactoryForMap);
        colNumTwo.setCellFactory(cellFactoryForMap);
        colNumOne.setCellFactory(cellFactoryForMap);
        colNumZero.setCellFactory(cellFactoryForMap);

        final VBox vbox = new VBox();

        vbox.setSpacing(0);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table_view);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);

        stage.show();
    }

    @SuppressWarnings("rawtypes")
	private ObservableList<Map> generateDataInMap() {
        int max = 100;
        ObservableList<Map> allData = FXCollections.observableArrayList();
        for (int i = 1; i < max; i++) {
            Map<String, String> dataRow = new HashMap<>();

            String value1 = "0000";

            dataRow.put(Column7, value1);
            dataRow.put(Column6, value1);
            dataRow.put(Column5, value1);
            dataRow.put(Column4, value1);
            dataRow.put(Column3, value1);
            dataRow.put(Column2, value1);
            dataRow.put(Column1, value1);
            dataRow.put(Column0, value1);
            dataRow.put(ColumnName, Long.toHexString(i));

            allData.add(dataRow);
        }
        return allData;
    }
    private static String toBin(int i) {
        StringBuilder sb = new StringBuilder();
        while (i > 0) {
            sb.insert(0, i & 1);
            i >>= 1;
        }
        if (sb.length() == 0) sb.append("0");
        return sb.toString();
    }
}