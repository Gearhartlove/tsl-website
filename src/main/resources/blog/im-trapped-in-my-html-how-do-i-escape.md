# I'm trapped in my HTML, how do I escape!?

genesis : I was confused about character escaping, so I'm going to write what I learned!

You see character escaping everywhere! It's used to "interpret a sequence of characters differently". A little wordy, I know. 

Typically there are two modes of thinking. One is the "programming language" or "context" you are working in, and the other 
is the "vanilla" or "plain" old character. Let me show you. 

- example 1 : **BASH**: take the following two examples, you will notice that one of them has raw '<' and '>' characters, and the other has them 'escaped' with the '\' character. In bash, the '<' and '>' are used to direct standard output, input, and error. So when you try to echo using these characters, the interpretation of the program doesn't know what to do. An important note here is that each language escapes it's own characters differently.
```bash
    C:\>echo <hello world>
    # sh: syntax error near unexpected token `newline'
```
```bash
    C:\>echo \<hello world\>
    # <hello world> 
```

Escaping is commonly done in programming languages and configuration files like JSON with a proceeding '/' character. In html, they take a different approach. They escape their characters with an "escape code". This is a similar idea to ASCII, in the sense that every character has a code you can render it as. [This website](https://mateam.net/html-escape-characters/) is a good resource for escaping characters. 

My struggle in understanding this was I was seeing my characters escaped via codes in html, but when they were rendered, I didn't see any of the codes and the html was'nt rendering properly, it was just text. The reason is : this is what escaping is designed to do. My mustache compiler escaped all of my html for me, and then my browser (firefox) rendered the escaped characters as just the raw characters. To fix this, I told mustache to not escape my html using {{{foo}}} instead of {{foo}}!

Last but not least, escaping is attractive because you can use "reserved character sequences" in you text / code & it helps prevent security vulnerabilities. If someone was able to save HTML into your application, they could add tags of their own to get html to render differently. When you escape the html, it is effectively neutralized because it will be interpreted as just the vanilla characters and not html. Pretty sweet!