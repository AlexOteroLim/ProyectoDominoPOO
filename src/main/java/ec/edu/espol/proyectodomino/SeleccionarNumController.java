/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectodomino;

import JuegoDomino.Ficha;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Javier Otero
 */
public class SeleccionarNumController implements Initializable {

    private ComboBox<String> numberCBX;
    private boolean posicionInicioFin;
    private ArrayList<Ficha> lineajuego;

    private Text textoInicioFin;
    @FXML
    private VBox mainVBox;
    private ComboBox<String> cbxFL1;
    private ComboBox<String> cbxFL2;
    public static int l1;
    public static int l2;
    @FXML
    private Button btnAcept;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] a = new String[6];
        a[0] = "1";
        a[1] = "2";
        a[2] = "3";
        a[3] = "4";
        a[4] = "5";
        a[5] = "6";
        
        lineajuego = DominoController.lineajuego;
        if(lineajuego.isEmpty()){
            Text l1Txt = new Text("Lado 1: ");
            Text l2Txt = new Text("Lado 2: ");            
            cbxFL1 = new ComboBox<>();
            cbxFL2 = new ComboBox<>();
            cbxFL1.setPromptText("Lado 1:");
            cbxFL2.setPromptText("Lado 2:");
            cbxFL1.getItems().addAll(a);
            cbxFL2.getItems().addAll(a);
            mainVBox.setSpacing(20);
            mainVBox.getChildren().addAll(l1Txt,cbxFL1,l2Txt,cbxFL2);
        }else{
            Text l1Txt = new Text("Lado: ");
            cbxFL1 = new ComboBox<>();
            cbxFL1.setPromptText("Lado:");
            cbxFL1.getItems().addAll(a);
            mainVBox.setSpacing(20);
            mainVBox.getChildren().addAll(l1Txt,cbxFL1);
            

        }
    }
    
     
    public void numCombo(ActionEvent event) {
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
    
    @FXML
    private void aceptarBtn(ActionEvent event) {
        Stage stage = (Stage)this.btnAcept.getScene().getWindow();
        if(lineajuego.isEmpty()){
            System.out.println(cbxFL1.getValue()+cbxFL2.getValue());            
            l1 = Integer.parseInt(cbxFL1.getValue());
            l2 = Integer.parseInt(cbxFL2.getValue());
            stage.close();
        }else{
            l1 = Integer.parseInt(cbxFL1.getValue());
            stage.close();            
        }
    }
    public int getL1() {
        return l1;
    }

    public void setL1(int l1) {
        this.l1 = l1;
    }

    public int getL2() {
        return l2;
    }

    public void setL2(int l2) {
        this.l2 = l2;
    }
    
}