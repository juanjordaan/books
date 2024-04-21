package cloud.jordaan.juan.books.domain;

public class Paging {
    Long limit;
    Long offset;
    boolean enabled;

    public Paging(Long page, Long pageCount) {
        if (page == null || pageCount == null) {
            enabled = false;
        } else {
            enabled = true;
            limit = pageCount;
            offset = (page - 1) * pageCount;
        }
    }

    public Long limit() {
        return limit;
    }

    public Long offset() {
        return offset;
    }

    public boolean enabled() {
        return enabled;
    }
}