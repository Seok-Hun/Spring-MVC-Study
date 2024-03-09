package hello.servlet.hellomvc.web.springmvc.v1;

import hello.servlet.hellomvc.domain.member.Member;
import hello.servlet.hellomvc.domain.member.MemberRepository;
import hello.servlet.hellomvc.web.frontcontroller.ModelView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class SpringMemberListControllerV1 {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v1/members")
    public ModelAndView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();
        ModelAndView modelView = new ModelAndView("members");
        modelView.addObject("members",members);

        return modelView;
    }
}
