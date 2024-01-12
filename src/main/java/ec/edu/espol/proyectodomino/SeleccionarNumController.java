/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectodomino;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Javier Otero
 */
public class SeleccionarNumController implements Initializable {

    @FXML
    private ComboBox<String> numberCBX;
    private boolean posicionInicioFin;

    
    @FXML
    private Text textoInicioFin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        posicionInicioFin = OpcionInicioFinController.posicionInicioFin;
        setTexto();
        
        for(int i = 1; i < 7; i++){
            numberCBX.getItems().add(""+i);
        }
    }
    
    
    private void setTexto(){   
        if (posicionInicioFin){
            textoInicioFin.setText(textoInicioFin.getText()+" Inicio");
        }
        else{
            textoInicioFin.setText(textoInicioFin.getText()+" Final");
        }      
    }
     

    @FXML
    private void numCombo(ActionEvent event) {
        String num = numberCBX.getValue();
        if (posicionInicioFin){
            for(int i = 1; i < 7; i++){
                String numer = ""+i;
                if (num.equals(numer)){
                    try{
                        ImageView imv = new ImageView("/ImagesDomino/"+App.juego.obtenerValorInicioLinea()+"-"+Integer.valueOf(num)+".png");
                        imv.setFitHeight(60);
                        imv.setFitWidth(100);
                        //App.juego.agregarFichaLinea(App.juego.getJugadores().get(1).getMano().get(App.juego.getJugadores().get(1).getMano().size()-1), App.juego.getJugadores().get(1));
                        FXMLLoader fxml = App.loadFXML("VistaDomino");
                        Scene cs = new Scene(fxml.load(),600,600); //1. cargar el controller en una escena
                        
                        VistaDominoController hc = fxml.getController(); // Para modificar atributos del controlardor a llegar
                        hc.hboxJuego.getChildren().add(imv);
                        hc.hboxUser.getChildren().remove(App.juego.getJugadores().get(1).getMano().size()-1);
                        
                        Stage st = new Stage();
                        st.setScene(cs);
                        st.show();
                        ComboBox<?> b = (ComboBox<?>)event.getSource();
                        Stage s = (Stage)b.getScene().getWindow(); //window no es un stage pero se puede setear en un (stage)
                        s.close();
                    }catch(IOException e){

                    }
                }
            }
        }
        else{
            for(int i = 1; i < 7; i++){
                String numer = ""+i;
                if (num.equals(numer)){
                    try{
                        ImageView imv = new ImageView("/ImagesDomino/"+Integer.parseInt(num)+"-"+App.juego.ObtenerValorFinLinea()+".png");
                        imv.setFitHeight(60);
                        imv.setFitWidth(100);
                        App.juego.agregarFichaLinea(App.juego.getJugadores().get(1).getMano().get(App.juego.getJugadores().get(1).getMano().size()-1), App.juego.getJugadores().get(1));
                        FXMLLoader fxml = App.loadFXML("VistaDomino");
                        Scene cs = new Scene(fxml.load(),600,600); //1. cargar el controller en una escena
                        VistaDominoController hc = fxml.getController(); // podificar atributos del controlardor a llegar
                        hc.hboxJuego.getChildren().add(imv);
                        hc.hboxUser.getChildren().remove(App.juego.getJugadores().get(1).getMano().size()-1);
                        
                        Stage st = new Stage();
                        st.setScene(cs);
                        st.show();
                        ComboBox<?> b = (ComboBox<?>)event.getSource();
                        Stage s = (Stage)b.getScene().getWindow(); //window no es un stage pero se puede setear en un (stage)
                        s.close();
                    }catch(IOException e){

                    }
                }
            }
        }
    }
}
