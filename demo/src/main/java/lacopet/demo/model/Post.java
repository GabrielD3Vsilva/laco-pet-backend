package lacopet.demo.model;

public class Post {
    private String image;
    private String description;

    // Construtor
    public Post(String image, String description) {
        this.image = image;
        this.description = description;
    }

    // Métodos de acesso (getters e setters)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Método para representar o objeto como String (opcional)
    @Override
    public String toString() {
        return "Person{" +
                "name='" + image + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
