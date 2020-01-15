public class User implements Comparable {
    private String name;
    private int age;
    public User(String name,int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "(" + name + "," + age + ')';
    }

//    @Override
//    public int compare(Object o1, Object o2) {
//        if(o1 instanceof User && o2 instanceof User){
//            User u1 = (User)o1;
//            User u2 = (User)o2;
//            return u1.age - u2.age;
//        }
//        return -1;
//    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof User){
            User obj = (User)o;
           if(this.age == obj.age){
               return this.name.compareTo(obj.name);
           }
           return -1;
        }
        return -1;
    }
}
