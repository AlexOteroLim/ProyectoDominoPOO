package ec.edu.espol.proyectodomino;

import JuegoDomino.Juego;
import JuegoDomino.Jugador;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

/**
 * JavaFX App
 */
public class App extends Application {
    public static Juego juego;
    private static Scene scene;
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("domino").load(), 1280, 720);

//        scene = new Scene(loadFXML("domino").load(), 1280, 780);
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
//        juego = new Juego();
//        juego.agregarJugador("user");
//        juego.agregarJugador("Bot");
//        //jugadores - instancias
//        Jugador jugador = juego.getJugadores().get(0);
//        Jugador bot = juego.getJugadores().get(1);
//        
//        Random rd = new Random();
//        primero = rd.nextBoolean(); //escoger quien inicia primero si bot o jugador
        
        
        launch();

//        System.out.println("-------------------\nBienvenido a Domino\n-------------------");
//
//            
//        if(primero){
//            //inicio jugador
//            while(jugador.jugabilidad(juego) && bot.jugabilidad(juego)){
//                jugador.jugadorJuego(juego);
//                if(bot.jugabilidad(juego))
//                    bot.botJuego(juego);
//            }
//            
//            
//        }else{
//            //inicia bot
//            while(jugador.jugabilidad(juego) && bot.jugabilidad(juego)){
//                bot.botJuego(juego);
//                if(jugador.jugabilidad(juego))
//                    jugador.jugadorJuego(juego); 
//            }
//        }
//            
//        //imprimir quien gana y pierde
//        if(jugador.jugabilidad(juego)==false){
//            if(jugador.getMano().size()==0)
//                System.out.println("El jugador "+jugador.getNombre()+" gana ya que jugó sus fichas. El jugador "+bot.getNombre()+ " pierde.");
//            else
//                System.out.println("El jugador "+jugador.getNombre()+" ya no puede jugar fichas. El jugador "+bot.getNombre()+ " gana.");
//        }
//        else{
//            if(bot.getMano().size()==0)
//                System.out.println("El jugador "+bot.getNombre()+" gana ya que jugó sus fichas. El jugador "+jugador.getNombre()+ " pierde.");
//            else
//                System.out.println("El jugador "+bot.getNombre()+" ya no puede jugar fichas. El jugador "+jugador.getNombre()+ " gana.");               
//        }
   }
    }
