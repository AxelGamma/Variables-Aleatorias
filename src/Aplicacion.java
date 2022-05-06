
public class Aplicacion {
    // Tamaño de la pruebas
    public static void main(String[] args) {
        Datos datos = new Datos(150, 150, 75, 75, 150, 20);
        datos.run();
        VariablesAleatorias aplicacion = new VariablesAleatorias(datos.getPruebas1(), datos.getPruebas2(), datos.getPruebas3(),
                datos.getPruebas4(), datos.getPruebas5(),datos.getPruebas6());
        aplicacion.run();

    }
}
