import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ShowaddCartServlet", value = "/ShowaddCartServlet")
public class ShowaddCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();

        int length = cookies.length;

        String userName = null;
        String password = null;

        for (int i=0; i<length; i++) {
            Cookie cookie = cookies[i];
            if (cookie.getName().equals("userName"))
                userName = cookie.getValue();
            else if (cookie.getName().equals("password"))
                password = cookie.getValue();
        }


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("<TITLE>Computers</TITLE>");
        out.println("</HEAD>");
        out.println("<BODY>");
        out.println("Hello Mr. " + userName + " <BR>Your password is: "+password);

        out.println("<BR>");
        out.println("<BR>");
        out.println("<BR>Select the Computer You wanner Buy :");
        out.println("<BR>");
        out.println("<BR><FORM METHOD=POST>");

        out.println("<BR><INPUT TYPE=CHECKBOX " + "NAME=book VALUE= \"Asus Laptop 252Mo SSD 10th Generation with RADEON graphics\"> Asus Laptop 252Mo SSD 10th Generation with RADEON graphics");

        out.println("<BR><INPUT TYPE=CHECKBOX " +
                "NAME=book VALUE=\"HP Desktop intel Core i5 with 1T HDD\"> HP Desktop intel Core i5 with 1T HDD");

        out.println("<BR><INPUT TYPE=CHECKBOX " +
                "NAME=book VALUE= \"MacBook 2021 with touch screen \"> MacBook 2021 with touch screen");

        out.println("<BR><INPUT TYPE=CHECKBOX " +
                "NAME=book VALUE=\"DELL laptop with finger print\"> DELL laptop with finger print");
        out.println("<BR>");
        out.println("<BR><INPUT TYPE=SUBMIT VALUE=\"Add to Cart\">");
        out.println("<BR><a href=\"ViewServlet\">Click to add,view and buy contents of Cart</a>");
        out.println("</FORM>");
        out.println("</BODY>");
        out.println("</HTML>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
