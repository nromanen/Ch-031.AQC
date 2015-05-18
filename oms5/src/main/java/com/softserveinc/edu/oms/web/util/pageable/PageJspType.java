/**
 * 
 */
package com.softserveinc.edu.oms.web.util.pageable;

/**
 * This enumerable used in AbstractPageableController and his descendants for
 * definition of type of some JSP page.
 * 
 * @author Vitalik
 * 
 */
public enum PageJspType {
	JspWithAjax, JspWithoutAjax, JspForCurrentPage;
}
