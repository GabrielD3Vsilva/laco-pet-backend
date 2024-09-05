package lacopet.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lacopet.demo.repository.LoginRepository;
import lacopet.demo.model.Post;
import java.util.List;

import lacopet.demo.model.RegisterRequest;
import lacopet.demo.model.User;
import lacopet.demo.service.EmailService;

@RestController
public class PostController {
    @Autowired
    private LoginRepository userRepository;

    @Autowired
    private EmailService emailService;

    @CrossOrigin(origins = "*")
    @PostMapping("/addPost")
    public ResponseEntity<String> addPost(@RequestBody RegisterRequest request) {
        String name = request.getName();
        String image = request.getImage();
        String description = request.getDescription();

        User user = userRepository.findByName(name);
        User admName = userRepository.findByName("Aliny Melquiades");
        Object post = new Post(image, description);
        String email = "alinymelquiadesdesiuza@gmail.com";
        admName.addPost(post);
        user.addPost(post);

        userRepository.save(user);
        userRepository.save(admName);

        emailService.sendEmail(email, "Novo post realizado", "Olá tem uma nova publicação esperando sua aprovação!" );

        return ResponseEntity.ok("Registro efetuado para o usuário: "+ name + image + description);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/returnPosts")
    public ResponseEntity<List<Object>> returnPosts( ) {
        User admName = userRepository.findByName("Aliny Melquiades");


        return ResponseEntity.ok(admName.getPosts());
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/returnAllPosts")
    public ResponseEntity<List<Object>> returnAllPosts( ) {
        User admName = userRepository.findByName("Aliny Melquiades");


        return ResponseEntity.ok(admName.getAprovedPosts());
    }


    @CrossOrigin(origins = "*")
    @PostMapping("/returnPostsToUser") 
    public ResponseEntity<List<Object>> returnPostsToUser(@RequestBody RegisterRequest request) {
        String name = request.getName();
        User user = userRepository.findByName(name);

        return ResponseEntity.ok(user.getPosts());
    }


    @CrossOrigin(origins = "*")
    @PostMapping("/deletePost")
    public ResponseEntity<List<Object>> deletePost(@RequestBody RegisterRequest request) {
        String name = request.getName();
        Number indexToArray = request.getIndexToArray();
        
        User user = userRepository.findByName(name);
        User adm  = userRepository.findByName("Aliny Melquiades");
        
        if (user != null && indexToArray.intValue() < user.getPosts().size()) {
            // Remove o post do array pelo índice
            user.getPosts().remove(indexToArray.intValue());
            
            // Remove o post na posição especificada
            
            // Salva a atualização no banco de dados
            userRepository.save(user);
            
            
            return ResponseEntity.ok(user.getPosts());
        } else {
            // Retorna uma resposta apropriada se o usuário não for encontrado ou o índice for inválido
            return null;
        }
    }



    @CrossOrigin(origins = "*")
    @PostMapping("/editPost")
    public ResponseEntity<List<Object>> editPost(@RequestBody RegisterRequest request) {
        String name = request.getName();
        String description = request.getDescription();
        String image = request.getImage();
        Number indexToArray = request.getIndexToArray();
    
        User user = userRepository.findByName(name);
        User AdmName = userRepository.findByName("Aliny Melquiades");
    
        // Certifique-se de que o usuário e o índice sejam válidos
        if (user != null && indexToArray.intValue() < user.getPosts().size()) {
            Object postObject = user.getPosts().get(indexToArray.intValue());
            List<Object> aprovedPostsList = user.getAprovedPosts(); // Usar nome diferente
    
            if (postObject instanceof Post) {
                Post postToUpdate = (Post) postObject;
    
                // Atualiza a descrição e a imagem do post original
                postToUpdate.setDescription(description);
                postToUpdate.setImage(image);
    
                // Acesse a descrição do post atualizado
                String updatedDescription = postToUpdate.getDescription();
    
                // Procura por um objeto em aprovedPostsList com a mesma descrição
                for (Object approvedPost : aprovedPostsList) {
                    if (((Post) approvedPost).getDescription().equals(updatedDescription)) {
                        // Atualiza a descrição e a imagem do post aprovado
                        Post approvedPostToUpdate = (Post) approvedPost;
                        approvedPostToUpdate.setDescription(description); // Atualiza a descrição
                        approvedPostToUpdate.setImage(image); // Atualiza a imagem
    
                        // Aqui você pode fazer algo com o approvedPost, como logar ou retornar uma informação
                    }
                }
    
                // Salva a atualização no banco de dados
                userRepository.save(user);
    
                // Retorna a lista de posts do usuário
                return ResponseEntity.ok(user.getPosts());
            } else {
                // Trate o caso em que o objeto não é do tipo Post
                return null;
            }
        } else {
            // Retorna uma resposta apropriada se o usuário não for encontrado ou o índice for inválido
            return null;
        }
    }
    
    


    @CrossOrigin(origins = "*")
    @PostMapping("/aprovePost")
    public ResponseEntity<List<Object>> aprovePost(@RequestBody RegisterRequest request) {
        
        Number indexToArray = request.getIndexToArray();
        
        
        User admName = userRepository.findByName("Aliny Melquiades");

        if (admName != null && indexToArray.intValue() < admName.getPosts().size()) {
            Object postObject = admName.getPosts().get(indexToArray.intValue());
            
        if (postObject instanceof Post) {
            Post postToUpdate = (Post) postObject;
            String description = postToUpdate.getDescription();
            String image = postToUpdate.getImage();
            // Salva a atualização no banco de dados
            Object aprovedPost = new Post(image, description);
            admName.getPosts().remove(indexToArray.intValue());
            admName.addAprovedPost(aprovedPost);
            
            userRepository.save(admName);

            return ResponseEntity.ok(admName.getAprovedPosts());
        } else {
            // Trate o caso em que o objeto não é do tipo Post
            return null;
        }
        } else {
        // Retorna uma resposta apropriada se o usuário não for encontrado ou o índice for inválido
            return null;
        }
    }



    @CrossOrigin(origins = "*")
    @PostMapping("/reprovePost")
    public ResponseEntity<List<Object>> reprovePost(@RequestBody RegisterRequest request) {
        
        Number indexToArray = request.getIndexToArray();
        
        
        User admName = userRepository.findByName("Aliny Melquiades");

        if (admName != null && indexToArray.intValue() < admName.getPosts().size()) {
            admName.getPosts().remove(indexToArray.intValue());
            
            userRepository.save(admName);

            return ResponseEntity.ok(admName.getAprovedPosts());
        
        } else {
        // Retorna uma resposta apropriada se o usuário não for encontrado ou o índice for inválido
            return null;
        }
    }

}
