package hello.servlet.hellomvc.web.frontcontroller.v1.controller;

import hello.servlet.hellomvc.domain.member.Member;
import hello.servlet.hellomvc.domain.member.MemberRepository;
import hello.servlet.hellomvc.web.frontcontroller.v1.ControllerV1;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MemberSaveControllerV1 implements ControllerV1 {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));

        Member member = new Member(username,age);

        memberRepository.save(member);

        req.setAttribute("member",member);
        // Model에 데이터 보관. view에서 member라는 이름으로 사용 가능
        // Model로 request의 내부 저장소 사용

        String viewPath = "/WEB-INF/views/save-result.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        dispatcher.forward(req,resp);
    }
}
