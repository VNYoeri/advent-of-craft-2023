package blog;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArticleTests {

    private static final String TEXT = "Amazing article !!!";
    private static final String AUTHOR = "Pablo Escobar";
    private static final Comment COMMENT = new Comment(AUTHOR, TEXT, LocalDate.now());
    private final Article article = new Article(
            "Lorem Ipsum",
            "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
    );

    @BeforeEach
    public void setUp() {
        this.article.comments().clear();
        this.article.comments().add(COMMENT);
    }

    @Test
    void commentsWithUniqueAuthorAndTextCombinationShouldBeAdded() throws CommentAlreadyExistException {
        var extraComment = new Comment("Cool stuff", "me", LocalDate.now());
        article.addComment(extraComment);

        assertThat(article.comments())
                .containsExactly(COMMENT, extraComment);
    }

    @Test
    void whenTheCommentHasAlreadyBeenAddedItShouldThrowACommentAlreadyExistsException() {
        assertThatThrownBy(() -> this.article.addComment(COMMENT))
                .isInstanceOf(CommentAlreadyExistException.class);
    }
}
