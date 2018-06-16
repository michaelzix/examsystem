package exam;

import com.jfinal.core.JFinal;

public class App {
    public static void main(String[] args) {
        JFinal.start("src/main/webapp",8080,"/",5);
    }
}
