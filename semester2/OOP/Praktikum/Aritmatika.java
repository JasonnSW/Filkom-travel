package OOP.Praktikum;

public class Aritmatika {
    public void hitungPenjumlahan(int a, int b) {
        int nilai = a + b;
        System.out.println("nilai penjumlahan adalah :" + nilai);
    }

    public static void hitungPerkalian(int a, int b) {
        int nilai = a * b;
        System.out.println("nilai perkalian adalah : " + nilai);
    }

    public static void hitungPengurangan(int a, int b) {
        int nilai = a - b;
        System.out.println("nilai pengurangan adalah :" + nilai);
    }

    public double hitungPembagian(double a, double b) {
        double nilai = a / b;
        System.out.println("nilai pembagian adalah :" + nilai);
        return nilai;
    }
}