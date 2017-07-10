package cs165.dynamic;

import cs165.DynamicDispatch;

import java.util.*;

import static cs165.DynamicDispatch.Call;
import static cs165.DynamicDispatch.On;


public class Main {
    public static void demo_api_basics() throws NoSuchMethodException {
        System.out.println("Begin demo_api_basics...");
        // There are three essential ways to use the DynamicDispatch public API.
        // Here we'll see two. Can you study the code and find the third way to call methods?

        // This pattern is called a fluent interface. Not interface as in Java interface.
        // More like interface as in how does a programmer use this class (interface with this code).

        // On(target_object).call("methodName").withArgs(any, arguments, you, want, to, pass, in);
        // Call("methodname").on(target_object).withArgs(any, arguments, you, want, to, pass, in);

        List<String> list = new ArrayList<>();
        Collection<String> coll = list;
        On(list).call("add").withArgs("Cat");
        Call("add").on(coll).withArgs("Dog");

        Call("add").on(list).withArgs(0, "Bat");
        List<String> extras = Arrays.asList("hello", "world");
        On(list).call("addAll").withArgs(extras);
        System.out.println(On(list).call("containsAll").withArgs(extras));
        System.out.println(On(list).call("toString").withArgs());
    }

    public static void demo_reuse() throws NoSuchMethodException {
        System.out.println("Begin demo_reuse...");
        // Since the class used to implement the fluent interface returns a new object instead
        // of mutating itself, we can reuse the parts several times.
        Collection<String> coll = new LinkedList<>(Arrays.asList("A", "B", "C", "D"));
        DynamicDispatch.Dispatch_with_Receiver x = On(coll);
        x.call("remove").withArgs(0);
        x.call("set").withArgs(2, "X");
        System.out.println(coll);

        String s = "hello", t = "Hi";
        DynamicDispatch.Dispatch_with_Method substring = Call("substring");
        System.out.println(substring.on(s).withArgs(3));
        System.out.println(substring.on(t).withArgs(1));
    }

    public static void demo_failure() {
        System.out.println("Begin demo_failure...");
        try {
            System.out.println(On("Hello, World!").call("charmAt").withArgs(13));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(On("Hello, World!").call("charAt").withArgs(13));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            System.err.println("Caught a RuntimeException, printing stacktrace of the original exception thrown...");
            e.getCause().printStackTrace();
        }
    }

    public static void main(String[] args) throws NoSuchMethodException {
        DynamicDispatch.log_to_err(true);
        demo_api_basics();
        demo_reuse();
        demo_failure();
    }
}
