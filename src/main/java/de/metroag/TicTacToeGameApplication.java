package de.metroag;



import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import de.metroag.domains.Player;
import de.metroag.repository.PlayerRepository;


@SpringBootApplication

public class TicTacToeGameApplication {


	public static void main(String[] args) {
	

		SpringApplication.run(TicTacToeGameApplication.class, args);
	}

    @Bean
    public CommandLineRunner demo(PlayerRepository playerRepository) {
        return (args) -> {

            //save a couple of players
            playerRepository.save(new Player("Annabel", "Annabel@Annabel.com", new BCryptPasswordEncoder().encode("Annabel")));
            playerRepository.save(new Player("David", "David@David.com",  new BCryptPasswordEncoder().encode("David")));

        };
    }

}
