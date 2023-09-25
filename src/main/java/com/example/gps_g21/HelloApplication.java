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
        stage.setTitle("Calculadora");
        stage.setScene(scene);
        stage.show();
    }

    public static float isValidInput(String input){
        String[] parts = input.split("[+\\-*/^]");
        if (parts.length != 2)//Verifica se tem dois números ou não
            return 0;

        try{
            float num1 = Float.parseFloat(parts[0]);//Tranformar a string em float
            float num2 = Float.parseFloat(parts[1]);
            char operation = input.replaceAll("[0-9.]", "").charAt(0);//Limpa todos os numeros e '.' da string deixando apenas o operador

            switch(operation){
                
                case '^':
                    return (float) Math.pow(num1, num2);

                case '+':
                    return num1 + num2;

                case '/':
                    if(num2 != 0){
                        return num1 / num2;
                    } else{
                        System.out.println("Nao pode dividir por zero.");
                        return 0;
                    }

                case '*'://by Diogo Gomes
                    return  num1 * num2;

                default:
                    System.out.println("Operador invalido.");
                    return 0;
            }

        }catch(NumberFormatException e){
            return 0;
        }
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

            /*
            if(isValidInput(input) != 0)
                System.out.println("Resultado: " + isValidInput(input));
            else
                System.out.println("Input invalido: " + input);
             */

            float result = isValidInput(input);
            System.out.println("Resultado: " + result);
        }

        System.out.println("Sai");
        System.exit(0);
    }
}