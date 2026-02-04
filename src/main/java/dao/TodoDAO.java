package dao;

import lombok.Cleanup;
import domain.TodoVO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {


    public String getTime(){

        String now = null; // query 실행한 값을 담는 객체

        try(Connection connection = ConnectionUtil.INSTANCE.getConnection();
        	// Connection 하나를 dbcp에서 가져온다. 이때 매서드호출 앞에 INSTANCE를 쓰는 이유는 해당 객체가 싱클톤이여서이다. 문법상
        	// 싱글톤을 사용 시에 붙여야 한다.
            PreparedStatement preparedStatement = connection.prepareStatement("select now()");
        	// PrepareStatement는 sql문을 미리 보내어서 컴파일 시간을 줄일 수 있게 한다. select now()는 현재 시간 가져오기
            ResultSet resultSet = preparedStatement.executeQuery();
        	// preparedStatement.executeQuery를 실행하면 DB는 결과 데이터를 표 형태로 보내준다. 이 표를 자바에서 다룰 수 있게 담아둔
        	// 객체가 바로 ResultSet, ResultSet은 표 전체를 메모리에 올리지 않고 커서라는 화살표로 한줄 씩 읽는다.
        ) {

            resultSet.next(); 

            now = resultSet.getString(1); // 결과표에 컬럼이 하나 뿐이므로 결과를 now에 가져온다.
        }catch(Exception e){
            e.printStackTrace();
        }
        return now;
    }

    public String getTime2() throws Exception {
    	// @cleanup을 붙이면 해당 변수가 참조하는 자원을 자동으로 닫아준다. try-catch-finally블록 생략가능
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement("select now()");
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        String now = resultSet.getString(1);

        return now;
    }
    
    public void insert(TodoVO vo) throws Exception {
    	 String sql = "insert into tbl_todo (title, dueDate, finished) values (?, ?, ?)";

         @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
         @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

         preparedStatement.setString(1, vo.getTitle());
         preparedStatement.setDate(2, Date.valueOf(vo.getDueDate()));
         preparedStatement.setBoolean(3, vo.isFinished());

         preparedStatement.executeUpdate();
    }
    public List<TodoVO> selectAll()throws Exception  {

        String sql = "select * from tbl_todo";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        

        List<TodoVO> list = new ArrayList<>();

        while(resultSet.next()) {
            TodoVO vo = TodoVO.builder()
                    .tno(resultSet.getLong("tno"))
                    .title(resultSet.getString("title"))
                    .dueDate( resultSet.getDate("dueDate").toLocalDate())
                    .finished(resultSet.getBoolean("finished"))
                    .build();

            list.add(vo);
        }

        return list;
    }
    
    public TodoVO selectOne(Long tno)throws Exception {

        String sql = "select * from tbl_todo where tno = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, tno);

        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        TodoVO vo = TodoVO.builder()
                .tno(resultSet.getLong("tno"))
                .title(resultSet.getString("title"))
                .dueDate( resultSet.getDate("dueDate").toLocalDate())
                .finished(resultSet.getBoolean("finished"))
                .build();

        return vo;
    }
    
    public void deleteOne(Long tno) throws Exception {

        String sql = "delete from tbl_todo where tno = ?";

        @Cleanup Connection    connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, tno);

        preparedStatement.executeUpdate();
    }
    
    public void updateOne(TodoVO todoVO)throws Exception{

        String sql = "update tbl_todo set title =?, dueDate = ?, finished = ? where tno =?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, todoVO.getTitle());
        preparedStatement.setDate(2, Date.valueOf(todoVO.getDueDate()));
        preparedStatement.setBoolean(3, todoVO.isFinished());
        preparedStatement.setLong(4, todoVO.getTno());

        preparedStatement.executeUpdate();
    }
}