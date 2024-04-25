package blog;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArticleTests {

    private static final String TEXT = "Amazing article !!!";
    private static final String AUTHOR = "Pablo Escobar";

    @Test
    void commentsWithUniqueAuthorAndTextCombinationShouldBeAdded() throws CommentAlreadyExistException {
        var article = new Article(
                "Lorem Ipsum",
                "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
        );
        article.addComment("Amazing article !!!", "Pablo Escobar");
        var extraComment = new Comment("Cool stuff", "me", LocalDate.now());
        article.addComment(extraComment.text(), extraComment.author());
        assertThat(article.comments())
                .containsExactly(new Comment(TEXT, AUTHOR, LocalDate.now()), extraComment);
    }

    @Test
    void whenTheCommentHasAlreadyBeenAddedItShouldThrowACommentAlreadyExistsException() throws CommentAlreadyExistException {
        var article = new Article(
                "Lorem Ipsum",
                "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
        );
        article.addComment("Amazing article !!!", "Pablo Escobar");
        assertThatThrownBy(() -> article.addComment("Amazing article !!!", "Pablo Escobar"))
                .isInstanceOf(CommentAlreadyExistException.class);
    }
}
