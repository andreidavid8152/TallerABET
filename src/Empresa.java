import java.util.HashMap;

public class Empresa {

    //Atributo
    private HashMap<String, Empleado> empleados;

    // Constructor
    public Empresa() {
        empleados = new HashMap<>();
    }

    //Metodos

    //metodo que registra un empleado en la empresa
    public boolean registrarEmpleado(String cedula, String nombre, double sueldo) {
        //Busca si el hashmap contiene la cedula, si es asi retorna falso, por el contrario si no encuentra dicha cedula devuelve verdadero.
        if (empleados.containsKey(cedula)) {
            return false;
        } else {
            Empleado empleado = new Empleado(cedula, nombre, sueldo);
            empleados.put(cedula, empleado);
            return true;
        }
    }

    //metodo que modifica un empleado a partir de la cedula
    public Empleado modificarEmpleado(String cedula, String nombre, double sueldo) {
        //Busca si el hashmap contiene la cedula, si es asi retorna el objeto modificado (Empleado), por el contrario si no encuentra dicha cedula retorna null.
        if (empleados.containsKey(cedula)) {
            Empleado empleado = empleados.get(cedula);
            empleado.setNombre(nombre);
            empleado.setSueldo(sueldo);
            return empleado;
        }
        return null;
    }

    //metodo que lista todos los empleado
    public String listarEmpleados() {
        String text = "";
        int cont = 1;
        //Recorre cada empleado en el hashmap
        for (Empleado empleado : empleados.values()) {
            text += cont + ". " + empleado.getNombre() + "\n";
            cont++;
        }
        return text;
    }

    //metodo que genera informe por cada empleado en la empresa
    public String generarInforme() {
        String text = "";
        int cont = 1;
        //Recorre cada empleado en el hashmap
        for (Empleado empleado : empleados.values()) {
            text += cont + ".\n" + empleado.toString() + "\n";
            cont++;
        }
        return text;
    }
}
