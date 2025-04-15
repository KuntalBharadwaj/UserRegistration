package practice.servlet;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.ArrayList;

class User {
    public String username;
    public String password;
}

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
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
                System.out.println("user already exists");
                resp.sendRedirect("index.jsp");
                return;
            }
        }

        User newUser = new User();
        newUser.username = username;
        newUser.password = password;
        users.add(newUser);
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, users);
        resp.sendRedirect("index.jsp");
    }
}
