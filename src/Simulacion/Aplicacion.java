package Simulacion;

public class Aplicacion {
    // Tamaño de la pruebas
    public static void main(String[] args) {
        // Introduccion de los datos a generar
        // Simulacion con 150 autobuses urbanos
        // Datos datos = new Datos(150, 150, 150, 75, 150, 20);
        Datos datos = new Datos(2000, 2000, 2000, 2000);
        datos.run();
        // VariablesAleatorias aplicacion = new VariablesAleatorias(datos.getPruebas1(),
        // datos.getPruebas2(), datos.getPruebas3(),
        // datos.getPruebas4(), datos.getPruebas5(),datos.getPruebas6());
        // aplicacion.run();

        SimulacionAutobuses aplicacion = new SimulacionAutobuses(datos.getPruebas1(), datos.getPruebas2(), datos.getPruebas3(),
                datos.getPruebas4());
        aplicacion.run();

    }
}
