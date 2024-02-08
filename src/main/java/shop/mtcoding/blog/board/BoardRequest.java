package shop.mtcoding.blog.board;

import lombok.Data;

public class BoardRequest {
    @Data
    public static class saveDTO {
        String author;
        String title;
        String content;
    }

    @Data
    public static class updateDTO {
        String author;
        String title;
        String content;
    }
}
