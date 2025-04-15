package practice.servlet;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        ObjectMapper mapper = new ObjectMapper();
        String filePath = "D:/java/JavaBackend/First_Servlet/src/main/resources/data.json";
        File file = new File(filePath);
        ArrayList<User> users = new ObjectMapper().readValue(file, new TypeReference<ArrayList<User>>() {});

        for (User user : users) {
            if (username.equals(user.username) && password.equals(user.password)) {
                resp.sendRedirect("index.jsp");
                return;
            }
        }

        System.out.println("Invalid username or password");
        resp.sendRedirect("Login.jsp");
    }
}
