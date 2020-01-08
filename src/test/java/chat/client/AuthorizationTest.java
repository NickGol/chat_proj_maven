package chat.client;

import TestUtils.*;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class AuthorizationTest {

    private static Method checkLoginCorrectness;
    private static Method readAuthData;
    private static Class<?> AuthorizationClass;

    @BeforeClass
    public static void beforeClass() {
        AuthorizationClass = TestUtils.getUserClass(Authorization.class.getName());
        checkLoginCorrectness = TestUtils.getMethod(AuthorizationClass,
                "checkLoginCorrectness",
                new int[]{Modifier.PRIVATE},
                boolean.class,
                String.class);
        checkLoginCorrectness.setAccessible(true);

        readAuthData = TestUtils.getMethod(AuthorizationClass,
                "readAuthData",
                new int[]{Modifier.PRIVATE},
                void.class,
                null);
        readAuthData.setAccessible(true);
    }

    /**
     * Testing regular expression for "login" field
     * @throws Throwable
     */
    @Test
    public void LoginRegExTest() throws Throwable {
        final String MESSAGE_TEMPLATE = "Error in method Authorization.checkLoginCorrectness(\"%s\");";
        Map<String, Boolean> checkLogins = new HashMap<String, Boolean>();
        checkLogins.put("q", false);
        checkLogins.put("qwe", true);
        checkLogins.put("qwe:", false);
        checkLogins.put("qwe ", false);
        checkLogins.put("qwe: ", false);
        checkLogins.put("qwe: rty", false);
        checkLogins.put("qwe rty", false);
        checkLogins.put("qwe:rty", false);
        checkLogins.put("QWEsfdsf+-=/';dwkwdjje", true);
        checkLogins.put("@#$%&*()1234567890_", true);
        checkLogins.put("QqqqqWwwwwEeeeeRrrrrTttttY", false);
        checkLogins.put("QqqqqWwwwwEeeeeRrrrrTtttt", true);
        for ( String key : checkLogins.keySet() ) {
            boolean actual = (boolean) TestUtils.invokeMethod(AuthorizationClass.newInstance(),
                    checkLoginCorrectness, key);
            String message = String.format(MESSAGE_TEMPLATE, key);
            assertEquals(message, checkLogins.get(key), actual);
        }
    }

    /**
     * Check for correct reading User logins and passwords from file
     * @throws Throwable
     */
    @Test
    public void readAuthDataTest() throws Throwable {

        Object obj = AuthorizationClass.newInstance();
        Field field = TestUtils.getField(AuthorizationClass, "source");
        TestUtils.updateFieldValue(obj, field, "src\\test\\resources\\Users.txt");
        TestUtils.invokeMethod(obj, readAuthData, null);
        Field field1 = TestUtils.getField(AuthorizationClass, "userInfo");
        Map<String, String> users = TestUtils.<Map<String, String>, Authorization>getFieldValue((Authorization)obj, field1);
        assertEquals("Incorrect number elements", 5, users.size());
        Map<String, String> usersLocal = new HashMap<>();
        usersLocal.put("User_1", "password_1");
        usersLocal.put("User_2", "password_2");
        usersLocal.put("User_3", "password_3");
        usersLocal.put("User_4", "password_4");
        usersLocal.put("User_5", "password_5");
        for (String key : usersLocal.keySet()) {
            assertEquals("Elements are not equal", usersLocal.get(key), users.get(key));
        }
    }

    @Test
    public void readAuthDataTest_1() throws Throwable {
        Object obj = AuthorizationClass.newInstance();
        Field field1 = TestUtils.getField(AuthorizationClass, "userInfo");

        assertThrows(IllegalArgumentException.class, () -> {
            TestUtils.<Map<String, String>, AuthorizationTest>getFieldValue(new AuthorizationTest(), field1);
        });
    }

    /**
     * Check for exception in case of incorrect path to file.
     * @throws Throwable
     */
    @Test
    public void readAuthDataTest_2() throws Throwable {

        Object obj = AuthorizationClass.newInstance();
        Field field = TestUtils.getField(AuthorizationClass, "source");
        TestUtils.updateFieldValue(obj, field, "src\\test\\resources\\Users5.txt");
        assertThrows(FileNotFoundException.class, ()-> {
            TestUtils.invokeMethod(obj, readAuthData, null);});
        }

    /**
     * Check for exception in case of incorrect data in file.
     * @throws Throwable
     */
    @Test
    public void readAuthDataTest_3() throws Throwable {

        Object obj = AuthorizationClass.newInstance();
        Field field = TestUtils.getField(AuthorizationClass, "source");
        TestUtils.updateFieldValue(obj, field, "src\\test\\resources\\Users1.txt");
        assertThrows(IOException.class, ()-> {
            TestUtils.invokeMethod(obj, readAuthData, null);});
    }
}










//            Field modifiersField = Field.class.getDeclaredField("modifiers");
//            modifiersField.setAccessible(true);
//            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);