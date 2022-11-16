package com.example.progetto_tweb;

import DAO.DAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "ServletAuthentication", value = "/ServletAuthentication")
public class ServletAuthentication extends HttpServlet {
    private String message;
    private DAO dao;


    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext ctx = config.getServletContext();
        String url = ctx.getInitParameter("dburl");
        String user = ctx.getInitParameter("dbuser");
        String password = ctx.getInitParameter("dbpassword");
        this.dao = new DAO(url,user, password);
        dao.registerDriver();

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        /* response.setContentType("text/html;charset=UTF-8");

        String login = request.getParameter("utente_login");
        String pass = request.getParameter("password_login");
        HttpSession s = request.getSession();
        if(login!=null && pass!= null){
            s.setAttribute("pass", pass);
            s.setAttribute("login", login);
        }
        PrintWriter out = response.getWriter();
        String url = response.encodeURL("/ServletAuthentication");

        try{
            String result = dao.getUtente(login, pass).getRuolo();
            out.println("<html><body>");
            out.println("<h1>ciao</h1>");
            out.println("<p>Ciao " + s.getAttribute("login")+"</p>");
            out.println("<p>"+result +"</p>");
            out.println("</body></html>");
        } catch (SQLException e){
            System.out.println("Errore: "+ e);
        }
        finally{
            out.close();
        } */

        PrintWriter out = response.getWriter();
        try{
            String login = request.getParameter("utente_login");
            String pass = request.getParameter("password_login");
            HttpSession s = request.getSession();
            if(login!=null && pass!= null){
                s.setAttribute("pass", pass);
                s.setAttribute("login", login);
            }

            String result = dao.getUtente(login, pass).getRuolo();

            response.setContentType("text/plain");
            out.print("Benvenuto " + login + ". Sei un " + result);
            out.flush();

        } catch (SQLException e) {
            System.out.println("Errore: "+ e);
        } finally {
            out.close();
        }


    }
}
