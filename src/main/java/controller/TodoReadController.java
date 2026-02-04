package controller;

import lombok.extern.log4j.Log4j2;
import dto.TodoDTO;
import service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "todoReadController", value = "/todo/read")
@Log4j2
public class TodoReadController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        try {
//            Long tno = Long.parseLong(req.getParameter("tno"));
//
//            TodoDTO todoDTO = todoService.get(tno);
//
//            //모델 담기
//            req.setAttribute("dto", todoDTO);
//
//            req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp);
//
//        }catch(Exception e){
//            log.error(e.getMessage());
//            throw new ServletException("read error");
//        }
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Long tno = Long.parseLong(req.getParameter("tno"));

            TodoDTO todoDTO = todoService.get(tno);

            //모델 담기
            req.setAttribute("dto", todoDTO);

            //쿠키 찾기  
            Cookie viewTodoCookie = findCookie(req.getCookies(), "viewTodos");
            // 브라우저에서 이미 읽어본 todo 글 번호 목록 쿠키가 없으면 만듦으로서 기록할 공간 확보

            String todoListStr = viewTodoCookie.getValue();
            // 쿠키가 없어서 만든 경우에는 todoListStr에 빈 문자열이 전달됨

            boolean exist = false;

            if(todoListStr != null && todoListStr.indexOf(tno+"-") >= 0){ // -을 붙임으로서 일의 자리만 같을 경우 방지
                exist = true;
            }

            log.info("exist: " + exist);

            if(!exist) {
                todoListStr += tno+"-";
                viewTodoCookie.setValue(todoListStr);
                viewTodoCookie.setMaxAge(60* 60* 24);
                viewTodoCookie.setPath("/");
                resp.addCookie(viewTodoCookie);
            }

//            1. 브라우저가 viewTodos 쿠키를 보냄
//            2. 서버가 그 쿠키 객체를 가져옴
//            3. 쿠키 안의 문자열("3-5-7-")을 꺼냄
//            4. 문자열에서 현재 no가 있는지 검색
//            5. 없으면 문자열 뒤에 no + "-" 추가
//            6. 같은 이름의 쿠키로 값 덮어쓰기
//            7. resp.addCookie()로 브라우저에 저장 지시



            req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp);

        }catch(Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            throw new ServletException("read error");
        }
    }
    private Cookie findCookie(Cookie[] cookies, String cookieName) {

        Cookie targetCookie = null;

        if(cookies != null && cookies.length > 0){
            for (Cookie ck:cookies) {
                if(ck.getName().equals(cookieName)){
                    targetCookie = ck;
                    break;
                }
            }
        }

        if(targetCookie == null){
            targetCookie = new Cookie(cookieName, "");
            targetCookie.setPath("/");
            targetCookie.setMaxAge(60*60*24);
        }

        return targetCookie;
    }


}