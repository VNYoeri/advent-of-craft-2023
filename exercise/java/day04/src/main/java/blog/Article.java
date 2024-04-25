package blog;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record Article(String name,
                      String content,
                      List<Comment> comments) {

    public Article(String name, String content) {
        this(name, content, new ArrayList<>());
    }

    public void addComment(String text, String author) throws CommentAlreadyExistException {
        addComment(text, author, LocalDate.now());
    }

    private void addComment(String text, String author, LocalDate creationDate) throws CommentAlreadyExistException {
        var comment = new Comment(text, author, creationDate);

        if (comments.contains(comment)) {
            throw new CommentAlreadyExistException();
        }

        comments.add(comment);
    }
}

