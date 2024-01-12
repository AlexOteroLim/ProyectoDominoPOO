module ec.edu.espol.proyectodomino {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.proyectodomino to javafx.fxml;
    exports ec.edu.espol.proyectodomino;
}
