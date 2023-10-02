package com.example.gps_g21;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class operacoesCalculadora {

    //Operacções básicas de calculadora
    public static float operacoesBasicas(String input){
        String[] parts = input.split("[+\\-*/^]");
        if (parts.length != 2)//Verifica se tem dois números ou não
            return -1;

        try{
            float num1 = Float.parseFloat(parts[0]);//Tranformar a string em float
            float num2 = Float.parseFloat(parts[1]);
            char operation = input.replaceAll("[0-9.]", "").charAt(0);//Limpa todos os numeros e '.' da string deixando apenas o operador

            switch(operation){
                case '-':
                    return num1 - num2;

                case '^':
                    return (float) Math.pow(num1, num2);

                case '+':
                    return num1 + num2;

                case '/':
                    if(num2 != 0){
                        return num1 / num2;
                    } else{
                        System.out.println("Nao pode dividir por zero.");
                        return -1;
                    }

                case '*':
                    return  num1 * num2;

                default:
                    System.out.println("Operador invalido.");
                    return -1;
            }

        }catch(NumberFormatException e){
            return -1;
        }
    }

    //Adicionar/subtrair dias a uma data
    public static String addSubtractDaysToDate(String input, int days) {
        try {
            String[] parts = input.split("[/]");
            if(parts[0].length() != 2 || parts[1].length() != 2 || parts[2].length() != 4)
                throw new Exception();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(input); //Recebe a data da string e converte para date
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            cal.add(Calendar.DATE, days);

            Date modifiedDate = cal.getTime();
            return sdf.format(modifiedDate); //Converter a string no formato data "dd/MM/yyyy"
        } catch (Exception e) {
            return "Formato de data inválido";
        }
    }

    //Diferença entre duas datas
    public static int differenceBetweenDates(String date1, String date2) {
        return 0;
    }

    //Binário - Hexadecimal
    public static String binaryToHexadecimal(String binary) {
        try {
            int decimal = Integer.parseInt(binary, 2);
            return Integer.toHexString(decimal).toUpperCase();
        } catch (NumberFormatException e) {
            return "Valor binário inválido";
        }
    }

    //Hexadecimal - Binário
    public static String hexadecimalToBinary(String hexadecimal) {
        try {
            int decimal = Integer.parseInt(hexadecimal, 16);
            return Integer.toBinaryString(decimal);
        } catch (NumberFormatException e) {
            return "Valor hexadecimal inválido";
        }
    }

    //Calcular Fibonacci
    public static long fibonacci(int n) {
        if (n < 2) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    //Calcular fatorial
    public static int factorial(int n) {
        int r = 1;

        if(n == 0)
            return r;

        for (int i = n; i > 0; i--){
            r *= i;
        }

        return r;
    }

    //Volume cilindro
    public static double volumeOfCylinder(double radius, double height) {
        return (Math.PI* Math.pow(radius,2)) * height;
    }

    //Volume cone
    public static double volumeOfCone(double radius, double height) {
        return ((Math.PI* Math.pow(radius,2)) * height ) / 3;
    }
}
