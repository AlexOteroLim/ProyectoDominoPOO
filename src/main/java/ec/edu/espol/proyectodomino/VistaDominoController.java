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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Javier Otero
 */
public class VistaDominoController implements Initializable {
    private ArrayList<Ficha> fichasBot;
    private ArrayList<Ficha> fichasUser;
    private ArrayList<Ficha> fichasLineaJuego;
    private Ficha selected = null;
    private Jugador bot;
    private Jugador user;
    
    @FXML
    private HBox hboxBot;
    @FXML
    private HBox hboxJuego;
    @FXML
    private HBox hboxUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fichasUser =App.juego.getJugadores().get(0).getMano();
        fichasBot = App.juego.getJugadores().get(1).getMano();
        
        agregarFichasLineaUser(fichasUser);
        agregarFichasLineaBot(fichasBot);
    }    
    
    @FXML
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
    
    @FXML
    public void agregarFichasLineaUser(ArrayList<Ficha> fichas){
        try{
            for (Ficha ficha : fichas){
                    ImageView imv = new ImageView("/ImagesDomino/"+ficha.getLado1()+"-"+ficha.getLado2()+".png");
                    imv.setFitHeight(60);
                    imv.setFitWidth(100);
                    
                    if (ficha instanceof FichaComodin){
                        FichaComodin fichac = (FichaComodin)ficha;
                        imv.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent t) ->{
                            try{
                                FXMLLoader fxml = App.loadFXML("OpcionInicioFin");
                                Scene cs = new Scene(fxml.load(),600,600); //1. cargar el controller en una escena
                                Stage st = new Stage();
                                st.setScene(cs);
                                st.show();
                                ImageView b = (ImageView)t.getSource();
                                Stage s = (Stage)b.getScene().getWindow(); //window no es un stage pero se puede setear en un (stage)
                                s.close();

                            }catch(IOException e){
                                Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo abir el FXML");
                                a.show();
                            }
                        });
                    hboxUser.getChildren().add(imv);
                    }
                    else{
                        imv.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent t) ->{
                            try{
                                hboxJuego.getChildren().add(imv);
                                hboxUser.getChildren().remove(t);
                                
                            }catch(Exception e){
                                
                            }
                        });
                    }
                    hboxUser.getChildren().add(imv);
                    
                }
        }catch(Exception e){
            
        }
    }
}

