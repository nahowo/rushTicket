package com.nahowo.rushTicket.config.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {
    USER_CREATE("UR001", "회원 생성 성공"),
    USER_LOGIN("UR002", "회원 로그인 성공"),
    VENUES_VIEW("VR001", "공연장 목록 조회 성공"),
    VENUE_VIEW("VR002", "공연장 조회 성공"),
    EVENT_CREATE("ER001", "이벤트 등록 성공"),
    EVENT_UPDATE("ER002", "이벤트 수정 성공"),
    EVENT_DELETE("ER003", "이벤트 삭제 성공"),
    EVENTS_VIEW("ER004", "이벤트 목록 조회 성공"),
    EVENT_VIEW("ER005", "이벤트 조회 성공"),
    SEAT_STATUS_VIEW("SR001", "공연 좌석 상태 조회 성공"),
    SEAT_BOOK("SR002", "공연 좌석 예매 성공"),
    SEAT_CANCEL("SR003", "공연 좌석 취소 성공"),
    SEAT_USED("SR004", "공연 티켓 사용 성공");

    private final String code;
    private final String message;
}
