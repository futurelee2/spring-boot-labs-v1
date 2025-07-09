sequenceDiagram
    participant U as 사용자 (회원/비회원)
    participant F as 프론트엔드
    participant B as 백엔드
    participant DB as 데이터베이스

    U->>F: 검색 조건/키워드 입력 후 검색 버튼 클릭
    F->>B: 검색 조건, 키워드, 현재 페이지 전송
    B->>DB: 조건에 맞는 게시글 목록 및 전체 개수 조회
    DB-->>B: 게시글 목록 및 전체 게시글 수 반환
    B-->>F: 게시글 목록 및 전체 페이지 수 전달
    F-->>U: 게시글 목록 및 페이지네이션 렌더링

    loop 페이지 이동
        U->>F: 페이지 번호 클릭
        F->>B: 검색 조건, 키워드, 페이지 번호 전송
        B->>DB: 해당 페이지에 맞는 게시글 조회
        DB-->>B: 게시글 목록 반환
        B-->>F: 게시글 목록 전달
        F-->>U: 게시글 목록 렌더링
    end
