package lacopet.demo.model;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        // Substitua a string a seguir pela sua URI do MongoDB Atlas
        String uri = "mongodb+srv://gabrield3vsilva:1981abcd.@cluster0.shx7xrb.mongodb.net/";
        MongoClient client = MongoClients.create(uri);
        
        // Verifica se a conexão foi bem-sucedida
        if (client != null) {
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
        } else {
            System.out.println("Erro ao conectar ao banco de dados.");
        }
        
        return client;
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "Users");
    }
}

