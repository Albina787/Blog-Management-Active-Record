import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Article {

    private int id;
    private String title;
    private String content;

    public Article() {}

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // CREATE
    public void save() throws Exception {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO articles(title, content) VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS
        );

        stmt.setString(1, title);
        stmt.setString(2, content);
        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            this.id = rs.getInt(1);
        }

        conn.close();
    }

    // READ
    public static Article findById(int id) throws Exception {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM articles WHERE id = ?"
        );

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        Article article = null;

        if (rs.next()) {
            article = new Article();
            article.id = rs.getInt("id");
            article.title = rs.getString("title");
            article.content = rs.getString("content");
        }

        conn.close();
        return article;
    }

    // UPDATE
    public void update() throws Exception {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "UPDATE articles SET title = ?, content = ? WHERE id = ?"
        );

        stmt.setString(1, title);
        stmt.setString(2, content);
        stmt.setInt(3, id);
        stmt.executeUpdate();

        conn.close();
    }

    // DELETE
    public void delete() throws Exception {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM articles WHERE id = ?"
        );

        stmt.setInt(1, id);
        stmt.executeUpdate();

        conn.close();
    }

    public int getId() {
        return id;
    }
}

