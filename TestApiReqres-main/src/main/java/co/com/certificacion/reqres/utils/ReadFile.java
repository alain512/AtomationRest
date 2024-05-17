package co.com.certificacion.reqres.utils;

import java.io.File;
import java.util.Scanner;

public class ReadFile {

    public static String returnFile(String route) {
        String line = "";
        Scanner input;
        try {
            input = new Scanner(new File(route));
            while (input.hasNextLine()) {
                line = line + input.nextLine();
            }
            input.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return line;
    }
}
