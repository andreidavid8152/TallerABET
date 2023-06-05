import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class App extends JFrame {

    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton registrarButton;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton modificarButton;
    private JTextArea textArea1;
    private JButton listarButton;
    private JTextArea textArea2;
    private JTextField textField7;
    private JTextField textField8;
    private JButton generarInformeButton;
    private JTextArea textArea3;
    //Se crea un objeto de tipo empresa para ejecutar el rol de pagos
    Empresa empresa = new Empresa();

    public App() {
        setContentPane(panel1);

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarEmpleado();
            }
        });
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                //Se crea un key listener para que el campo cedula acepte unicamente numeros y que el limite de numeros a ingresar sea de 10
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || textField1.getText().length() >= 10) {
                    e.consume();  // Ignorar el evento de la tecla
                }
            }
        });
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarEmpleados();
            }
        });
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarEmpleado();
            }
        });
        textField4.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                //Se crea un key listener para que el campo cedula acepte unicamente numeros y que el limite de numeros a ingresar sea de 10
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || textField4.getText().length() >= 10) {
                    e.consume();  // Ignorar el evento de la tecla
                }
            }
        });
        generarInformeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarInforme();
            }
        });
    }

    //FUNCIONES

    //funcion que genera informes de cada empleado
    public void generarInforme(){
        //Si el metodo no devuelve un string vacio este muestra los informe, por el contrario muestra un mensaje de error.
        if (!empresa.generarInforme().equals("")) {
            textArea3.setText("INFORMES: \n\n" + empresa.generarInforme());
        } else {
            textArea3.setText("No se puede generar un informe porque no existen empleados.");
        }
    }

    //funcion que enlista cada empleado
    public void listarEmpleados() {
        //Si el metodo no devuelve un string vacio este enlista los usuarios, por el contrario muestra un mensaje de error.
        if (!empresa.listarEmpleados().equals("")) {
            textArea2.setText("Estos son los empleados registrados: \n" + empresa.listarEmpleados());
        } else {
            textArea2.setText("No existen empleados para enlistar.");
        }
    }

    //funcion que modifica los datos de los empleados de la empresa
    public void modificarEmpleado() {
        //Validacion de datos por cada cada campo de texto
        if (verificarCampoNumeroCedula(textField4.getText())) {
            if (verificarCampoTexto(textField5.getText())) {
                if (verificarCampoTexto(textField8.getText())) {
                    if (verificarCampoNumero(textField6.getText())) {
                        //variable que almacena un objeto tipo empleado o un null, todo esto dependera de que arroje el metodo modificarEmpleado()
                        Empleado empleado = empresa.modificarEmpleado(textField4.getText(), textField5.getText() + " " + textField8.getText(), Double.parseDouble(textField6.getText()));
                        //En caso de que empleado retorne algo difernte a null este modifica los datos del empleado, por el contrario muestra un mensaje de error
                        if (empleado != null) {
                            textArea1.setText("Se ha modificado el usuario con la cedula: " + textField4.getText() + "\n");
                            textArea1.append("Nombre: " + empleado.getNombre() + "\n");
                            textArea1.append("Sueldo: " + String.valueOf(empleado.getSueldo()) + "\n");
                        } else {
                            textArea1.setText("No existe un empleado con la cedula: " + textField4.getText());
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Error. El campo sueldo solo acepta enteros positivos.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error. El campo apellido solo acepta caracteres.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error. El campo nombre solo acepta caracteres.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error. El campo cedula solo acepta 10 digitos.");
        }
    }

    //funcion que agrega empleados a la empresa
    public void agregarEmpleado() {
        //Validacion de datos por cada cada campo de texto
        if (verificarCampoNumeroCedula(textField1.getText())) {
            if (verificarCampoTexto(textField3.getText())) {
                if (verificarCampoTexto(textField7.getText())) {
                    if (verificarCampoNumero(textField2.getText())) {
                        //Se verifica que el metodo registrarEmpleado retorne un true lo cual significa que este empleado no se ha registrado y se pueda registrar, por el contrario
                        //si el empleado ya ha sido registrado se enviara un mensaje de error.
                        if (empresa.registrarEmpleado(textField1.getText(), textField3.getText() + " " + textField7.getText(), Double.parseDouble(textField2.getText()))) {
                            JOptionPane.showMessageDialog(null, "El empleado se ha agregado correctamente.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Error. El empleado ya ha sido agregado.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Error. El campo sueldo solo acepta enteros positivos.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error. El campo apellido solo acepta caracteres.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error. El campo nombre solo acepta caracteres.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error. El campo cedula solo acepta 10 digitos.");
        }
    }

    //funcion que verifica los campos de texto
    public boolean verificarCampoTexto(String textField) {
        // Verifica si el campo de texto está vacío
        if (textField == null || textField.isEmpty()) {
            return false;
        }

        // Verifica si el campo de texto contiene únicamente letras
        return textField.matches("[a-zA-Z]+");
    }

    //funcion que verifica el campo de texto cedula
    public boolean verificarCampoNumeroCedula(String field) {
        // Verificar si el campo tiene exactamente 10 dígitos
        if (field.length() != 10) {
            return false;
        }

        // Si todas las condiciones se cumplen, devolver true
        return true;
    }

    //funcion que verifica los campos de texto de tipo numero
    public boolean verificarCampoNumero(String field) {
        // Verificar si el campo está vacío
        if (field == null || field.trim().isEmpty()) {
            return false;
        }

        // Verificar si el campo contiene sólo números
        for (int i = 0; i < field.length(); i++) {
            if (!Character.isDigit(field.charAt(i))) {
                return false;
            }
        }

        // Convertir la cadena de texto a un número y verificar si es mayor a 0
        int number = Integer.parseInt(field);
        if (number <= 0) {
            return false;
        }

        // Si todas las condiciones se cumplen, devolver true
        return true;
    }
}
