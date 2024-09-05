package lacopet.demo.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class User {

    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private List<Object> posts; // Alteração do tipo para List<Object>
    private List<Object> aprovedPosts;
    // Construtor sem parâmetros
    public User() {
        this.posts = new ArrayList<>();
        this.aprovedPosts = new ArrayList<>();
    }

    // Construtor com parâmetros
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.posts = new ArrayList<>();
        this.aprovedPosts = new ArrayList<>();
    }

    // Getters e setters
    public List<Object> getPosts() {
        return posts;
    }
    public List<Object> getAprovedPosts() {
        return aprovedPosts;
    }

    public void setAprovedPosts(List<Object> aprovedPosts) {
        this.aprovedPosts = aprovedPosts;
    }

    public void setPosts(List<Object> posts) {
        this.posts = posts;
    }

    public void addPost(Object post) {
        this.posts.add(post); // Método para adicionar um objeto à lista
    }

    public void addAprovedPost(Object aprovedPost) {
        this.aprovedPosts.add(aprovedPost); // Método para adicionar um objeto à lista
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Restante dos getters e setters...
}
