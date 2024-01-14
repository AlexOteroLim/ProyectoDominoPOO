/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JuegoDomino;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Javier Otero
 */
public class Jugador {
    private String nombre;
    private ArrayList<Ficha> mano;

    public ArrayList<Ficha> getMano() {
        return mano;
    }

    public Jugador(String nombre, ArrayList<Ficha> mano) {
        this.nombre = nombre;
        this.mano = mano;
    }

    public String getNombre() {
        return nombre;
    }

    public Ficha getFicha(int i) {
        if (i < mano.size() && i>=0)
            return this.mano.get(i);
        else
            return null;
    }
    
    public void imprimirMano(){
        int count = 0;
        for (Ficha ficha : mano){
            count++;
            if (mano.size() != count)
                System.out.print(ficha.toString()+"-");
            else
                System.out.println(ficha.toString());
        }
    }
    
    public void removerFicha(Ficha f){
        for (Ficha ficha : mano){
            if ((f.getLado1() == ficha.lado1) && (f.getLado2() == ficha.lado2))
            {
                mano.remove(ficha);
                break;
            }
        }
        
    }
    //verifica si se puede seguir jugando
    public boolean jugabilidad(Juego juego){
        if(juego.getLineajuego().isEmpty()){
            return true;
        }else{
            if(this.getMano().isEmpty()){
                return false;
                }
            else{
                for(Ficha f : this.getMano()){
                    if (f instanceof FichaComodin){
                            return true;
                        }
                    else if(f.getLado1()== juego.ObtenerValorFinLinea() || f.getLado2() == juego.obtenerValorInicioLinea()){
                            return true;
                        }
                    }
                    return false;
                }
        }
    }
    //verifica si el jugador tiene alguna ficha comodin en su mano
    public boolean tieneComodin(){
        for(Ficha f: this.getMano()){
            if(f instanceof FichaComodin){
                return true;
            }
        }
        return false;
    }
    //comportamiento del bot mientras juega
    public void botJuego(Juego juego){
        System.out.println("-------------------\nJugador " + this.getNombre() +"\n-------------------");
        System.out.print("Jugador " + this.getNombre() +": Mano -> ");
        this.imprimirMano();
        System.out.print("Linea de Juego -> ");
        juego.maquina(this);
        juego.mostrarLinea();        
    }
}