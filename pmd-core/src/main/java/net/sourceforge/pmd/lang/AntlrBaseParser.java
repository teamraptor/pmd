/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;

import net.sourceforge.pmd.lang.antlr.AntlrTokenManager;
import net.sourceforge.pmd.lang.ast.AntlrBaseNode;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.ast.ParseException;

/**
 * Generic Antlr parser adapter for all Antlr parsers.
 */
public abstract class AntlrBaseParser implements net.sourceforge.pmd.lang.Parser {

    protected final ParserOptions parserOptions;

    public AntlrBaseParser(ParserOptions parserOptions) {
        this.parserOptions = parserOptions;
    }

    @Override
    public ParserOptions getParserOptions() {
        return parserOptions;
    }

    @Override
    public TokenManager getTokenManager(String fileName, Reader source) {
        try {
            return new AntlrTokenManager(getLexer(source), fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Node parse(String fileName, Reader source) throws ParseException {
        AntlrBaseNode rootNode = null;
        try {
            Parser parser = getParser(getLexer(source));
            Method rootMethod = parser.getClass().getMethod(parser.getRuleNames()[0]);
            rootNode = (AntlrBaseNode) rootMethod.invoke(parser);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return rootNode;
    }

    @Override
    public Map<Integer, String> getSuppressMap() {
        return new HashMap<>();
    }

    protected abstract Lexer getLexer(Reader source) throws IOException;

    protected abstract Parser getParser(Lexer lexer);
}
