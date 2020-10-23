﻿# ✍ 일정관리 앱✨
 
비트캠프 자바 과정 2차 해커톤에서 진행한 팀 프로젝트입니다. (김하연, 이승혁)

## 목표

- 날짜와 시간에 따라 달라지는 메인 화면 구성
- 할 일과 한 일을 관리할 수 있는 투두 리스트 제공
- 투두 리스트에 기반한 리포트 제공
 - 그래프와 관련 이미지로 시각화
- 오늘자, 이번주차 투두리스트 조회 기능
- 특정 날짜로 일정 검색
- 메세지 기능을 통해 회원과 회원 사이의 소통 활성화


## 주요 명령어와 기능

* Member
 - /member/add    - 새로운 회원가입을 받는다.
 - /member/list   - 가입된 회원 정보를 본다. (Command 클래스에 Member 을 받으며 로그인을 하지 않으면 사용 할수 없다.)
 - /member/detail - 아이디 정보를 입력하여 회원정보를 간략히 출력해준다.
 - /member/update - 아이디 입력으로 회원정보를 수정할 수 있다.
 - /member/delete - 회원정보를 삭제할 수 있다.
 
* Todo
 - /todo/add      - 스케줄을 추가할수 있다.
 - /todo/delete   - 이미 등록된 스케줄을 삭제할 수 있다.
 - /todo/detail   - 등록된 스케줄의 상세정보를 확인할 수 있다.
 - /todo/list     - 스케줄 정보를 현재 날짜에 맞추어 출력해 준다.
 - /todo/update   - 등록된 할 일 정보를 수정할 수 있다.
 - /todo/check    - 해야할 일의 완료,비완료 상태를 체크할 수 있다.
 - /todo/uncheck  - 해야할 일의 완료,비완료 상태를 체크할 수 있다.
 
 * Message
 - /message/send  - 입력한 회원에게 메시지를 전송할 수 있다.
 - /message/list  - 현재 회원정보를 바탕으로 전송받은 메시지 리스트를 출력해준다.
 - /message/delete - 제목 검색을 통해 전송받은 메시지를 삭제할 수 있다.
 - /message/detail - 제목 검색을 통해 메시지를 스크린에 표시해준다.
 
 * indenpendent Command
 - /              - 현재시간, 상태, 오늘의 할일을 출력한다.
 - /login         - 등록된 회원정보를 바탕으로 로그인 할수 있다.
 - /status        - 간단한 상태메시지를 등록하고 main화면에 보여준다.
 - /report        - 주간 완료한 일을 바탕으로, 많이한 일, 부족한 일 의 상태를 출력한다.
 - /hello         - 로그인 정보를 바탕으로 간단한 메시지를 출력한다.
 

## 화면

**메인 화면**


- 현재 시스템의 날짜와 시간을 출력합니다.
- 현재 시스템의 시간 정보에 맞게 아스키 아트를 출력합니다.
- 현재 상태메시지를 등록하고 상태 메시지를 출력합니다.
- "`로그인 한 멤버명`님, 총 `완료하지 않은 Todo 개수` 개의 할 일이 남았습니다." 메시지를 출력한다.
- 시스템 날짜에 따른 할 일 정보 목록을 출력한다.
 - 시간 순으로 정렬한다.
 - 할 일 정보: 완료여부(● : 완료, ○ : 미완료), 항목, 제목

*예시 화면*

