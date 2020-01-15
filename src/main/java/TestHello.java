import com.sun.deploy.util.StringUtils;
import com.sun.jmx.snmp.SnmpUnknownAccContrModelException;

import javax.xml.namespace.QName;
import java.lang.reflect.Field;

public class  TestHello {
    public static int age = 20;
    static {
        age = 200;
        System.out.println("user....");
    }
    public static final int a = 200;
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
       String s = "abck";
        Field value = String.class.getDeclaredField("value");
        value.setAccessible(true);
        char[]o= (char[]) value.get(s);
        o[2]='a';
        System.out.println(s);
            String str = "a d  d   g";
        String s1 = StringUtils.trimWhitespace("str:" + str);
        System.out.println(s1);
        byte a = 127;
        int b = a + 1;
        byte c = (byte)b;
        System.out.println(c);

    }
}
