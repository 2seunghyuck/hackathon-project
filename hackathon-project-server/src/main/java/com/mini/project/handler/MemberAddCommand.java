package com.mini.project.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.mini.project.domain.Member;
import com.mini.util.Prompt;

public class MemberAddCommand implements Command {

  List<Member> memberList;

  public MemberAddCommand(List<Member> list) {
    this.memberList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in, Member loggedInMember) {
    try {
      out.println("[회원가입]");

      Member member = new Member();
      member.setName(Prompt.inputString("이름? ", out, in));
      member.setId(Prompt.inputString("아이디?", out, in));
      member.setPassword(Prompt.inputString("암호? ", out, in));

      memberList.add(member);

    } catch (Exception e) {
      out.printf("작업 처리 중 오류 발생! - %s\n", e.getMessage());
    }
  }

}
