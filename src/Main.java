public class Main {
    public static void main(String[] args) throws Exception {

        // CREATE
        Article article = new Article("My First Article", "Active Record pattern example.");
        article.save();

        // READ
        Article found = Article.findById(article.getId());

        // UPDATE
        found.update();

        // CREATE COMMENT
        Comment comment = new Comment(article.getId(), "Great article!");
        comment.save();

        // DELETE
        // found.delete();
    }
}

