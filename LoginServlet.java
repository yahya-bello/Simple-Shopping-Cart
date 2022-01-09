import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    private void sendLoginForm(HttpServletResponse response,
                               boolean withErrorMessage)
            throws ServletException, IOException

    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("<TITLE>Login</TITLE>");
        out.println("</HEAD>");
        out.println("<BODY>");

        if (withErrorMessage)
            out.println("Login failed. Please try again.<BR>");

        out.println("<BR>");
        out.println("<BR>Please enter your information");
        out.println("<BR><FORM METHOD=POST>");
        out.println("<BR>User Name: <INPUT TYPE=TEXT NAME=userName>");
        out.println("<BR>Password: <INPUT TYPE=PASSWORD NAME=password>");
        out.println("<BR><INPUT TYPE=SUBMIT VALUE=Submit>");
        out.println("</FORM>");
        out.println("</BODY>");
        out.println("</HTML>");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sendLoginForm(response, false);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");


   /* HttpSession session = request.getSession(true);
    session.setAttribute("loggedIn", new String("true"));
*/

        Cookie c1 = new Cookie("userName", userName);
        Cookie c2 = new Cookie ("password", password);
        response.addCookie(c1);
        response.addCookie(c2);

        if (userName!=null && password!=null &&
                userName.equals("yahya") && password.equals("bello")) {
            response.sendRedirect("http://localhost:8085/Assignment_war_exploded/ShowaddCartServlet");
        }
        else {
            sendLoginForm(response, true);
        }

    }
}
