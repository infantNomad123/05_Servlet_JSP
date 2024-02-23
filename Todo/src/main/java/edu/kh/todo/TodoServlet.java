package edu.kh.todo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/todo-search")
public class TodoServlet extends HttpServlet   {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		List<String> list = new ArrayList<String>();
		String path = "/WEB-INF/views/todo/todo_result.jsp";
		
//		POST 방식
//		URL에 변수(데이터)를 노출하지 않고 요청 데이터를 HTTP Body에 포함하여 전
//		송
//
//		전송하는 길이 제한이 없음.
//		Body에 데이터가 들어가기 때문에 길이에 제한이 없지만 최대 요청을 받는 시간
//		(Time Out)이
//		존재해서 페이지 요청, 기다리는 시간 존재
//
//		캐싱할 수 없음.
//		(URL에 데이터가 노출 되지 않으므로 즐겨찾기나 캐싱 불가능)
		
		try {
			fis = new FileInputStream("io_test/TodoList.dat");
			ois = new ObjectInputStream(fis);
			String content = (String) ois.readObject();
			list.add(content);
			req.getRequestDispatcher(path).forward(req, resp);
			req.setAttribute("list", list);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
					if(ois !=null) ois.close();
			}catch(IOException e){
				e.printStackTrace();
//				GET 방식
//			
//				URL(주소)에 데이터를 포함 시켜 요청하는 방식
//				→ 비밀번호 같은 데이터도 주소에 모두 보이기 때문에 로그인 같은 기능은 GET
//				방식 부적합
//				작성 가능한 URL(주소)의 길이는 브라우저에 따라 다름 (제한 있음)
//				(https://zetawiki.com/wiki/URL의_최대_길이)
//				캐싱 가능 (ex. 즐겨 찾기, 북마크)
//				(한번 접근 후, 또 요청할 시 빠르게 접근하기 위해 데이터를 저장 시켜 놓는 것)
			}
		}
	}
}

