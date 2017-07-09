package cs165.dynamic;

import java.util.ArrayList;
import static cs165.dynamic.DynamicDispatch.dynamicallyCallMethodOnObject;

class A {
//    @Override
//    public String toString() {
//        return "A string";
//    }
}

class B extends A {
//    @Override
//    public String toString() {
//        return "B string";
//    }
}

public class Main {
    public static void main(String[] args) {
//        System.out.println(Integer.TYPE.toString());
//        ArrayList<String> xs = new ArrayList<>();
//        xs.add("a");
//        xs.add("b");
//        dynamicallyCallMethodOnObject("add", xs, "c");
//        dynamicallyCallMethodOnObject("add", xs, 3, "d");
//        System.out.println(xs);
        A aa = new A();
        A ab = new B();
        System.out.println(dynamicallyCallMethodOnObject("toString", ab));

    }
}
