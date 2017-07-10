package cs165.dynamic;

import cs165.DynamicDispatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static cs165.DynamicDispatch.Call;
import static cs165.DynamicDispatch.On;


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
    public static void main(String[] args) throws NoSuchMethodException {
//        System.out.println(Integer.TYPE.toString());
        DynamicDispatch.log_to_err(true);
        ArrayList<String> xs = new ArrayList<>();
        On(xs).call("add").withArgs("Cat");
        Call("add").on(xs).withArgs("Dog");
        Call("add").on(xs).withArgs(0, "Bat");
        List<String> extra = Arrays.asList("hello", "world");
        On(xs).call("addAll").withArgs(extra);
        System.out.println(On(xs).call("containsAll").withArgs(extra));
        System.out.println(xs);

//        xs.add("a");
//        xs.add("b");
//        dynamicallyCallMethodOnObject("add", xs, "c");
//        dynamicallyCallMethodOnObject("add", xs, 3, "d");
//        System.out.println(xs);
        A aa = new A();
        A ab = new B();


    }
}
