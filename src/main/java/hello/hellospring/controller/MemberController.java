package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class MemberController {
    private final MemberService memberService; //다양한 서비스에서 멤버서비스를 쓸수있는데 일일히 new로 선언하는것보다 컨테이너에 등록하는게 좋다

    @Autowired //멤버컨트롤러를 스프링컨테이너가 뜰때 생성하는데 그때 생성자를 호출한다, Autowired를 사용하면 자동으로 멤버서비스에 가져다가 연결시켜줌
    public MemberController(MemberService memberService) {  //생성자를 통해 memberService를 MemberController에 주입
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")  //데이터를 form같은데 넣어서 등록할떄 POST 사용 get은 대부분 조회할떄 사용
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}