![image](https://user-images.githubusercontent.com/50407047/96745433-18e33b80-1401-11eb-8556-57512df9b50f.png)

![image](https://user-images.githubusercontent.com/50407047/96745570-3ca68180-1401-11eb-96f3-e4f8d01838f5.png)


**일주일 통계 화면**
- 일주일 간 완료한 Todo 항목의 비율을 계산해 그래프로 출력합니다.
- 일주일 간 많이 한 항목에 해당하는 아스키 아트를 출력합니다.

*예시 화면*

![image](https://user-images.githubusercontent.com/50407047/96745057-aa05e280-1400-11eb-8448-83fec3f4f148.png)

![image](https://user-images.githubusercontent.com/50407047/96745158-c99d0b00-1400-11eb-9314-ddd14f6392ac.png)

**메시지 출력 화면**
- 아이디를 정하여 해당 아이디를 가진 사용자에게 메시지를 보낼 수 있습니다.
- 발신자와 제목을 아스키 아트와 함께 출력합니다.
- 커맨드 입력을 통해 메시지의 전송 받은시간,내용 등을 출력합니다.

![image](https://user-images.githubusercontent.com/68311187/96948918-aa49cf00-1521-11eb-8e15-cf5eaab6f65b.png)

![image](https://user-images.githubusercontent.com/68311187/96949020-ec731080-1521-11eb-9c29-083391de54a4.png)

## 어려웠던 점

### 로그인, 로그아웃 기능

stateful과 달리 stateles 프로그램은 작업이 끝나면 바로 연결이 끊어지기 때문에 클라이언트가 연결을 할 때마다 유저임을 식별할 수 있는 아이디를 요청 메시지와 함께 보내도록 정의하였다.

![image](https://user-images.githubusercontent.com/50407047/96950721-e717c500-1525-11eb-9bf7-26b863f0fb7a.png)


**ClientApp.request()**
```java
  private static void request(String message) {
    // 클라이언트가 서버에 stop 명령을 보내면 다음 변수를 true로 변경한다.
    boolean stop = false;

    try (Socket socket = new Socket(host, port);
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

      // 메시지 이전 줄에 아이디를 보낸다.
      // clientId.equals("$%$)가 true면 로그아웃된 상태
      // false면 로그인된 상태
      out.println(clientId  + "," + message);
      out.flush();

      receiveResponse(out, in);

      if (message.equalsIgnoreCase("stop")) {
        stop = true;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (stop) {
      // 서버를 멈추기 위해 그냥 접속했다가 끊는다.
      try (Socket socket = new Socket(host, port)) {
        // 아무것도 안한다.
        // 서버가 stop 할 기회를 주기 위함이다.
      } catch (Exception e) {
        // 아무것도 안한다.
      }
    }
  }
```

**ClientApp.receiveResponse()**
```java
  private static void receiveResponse(PrintWriter out, BufferedReader in) throws Exception {
    while (true) {
      String response = in.readLine();
      if (response.length() == 0) {
        break;
      } else if (response.length() > 3 && response.substring(0, 3).equals("$%$")) { // 
        clientId = response.substring(3, response.length());
        } 
      else if (response.equals("!{}!")) {
        // 사용자로부터 값을 입력을 받아서 서버에 보낸다.
        out.println(Prompt.inputString(""));
        out.flush(); // 주의! 출력하면 버퍼에 쌓인다. 서버로 보내고 싶다면 flush()를 호출하라!
      } else {
        System.out.println(response);
      }
    }
  }
```

**ServerApp.handleClient()**

```java
String fullRequest = in.readLine();
String[] requests = fullRequest.split(",");
String clientId = requests[0];
String body = requests[1];
//...

// clientId가 특정 문자열일 경우 로그아웃 상태로 간주한다.
if (clientId.equals("$%$")) {
  //로그아웃 상태에서 할 일
  //...

// 특정 문자열이 아닐 경우 로그인 상태로 간주한다
} else {
  // clientId가 유효한지 검증
  //...
}

// body로 실제 명령 수행 
Command command = (Command) context.get(request);
```

### 로그인한 멤버의 데이터 접근
**ServerApp.handleClient()**

```java
String fullRequest = in.readLine();
String[] requests = fullRequest.split(",");
String clientId = requests[0];
String body = requests[1];
//...

// clientId가 특정 문자열일 경우 로그아웃 상태로 간주한다.
if (clientId.equals("$%$")) {
  //로그아웃 상태에서 할 일
  //...

// 특정 문자열이 아닐 경우 로그인 상태로 간주한다
} else {
  // clientId가 유효한지 검증
  //...
}

// body로 실제 명령 수행 
Command command = (Command) context.get(request);
```


**handler.Command**

커맨드 인터페이스의 `execute()`  메서드를 새로 정의한다. pms 프로젝트에서는 `execute(PrintWriter out, BufferedReader in)`이었는데, 이번 프로젝트에서는 `execute(PrintWriter out, BufferedReader in, Member member)`로 정의하였다.

```java
public interface Command {
  void execute(PrintWriter out, BufferedReader in, Member member);
}
```



그러면 다음과 같이 구현체의 `execute()` 메서드 안에서 로그인한 멤버의 정보로 작업을 수행할 수 있다. 
**handler.MainScreenCommand**
```java
public class MainScreenCommand implements Command {
  //...
  
  @Override
  public void execute(PrintWriter out, BufferedReader in, Member loggedInMember) {
    //..
    // 이렇게 로그인한 멤버에 접근하여 데이터를 사용할 수 있다!
    todoList = member.getTodoList();
    out.printf("%s님, %s \n", member.getName(), message);
    out.printf("%d개의 할 일이 남았습니다.\n", countTodo(todoList, date));
    out.printf("%s개의 안 읽은 메시지가 있습니다.\n", countUnReadMessage(member.getMessageList()));
    //..
  }
}
```

**ServerApp.handleClient()**

실제로 메서드를 사용할 때는 다음과 같이 clientId로 찾은 멤버를 파라미터로 넘겨준다.

```java
//...
if (clientId.equals("$%$")) {
  //로그아웃 상태에서 할 일
  //...

// 특정 문자열이 아닐 경우 로그인 상태로 간주한다
} else {
  // clientId가 유효한지 검증
  //...
}
Command command = (Command) context.get(request);
if (command != null) {
// 클라이언트가 보낸 clientId로 Member롤 찾아 파라미터로 넘겨준다.  
	command.execute(out, in, currentMember);
} else {
	out.println("해당 명령을 처리할 수 없습니다!");
}
```
