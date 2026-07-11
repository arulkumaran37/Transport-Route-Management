package com.wipro.route.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wipro.route.bean.RouteBean;
import com.wipro.route.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Administrator admin = new Administrator();

    public String addRecord(HttpServletRequest request) {
        RouteBean bean = new RouteBean();
        bean.setRouteCode(request.getParameter("routeCode"));
        bean.setRouteName(request.getParameter("routeName"));
        bean.setStartLoc(request.getParameter("startLoc"));
        bean.setEndLoc(request.getParameter("endLoc"));
        
        String distStr = request.getParameter("distanceKm");
        double distance = 0.0;
        if(distStr != null && !distStr.trim().isEmpty()) {
            distance = Double.parseDouble(distStr);
        }
        bean.setDistanceKm(distance);
        bean.setRemarks(request.getParameter("remarks"));
        
        return admin.addRecord(bean);
    }

    public RouteBean viewRecord(HttpServletRequest request) {
        String routeCode = request.getParameter("routeCode");
        String routeName = request.getParameter("routeName");
        return admin.viewRecord(routeCode, routeName);
    }

    public List<RouteBean> viewAllRecords(HttpServletRequest request) {
        return admin.viewAllRecords();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");

        if ("newRecord".equals(operation)) {
            String result = addRecord(request);
            if ("FAIL".equals(result) || "INVALID INPUT".equals(result) || "INVALID ROUTE CODE".equals(result) 
                || "INVALID ROUTE NAME".equals(result) || "INVALID LOCATION".equals(result) || "ALREADY EXISTS".equals(result)) {
                response.sendRedirect("Views/error.html");
            } else {
                response.sendRedirect("Views/success.html");
            }
        } 
        else if ("viewRecord".equals(operation)) {
            RouteBean bean = viewRecord(request);
            if (bean == null) {
                request.setAttribute("message", "No matching records exists! Please try again!");
            } else {
                request.setAttribute("routeBean", bean);
            }
            RequestDispatcher rd = request.getRequestDispatcher("/Views/displayRoute.jsp");
            rd.forward(request, response);
        } 
        else if ("viewAllRecords".equals(operation)) {
            List<RouteBean> list = viewAllRecords(request);
            if (list == null || list.isEmpty()) {
                request.setAttribute("message", "No records available!");
            } else {
                request.setAttribute("routeList", list);
            }
            RequestDispatcher rd = request.getRequestDispatcher("/Views/displayAllRoutes.jsp");
            rd.forward(request, response);
        }
    }
}