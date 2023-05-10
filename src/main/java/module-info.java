module com.filip.tablehw {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.postgresql.jdbc;
    requires java.sql;
    requires java.base;
    
    opens com.filip.tablehw to javafx.fxml;
    exports com.filip.tablehw;
}
