package com.uragiljay.spring051701.Dao;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.uragiljay.spring051701.Dto.BDto;



public class BDao {

	
	DataSource dataSource;

	public BDao() {
		super();
		// TODO Auto-generated constructor stub
		
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			e.printStackTrace();
			
		}
		
		
	}
	
		
	public ArrayList<BDto> list() {
		
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			conn = dataSource.getConnection();
			String query = "select * from mvc_board order by bgroup desc, bstep asc";
			pstmt = conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				
				int bId = rs.getInt("bid");
				String bName = rs.getString("bname");
				String bTitle = rs.getString("btitle");
				String bContent = rs.getString("bcontent");
				Timestamp bDate = rs.getTimestamp("bdate");
				int bHit = rs.getInt("bhit");
				int bGroup = rs.getInt("bgroup");
				int bStep = rs.getInt("bstep");
				int bIndent = rs.getInt("indent");
				
				 BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				dtos.add(dto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
					if(rs != null) {
						rs.close();
						
					}
					if(pstmt != null) {
						pstmt.close();
						
					}
					if(conn != null) {
						conn.close();
						
					}
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
		return dtos;
	}
	
}
