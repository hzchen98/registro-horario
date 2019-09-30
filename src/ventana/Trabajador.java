package ventana;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Trabajador implements Serializable, Comparable {

    private String nombre;
    private String apellidos;
    private char[] clave;
    private String dni;
    private ArrayList<LocalDateTime> listaEntrada = new ArrayList<LocalDateTime>();
    private ArrayList<LocalDateTime> listaSalida = new ArrayList<LocalDateTime>();

    public Trabajador() {
    }

    public Trabajador(String nombre, String apellidos, char[] clave) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.clave = clave;
    }

    public Trabajador(String nombre, String apellidos, char[] clave, String dni) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.clave = clave;
        this.dni = dni;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void guardarEntrada() {
        listaEntrada.add(LocalDateTime.now());
    }

    public void guardarSalida() {
        listaSalida.add(LocalDateTime.now());
    }

    public boolean sigueDentro() {
        return listaEntrada.size() > listaSalida.size();
    }

    public boolean esClaveCorrecta(char[] claveIntroducido) {
        return Arrays.equals(clave, claveIntroducido);
    }

    public boolean esClaveCorrecto(String claveIntroducido) {
        for (int i = 0; i < claveIntroducido.length(); i++) {
            if (claveIntroducido.charAt(i) != clave[i]) {
                return false;
            }
        }
        return true;
    }

    public void setClave(char[] claveNueva) {
        clave = claveNueva;
    }

    public static void ordenarListaTrabajadorNombre(ArrayList<Trabajador> lista) {
        boolean noOrdenado = true;
        Trabajador intercambio;
        for (int i = 0; i < lista.size() - 1 && noOrdenado; i++) {
            noOrdenado = false;
            for (int j = i; j < lista.size() - 1; j++) {
                if (lista.get(j).getNombre().compareTo(lista.get(j + 1).getNombre()) > 0) {
                    noOrdenado = true;
                    intercambio = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, intercambio);
                }
            }
        }
    }
    
    public static void ordenarListaTrabajadorApellidos(ArrayList<Trabajador> lista) {
        boolean noOrdenado = true;
        Trabajador intercambio;
        for (int i = 0; i < lista.size() - 1 && noOrdenado; i++) {
            noOrdenado = false;
            for (int j = i; j < lista.size() - 1; j++) {
                if (lista.get(j).getApellidos().compareTo(lista.get(j + 1).getApellidos()) > 0) {
                    noOrdenado = true;
                    intercambio = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, intercambio);
                }
            }
        }
    }
    
    public static void ordenarListaTrabajadorDni(ArrayList<Trabajador> lista) {
        boolean noOrdenado = true;
        Trabajador intercambio;
        for (int i = 0; i < lista.size() - 1 && noOrdenado; i++) {
            noOrdenado = false;
            for (int j = i; j < lista.size() - 1; j++) {
                if (lista.get(j).getDni().compareTo(lista.get(j + 1).getDni()) > 0) {
                    noOrdenado = true;
                    intercambio = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, intercambio);
                }
            }
        }
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public int compareTo(Object o) {
        Trabajador t = (Trabajador) o;
        return nombre.compareTo(t.getNombre());
    }

    public static ArrayList<Trabajador> buscarListaNombre(ArrayList<Trabajador> lista, String nombre, BuscarTrabajador ventanaBuscar) {
        ArrayList<Trabajador> listaEncontrado = new ArrayList<>();
        ArrayList<Integer> posiciones = new ArrayList<Integer>();
        Trabajador t;
        for (int i = 0; i < lista.size(); i++) {
            t = lista.get(i);
            if (t.getNombre().contains(nombre)) {
                listaEncontrado.add(t);
                posiciones.add(i);
            }
        }

        ventanaBuscar.setPosTrabajador(posiciones);
        return listaEncontrado;
    }

    public static ArrayList<Trabajador> buscarListaApellido(ArrayList<Trabajador> lista, String apellido, BuscarTrabajador ventanaBuscar) {
        ArrayList<Trabajador> listaEncontrado = new ArrayList<>();
        ArrayList<Integer> posiciones = new ArrayList<Integer>();
        Trabajador t;
        for (int i = 0; i < lista.size(); i++) {
            t = lista.get(i);
            if (t.getApellidos().contains(apellido)) {
                listaEncontrado.add(t);
                posiciones.add(i);
            }
        }
        ventanaBuscar.setPosTrabajador(posiciones);
        return listaEncontrado;
    }

    public static ArrayList<Trabajador> buscarListaDni(ArrayList<Trabajador> lista, String dni, BuscarTrabajador ventanaBuscar) {
        ArrayList<Trabajador> listaEncontrado = new ArrayList<>();
        ArrayList<Integer> posiciones = new ArrayList<Integer>();
        Trabajador t;
        for (int i = 0; i < lista.size(); i++) {
            t = lista.get(i);
            if (t.getDni().contains(dni)) {
                listaEncontrado.add(t);
                posiciones.add(i);
            }
        }
        ventanaBuscar.setPosTrabajador(posiciones);
        return listaEncontrado;
    }
    
    public int getCantidadRegistro(){
        return listaEntrada.size();
    }
    
    public LocalDateTime getEntrada(int pos){
        return listaEntrada.get(pos);
    }
    
    public LocalDateTime getSalida(int pos) throws IndexOutOfBoundsException{
        return listaSalida.get(pos);
    }
}
