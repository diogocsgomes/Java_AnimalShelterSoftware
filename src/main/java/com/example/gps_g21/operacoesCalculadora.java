package com.example.gps_g21;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;

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
    /*
    public static int differenceBetweenDates(String date1, String date2) {
        try {
            String[] parts = date1.split("[/]");
            String[] parts2 = date2.split("[/]");
            if (parts[0].length() != 2 || parts[1].length() != 2 || parts[2].length() != 4 ||
            parts2[0].length() != 2 || parts2[1].length() != 2 || parts2[2].length() != 4){
                throw new Exception();
            }
            int days, days2;
            days = (Integer.parseInt(parts[2])/365) + (Integer.parseInt(parts[1])/30) + Integer.parseInt(parts[0]);
            days2 = (Integer.parseInt(parts2[2])/365) + (Integer.parseInt(parts2[1])/30) + Integer.parseInt(parts2[0]);
            if(days2>days) {
                return days2 - days;
            }else{
                return days - days2;
            }
        }catch (Exception e) {
            return 0;
        }
    }
    */

    public static int differenceBetweenDates(String date1, String date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date firstDate = sdf.parse(date1);
            Date secondDate = sdf.parse(date2);
            long timeDifference = Math.abs(firstDate.getTime() - secondDate.getTime());
            long daysDifference = timeDifference / (24 * 60 * 60 * 1000);
            return (int) daysDifference;
        } catch (ParseException e) {
            //no caso de erro a dar parse
            e.printStackTrace();
            return -1;
        }
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
        try {
            if (n < 0) {
                throw new IllegalArgumentException("O numero não pode ser negativo.");
            } else if (n < 2) {
                return n;
            } else {
                return fibonacci(n - 1) + fibonacci(n - 2);
            }
        } catch(IllegalArgumentException e){
            System.err.println(e.getMessage());
            return -1;
        }
    }

    //Calcular fatorial
    public static int factorial(int n) {
        try {
            int r = 1;

            if (n < 0) {
                throw new IllegalArgumentException("O número não pode ser negativo.");
            } else if (n == 0) {
                return r;
            } else {
                for (int i = n; i > 0; i--) {
                    r *= i;
                }
                return r;
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return -1; // or another appropriate value to indicate an error
        }
    }

    //Volume cilindro
    public static double volumeOfCylinder(double radius, double height) {
        try{
            if(radius < 0 || height < 0){
                throw new IllegalArgumentException("Pelo menos um dos valores é negativo.");
            } else{
                double volume = (Math.PI* Math.pow(radius,2)) * height;

                //arredonda p 2 casas
                return Math.round(volume * 100.0) / 100.0;
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return -1; // or another appropriate value to indicate an error
        }
    }

    //Volume cone
    public static double volumeOfCone(double radius, double height) {
        try{
            if(radius < 0 || height < 0){
                throw new IllegalArgumentException("Pelo menos um dos valores é negativo.");
            } else{
                double volume = ((Math.PI* Math.pow(radius,2)) * height ) / 3;

                return Math.round(volume * 100.0) / 100.0;
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return -1; // or another appropriate value to indicate an error
        }
    }
}
