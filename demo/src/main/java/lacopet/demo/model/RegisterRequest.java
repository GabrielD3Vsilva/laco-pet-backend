package lacopet.demo.model;

public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String confirmPassword;
    private String image;
    private String description;
    private Number indexToArray;

    public Number getIndexToArray() {
        return indexToArray;
    }

    public void setIndexToArray(Number indexToArray) {
        this.indexToArray = indexToArray;
    }




    public String getImage() {
        return image;
    }

    public String getDescription(){
        return description;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    // Getters e setters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setUsername(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
