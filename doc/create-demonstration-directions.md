# Directions for creating a demonstration of dynamic dispatch

Now that you have a working `DynamicDispatch`, we will demonstrate its functionality and explore its public API (Application Programming Interface) (loosely, "how do I use this thing").

## Directions

Working in the class `cs165.dynamic.Main` you will create several demonstrations. First take some time to understand the code already there. The `main` method turns on logging (you may want to turn it on and off for certain tests) and calls several methods. Read the demonstration methods to get a feel for what is going on.

Now, craft your own demos:

* Create a demo showing the behaviour of dynamic dispatch in Java using dummy classes like `A`, `B`, `C` or something more fun (like animals, plants, building or vehicle types, food categories). Create some methods at different levels of the inheritance hierarchy, override some, overload some. Now create objects and call those methods to show how Java performs method dispatch. Call the same methods with your DynamicDispatch mechanism to show how it comes to the same methods by a recursive search based on runtime type.
* Create a demo showing examples where the mechanism has to search three or even four levels down the hierarchy to find the method definition (see if you can find a way to do this from the standard library classes for an extra challenge). Show an example where a method is found right away.
* Create a demo showing how to get a primitive return value back from a method invoked with this mechanism. Show how to cast the result to the desired type if the return value is a reference type.
* Create a demo of taking input from a user to perform actions on an object. Start by making a string called `s` of `Hello, world!`. Now, in a loop, prompt the user for a method to invoke on the string and any string arguments it takes. Do this however you think makes sense. Using the mechanism, call the method on the string with the provided arguments and print the result (you may want to wrap this in a `try catch NoSuchMethodException`). Then prompt the user again in a loop.
   * When trying this demo out, you should consider trying the following methods:
      * `compareTo(String other)`
      * `concat(String other)`
      * `contains(String other)`
      * `endsWith(String suffix)`
      * `equals(Object other)`
      * `equalsIgnoreCase(String other)`
      * `getBytes()`
      * `hashCode()`
      * `indexOf(String s)`
      * `isEmpty()`
      * `lastIndexOf(String s)`
      * `length()`
      * `matches(String regex)`
      * `replace(String target, String replacement)`
      * `replaceAll(String regex, String replacement)`
      * `split(String regex)`
      * `startsWith(String prefix)`
      * `toLowerCase()`
      * `toUpperCase()`
   * Pretty dynamic all right. Now modify the demo so that it first takes a string from the user, instead of hard-coding it to be `Hello, world!`.
   * Write in a block comment why you think I only suggested you try these methods, and not others from `String`. If you're not sure, try calling other methods from `String`, observe the results.
* Create a demo to measure the performance difference between normal Java method dispatch and your mechanism that uses runtime reflection to acheive the same result.
   * Create a string variable
   * Take a time stamp with `System.currentTimeMillis()`.
      * One thousand times in a for loop, invoke the `length` method on the string.
      * Take another time stamp and subtract the two to find the total elapsed time.
   * Create a variable like this: `DynamicDispatch.Dispatch_with_Method_and_Receiver m = On(my_string).call("length");` replacing `my_string` with whatever your string is called.
   * Repeat the procedure above for timing the thousand method calls. Invoke the method like this: `m.withArgs()`
   * Now write in a comment block what you found the relative performance to be. Conjecture about why the results look the way they do (just make some educated guesses).
* Finally, in a comment block above `main`, comment on the limitations of this mechanism, its ease of use, and compare it to calling methods in the normal Java compiler way (how does the type system help you there).

## Conclusion

You have built a mechanism for modeling and performing dynamic method dispatch in Java. You have learned how to use the API for `DynamicDispatch`. And you have demonstrated a gamut of functionality and behaviour.

## Grading

Turn in your `cs165.dynamic.Main.java` file to canvas for review by me. I'll be judging based on effort, so don't half-*ss it.
