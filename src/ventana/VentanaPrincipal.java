package ventana;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Local_Admin
 */
public class VentanaPrincipal extends javax.swing.JFrame implements ActionListener {

    /**
     * Creates new form NewJFrame
     */
    private final DateTimeFormatter formatoFechaTiempo = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private final File ficheroConfiguracion = new File("src/config.txt");
    private final String defectoFicheroDatos = (new JFileChooser()).getFileSystemView().getDefaultDirectory().toString() + "\\registroHorario.txt";
    private File ficheroDatos;
    private ArrayList<Trabajador> listaTrabajador;
    private int pos = 0;

    public VentanaPrincipal() {
        initComponents();
        obtenerFicheroDatos();
        obtenerDatosGuardados();
        setVisible(true);
        visListaTrabajador();
        Timer temporizadorFechaTiempo = new Timer(1000, this);
        temporizadorFechaTiempo.start();
        System.out.println("Working Directory = "
                + System.getProperty("user.dir"));
        //setIconImage((new ImageIcon("/imagen/icono.ico")).getImage());
        //System.out.println(ficheroDatos.toString());
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    private void obtenerDatosGuardados() {
        if (ficheroDatos.length() > 0) {
            try {
                ObjectInputStream leerObjetos = new ObjectInputStream(new FileInputStream(ficheroDatos));
                listaTrabajador = (ArrayList) leerObjetos.readObject();
                leerObjetos.close();
            } catch (FileNotFoundException ex) {
                //JOptionPane.showMessageDialog(null, "No se ha localizado el fichero de datos!", "Error Fichero de Datos", JOptionPane.ERROR);
                cambiarLocalizacionFicheroDatos();
            } catch (IOException ex) {
                System.out.println(ex);
                //JOptionPane.showMessageDialog(null, "Ha surgido algún problema a la hora de editar la localización del fichero de datos!", "Error Fichero de Datos", JOptionPane.WARNING_MESSAGE);

            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
        } else {
            listaTrabajador = new ArrayList<Trabajador>();
        }
    }

    private void obtenerFicheroDatos() {
        DataInputStream lecturaFicheroConfiguracion;
        String ruta = "";
        try {
            lecturaFicheroConfiguracion = new DataInputStream(new FileInputStream(ficheroConfiguracion));
            ruta = lecturaFicheroConfiguracion.readUTF();
            lecturaFicheroConfiguracion.close();
        } catch (FileNotFoundException ex) {
            cambiarLocalizacionFicheroDatos();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        if (ruta.equals("")) {
            cambiarLocalizacionFicheroDatos();
        } else {
            ficheroDatos = new File(ruta);
        }
    }

    private void cambiarLocalizacionFicheroDatos() {
        DataOutputStream ficheroConfig = null;
        try {
            String ruta;
            ruta = guardarRutaFicheroDatos();
            ficheroConfig = new DataOutputStream(new FileOutputStream(ficheroConfiguracion));
            ficheroConfig.writeUTF(ruta);
            ficheroConfig.close();
            //System.out.println(ruta);
            if (ficheroDatos != null) {
                ficheroDatos.renameTo(new File(ruta));
            } else {
                ficheroDatos = new File(ruta);
            }
        } catch (IOException ex) {
            System.out.println(ex);
            //JOptionPane.showMessageDialog(null, "Ha surgido algún problema a la hora de editar la localización del fichero de datos!", "Error Fichero de Datos", JOptionPane.ERROR);
        }
    }

    private String guardarRutaFicheroDatos() {
        String rutaAGuardar;
        boolean guardar = false;
        File ficheroDatosGuardar;
        JFileChooser saveFile = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("FICHEROS TXT(.txt)", "txt", "text");
        saveFile.setFileFilter(filter);

        do {
            saveFile.showSaveDialog(null);
            ficheroDatosGuardar = saveFile.getSelectedFile();

            if (ficheroDatosGuardar == null) {
                JOptionPane.showMessageDialog(this, "El fichero se ha guardado por defecto en " + defectoFicheroDatos, "Fichero de Datos", JOptionPane.INFORMATION_MESSAGE);
                rutaAGuardar = defectoFicheroDatos;
                guardar = true;
            } else {
                rutaAGuardar = ficheroDatosGuardar.toString();
                if (!rutaAGuardar.substring(rutaAGuardar.length() - 4).equalsIgnoreCase(".txt")) {
                    rutaAGuardar += ".txt";
                }
                if ((new File(rutaAGuardar)).exists()) {
                    int opcionExiste = JOptionPane.showConfirmDialog(saveFile, "Ya existe el fichero con el mismo nombre, ¿desea reemplazarlo?", "Copia de seguridad",
                            JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
                    if (opcionExiste == JOptionPane.YES_OPTION) {
                        guardar = true;
                    }
                } else {
                    guardar = true;
                }
            }
        } while (!guardar);
        return rutaAGuardar;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        textoFechaTiempo = new javax.swing.JLabel();
        labelTextoNombre = new javax.swing.JLabel();
        labelTextoApellidos = new javax.swing.JLabel();
        buttonNuevoTrabajador = new javax.swing.JButton();
        buttonRegistrarEntrada = new javax.swing.JButton();
        buttonRegistrarSalida = new javax.swing.JButton();
        labelNombre = new javax.swing.JLabel();
        labelApellidos = new javax.swing.JLabel();
        labelTextoDni = new javax.swing.JLabel();
        labelDni = new javax.swing.JLabel();
        buttonConsultarRegistro = new javax.swing.JButton();
        buttonSiguiente = new javax.swing.JButton();
        buttonAnterior = new javax.swing.JButton();
        buttonBuscarTrabajador = new javax.swing.JButton();
        buttonModificarTrabajador = new javax.swing.JButton();
        buttonBorraTrabajador = new javax.swing.JButton();
        labelTextoEstado = new javax.swing.JLabel();
        labelEstado = new javax.swing.JLabel();
        comboOrdenar = new javax.swing.JComboBox<>();
        labelTextoOrdenar = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        menuCambiarFicheroDatos = new javax.swing.JMenuItem();
        menuCopiaSeguridad = new javax.swing.JMenuItem();

        jScrollPane1.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        textoFechaTiempo.setText(formatoFechaTiempo.format(LocalDateTime.now()));

        labelTextoNombre.setText("NOMBRE :");

        labelTextoApellidos.setText("APELLIDOS :");

        buttonNuevoTrabajador.setText("NUEVO TRABAJADOR");
        buttonNuevoTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNuevoTrabajadorActionPerformed(evt);
            }
        });

        buttonRegistrarEntrada.setText("REGISTRAR ENTRADA");
        buttonRegistrarEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRegistrarEntradaActionPerformed(evt);
            }
        });

        buttonRegistrarSalida.setText("REGISTRAR SALIDA");
        buttonRegistrarSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRegistrarSalidaActionPerformed(evt);
            }
        });

        labelTextoDni.setText("DNI :");

        buttonConsultarRegistro.setText("CONSULTAR REGISTRO");
        buttonConsultarRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConsultarRegistroActionPerformed(evt);
            }
        });

        buttonSiguiente.setText("SIGUIENTE");
        buttonSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSiguienteActionPerformed(evt);
            }
        });

        buttonAnterior.setText("ANTERIOR");
        buttonAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAnteriorActionPerformed(evt);
            }
        });

        buttonBuscarTrabajador.setText("BUSCAR TRABAJADOR");
        buttonBuscarTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarTrabajadorActionPerformed(evt);
            }
        });

        buttonModificarTrabajador.setText("MODIFICAR DATOS");
        buttonModificarTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonModificarTrabajadorActionPerformed(evt);
            }
        });

        buttonBorraTrabajador.setText("BORRAR TRABAJADOR");
        buttonBorraTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBorraTrabajadorActionPerformed(evt);
            }
        });

        labelTextoEstado.setText("ESTADO:");

        comboOrdenar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NOMBRE", "APELLIDOS", "DNI" }));
        comboOrdenar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboOrdenarItemStateChanged(evt);
            }
        });
        comboOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboOrdenarActionPerformed(evt);
            }
        });

        labelTextoOrdenar.setText("ORDENAR POR:");

        jMenu2.setText("Editar");

        menuCambiarFicheroDatos.setText("Cambiar localización de fichero de datos");
        menuCambiarFicheroDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCambiarFicheroDatosActionPerformed(evt);
            }
        });
        jMenu2.add(menuCambiarFicheroDatos);

        menuCopiaSeguridad.setText("Realizar una copia de seguridad");
        menuCopiaSeguridad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCopiaSeguridadActionPerformed(evt);
            }
        });
        jMenu2.add(menuCopiaSeguridad);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(buttonAnterior)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(labelTextoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(64, 64, 64)
                                            .addComponent(labelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(labelTextoApellidos)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(labelTextoDni, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(64, 64, 64)
                                            .addComponent(labelDni, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(labelTextoEstado)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(125, 125, 125)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(labelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(132, 132, 132))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(buttonSiguiente)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textoFechaTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(buttonBuscarTrabajador, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                .addGap(32, 32, 32)
                                .addComponent(buttonBorraTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(buttonNuevoTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonModificarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRegistrarEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(labelTextoOrdenar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboOrdenar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(buttonRegistrarSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonConsultarRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonNuevoTrabajador)
                    .addComponent(buttonBuscarTrabajador)
                    .addComponent(buttonBorraTrabajador))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTextoNombre)
                    .addComponent(labelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelApellidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelTextoApellidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSiguiente)
                    .addComponent(buttonAnterior))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTextoDni, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDni, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(labelTextoEstado))
                    .addComponent(labelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboOrdenar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTextoOrdenar))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonRegistrarEntrada)
                    .addComponent(buttonRegistrarSalida))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonModificarTrabajador)
                    .addComponent(buttonConsultarRegistro))
                .addGap(37, 37, 37)
                .addComponent(textoFechaTiempo))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonRegistrarEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistrarEntradaActionPerformed
        // TODO add your handling code here:
        if (listaTrabajador.get(pos).sigueDentro()) {
            JOptionPane.showMessageDialog(this, "Debe registrar una salida antes del nuevo registro de entrada!", "Registrar endrada", JOptionPane.WARNING_MESSAGE);
        } else if (registrar("entrada")) {
            listaTrabajador.get(pos).guardarEntrada();
            visListaTrabajador();
        }
    }//GEN-LAST:event_buttonRegistrarEntradaActionPerformed

    private boolean registrar(String tipo) {
        if (esCorrectoContrasenaIntroducido("Registrar " + tipo)) {
            if (JOptionPane.showConfirmDialog(this, "¿Seguro que desea registrar " + tipo + "?\n" + textoFechaTiempo.getText(), "Registro " + tipo, JOptionPane.YES_OPTION, JOptionPane.NO_OPTION) == JOptionPane.YES_OPTION) {
                return true;
            }
        }
        return false;
    }

    private void buttonRegistrarSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistrarSalidaActionPerformed
        // TODO add your handling code here:
        if (!listaTrabajador.get(pos).sigueDentro()) {
            JOptionPane.showMessageDialog(this, "Debe registrar una entrada antes del nuevo registro de salida!", "Registrar salida", JOptionPane.WARNING_MESSAGE);
        } else if (listaTrabajador.get(pos).sigueDentro() && registrar("salida")) {
            listaTrabajador.get(pos).guardarSalida();
            visListaTrabajador();
        }
    }//GEN-LAST:event_buttonRegistrarSalidaActionPerformed

    private void buttonConsultarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConsultarRegistroActionPerformed
        // TODO add your handling code here:
        new VentanaRegistro(listaTrabajador.get(pos), this);
    }//GEN-LAST:event_buttonConsultarRegistroActionPerformed

    private void menuCambiarFicheroDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCambiarFicheroDatosActionPerformed
        this.setVisible(false);
        cambiarLocalizacionFicheroDatos();
        this.setVisible(true);
    }//GEN-LAST:event_menuCambiarFicheroDatosActionPerformed

    private void buttonBuscarTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarTrabajadorActionPerformed
        // TODO add your handling code here:
        new BuscarTrabajador(this, listaTrabajador);
    }//GEN-LAST:event_buttonBuscarTrabajadorActionPerformed

    private void buttonAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAnteriorActionPerformed
        // TODO add your handling code here:
        if (pos > 0) {
            pos--;
            visListaTrabajador();
        } else {
            JOptionPane.showMessageDialog(this, "No existe más trabajadores anteriores!", "No existe más", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_buttonAnteriorActionPerformed

    private void buttonSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSiguienteActionPerformed
        // TODO add your handling code here:
        if (pos < listaTrabajador.size() - 1) {
            pos++;
            visListaTrabajador();
        } else {
            JOptionPane.showMessageDialog(this, "No existe más trabajadores siguientes!", "No existe más", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_buttonSiguienteActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        guardarDatos();
    }//GEN-LAST:event_formWindowClosing

    private void guardarDatos() {
        try {
            // TODO add your handling code here:
            ObjectOutputStream guardarDatos = new ObjectOutputStream(new FileOutputStream(ficheroDatos));
            guardarDatos.writeObject(listaTrabajador);
            guardarDatos.close();
            //System.out.println("Se guardó correctamente en " + ficheroDatos);
        } catch (IOException ex) {
            System.out.println(ex);
            //System.out.println("Surgió un error a la hora de guardar en " + ficheroDatos);
        }
    }

    private void buttonModificarTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificarTrabajadorActionPerformed
        // TODO add your handling code here:
        new NuevoTrabajador(listaTrabajador, this, pos);
    }//GEN-LAST:event_buttonModificarTrabajadorActionPerformed

    private void buttonBorraTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBorraTrabajadorActionPerformed
        // TODO add your handling code here:
        if (esCorrectoContrasenaIntroducido("Borrar trabajador")) {
            if (JOptionPane.showConfirmDialog(this, "¿Seguro que desea borrar el trabajador?", "Borrar trabajador", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                listaTrabajador.remove(pos);
                visListaTrabajador();
            }
        }
    }//GEN-LAST:event_buttonBorraTrabajadorActionPerformed

    private void buttonNuevoTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNuevoTrabajadorActionPerformed
        // TODO add your handling code here:
        //setVisible(false);
        new NuevoTrabajador(listaTrabajador, this);
        visListaTrabajador();
    }//GEN-LAST:event_buttonNuevoTrabajadorActionPerformed

    private void menuCopiaSeguridadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCopiaSeguridadActionPerformed
        // TODO add your handling code here:
        guardarDatos();

        String rutaCopia;
        boolean copiado = false;
        JFileChooser saveFile = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("FICHEROS TXT(.txt)", "txt", "text");
        saveFile.setFileFilter(filter);
        int chooserOption;

        do {
            chooserOption = saveFile.showSaveDialog(this);
            if (chooserOption != JFileChooser.CANCEL_OPTION) {
                rutaCopia = saveFile.getSelectedFile().toString();
                if (!rutaCopia.substring(rutaCopia.length() - 4).equalsIgnoreCase(".txt")) {
                    rutaCopia += ".txt";
                }
                File ficheroDestino = new File(rutaCopia);

                if (ficheroDestino.exists()) {
                    int opcionExiste = JOptionPane.showConfirmDialog(saveFile, "Ya existe el fichero con el mismo nombre, ¿desea reemplazarlo?", "Copia de seguridad",
                            JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
                    if (opcionExiste == JOptionPane.YES_OPTION) {
                        try {
                            Files.copy(ficheroDatos.toPath(), ficheroDestino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                            JOptionPane.showMessageDialog(this, "Se realizó correctamente la copia de seguridad!", "Copia de seguridad", JOptionPane.INFORMATION_MESSAGE);
                        } catch (IOException ex) {
                            System.out.println(ex);
                        }
                        copiado = true;
                    } else if (opcionExiste != JOptionPane.NO_OPTION) {
                        copiado = true;
                    }
                } else {
                    try {
                        Files.copy(ficheroDatos.toPath(), ficheroDestino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        JOptionPane.showMessageDialog(this, "Se realizó correctamente la copia de seguridad!", "Copia de seguridad", JOptionPane.INFORMATION_MESSAGE);
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                    copiado = true;
                }
            } else {
                copiado = true;
            }
        } while (!copiado);
    }//GEN-LAST:event_menuCopiaSeguridadActionPerformed

    private void comboOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboOrdenarActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_comboOrdenarActionPerformed

    private void comboOrdenarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboOrdenarItemStateChanged
        // TODO add your handling code here:
        switch (comboOrdenar.getSelectedIndex()) {
            case 0:
                Trabajador.ordenarListaTrabajadorNombre(listaTrabajador);
                break;

            case 1:
                Trabajador.ordenarListaTrabajadorApellidos(listaTrabajador);
                break;

            case 2:
                Trabajador.ordenarListaTrabajadorDni(listaTrabajador);
        }
        pos = 0;
        visListaTrabajador();
    }//GEN-LAST:event_comboOrdenarItemStateChanged

    public void visListaTrabajador() {
        Trabajador actualVis;
        if (listaTrabajador.size() != 0) {
            actualVis = listaTrabajador.get(pos);
            labelApellidos.setText(actualVis.getApellidos());
            labelDni.setText(actualVis.getDni());
            labelNombre.setText(actualVis.getNombre());
            if (actualVis.sigueDentro()) {
                String u = System.getProperty("user.dir") + "\\src\\trabajo.png";
                ImageIcon m = new ImageIcon(u);
                labelEstado.setIcon(m);
            } else {

                String u = System.getProperty("user.dir") + "\\src\\noTrabajo.png";
                ImageIcon m = new ImageIcon(u);
                labelEstado.setIcon(m);
            }
        } else {
            labelNombre.setText("Aún no existe ningún trabajador!");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    private boolean esCorrectoContrasenaIntroducido(String titulo) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Contraseña del trabajador:");
        JPasswordField pass = new JPasswordField(10);
        panel.add(label);
        panel.add(pass);
        String[] options = new String[]{"OK", "Cancelar"};
        int option;
        boolean noCorrecto = true;
//        pass.requestFocus();
        do {
            option = JOptionPane.showOptionDialog(this, panel, titulo,
                    JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]);

            if (option == 0) {
                if (!listaTrabajador.get(pos).esClaveCorrecta(pass.getPassword())) {
                    JOptionPane.showMessageDialog(panel, "Contraseña incorrecta!", titulo, JOptionPane.WARNING_MESSAGE);
                } else {
                    noCorrecto = false;
                }
            } else {
                noCorrecto = false;
            }
        } while (noCorrecto);

        if (!noCorrecto && option == 0) {
            return true;
        }
        return false;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAnterior;
    private javax.swing.JButton buttonBorraTrabajador;
    private javax.swing.JButton buttonBuscarTrabajador;
    private javax.swing.JButton buttonConsultarRegistro;
    private javax.swing.JButton buttonModificarTrabajador;
    private javax.swing.JButton buttonNuevoTrabajador;
    private javax.swing.JButton buttonRegistrarEntrada;
    private javax.swing.JButton buttonRegistrarSalida;
    private javax.swing.JButton buttonSiguiente;
    private javax.swing.JComboBox<String> comboOrdenar;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelApellidos;
    private javax.swing.JLabel labelDni;
    private javax.swing.JLabel labelEstado;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelTextoApellidos;
    private javax.swing.JLabel labelTextoDni;
    private javax.swing.JLabel labelTextoEstado;
    private javax.swing.JLabel labelTextoNombre;
    private javax.swing.JLabel labelTextoOrdenar;
    private javax.swing.JMenuItem menuCambiarFicheroDatos;
    private javax.swing.JMenuItem menuCopiaSeguridad;
    private javax.swing.JLabel textoFechaTiempo;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        textoFechaTiempo.setText(formatoFechaTiempo.format(LocalDateTime.now()));
    }
}
