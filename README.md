# SGRAM_Android

**Socket.IO** 와 **WebSocket** 중 🍻  **WebSokcet** 사용

# ANDROID DESCRIPTION ✨

- 회원가입 ✅
- 로그인 ✅
- 소켓 통신 접속 ✅
    - ISSUE
    소켓 연결을 시도하던 도중, **소켓 접속**과 **소켓 요청**을 동시 이슈로 연결 실패 😭
    **접속과 요청 분리 후**, String  값으로 메세지 전송했지만 **NULL값**으로 전송
    WHY❔
    버튼을 클릭하면 일정 시간 이후 값이 EditText에서 **값이 사라지게** 되는데 값이 사라진 후 값을 저장 => 🤼 전역 변수로 선언한 뒤, 버튼을 클릭할 시에 바로 값을 저장

