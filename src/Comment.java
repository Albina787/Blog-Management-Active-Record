
import java.sql.*;

public class Comment {

    private int id;
    private int articleId;
    private String text;

    public Comment(int articleId, String text) {
        this.articleId = articleId;
        this.text = text;
    }

    public void save() throws Exception {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO comments(article_id, text) VALUES (?, ?)"
        );

        stmt.setInt(1, articleId);
        stmt.setString(2, text);
        stmt.executeUpdate();

        conn.close();
    }

    public void delete() throws Exception {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM comments WHERE id = ?"
        );

        stmt.setInt(1, id);
        stmt.executeUpdate();

        conn.close();
    }
}
