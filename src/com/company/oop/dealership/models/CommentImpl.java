package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Comment;
import com.company.oop.dealership.utils.ValidationHelpers;

import static java.lang.String.format;

public class CommentImpl implements Comment {

    public static final int CONTENT_LEN_MIN = 3;
    public static final int CONTENT_LEN_MAX = 200;
    private static final String CONTENT_LEN_ERR = format(
            "Content must be between %d and %d characters long!",
            CONTENT_LEN_MIN,
            CONTENT_LEN_MAX);

    private String content;
    private String author;


    public CommentImpl(String content, String author) {
        setContent(content);
        setAuthor(author);
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    private void setContent(String content) {
        ValidationHelpers.validateIntRange(content.length(), CONTENT_LEN_MIN, CONTENT_LEN_MAX, CONTENT_LEN_ERR);
        this.content = content;
    }

    private void setAuthor(String author) {
        this.author = author;
    }

    public String print() {
        return String.format(
                "%s\n" +
                        "%s\n" +
                        "User: %s\n%s\n", "----------", getContent(), getAuthor(), "----------");
    }
}
