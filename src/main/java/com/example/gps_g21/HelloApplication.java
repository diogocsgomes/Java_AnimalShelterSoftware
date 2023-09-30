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
    public static void main(String[] args) {
        //launch();
        Scanner in = new Scanner(System.in);
        String input = new String();
        int opcao = 1;
        float resultFlo = 0;
        String resultStr = new String();

        while (true)
        {
            System.out.print("\n\n->0:Terminar\n");
            System.out.print("->1:Operacoes basicas calculadora\n");
            System.out.print("->2:Add/subtract days to a date\n");
            System.out.print("->3:Difference between dates\n");
            System.out.print("->4:Binary to hexadecimal conversion\n");
            System.out.print("->5:Hexadecimal to binary conversion\n");
            System.out.print("->6:Fibonacci number\n");
            System.out.print("->7:Factorial\n");
            System.out.print("->8:Volume of a cylinder\n");
            System.out.print("->9:Volume of a cone\n");
            System.out.print("\n-> ");
            opcao = in.nextInt();

            switch (opcao){
                case 1:
                    in.nextLine();
                    System.out.print("-> ");
                    input = in.nextLine();
                    resultFlo = operacoesCalculadora.operacoesBasicas(input); //Retorna float
                    System.out.println("Resultado: " + resultFlo);
                    break;
                case 2:
                    in.nextLine();
                    System.out.print("Introduza a data (01/01/2023)-> ");
                    input = in.nextLine();
                    System.out.print("Introduza os dias a somar ou subtrair (2 || -2)-> ");
                    int days = in.nextInt();
                    resultStr = operacoesCalculadora.addSubtractDaysToDate(input, days); //Retorna string
                    System.out.println("Resultado: " + resultStr);
                    break;
                case 3:
                    System.out.print("-> ");
                    input = in.nextLine();
                    break;
                case 4:
                    System.out.print("-> ");
                    input = in.nextLine();
                    break;
                case 5:
                    System.out.print("-> ");
                    input = in.nextLine();
                    break;
                case 6:
                    System.out.print("-> ");
                    input = in.nextLine();
                    break;
                case 7:
                    System.out.print("-> ");
                    input = in.nextLine();
                    break;
                case 8:
                    System.out.print("-> ");
                    input = in.nextLine();
                    break;
                case 9:
                    System.out.print("-> ");
                    input = in.nextLine();
                    break;
                default:
                    System.exit(0);
                    break;
            }
        }
    }
}