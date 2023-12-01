package domain;


import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/** Clase perteneciente al modelo. Contiene información del coronavirus
 *  @author Gonzalo Tamariz-Martel Sanchez
 *  @version 1.0
 */
public class Coronavirus extends Elemento {
    public Coronavirus(int x, int y){
        super(x,y);
        try {
            this.imagen= ImageIO.read(new File("./resources/bacterias.png"));
        } catch (IOException e) {
            System.out.println("Error cargando imagenes");
        }

    }


}
