package com.mini.project.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.mini.project.domain.Member;
import com.mini.util.Prompt;

public class MemberDeleteCommand implements Command {

  List<Member> memberList;

  public MemberDeleteCommand(List<Member> list) {
    this.memberList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in, Member loggedInMember) {
    try {
      out.println("[회원 삭제]");
      String id = Prompt.inputString("아이디? ", out, in);
      int index = indexOf(id);

      if (index == -1) {
        out.println("해당 이름의 회원이 없습니다.");
        return;
      }

      String response = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ", out, in);
      if (!response.equalsIgnoreCase("y")) {
        out.println("회원 삭제를 취소하였습니다.");
        return;
      }

      memberList.remove(index);
      out.println("회원을 삭제하였습니다.");

    } catch (Exception e) {
      out.printf("작업 처리 중 오류 발생! - %s\n", e.getMessage());
    }
  }
  
  private int indexOf(String id) {
    for (int i = 0; i < memberList.size(); i++) {
      Member member = memberList.get(i);
      if (member.getId().equals(id)) {
        return i;
      }
    }
    return -1;
  }
}
