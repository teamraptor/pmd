/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.cpd.token;

import net.sourceforge.pmd.lang.TokenManager;

/**
 * A generic filter for JavaCC-based token managers that allows to use comments
 * to enable / disable analysis of parts of the stream
 */
public class JavaCCTokenFilter extends AbstractTokenFilter {

    /**
     * Creates a new AbstractTokenFilter
     *
     * @param tokenManager The token manager from which to retrieve tokens to be filtered
     */
    public JavaCCTokenFilter(TokenManager tokenManager) {
        super(tokenManager);
    }
}
