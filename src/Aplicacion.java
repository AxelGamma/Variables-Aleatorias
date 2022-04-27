import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

public class Aplicacion {
    // Tamaño de la pruebas
    public static void main(String[] args) {
        Datos datos = new Datos(150, 150, 75, 75, 150, 20);
        datos.run();
        Simulacion aplicacion = new Simulacion(datos.getPruebas1(),datos.getPruebas2(),datos.getPruebas3(),datos.getPruebas4());
        aplicacion.run();

    }
}
