import java.util.TreeMap;

public class Test01 {
    public static void main(String[] args) {
        User user1 = new User("jack", 11);
        User user2 = new User("tom", 11);
        TreeMap<User, String> map = new TreeMap<>();
        map.put(user1,"a");
        map.put(user2,"b");
        System.out.println(map);
    }
}
