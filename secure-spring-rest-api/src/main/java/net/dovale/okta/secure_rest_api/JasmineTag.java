package net.dovale.okta.secure_rest_api;


import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;

public class JasmineTag extends SimpleTagSupport {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();

            out.println("<a href='/" + title + "'>" + title + "</a>");
    }
}