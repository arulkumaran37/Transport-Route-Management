package com.wipro.route.dao;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.wipro.route.bean.RouteBean;
import com.wipro.route.util.DBUtil;

public class RouteDAO {

    public String generateRouteID(String routeCode, String routeName) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String seqNum = "";
        
        try {
            conn = DBUtil.getDBConnection();
            pstmt = conn.prepareStatement("SELECT ROUTE_SEQ.NEXTVAL FROM DUAL");
            rs = pstmt.executeQuery();
            if (rs.next()) {
                seqNum = String.valueOf(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if(rs != null) rs.close(); if(pstmt != null) pstmt.close(); if(conn != null) conn.close(); } catch(Exception e){}
        }

        DateFormat f = new SimpleDateFormat("yyyyMMdd");
        String temp = f.format(new Date());
        String codePrefix = routeCode.substring(0, 2).toUpperCase();
        
        return temp + codePrefix + seqNum;
    }

    public boolean recordExists(String routeCode, String routeName) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean exists = false;
        try {
            conn = DBUtil.getDBConnection();
            pstmt = conn.prepareStatement("SELECT 1 FROM ROUTE_TB WHERE ROUTECODE = ? AND ROUTENAME = ?");
            pstmt.setString(1, routeCode);
            pstmt.setString(2, routeName);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                exists = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if(rs != null) rs.close(); if(pstmt != null) pstmt.close(); if(conn != null) conn.close(); } catch(Exception e){}
        }
        return exists;
    }

    public String createRecord(RouteBean bean) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getDBConnection();
            String query = "INSERT INTO ROUTE_TB (ROUTEID, ROUTECODE, ROUTENAME, START_LOC, END_LOC, DISTANCE_KM, REMARKS) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, bean.getRouteId());
            pstmt.setString(2, bean.getRouteCode());
            pstmt.setString(3, bean.getRouteName());
            pstmt.setString(4, bean.getStartLoc());
            pstmt.setString(5, bean.getEndLoc());
            pstmt.setDouble(6, bean.getDistanceKm());
            pstmt.setString(7, bean.getRemarks());
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                return bean.getRouteId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if(pstmt != null) pstmt.close(); if(conn != null) conn.close(); } catch(Exception e){}
        }
        return "FAIL";
    }

    public RouteBean fetchRecord(String routeCode, String routeName) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        RouteBean bean = null;
        try {
            conn = DBUtil.getDBConnection();
            pstmt = conn.prepareStatement("SELECT * FROM ROUTE_TB WHERE ROUTECODE = ? AND ROUTENAME = ?");
            pstmt.setString(1, routeCode);
            pstmt.setString(2, routeName);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                bean = new RouteBean();
                bean.setRouteId(rs.getString("ROUTEID"));
                bean.setRouteCode(rs.getString("ROUTECODE"));
                bean.setRouteName(rs.getString("ROUTENAME"));
                bean.setStartLoc(rs.getString("START_LOC"));
                bean.setEndLoc(rs.getString("END_LOC"));
                bean.setDistanceKm(rs.getDouble("DISTANCE_KM"));
                bean.setRemarks(rs.getString("REMARKS"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if(rs != null) rs.close(); if(pstmt != null) pstmt.close(); if(conn != null) conn.close(); } catch(Exception e){}
        }
        return bean;
    }

    public List<RouteBean> fetchAllRecords() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<RouteBean> list = new ArrayList<>();
        try {
            conn = DBUtil.getDBConnection();
            pstmt = conn.prepareStatement("SELECT * FROM ROUTE_TB");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                RouteBean bean = new RouteBean();
                bean.setRouteId(rs.getString("ROUTEID"));
                bean.setRouteCode(rs.getString("ROUTECODE"));
                bean.setRouteName(rs.getString("ROUTENAME"));
                bean.setStartLoc(rs.getString("START_LOC"));
                bean.setEndLoc(rs.getString("END_LOC"));
                bean.setDistanceKm(rs.getDouble("DISTANCE_KM"));
                bean.setRemarks(rs.getString("REMARKS"));
                list.add(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if(rs != null) rs.close(); if(pstmt != null) pstmt.close(); if(conn != null) conn.close(); } catch(Exception e){}
        }
        return list;
    }
}