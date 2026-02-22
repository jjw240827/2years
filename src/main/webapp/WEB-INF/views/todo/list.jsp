<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <title>Todo List</title>
</head>
<body>

<div class="container-fluid"> <!-- 전체크기로 꽉 채운다. 가로,세로 100% -->
    <div class="row">
        <div class="row">
            <div class="col">
                <nav class="navbar navbar-expand-lg navbar-light bg-light"> <!-- 화면이 Lg, 즉 일정 크기 이상이면
                메뉴를 펼치고 이하면 버튼을 추가한다. -->
                    <div class="container-fluid">
                        <a class="navbar-brand" href="#">Navbar</a>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span> <!-- 햄버거 모양의 버튼, 반응형 navbar사용.
                            collapse기능 사용. 클릭하면 특정 영역을 접거나 펼친다. navbarNavAltMarkup 어떤 요소를 접을 것인지  id가 navbarNavAltMarkup인 요소를 제어  false는 현재 접혀있다는 것. 열리면 true로 자동 변경
                            aria-label="Toggle navigation"은 시간 장애인 용 설명 <span class="navbar-toggler-icon"></span> 햄버거 아이콘 생성
                            -->
                        </button>
                        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                            <div class="navbar-nav">
                                <a class="nav-link active" aria-current="page" href="#">Home</a>
                                <a class="nav-link" href="/todo/register">Register</a>
                                <a class="nav-link" href="/todo/list">List</a>
                            </div>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
        <div class="row content">
            <div class="col">
                <div class="card">
                    <div class="card-header">
                        Featured
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">Special title treatment</h5>
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">Tno</th>
                                <th scope="col">Title</th>
                                <th scope="col">Writer</th>
                                <th scope="col">DueDate</th>
                                <th scope="col">Finished</th>
                            </tr>
                            </thead>
                            <tbody>
                             <c:forEach items="${responseDTO.dtoList}" var="dto">
                                <tr>
                                    <th scope="row"><c:out value="${dto.tno}"/></th>
                                    <td>
                                        <a href="/todo/read?tno=${dto.tno}&page=${responseDTO.page}&size=${responseDTO.size}"
   class="text-decoration-none">
                                            <c:out value="${dto.title}"/>
                                        </a>
                                    </td>
                                    <td><c:out value="${dto.writer}"/></td>
                                    <td><c:out value="${dto.dueDate}"/></td>
                                    <td><c:out value="${dto.finished}"/></td>
                                </tr>
                            </c:forEach>
                            <%-- dtoList를 순회하며 데이터를 출력합니다 --%>
                           
                            </tbody>
                        </table>
                    </div>
                    </div>
            </div>
        </div>

    </div>
    
    <div class="row content">
        <h1>Content</h1>
    </div>

    <div class="row footer">
        <div class="row fixed-bottom" style="z-index: -100">
            <footer class="py-1 my-1">
                <p class="text-center text-muted">Footer</p>
            </footer>
        </div>
    </div>
</div>

 <div class="float-end">
                            <ul class="pagination flex-wrap">
                                <c:if test="${responseDTO.prev}">
                                    <li class="page-item">
                                        <a class="page-link" data-num="${responseDTO.start -1}">Previous</a>
                                    </li>
                                </c:if>

                                <c:forEach begin="${responseDTO.start}" end="${responseDTO.end}" var="num">
                                    <li class="page-item ${responseDTO.page == num? "active":""} ">
                                        <a class="page-link"  data-num="${num}">${num}</a></li>
                                </c:forEach>

                                <c:if test="${responseDTO.next}">
                                    <li class="page-item">
                                        <a class="page-link"  data-num="${responseDTO.end + 1}">Next</a>
                                    </li>
                                </c:if>
                            </ul>

                        </div>

<script>
document.querySelector(".pagination").addEventListener("click", function (e) {
    e.preventDefault()
    e.stopPropagation()

    const target = e.target


    if(target.tagName !== 'A') {
        return
    }
    const num = target.getAttribute("data-num")

    self.location = `/todo/list?page=\${num}` //백틱(` `)을 이용해서 템플릿 처리
},false)

</script>

</body>
</html>