package servlets;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Helper {
    public static int getProjectIdFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("projectId")) {
                return Integer.parseInt(cookie.getValue());
            }
        }
        return -1;
    }

    public static int getUserIdFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userId")) {
                return Integer.parseInt(cookie.getValue());
            }
        }
        return -1;
    }
}
