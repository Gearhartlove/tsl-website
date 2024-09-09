# A Magical Elixir

![aoeaoeu](../assets/png/elixir.png)

Elixir is a functional programming language which runs on the Erlang VM (the BEAM). I've started to learn this ecosystem recently and really love what I am seeing. Below is a rant of my learning so far. *Sorry if there are error or erroneous statements, I'm going off of my memory!* 

Elixir boasts an developer friendly syntax, promising open source projects (LiveView, Phoenix, etc), amazing content to learn from exercism.com & conference videos (ElixirConf), and a passionate creator (Jose Valim).

The syntax for a basic elixir program looks a lot like this: 

```elixir
defmodule HelloWorldModule
    def message
        "Hello, World!"
    end
end

iex> HelloWorldModule.message()
"Hello, World!"
```

This small program defines a module named `HelloWorldModule` along with a function called `message`. When the message function is invoked, it returns the String "Hello, World!". Pretty simple right!

All data in this language is immutable. Notice what happens to this list: 

```elixir
iex> list = [1, "foo", :bar]
[1, "foo", :bar]
iex> List.delete_at(list, 0)
["foo", :bar]
iex> list
[1, "foo", :bar]
iex> List.pop_at(list, 0)
{1, ["foo", :bar]}
iex> list
[1, "foo", :bar]
```

In the above example, a list is instantiated with three variables. I try to delete the 0 index from the list, which returns nicely. When I then try to see the contents of my list, some of you might be surprised to see that my list has not changed! *note - you can return the element removed and the what-would-be-list with List.pop_at*. What's going on here!

At the heart of all functional languages is the idea that - given the same set of inputs and a function, you should get the same results. Bringing this back to programming, functions can be much nicer to maintain for developers years down the line if their purpose does not change when given an input. 

In the above example, given a list and the *List.delete_at/2* funciton, it will return a list with the desired index deleted every single time. That's what the function does plain and simple. No matter if it's today or at somepoint in the future, I will get the same result. That's the beauty of functional programming. 

There are three axis if you will to programming. You have data, functionality, and time. Functional programming detaches data from functionality. And spoiler alert, when you leave a function alone for X amount of time, it will still do the same thing when you come back to it. That's basically all the algebraic lambda math summarized from my POV. 

Somethings I've liked about the Elixir ecosystem thus far is: 
- pipe operator '|>' : pipe functions into each other. 
- mix build tool core to language 
- hex package manager 
- great website docs experience (very fast on search!)
- Front and center concurrency with Processes, Agents, Registries, Supervisors
- amazing 'views' into how systems actually work with `:observer.start()`
- iex repl 
- magic cookie elixir distributed systems entablement
- messaging passing patterns with processes
- pattern matching!

I'm loving this experience thus far and will continue to explore it. This feels like an undercover technology built on a strong erlang foundation I can grow with. Cheers to more! 