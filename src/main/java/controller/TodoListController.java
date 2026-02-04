
package controller;

import lombok.extern.log4j.Log4j2;
import dto.TodoDTO;
import service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "todoListController", value = "/todo/list")
@Log4j2
public class TodoListController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("todo list..................");

        try {
            List<TodoDTO> dtoList = todoService.listAll();
            log.info("가져온 데이터 개수: " + dtoList.size());
            req.setAttribute("dtoList", dtoList);
            req.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(req,resp);
            
        } catch (Exception e) {
            log.error("Todo list error", e);  // ← 이게 핵심
            throw new ServletException("list error", e);
        }
    }
}