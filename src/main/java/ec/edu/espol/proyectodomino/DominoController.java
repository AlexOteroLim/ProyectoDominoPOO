/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectodomino;

import JuegoDomino.Ficha;
import JuegoDomino.FichaComodin;
import JuegoDomino.Juego;
import JuegoDomino.Jugador;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jmuni
 */
public class DominoController implements Initializable {
    private Juego juego;
    public static ArrayList<Ficha> lineajuego;
    private ArrayList<Ficha> manoBot;
    private ArrayList<Ficha> manoJugador;
    private Jugador bot;
    private Jugador jugador;
    @FXML
    private HBox lineaBotHbox;
    @FXML
    private HBox lineaJugadorHbox;
    @FXML
    private HBox lineaJuegoHBox;
    @FXML
    private Text usrTxt;
    public static boolean jGanador;
    @FXML
    private BorderPane pantalla;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pantalla.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #0097b2, #7ed957);");
        //variables del juego
        juego = new Juego();
        usrTxt.setText("Linea " + PantallaInicioController.njugador);
        juego.agregarJugador(PantallaInicioController.njugador);
        juego.agregarJugador("bot");
        //jugadores - instancias
        ArrayList<Jugador> jugadores = juego.getJugadores();
        jugador = jugadores.get(0);
        bot = jugadores.get(1);
//        lineaUsr.setText("Línea de " + PantallaInicioController.njugador); //modidica el nombre del usuario (revisar PIcontroller)
        manoJugador =jugador.getMano();
        manoBot = bot.getMano();
        lineajuego = juego.getLineajuego();
        
        cargarFichas(bot, manoBot);
        cargarFichas(jugador, manoJugador);
        
        System.out.println("Inicio Juego");
        System.out.println(bot.getMano().toString());
        
    } 
    //carga las fichas
    public void cargarFichas(Jugador j, ArrayList<Ficha> fichas){
        System.out.println(j.getNombre());
        if(j.getNombre().equals("bot")){
            for(Ficha f: fichas){
                ImageView img = (ImageView)this.imgFicha(f.getLado1(), f.getLado2());
                //bot.botJuego(juego);
                lineaBotHbox.getChildren().add(img);
            }
        }else{
            for(Ficha f: fichas){
                ImageView img = (ImageView)this.imgFicha(f.getLado1(), f.getLado2());
                img.setOnMouseExited(event -> {
                    //cuando pase por encima del mouse se vea puntero
                   img.setCursor(Cursor.HAND);
                });
                img.setOnMouseClicked(event ->{
                    System.out.println(f.toString());
                    if(f instanceof FichaComodin){//dos casos, si hay elementos en lJuego o no
                        //si no hay elementos en lineajuego
                        if(lineajuego.isEmpty()){
                            try {
                            //Abrir selecNumController
                            //ventanaSelecNum
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("SeleccionarNum.fxml"));
                            // Crear la nueva ventana
                            Stage nuevaVentana = new Stage();
                            // Configurar la escena y mostrar la nueva ventana
                            Scene scene;
                            
                            scene = new Scene(loader.load(), 400, 400);
                            nuevaVentana.setScene(scene);
                            nuevaVentana.showAndWait();
                            
                            //entrada del SelecNumController
                            int l1 = SeleccionarNumController.l1;
                            int l2 = SeleccionarNumController.l2;
                            juego.agregarFichaLineaComodin(f, j, l1, l2);
                            lineaJugadorHbox.getChildren().remove(img);
                            juego.maquina(bot);
                            refreshJugador(bot);
                            refreshLJuego();
                            System.out.println(lineajuego.toString());
                            } catch (IOException ex) {
                            }
                        }else{
                            //cuando hay fichas en mesa
                            //abrir ventana OpcionInicioFinController
                            try{
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("OpcionInicioFin.fxml"));
                            // Crear la nueva ventana
                            Stage nuevaVentana = new Stage();
                            // Configurar la escena y mostrar la nueva ventana
                            Scene scene;
                            
                            scene = new Scene(loader.load(), 400, 400);
                            nuevaVentana.setScene(scene);
                            nuevaVentana.showAndWait();
                            
                            boolean pos = OpcionInicioFinController.posicionInicioFin;
                            int l1 = SeleccionarNumController.l1;
                            if(pos){
                                juego.agregarFichaLineaComodin(f, j, l1, juego.obtenerValorInicioLinea());
                                lineaJugadorHbox.getChildren().remove(img);
                                if(bot.jugabilidad(juego)){
                                juego.maquina(bot);
                                refreshJugador(bot);
                              }else{
                                jGanador = true;
                                this.lanzarVtnGanador().show();
                              }
                                refreshLJuego();
                            } else{
                                juego.agregarFichaLineaComodin(f, j, juego.ObtenerValorFinLinea(), l1);
                                lineaJugadorHbox.getChildren().remove(img);
                                if(bot.jugabilidad(juego)){
                                juego.maquina(bot);
                                refreshJugador(bot);
                              }else{
                                jGanador = true;
                                this.lanzarVtnGanador().show();
                              }
                                refreshLJuego();
                            }
                            
                            } catch(IOException ex){
                            }
                            
                        }
                    }
                    else{ //si ficha no es comodin
                        if(juego.agregarFichaLinea(f, j)){
                              lineaJugadorHbox.getChildren().remove(img);
                              if(bot.jugabilidad(juego)){
                                juego.maquina(bot);
                                refreshJugador(bot);
                              }else{
                                jGanador = true;
                                this.lanzarVtnGanador().show();
                              }                              
                              refreshLJuego();
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
    public void refreshJugador(Jugador j){
        if(j.getNombre().equals("bot")){
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
                    if(f instanceof FichaComodin){
                        ImageView img = imgFicha(f.getLado1(), f.getLado2());
                        ColorAdjust colorAdjust = new ColorAdjust();
                        colorAdjust.setHue(0.1); // Ajustar el tono al dorado
                        colorAdjust.setSaturation(1.0); // Máxima saturación para intensificar el color
                        colorAdjust.setBrightness(0.5); // Ajustar el brillo
                        img.setEffect(colorAdjust);
                        lineaJuegoHBox.getChildren().add(img);
                    }
                    else{
                        ImageView img = imgFicha(f.getLado1(), f.getLado2());
                        lineaJuegoHBox.getChildren().add(img);                        
                    }
                }
            if(manoJugador.isEmpty()){
                jGanador = true;
                this.lanzarVtnGanador();
            } else if(manoBot.isEmpty()){
                jGanador = false;
                this.lanzarVtnGanador();
            }
            if(!jugador.jugabilidad(juego) || !bot.jugabilidad(juego)){
                if(!jugador.jugabilidad(juego) && !bot.tieneComodin()){
                    jGanador = false;
                    this.lanzarVtnGanador().show();
                }else if(!bot.jugabilidad(juego) && !jugador.tieneComodin()){
                    jGanador = true;
                    this.lanzarVtnGanador().show();
                }
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
    public Stage lanzarVtnGanador(){
        try {
            //ventanaSelecNum
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaFinal.fxml"));
            // Crear la nueva ventana
            Stage nuevaVentana = new Stage();
            // Configurar la escena y mostrar la nueva ventana
            Scene scene;
            scene = new Scene(loader.load(), 600, 400);
            nuevaVentana.setScene(scene);
            return nuevaVentana;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}