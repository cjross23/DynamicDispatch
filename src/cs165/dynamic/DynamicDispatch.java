package cs165.dynamic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by cole on 7/9/17.
 */
public class DynamicDispatch {
    public static Object dynamicallyCallMethodOnObject(String method_name, Object o, Object... arguments) {
        return helper(o.getClass(), method_name, o, arguments);
    }
    
    private static Object helper(Class<?> c, String method_name, Object o, Object[] arguments) {
        System.out.println("Trying to find method on class: " + c);
        if (c == null) return null;
        for (Method m : c.getDeclaredMethods()) {
            if (method_name.equals(m.getName()) && parameterTypesMatchArguments(m.getParameterTypes(), arguments)) {
                System.out.println("Found method!");
                try {
                    return m.invoke(o, arguments);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

        System.out.println("Didn't find method, searching upwards...");
        return helper(c.getSuperclass(), method_name, o, arguments);
    }

    private static boolean parameterTypesMatchArguments(Class<?>[] parameterTypes, Object[] arguments) {
        if (parameterTypes.length != arguments.length) return false;
        for (int i = 0; i < arguments.length; i++) {
            if (parameterTypes[i].isPrimitive()) {
                if (!willUnwrapToPrimitive(arguments[i], parameterTypes[i]))
                    return false;
            }
            else {
                if (!parameterTypes[i].isInstance(arguments[i]))
                    return false;
            }
        }
        return true;
    }

    private static boolean willUnwrapToPrimitive(Object x, Class<?> primitiveType) {
        final String name = primitiveType.toString();
        switch (name) {
            case "double":
                if(x instanceof Double) return true;
            case "float":
                if(x instanceof Float) return true;
            case "long":
                if(x instanceof Long) return true;
            case "int":
                if(x instanceof Integer) return true;
            case "char":
                if(x instanceof Character) return true;
                if(name.equals("char")) break;
            case "short":
                if(x instanceof Short) return true;
            case "byte":
                if(x instanceof Byte) return true;
                break;
            case "boolean":
                if(x instanceof Boolean) return true;
                break;
        }
        return false;
    }
}
