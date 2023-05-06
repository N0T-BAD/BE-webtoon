package com.blockpage.webtoonservice.adaptor.web.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.jpa.repository.Meta;

@Getter
@ToString
@JsonInclude(Include.NON_NULL)
public class ApiResponseView<T> {

    private final T data;
    private final Meta meta;

    public ApiResponseView(T data, Meta meta) {
        this.data = data;
        this.meta = meta;
    }

    public ApiResponseView(T data) {
        this.data = data;
        this.meta = null;
    }

    public enum Sort {
        CREATED_AT,
        NONE
    }

    @Getter
    private static class Pagination {

        private final int offset;
        private final int limit;
        private final long totalCount;

        public static Pagination DEFAULT_PAGING = new Pagination(0, 0, 0);

        private Pagination(int offset, int limit, long totalCount) {
            this.offset = offset;
            this.limit = limit;
            this.totalCount = totalCount;
        }
    }
}
