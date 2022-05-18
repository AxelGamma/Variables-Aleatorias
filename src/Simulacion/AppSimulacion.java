package Simulacion;

public class AppSimulacion {
    // Tamaño de la pruebas
    public static void main(String[] args) {
        //Los 2000 son el numero de muestras que vamos a tomar 
        Datos datos = new Datos(2000, 2000, 2000, 2000);
        datos.run();
        new SimulacionAutobuses(datos.getPruebas1(), datos.getPruebas2(), datos.getPruebas3(),
        datos.getPruebas4()).run();

    }
}
