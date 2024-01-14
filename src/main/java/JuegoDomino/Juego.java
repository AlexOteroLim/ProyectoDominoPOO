/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JuegoDomino;

import static ec.edu.espol.proyectodomino.App.loadFXML;
import ec.edu.espol.proyectodomino.VistaDominoController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Javier Otero
 */
public class Juego {
    public ArrayList<Ficha> lineajuego;
    private ArrayList<Jugador> jugadores;
    
    
    
    public Juego(){
        this.lineajuego = new ArrayList<>();
        this.jugadores = new ArrayList<>();
    }

    public ArrayList<Ficha> getLineajuego() {
        return lineajuego;
    }
    
    public void setLineajuego(ArrayList<Ficha> lineajuego) {
        this.lineajuego = lineajuego;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
    public void agregarJugador(String nombre){
        jugadores.add(new Jugador(nombre, Utilitaria.crearManoJugador()));
    }
    public int obtenerValorInicioLinea(){
        return lineajuego.get(0).getLado1();
    }
    public int ObtenerValorFinLinea(){
        return lineajuego.get(lineajuego.size()-1).getLado2();
    }
    public void mostrarLinea(){
        for(int i = 0; i< lineajuego.size(); i++){
            if(i != lineajuego.size()-1)
                System.out.print(lineajuego.get(i).toString() + " - ");
            else
                System.out.println(lineajuego.get(i).toString());
        }
    }
    public boolean agregarFichaLinea(Ficha f, Jugador j){ //agrega ficha a linea(mesa); retorna booleano y remueve ficha de la mano
            //si esta vacia
        if (lineajuego.isEmpty()){
            lineajuego.add(f);
            j.removerFicha(f);
            return true;
        }
        else{
            //si hay elementos
            if(f.getLado2() == this.obtenerValorInicioLinea()){
                    lineajuego.add(0, f);
                    j.removerFicha(f);
                    return true;
                }
                else if(f.getLado1() == this.ObtenerValorFinLinea()){
                    lineajuego.add(f);
                    j.removerFicha(f);
                    return true;
                }
            }
        return false;
        }
    public void agregarFichaLineaComodin(Ficha f, Jugador j,int l1,int l2){
            FichaComodin f2 = (FichaComodin) f;
            f2.setLado1(l1);
            f2.setLado2(l2);
            if (lineajuego.isEmpty()){
                lineajuego.add(f2);
                j.removerFicha(f2);
                //return true;
            }
            else{
            //si hay elementos
            if(f2.getLado2() == this.obtenerValorInicioLinea()){
                    lineajuego.add(0, f2);
                    j.removerFicha(f2);
                    //return true;
                }
                else if(f2.getLado1() == this.ObtenerValorFinLinea()){
                    lineajuego.add(f2);
                    j.removerFicha(f2);
                    //return true;
                }
            }
        //return false;
    }
    //comportamiento - decide cual ficha escoge, asigna random si es comodin
    public void maquina(Jugador bot){
        for(Ficha f: bot.getMano()){
            //si es f comodin
            if(f instanceof FichaComodin){
                FichaComodin f2 = (FichaComodin) f;
                Random rd = new Random();
                //va al inicio o al final
                boolean aleatorio1 = rd.nextBoolean();

                //si es que va al inicio
                if (aleatorio1){
                    f2.setLado1(rd.nextInt(6)+1);
                    f2.setLado2(obtenerValorInicioLinea());
                    lineajuego.add(0, f2);
                    bot.removerFicha(f);
                    break;
                }
                //si es que va al final
                else{
                    f2.setLado2(rd.nextInt(6)+1);
                    f2.setLado1(ObtenerValorFinLinea());
                    lineajuego.add(f2);
                    bot.removerFicha(f);
                    break;
                }
            }
            //si es que no hay elementos en la lineaJuego
            if(lineajuego.isEmpty()){
                lineajuego.add(f);
                bot.removerFicha(f);
                break;
            }else{
                    //si no es f comodin
                    if (lineajuego.isEmpty()){
                        lineajuego.add(f);
                        bot.removerFicha(f);
                        break;
                    }
                    else{
                        if(f.getLado2() == this.obtenerValorInicioLinea()){
                            lineajuego.add(0, f);
                            bot.removerFicha(f);
                            break;
                        }
                        else if(f.getLado1() == this.ObtenerValorFinLinea()){
                            lineajuego.add(f);
                            bot.removerFicha(f);
                            break;
                        }
                    }
                }
            }
        }
    }
