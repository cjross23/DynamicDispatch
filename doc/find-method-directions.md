# Directions for finding the appropriate method to invoke

In [`DynamicDispatch.java`](../src/cs165/DynamicDispatch.java) you will find a lot of working support code, but the most interesting method is left to you: `callMethodFromClassElseFromSuper`.

Study `DynamicDispatch.main` to see how the method `dynamicallyCallMethodOnObject` is used. Then see how it kicks off the search by calling `callMethodFromClassElseFromSuper`, which recursively searches upwards in the class hierarchy until it finds a suitable method to invoke.

First, see what the `getClass()` method does. In main, create a new `ArrayList` and assign it to a variable of type `Collection`. Now, print out the result of calling `getClass()` on that variable. What does it show you?

Knowing this, we will implement code to dynamically dispatch to a given method based on the runtime type of an object. Java always performs dynamic dispatch (also called late binding, or dynamic binding, etc) when invoking a method on an object. It looks up the runtime type of the actual object sitting on the JVM heap, then it invokes the most specific method definition if it has been overriden one or more times in the inheritance hierarchy.

We want to model this process of dynamic dispatch by implementing a recursive search for the desired method through the inheritance hierarchy.

## Recipe

In the method `callMethodFromClassElseFromSuper`:

1. Since we'll be using recursion, we need to have a base case. We'll be recursing up the inheritance hierarchy with `Class.getSuperClass()`. If you do this iteratively we eventually end up with the class `Object`, which is the parent of all classes in Java. If you call `getSuperClass()` on `Object`, it returns `null`.
   * In your method, first check to see if the `Class` argument is `null`, and if it is we know our search failed to find any method to invoke.
   * Craft a useful error message (see comment below your method for an example).
   * Throw a new `NoSuchMethodException`, giving your error message to the constructor.
2. Next, log a message indicating that you are now trying to find the method on the current class (see comment below your method for an example). Call the static method `log` with the string you want to log.
3. Get the declared methods of the class (using `getDeclaredMethods()`) and loop over them using a for-each loop.
   1. For each `Method` object, check if its name (returned by `getName()`) is the name of the method you are looking for.
   2. If it is, further check that its parameter types (returned by `getParameterTypes()`) are compatible with the arguments you are given. Do this with the provided static method `parameterTypesMatchArguments`.
   3. If the name matches and the argument types are compatible, we've found our method! Log a message stating you found a suitable method (see comments for an example).
   4. Try to return the result of `invoke`ing the method on the `Object` `o` with the provided `arguments`.
      * Catch any `IllegalAccessException`s and throw a `new RuntimeException`, giving the constructor the `IllegalAccessException` you caught.
      * Catch any `InvocationTargetException`s and craft a useful error message (see comment below the method for an example). Then throw a `new RuntimeException`, giving it your message and the `InvocationTargetException`'s cause (use the `getCause()` method).
4. If you looped through all the declared methods on the given class, and none of them matched the desired method, then it's time to continue the search in the parent class, to see if they have a definition for it. Return the result of recursively calling `callMethodFromClassElseFromSuper` with the super class of the class argument, and the same parameters otherwise.

## Completion

Having completed these steps, you have built the core logic of a quite dynamic dispatch system that uses reflection and recursion to find the most appropriate method definition to invoke on a given object, starting the search at the class of the actual object as determined at runtime. Kudos, this is seriously cool stuff.

Write eight examples in the main method that show off this functionality, calling various methods on various objects.
