# Java Exceptions
This is a short blog about Java exceptions. This is my process and my philosophy on the two.

Exceptions come in two flavors. 

- Runtime Exceptions
- Exceptions

Each exception has their pros and cons, here they are.

# Runtime Exceptions

example : 

```java
package org.tsl;

class FooRuntimeException extends RuntimeException {}

public class Main {

    public static void main(String[] args) {
        throw new FooRuntimeException();
    }
}
```

In this example, `FooRuntimeException` extends `RuntimeException`. I can throw an exception ez pz.

`RuntimeExceptions` are known as "unchecked" exceptions because the method signature does not change. 

`RuntimeExceptions` are ideal when knowing about the error kind does not matter in the slightest. This is typically done when a service is misused. If an api does not support an operation you tell it to perform, there is nothing that can be done, so a runtime exception will cleanly end the program.

# Exceptions

example :
```java
package org.tsl;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            File.createTempFile("foo", ".txt", new File("/readOnly"));
        } catch (IOException e) {
            System.out.println("Unable to create File.");
            System.out.println(e.getMessage());
        }
    }
}
```

My ready only file : 
```text
$ ls -ahl

dr--r--r-- 2 kristoff kristoff 4.0K May  3 16:46 readOnly/
```

Program output :
```text
Unable to create File.
No such file or directory
```
<br>

In this example, I'm trying to call the java static method `java.io.File.createTempFile`. This is a method that creates unique files from a preset postfix and suffix. When reading the documentation, it says : 

> ...
> 
> If the `directory` argument is not `null` and its abstract pathname is valid and denotes an existing, writable directory, then the file will be created in that directory. Otherwise the file will not be created and an `IOException` will be thrown. Under no circumstances will a directory be created at the location specified by the `directory` argument

The function signature for this program looks like this : 

```java
    public static File createTempFile(String prefix, String suffix,
                                      File directory)
        throws IOException { ... }
```

From both the documentation and the method signature, I know how this file can fail. This comes from the `IOException` checked exception. 

I am able to respond to the fail of this program by "try catching" the `IOException` error and responding to it. In this case I just print a custom message as well as the error I encountered.

Called exceptions are great when you know the bounds of your code very well. It is defined how it can succeed and fail. This luxury is not always present can be hand when it is. 

# Subjective Opinions

## Grokking Code
Knowing how a program can fail lets developers react accordingly. Sometimes failing gracefully is preferred to panicking, but not all the time. When you know how something can fail through checked exceptions, the invoking code can and should expect failure by reacting accordingly. The function is no longer a black box, success and failure states are well defined meaning we can grok both outcomes easier!

## Developer experience
Littering `throws MyException` throughout a call stack sucks. It can be a heavy burden to trail that up and up the stack. Throwing an unchecked Exception makes it way easier to just "throw an error" hands down. 

It's important to know that you don't have to bubble up the errors all the way up, you can handle it within a call stack. The top level caller / handler does not need to handle the error necessarily. 

## Boundness
It's easy to waste time designing organizing something that does not exist. If you don't know how a program can fail, setting explicit fail conditions can be a waste of time because the requirements for the program can and will change. Better planning encourages checked exceptions.

# Wrap Up 
Failing in a program is not a bad thing. Sometimes it protects us, other times it informs us, and most of the time it happens for a good reason. We can use checked exceptions to react accordingly and unchecked exceptions to denote bad api usage or un-saveable states.

You don't need to learn every exception to be useful. You can work with the function calls your already using and react to their checked exception requirements. It will be handy to write your own exceptions to propagate better error handling, and inform the user of what went wrong. 