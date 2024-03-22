<%@ page import="hello.servlet.hellomvc.domain.member.Member" %>
<%@ page import="hello.servlet.hellomvc.domain.member.MemberRepository" %><%--
  Created by IntelliJ IDEA.
  User: hamer
  Date: 2024-03-06
  Time: 오후 2:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();

    System.out.println("MemberSaveServlet.service");
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));
    // request, response 사용 가능(req, resp는 불가)
    // JSP도 서블릿으로 변환되어 사용된다. 따라서 HttpServlet의 기능을 그대로 사용할 수 있다.

    Member member = new Member(username,age);

    memberRepository.save(member);
%>

<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
