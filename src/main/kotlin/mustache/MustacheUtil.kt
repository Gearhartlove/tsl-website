package mustache

import com.github.mustachejava.Mustache
import com.github.mustachejava.MustacheFactory
import java.io.File
import java.io.PrintWriter

class MustacheUtil {
    /**
     *  Useful utilities for mustache renderer, to avoid rewriting builder plate code.
     */
    companion object {
        /**
         * Take input and render a mustache template.
         * @param scopes intended scope to populate mustache template
         * @param renderPath path to rendered file. Not a directory.
         * @param templatePath path to template. Not a directory.
         * @param name used to identify mustache buffered readers
         */
        fun render(
            scopes: Map<String, Any>,
            renderPath: String,
            templatePath: String,
            mustacheFactory: MustacheFactory,
            name: String
        ) {
            val destination = File(renderPath)
            val printWriter = PrintWriter(destination)
            val template = File(templatePath)
            val mustache: Mustache = mustacheFactory.compile(template.bufferedReader(), name)
            mustache.execute(printWriter, scopes)
            printWriter.close()
        }
    }
}