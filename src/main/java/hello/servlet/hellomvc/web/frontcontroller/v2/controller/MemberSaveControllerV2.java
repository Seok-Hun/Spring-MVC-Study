package hello.servlet.hellomvc.web.frontcontroller.v2.controller;

import hello.servlet.hellomvc.domain.member.Member;
import hello.servlet.hellomvc.domain.member.MemberRepository;
import hello.servlet.hellomvc.web.frontcontroller.MyView;
import hello.servlet.hellomvc.web.frontcontroller.v2.ControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MemberSaveControllerV2 implements ControllerV2 {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));

        Member member = new Member(username,age);

        memberRepository.save(member);

        req.setAttribute("member",member);

        return new MyView("/WEB-INF/views/save-result.jsp");
    }
}
