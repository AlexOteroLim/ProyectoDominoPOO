/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectodomino;

import JuegoDomino.Ficha;
import JuegoDomino.FichaComodin;
import JuegoDomino.Juego;
import JuegoDomino.Jugador;
import static ec.edu.espol.proyectodomino.App.juego;
import static ec.edu.espol.proyectodomino.App.primero;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jmuni
 */
public class DominoController implements Initializable {
    private Juego juego;
    private ArrayList<Ficha> lineajuego;
    private ArrayList<Ficha> manoBot;
    private ArrayList<Ficha> manoJugador;
    private Jugador jugador;
    private Jugador bot;

    @FXML
    private HBox lineaBotHbox;
    @FXML
    private HBox lineaJugadorHbox;
    @FXML
    private HBox lineaJuegoHBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //variables del juego
        juego = App.juego;
        lineajuego = juego.getLineajuego();
        jugador = juego.getJugadores().get(0);
        bot = juego.getJugadores().get(1);
        manoBot = bot.getMano();
        manoJugador = jugador.getMano();
        
        cargarFichas(bot, manoBot);
        cargarFichas(jugador, manoJugador);
        
        System.out.println("Inicio Juego");
        System.out.println(bot.getMano().toString());
        
    } 
    //carga las fichas
    public void cargarFichas(Jugador j, ArrayList<Ficha> fichas){
        System.out.println(j.getNombre());
        if(j.getNombre().equals("Bot")){
            for(Ficha f: fichas){
                ImageView img = (ImageView)this.imgFicha(f.getLado1(), f.getLado2());
                //bot.botJuego(juego);
                lineaBotHbox.getChildren().add(img);
            }
        } else{
            for(Ficha f: fichas){
                ImageView img = (ImageView)this.imgFicha(f.getLado1(), f.getLado2());
                img.setOnMouseExited(event -> {
                    //cuando pase por encima del mouse se vea puntero
                   img.setCursor(Cursor.HAND);
               });
                img.setOnMouseClicked(event ->{
                    System.out.println(f.toString());
                    if(f instanceof FichaComodin){
                        
                        if(lineajuego.isEmpty()){
                            try {                            
                            //ventanaSelecNum
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("SeleccionarNum.fxml"));
                            // Crear la nueva ventana
                            Stage nuevaVentana = new Stage();
                            SeleccionarNumController controller = loader.getController();
                            // Configurar la escena y mostrar la nueva ventana
                            Scene scene;
                            
                            scene = new Scene(loader.load(), 400, 400);
                            nuevaVentana.setScene(scene);
                            nuevaVentana.showAndWait();
                            int l1 = SeleccionarNumController.l1;
                            int l2 = SeleccionarNumController.l2;
                            lineaJugadorHbox.getChildren().remove(img);
                            juego.maquina(bot);
                            refreshLJuego();
                            refreshJugador(bot);
                            System.out.println(lineajuego.toString());
                            juego.agregarFichaLineaComodin(f, j, l1, l2);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                        
                    
                    }
                    else{ //si ficha no es comodin
                        if(juego.agregarFichaLinea(f, j)){
                              lineaJugadorHbox.getChildren().remove(img);
                              juego.maquina(bot);
                              refreshLJuego();
                              refreshJugador(bot);
                              System.out.println(lineajuego.toString());
                            } else{
                            //lanzar que ficha no es compatible
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setContentText("Ficha "+f.toString() +" Incompatible");
                            alert.show();
                        }
                        //jugador.jugadorJuego(juego,f);
                    }});
                lineaJugadorHbox.getChildren().add(img);
            }
        }
    }
    public int posFicha(Ficha f){
        if(lineajuego.isEmpty())
            return 0;
        if(f.getLado2() == juego.obtenerValorInicioLinea()){
                return 0;
            }else if(f.getLado1() == juego.ObtenerValorFinLinea()){
                return 1;
            }else
                return 0;
    }
    public void refreshJugador(Jugador j){
        if(j.getNombre().equals("Bot")){
            lineaBotHbox.getChildren().clear();
            for(Ficha f: j.getMano()){
                    ImageView img = imgFicha(f.getLado1(), f.getLado2());
                    lineaBotHbox.getChildren().add(img);
                }
        }else{
            lineaJugadorHbox.getChildren().clear();
            for(Ficha f: j.getMano()){
                    ImageView img = imgFicha(f.getLado1(), f.getLado2());
                    lineaJugadorHbox.getChildren().add(img);
                }
        }
    }
    public void refreshLJuego(){
        lineaJuegoHBox.getChildren().clear();
            for(Ficha f: lineajuego){
                    Node img = imgFicha(f.getLado1(), f.getLado2());
                    lineaJuegoHBox.getChildren().add(img);
                }
    }
    public ImageView imgFicha(int l1, int l2){
        ImageView imv = new ImageView("/ImagesDomino/-1--1.png");
        imv.setFitHeight(90);
        imv.setFitWidth(150);
        if(l1!=-1 && l2!=-1){
            imv = new ImageView("/ImagesDomino/"+l1+"-"+l2+".png"); 
            imv.setFitHeight(90);
            imv.setFitWidth(150);
            return imv;
        }
        return imv;
    }
}
