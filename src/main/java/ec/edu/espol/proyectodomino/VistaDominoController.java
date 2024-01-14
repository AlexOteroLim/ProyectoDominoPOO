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
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Javier Otero
 */
public class VistaDominoController implements Initializable {
    private Juego juego;
    private ArrayList<Ficha> fichasBot;
    private ArrayList<Ficha> fichasUser;
    private ArrayList<Ficha> fichasLineaJuego;
    private Boolean primero; 
    public static Ficha selected;
    private Jugador bot;
    private Jugador user;

    private ArrayList<Ficha> lineajuego;
        @FXML
    private HBox hboxBot;

    @FXML
    private Text lineaUsr;
    public HBox hboxJuego;
    @FXML
    public HBox hboxUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        juego = new Juego();
        juego.agregarJugador("user");
        juego.agregarJugador("Bot");
        //jugadores - instancias
        Jugador jugador = juego.getJugadores().get(0);
        Jugador bot = juego.getJugadores().get(1);
        Random rd = new Random();
        primero = rd.nextBoolean(); //escoger quien inicia primero si bot o jugador
        lineaUsr.setText("Línea de " + PantallaInicioController.njugador); //modidica el nombre del usuario (revisar PIcontroller)
        fichasUser =jugador.getMano();
        fichasBot = bot.getMano();
        fichasLineaJuego = juego.getLineajuego();
        agregarFichasLineaUser(fichasUser);
        agregarFichasLineaBot(fichasBot);

    }    
    
    public void agregarFichasLineaBot(ArrayList<Ficha> fichas){
        try{
            for (Ficha ficha : fichas){
                ImageView imv = new ImageView("/ImagesDomino/"+ficha.getLado1()+"-"+ficha.getLado2()+".png");
                imv.setFitHeight(60);
                imv.setFitWidth(100);
                hboxBot.getChildren().add(imv);
            }
        }catch(Exception e){
            
        }
    }
    
    public void agregarFichasLineaUser(ArrayList<Ficha> fichas){
        try{
            for (Ficha ficha : fichas){
                    ImageView imv = new ImageView("/ImagesDomino/"+ficha.getLado1()+"-"+ficha.getLado2()+".png");
                    imv.setFitHeight(60);
                    imv.setFitWidth(100);
                    
                    if (ficha instanceof FichaComodin){
                        this.selected = ficha;
                        FichaComodin fichac = (FichaComodin)ficha;
                        imv.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent t) ->{
                            try{
                                FXMLLoader fxml = App.loadFXML("OpcionInicioFin");
                                Scene cs = new Scene(fxml.load(),600,600); //1. cargar el controller en una escena
                                Stage st = new Stage();
                                st.setScene(cs);
                                st.show();
                                
                                

                            }catch(IOException e){
                                Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo abir el FXML");
                                a.show();
                            }
                        });
                    hboxUser.getChildren().add(imv);
                    }
                    else{
                        this.selected = ficha;
                        imv.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent t) ->{
                            try{
                                if(juego.agregarFichaLinea(selected, user)){
                                    //si esta vacia
                                    if (lineajuego.isEmpty()){
                                        lineajuego.add(ficha);
                                        user.removerFicha(ficha);
                                        hboxJuego.getChildren().add(imv);
                                    }
                                    else{
                                        //si hay elementos
                                        if(selected.getLado2() == juego.obtenerValorInicioLinea()){
                                                lineajuego.add(0, selected);
                                                user.removerFicha(selected);
                                                hboxJuego.getChildren().add(0,imv);
                                            }
                                            else if(selected.getLado1() == juego.ObtenerValorFinLinea()){
                                                lineajuego.add(selected);
                                                user.removerFicha(selected);
                                                hboxJuego.getChildren().add(imv);
                                            }
                                        }
                                    hboxUser.getChildren().remove(t);
                                }
                            }catch(Exception e){
                                
                            }
                        });
                    }
                    hboxUser.getChildren().add(imv);
                    
                }
        }catch(Exception e){
            
        }
        
    }

    public HBox getHboxJuego() {
        return hboxJuego;
    }

    public void setHboxJuego(HBox hboxJuego) {
        this.hboxJuego = hboxJuego;
    }
    
}
