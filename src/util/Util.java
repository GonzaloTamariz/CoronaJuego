package util;

import java.util.Random;

/**
 * Contiene funciones de utilidad para las diferentes clases del juego
 * @author Gonzalo Tamariz-Martel Sanchez
 * @version 1.0
 */

public class Util {
    /**
     *  Función destinada a la generación de posiciones aleatorias
     * @param maximo Numero máximo
     *  @return Devuelve numero aleatorio
     */
    public static int generarPosicionAleatoria(int maximo){
        Random rand = new Random();
        int posicion=rand.nextInt(maximo);
        return posicion;
    }
    /**
     Detiene el programa el tiempo especificado
     @param segundos número de segundos a esperar
     */
    public static void wait(double segundos)
    {
        try
        {
            Thread.sleep((long) (segundos*1000));
        }
        catch(Exception e)
        {

        }
    }

}
