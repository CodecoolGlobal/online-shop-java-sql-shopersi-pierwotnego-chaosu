package model.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReaderForDispaly {


    public static List<String> showMenu(String menuName) {

        List<String> lines = new ArrayList<String>();

        try{
//            File file = new File("src/main/resources/menu/logMenu.txt");
            File file = new File("src/main/resources/menu/"+ menuName +".txt");


            Scanner input = new Scanner(file);

            while (input.hasNextLine()){

                lines.add(input.nextLine());
            }
            input.close();
        }   catch (Exception ex){
            ex.printStackTrace();
        }

        return lines;
    }


}
