import com.github.mustachejava.DefaultMustacheFactory
import java.io.File
import java.io.PrintWriter

val scopes = mapOf("content" to "Hello World!")

val pw = PrintWriter(System.out)
val mf = DefaultMustacheFactory()
val mustache = mf.compile(File("foobar.txt").bufferedReader(), "example")
mustache.execute(pw, scopes)
pw.close()
