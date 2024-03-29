package com.blockpage.webtoonservice.adaptor.web.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import javax.swing.Painter;
import lombok.Getter;
import lombok.ToString;

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

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Meta {

        private final Sort sort;
        private final Pagination pagination;

        public static Meta DEFAULT_META = new Meta("DESC", Pagination.DEFAULT_PAGING);

        public Meta(String sort, Pagination pagination) {
            this.sort = Sort.valueOf(sort);
            this.pagination = pagination;
        }

        public enum Sort {
            DESC,
            ASC,
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

}
