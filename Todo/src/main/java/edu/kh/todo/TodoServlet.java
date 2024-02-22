package edu.kh.todo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/todo-search")
public class TodoServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		List<String> list = new ArrayList<String>();
		String path = "/WEB-INF/views/todo/todo_result.jsp";
		
		try {
			fis = new FileInputStream("io_test/TodoList.dat");
			ois = new ObjectInputStream(fis);
			String content = (String) ois.readObject();
			list.add(content);
			req.getRequestDispatcher(path).forward(req, resp);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
					if(ois !=null) ois.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}
