package com.example.gps_g21;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }


    public static boolean isOperator(char input){

        return input == '+' || input == '-' || input == '*' || input == '/'
                || input == '^';

    }

    public static boolean isDecimalPoint(char input){

        return input == '.' || input == ',';

    }

    public static boolean isValidInput(String input){

        int numOperators = 0;
        int numDigits = 0;
        int decimalPoints = 0;

        for(int i = 0; i < input.length(); i++)
        {
            if(isOperator(input.charAt(i)))
                numOperators++;
            if(Character.isDigit(input.charAt(i)))
                numDigits++;
            if(isDecimalPoint(input.charAt(i)))
                decimalPoints++;
        }

        if(input.length() != numOperators + numDigits + decimalPoints) {
            //(input.length() != numOperators + numDigits + decimalPoints) ->significa que existem caraccteres invalidos

            return false;

        }

        if(isOperator(input.charAt(0)) || isOperator(input.charAt(input.length() - 1))
                || isDecimalPoint(input.charAt(0)) || isDecimalPoint(input.charAt(input.length() - 1)))
            return false; //verifica se o primeiro e ultimo operadores sao invalidos


        for(int i = 0; i < input.length(); i++)
        {
            //NÃO É PERCISO PRECUPAR COM OPERAÇOES DO TIPO INDEX = -1 OU INDEX > LENGTH PQ A OPERAÇÃO ANTERIOIR JA FILTROU ISSO

            if(isDecimalPoint(input.charAt(i)))
            {
                if(isDecimalPoint(input.charAt(i -1)) || isDecimalPoint(input.charAt(i + 1)))
                    return false; // verifica se existem 2 "." juntos

                if(isOperator(input.charAt( i - 1)) || isOperator(input.charAt(i + 1)))
                {
                    return false; //verifica se existem operadores e pontos juntos "+."
                }
            }

            if(isOperator(input.charAt(i)))
            {
                if(isOperator(input.charAt(i - 1)) || isOperator(input.charAt(i + 1)))
                {
                    return false; // verifica se existem operadores juntos "--"
                }
            }


        }
        return true;


    }


    public static void main(String[] args) {
        //launch();
        Scanner in = new Scanner(System.in);
        String input = new String();

        while (true)
        {
            System.out.print("\n-> ");
            input = in.nextLine();
            if(input.equalsIgnoreCase("sair"))
                break;
            if(isValidInput(input))
                System.out.println("Input valido :)) " + input);
            else
                System.out.println("Input invalido :((( " + input);


        }


        System.out.println("Sai");




    }
}