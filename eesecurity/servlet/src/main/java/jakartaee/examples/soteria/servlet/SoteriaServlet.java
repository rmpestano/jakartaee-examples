/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jakartaee.examples.soteria.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A servlet example with Soteria.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@ServletSecurity(
        @HttpConstraint(rolesAllowed = "user"))
@WebServlet("/protected/*")
public class SoteriaServlet extends HttpServlet {

    /**
     * Handle the GET request.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print("And voila we were allowed to see this!");
    }
}
