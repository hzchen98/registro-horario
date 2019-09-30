
package ventana;

import javax.swing.text.JTextComponent;


public class Utilidades {

    
    
    public static boolean  esNoVacio(JTextComponent elemento) {
        return ! elemento.getText().equals("");
    }
}
