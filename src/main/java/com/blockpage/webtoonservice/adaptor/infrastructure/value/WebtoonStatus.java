package com.blockpage.webtoonservice.adaptor.infrastructure.value;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum WebtoonStatus {
    PUBLISH(0, "배포중"),
    REGISTRATION_WAITING(1, "등록 요청"),
    REGISTRATION_REJECTION(2, "등록 거부"),
    MODIFICATION_WAITING(3, "수정 요청"),
    MODIFICATION_REJECTING(4, "수정 거부"),
    REMOVE_WAITING(5, "삭제 요청"),
    REMOVE(6, "삭제됨"),
    ;

    Integer key;
    String value;

    public static WebtoonStatus findWebtoonStatusByKey(Integer key) {
        return Arrays.stream(WebtoonStatus.values())
            .filter(k -> k.getKey() == key)
            .findFirst()
            .get();
    }

    public static WebtoonStatus findStatusByValue(String value){
        return Arrays.stream(WebtoonStatus.values())
            .filter(k -> k.getValue().equals(value))
            .findFirst()
            .get();
    }
}
