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
        String filePath = getServletContext().getRealPath("/WEB-INF/data.json");
        File file = new File(filePath);

        ArrayList<User> users = new ObjectMapper().readValue(file, new TypeReference<ArrayList<User>>() {});

        for (User user : users) {
            System.out.println(user.username);
            System.out.println(user.password);
            if (username.equals(user.username) && password.equals(user.password)) {
                resp.sendRedirect("/index.jsp");
            }
        }

        User newUser = new User();
        newUser.username = username;
        newUser.password = password;
        users.add(newUser);

//        for (User user : users) {
//            System.out.println(user.username);
//            System.out.println(user.password);
//        }

        System.out.println();
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, users);
        resp.sendRedirect("index.jsp");

    }
}
