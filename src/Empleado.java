public class Empleado {

    // Atributos
    private String cedula;
    private String nombre;
    private double sueldo;
    private double aporteSeguro;
    private double impuestoRenta;
    private double decimotercer;
    private double decimocuarto;

    // Constructor
    public Empleado(String cedula, String nombre, double sueldo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.sueldo = sueldo;
        aporteSeguro = 0;
        impuestoRenta = 0;
        decimotercer = 0 ;
        decimocuarto = 0;
    }

    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    // Metodos

    //metodo que multiplica el sueldo por el 9.35%, para calcular el aporte al seguro.
    public double calcularAporteSeguro() {
        aporteSeguro = this.sueldo * 0.0935;
        return aporteSeguro;
    }

    //metodo que calcula el sueldo anual y segun eso va a calcular un impuesto a la renta diferente.
    public double calcularImpuestoRenta() {
        double sueldoAnual = sueldo * 12;

        if (sueldoAnual <= 5000) {
            impuestoRenta = 0;
        } else if (sueldoAnual>5000 && sueldoAnual <= 10000) {
            impuestoRenta = sueldoAnual * 0.1;
        } else if (sueldoAnual>10000 && sueldoAnual <= 18000) {
            impuestoRenta = sueldoAnual * 0.2;
        } else {
            impuestoRenta = sueldoAnual * 0.3;
        }
        return impuestoRenta;
    }

    //metodo que calcula el decimotercero sueldo anualmente multiplicando el sueldo por los doce meses anuales y los divide para 12, en fin acaba dando el mismo sueldo.
    public double calcularDecimoTerceroAnual() {
        decimotercer = (this.sueldo*12) / 12;
        return decimotercer;
    }

    //metodo que calcula el decimocuarto sueldo que segun el salario basico unificado, este es de 450
    public double calcularDecimoCuarto() {
        //Nos basamos en que el trabajador laboro 240 horas al mes
        double salarioBasicoUnificado = 450;
        decimocuarto = salarioBasicoUnificado;
        return decimocuarto;
    }

    //metodo toString sobreescrito para devolver los datos necesarios.
    @Override
    public String toString() {
        String text = "";
        text += "Nombre: " + getNombre() + "\n";
        text += "Sueldo mensual: " + String.format("%.2f", getSueldo()) + "\n";
        text += "Sueldo anual: " + String.format("%.2f", getSueldo()*12) + "\n";
        text += "Decimo tercer sueldo (anual): " + String.format("%.2f", calcularDecimoTerceroAnual()) + "\n";
        text += "Decimo cuarto sueldo (Pago agosto): " + String.format("%.2f", calcularDecimoCuarto()) + "\n";
        text += "Aporte al seguro social (mensual): " + String.format("%.2f", calcularAporteSeguro()) + "\n";
        text += "Impuesto a la renta: " + String.format("%.2f", calcularImpuestoRenta()) + "\n";
        text += "Sueldo a recibir (ANUAL (quitado impuestos)- antes del decimotercer y cuarto sueldo): " + String.format("%.2f", (getSueldo()*12 - (calcularAporteSeguro()*12) - calcularImpuestoRenta())) + "\n";
        text += "Sueldo a recibir (ANUAL (quitado impuestos)- despues del decimo tercer y cuarto sueldo): " + String.format("%.2f", (getSueldo()*12 - (calcularAporteSeguro()*12) - calcularImpuestoRenta() + calcularDecimoCuarto() + calcularDecimoTerceroAnual())) + "\n";
        return text;
    }
}
