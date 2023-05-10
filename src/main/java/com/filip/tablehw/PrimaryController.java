package com.filip.tablehw;

import static com.filip.tablehw.Database.props;
import static com.filip.tablehw.Database.url;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.text.Font;
import javafx.util.Callback;

public class PrimaryController implements Initializable {

    private ObservableList<ObservableList> data;

    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private TableView tableView;
    @FXML
    private TextArea txtOutput;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtOutput.setText("");
        txtOutput.setFont(new Font(20));
    }

    @FXML
    private void searchClicked(ActionEvent event) {

        txtOutput.setText("");

        data = FXCollections.observableArrayList();
        tableView.getItems().clear();
        tableView.getColumns().clear();

        try {
            Connection con = DriverManager.getConnection(url, props);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(txtSearch.getText());

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableView.getColumns().addAll(col);
            }

            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }

            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            txtOutput.setText(e.getMessage());
        }
        tableView.setItems(data);
        tableView.refresh();
    }

}
