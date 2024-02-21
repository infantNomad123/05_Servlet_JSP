package edu.kh.servlet2.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex3")
public class ExampleServlet3 extends HttpServlet {
	
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String pizza = req.getParameter("pizza");
	String type = req.getParameter("type");
	String ordererName = req.getParameter("ordererName");
	String ordererAddress = req.getParameter("ordererAddress");
	String[] opt = req.getParameterValues("opt");
	
	System.out.println("[서버]/ex2 요청됨");
	
	int price = 0;
	switch(pizza) {
	case "페페로니" : price += 10000; break;
	case "감자" : price += 11000; break;
	case "불고기" : 
	case "골드쉬림프" : price += 12000; break;
	}
	if(type == "cheeseCrust") price+=3000;
	else  price += 2000;
	
	if(opt != null) {
		for(String o : opt) {
			switch(o) {
			case "페페로니": price += 500; break;
			case "쉬림프": price += 2000; break;
			case "베이컨": price += 3000; break;
			}
		}
	}
	resp.setContentType("text/html; charset = utf-8");
	
	PrintWriter out = resp.getWriter();
	StringBuilder sb = new StringBuilder();
	
	
	
	sb.append("<!DOCTYPE html>");
	sb.append("<html>");
	
	sb.append("<head>");
	sb.append("<title>");
	sb.append(String.format("%s 님 주문 영수중", ordererName));
	sb.append("</title>");
	sb.append("</head>");
	
	
	sb.append("<body>");
	sb.append("<h3>");
	sb.append(ordererName);
	sb.append("</h3>");
	
	sb.append(String.format("<h3>주소 : %s </h3>", ordererAddress));
	
	sb.append("<ul>");
	sb.append(String.format("<li>피자 : %s </li>", pizza));
	
	String temp = type.equals("cheeseCrust") ? "치즈크러스트" : "Thick 크러스트";
  sb.append(String.format("<li>크러스트 : %s </li>", temp));
  
  if(opt != null) {//선택한 옵션이 있을 경우
  	
  	sb.append("<li>");
  	sb.append("선택한 옵션 : " );
  	
  	for(String o : opt) sb.append(o+ " ");
  	sb.append("</li>");
  	
  }
	sb.append("</ul>");
	
	sb.append(String.format("<h3>금액 : %d원</h3>", price));
	
	sb.append("</body>");
	sb.append("</html>");
	
	out.print(sb.toString());
	
	
}
}
