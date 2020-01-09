package chat.client;

//import com.sun.istack.internal.NotNull;

import javax.annotation.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Authorization {

    private Map<String, String> userInfo = new HashMap<String, String>();
    private static String source = "src\\main\\resources\\Users.txt";

    public static void main(String[] args) {

        Authorization aut = new Authorization();
        try {
            aut.readAuthData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readAuthData() throws IOException {

        String line;
        Map<String, String> userInfoLocal = new HashMap<String, String>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            while( (line=reader.readLine()) != null ) {
                if( line.contains("login: ") && line.contains("password: ") ) {
                    StringBuilder sb = new StringBuilder(line);
                    String login = line.split("login: ")[1].split(" ")[0];
                    String password = line.split("password: ")[1].split(" ")[0];
                    //System.out.println(login + " " + password + " Ok");
                    userInfoLocal.put(login, password);
                } else throw new IOException("Incorrect data in Users.txt file");
            }
        } /*catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/ /*catch (IOException e) {
            e.printStackTrace();
        }*/
        userInfo = userInfoLocal;
        System.out.println(userInfo);

        //лист ключей
        List keyList = new ArrayList(userInfo.keySet());
//лист значений
        List valueList = new ArrayList(userInfo.values());
////лист ключ-значения
        List entryList = new ArrayList(userInfo.entrySet());
        System.out.println(userInfo);
    }

    private boolean searchLoginInList(String login) {

        return userInfo.containsKey(login);
    }

    boolean checkLoginCorrectness(String login) {
        return login.matches("^(([\\p{L}|\\p{Graph}|[№]&&[^\\s]]){3,25})$");
        //return login.matches("^(([\\x20-\\x7E&&[^\\s:]]){3,25})$");
    }

    /**
     * This function makes search in authorization base/
     * Returns "true" if finds user with the same combination of "login" and "password" fields.
     * @param login
     * @param password
     * @return
     */
    public boolean checkPairLoginPassword(/*@NotNull*/ String login, /*@NotNull*/ String password) {
        return password.equals( userInfo.get(login) );
    }
}
//[\p{L}&&[^\p{Lu}]]