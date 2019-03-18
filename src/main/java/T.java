import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class T {
    public String eat(String food){
        return food+"的肥料";
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        T t = new T();
//        String 面包 = t.eat("面包");
//        System.out.println(面包);
        Class<T> clazz = T.class;
        Method eat = clazz.getDeclaredMethod("eat", String.class);
        eat.setAccessible(true);
        Object 面包 = eat.invoke(t, "面包");
        System.out.println(面包);
    }
}
