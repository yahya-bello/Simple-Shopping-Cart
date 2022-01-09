import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ViewServlet", value = "/ViewServlet")
public class ViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        res.setContentType("text/html");
        res.setHeader("pragma", "no-cache");
        PrintWriter out = res.getWriter();
        out.print("Click all the 4 options for the first time it won't work " +
                "then the second time it will work. ");
        out.print("<HTML><HEAD><TITLE>Online Shop</TITLE>"+
                "</HEAD><BODY><FORM METHOD=POST ACTION=");
        out.print(res.encodeRedirectURL(req.getRequestURI()));
        out.print("><INPUT TYPE=SUBMIT NAME=computer VALUE="+
                "\"Put a computer into the shopping cart\">"+
                "<br>"+
                "<INPUT TYPE=SUBMIT NAME=phone VALUE="+
                "\"Put a phone into the shopping cart\">"+
                "<br>"+
                "<INPUT TYPE=SUBMIT NAME=see VALUE="+
                "\"Show the contents of the Cart\">"+
                "<br>"+
                "<INPUT TYPE=SUBMIT NAME=buy VALUE="+
                "\"Buy contents from Cart\">"+
                "</FORM></BODY></HTML>");
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String msg;

        HttpSession session = req.getSession(true);
        if(session.isNew())
        {
            session.setAttribute("computer", new int[] { 0 });
            session.setAttribute("phone", new int[] { 0 });
        }

        int[] computer = (int[])session.getAttribute("computer");
        int[] phone = (int[])session.getAttribute("phone");

        if(req.getParameter("computer") != null)
        {
            computer[0]++;
            msg = "added a computer. we now have "+computer[0]+".";
        }
        else if(req.getParameter("phone") != null)
        {
            phone[0]++;
            msg = "added a phone. we now have "+phone[0]+".";
        }
        else if(req.getParameter("buy") != null)
        {
            session.invalidate();
            msg = "Your order for "+computer[0]+" computers and "+phone[0]+
                    " phones has been accepted. Your shopping cart is empty now.";
        }
        else
        {
            msg = "You have "+computer[0]+" computers and "+phone[0]+
                    " phones in your shopping cart.";
        }

        res.setContentType("text/html");
        res.setHeader("pragma", "no-cache");
        PrintWriter out = res.getWriter();
        out.print("<HTML><HEAD><TITLE>Shopping Cart</TITLE></HEAD><BODY>");
        out.print(msg);
        out.print("<HR><A HREF=\"");
        out.print(res.encodeRedirectURL(req.getRequestURI()));
        out.print("\">Back to the shop</A></BODY></HTML>");
        out.close();

    }
}
