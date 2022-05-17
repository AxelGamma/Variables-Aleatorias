package Pruebas;

public class QuickSort {
    public void quickSor() {

    }

    public void quickSort(double[] a, int primero, int ultimo) {
        int i, j, central;
        double pivote;
        central = (primero + ultimo) / 2;
        pivote = a[central];
        i = primero;
        j = ultimo;
        do {
            while (a[i] < pivote)
                i++;
            while (a[j] > pivote)
                j--;
            if (i <= j) {
                intercambiar(a, i, j);
                i++;
                j--;
            }
        } while (i <= j);
        if (primero < j) {
            quickSort(a, primero, j);
        }
        if (i < ultimo) {
            quickSort(a, i, ultimo);
        }

    }

    public static void intercambiar(double[] a, int i, int j) {
        double aux = a[i];
        a[i] = a[j];
        a[j] = aux;
    }

}
