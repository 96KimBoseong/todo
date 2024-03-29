# 주특기 플러스 복습과제


---
회원가입
---
- [x] 닉네임 , 비밀번호 ,비밀번호 확인 request에서 전달 받기
- [x] 닉네임은 최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 구성하기
- [x] 비밀번호는 최소 4자 이상이며, 닉네임과 같은 값이 포함된 경우 회원가입에 실패로 만들기
- [x] 비밀번호 확인은 비밀번호와 정확하게 일치하기 
- [x] 데이터베이스에 존재하는 닉네임을 입력한 채 회원가입 버튼을 누른 경우 "중복된 닉네임입니다." 라는 에러메세지를 response에 포함하기

---
로그인
---
- [x] 닉네임, 비밀번호를 request에서 전달받기
- [x] 로그인 버튼을 누른 경우 닉네임과 비밀번호가 데이터베이스에 등록됐는지 확인한 뒤, 하나라도 맞지 않는 정보가 있다면 "닉네임 또는 패스워드를 확인해주세요."라는 에러 메세지를 response에 포함하기
- [ ] 로그인 성공 시, 로그인에 성공한 유저의 정보를 JWT를 활용하여 클라이언트에게 Cookie로 전달하기

---
게시물 리스트 조회
---

- [x] 제목, 작성자명(nickname), 작성 날짜를 조회하기
- [x] 작성 날짜 기준으로 내림차순 정렬하기

---
게시글 작성
---

- [x] 토큰을 검사하여, 유효한 토큰일 경우에만 게시글 작성 가능 
- [x] 제목(500자 까지 입력 가능), 작성 내용을 입력하기(5000자 까지 입력 가능)

---
게시글 조회
---

- [x] 제목, 작성자명(nickname), 작성 날짜, 작성 내용을 조회하기
      
---
게시글 수정
---

- [x] 토큰을 검사하여, 해당 사용자가 작성한 게시글만 수정 가능 
      
---
게시글 삭제
---

 - [x] 토큰을 검사하여, 해당 사용자가 작성한 게시글만 삭제 가능

---
댓글 작성
---

- [x] 게시글과 연관 관계를 가진 댓글 테이블 추가
- [x] 토큰을 검사하여, 유효한 토큰일 경우에만 게시글 작성 가능
- [x] 작성 내용을 입력하기
- [ ] 게시글에 대한 좋아요 

---
게시글과 댓글 목록 조회 API, 댓글 수정/ 삭제API
---
- [x] 게시글 조회 API 호출시 해당 게시글의 댓글 목록도 응답
- [x] 토큰을 검사하여, 해당 사용자가 작성한 댓글만 수정/삭제 가능

---
개선과제
---
- [x] Controller test code 작성해보기
- [ ] AWS EC2 배포하기
---



