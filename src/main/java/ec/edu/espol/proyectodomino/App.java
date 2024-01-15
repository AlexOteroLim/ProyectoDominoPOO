package ec.edu.espol.proyectodomino;

import JuegoDomino.Juego;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
//autores Annabella Sanchez, Juan Munizaga, Alex Otero
public class App extends Application {
    public static Juego juego;
    private static Scene scene;
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("PantallaInicio").load(),1280,720);
        stage.setTitle("Juego de Domino");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/imagenesOtros/dominoIcon.png")));
        stage.setScene(scene);
        stage.show();
    }
    
    public static FXMLLoader loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader;
    }

//    static void setRoot(String fxml) throws IOException {
//        scene.setRoot(loadFXML(fxml));
//    }

    public static void main(String[] args){

        
        launch();


    }
}
