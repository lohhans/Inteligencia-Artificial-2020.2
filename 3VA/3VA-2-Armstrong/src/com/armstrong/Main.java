package com.armstrong;

public class Main {

    public static void main(String[] args) {

        float recebe_zero_transmite_zero = (float) 0.92;
        float recebe_zero_transmite_um = (float) 0.18;

        float recebe_um_transmite_zero = (float) 0.08;
        float recebe_um_transmite_um = (float) 0.82;

        float tensmite0 = (float) 0.56;
        float tensmite1 = (float) 0.44;

        float A = (recebe_um_transmite_um*tensmite1)+(recebe_um_transmite_zero*tensmite0);
        float B = (recebe_zero_transmite_zero*tensmite0)+(recebe_zero_transmite_um*tensmite1);
        float C = (recebe_um_transmite_um*tensmite1)/B;
        float D = (recebe_zero_transmite_zero*tensmite0)/A;
        float E = (recebe_zero_transmite_um*tensmite1)+(recebe_um_transmite_zero*tensmite0);

        System.out.println("RESPOSTA DO 4A): "+A);
        System.out.println("RESPOSTA DO 4B): "+B);
        System.out.println("RESPOSTA DO 4C): "+C);
        System.out.println("RESPOSTA DO 4D): "+D);
        System.out.println("RESPOSTA DO 4E): "+E);
    }
}