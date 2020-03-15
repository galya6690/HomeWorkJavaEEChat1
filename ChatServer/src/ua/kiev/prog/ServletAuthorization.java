package ua.kiev.prog;

import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ServletAuthorization extends HttpServlet {

   private HashMap<String, String> hashMap =new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String yesornoStr = req.getParameter("yesorno");
        int n = Integer.parseInt(yesornoStr);


        if (n == 1) {
            HttpSession session = req.getSession();
            hashMap.put(req.getParameter("login"), req.getParameter("password"));
            resp.setStatus(200);
        }
        if (n == 2) {
                if ((hashMap.containsKey(req.getParameter("login")) && hashMap.get(req.getParameter("login")).equals((req.getParameter("password"))))) {
                    resp.setStatus(200);
                }
                else{
                    resp.setStatus(300);
                }
            }
        }

    }