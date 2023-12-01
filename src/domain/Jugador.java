package domain;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/** Clase perteneciente al modelo. Contiene información del jugador
 *  @author Gonzalo Tamariz-Martel Sanchez
 *  @version 1.0
 */
public class Jugador extends Elemento {

    public Jugador(int x, int y){
        super(x,y);
        try {
            this.imagen = ImageIO.read(new File("./resources/images.png"));
        } catch (IOException e) {
            System.err.println("Error cargar imagen");
        }
    }

}
