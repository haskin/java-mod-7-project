package dev.haskin.javamod7springproject;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import dev.haskin.javamod7springproject.dto.UserAdvanced;
import dev.haskin.javamod7springproject.dto.UserBasic;
import dev.haskin.javamod7springproject.model.Author;
import dev.haskin.javamod7springproject.model.Book;
import dev.haskin.javamod7springproject.model.Genre;
import dev.haskin.javamod7springproject.model.ReadingList;
import dev.haskin.javamod7springproject.model.User;
import dev.haskin.javamod7springproject.repository.AuthorRepository;
import dev.haskin.javamod7springproject.repository.BookRepository;
import dev.haskin.javamod7springproject.repository.GenreRepository;
import dev.haskin.javamod7springproject.repository.ReadingListRepository;
import dev.haskin.javamod7springproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class Javamod7springprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(Javamod7springprojectApplication.class, args);
	}

	@Transactional
	@Component
	public class StartUpRunner implements CommandLineRunner {

		@Autowired
		private AuthorRepository authorRepository;
		@Autowired
		private BookRepository bookRepository;
		@Autowired
		private GenreRepository genreRepository;
		@Autowired
		private ReadingListRepository readingListRepository;
		@Autowired
		private UserRepository userRepository;
		@Autowired
		private ModelMapper modelMapper;

		@Override
		public void run(String... args) throws Exception {
			System.out.println("Running");

			// Authors
			Author rowling = Author.builder().name("J.K. Rowling").build();
			rowling = authorRepository.save(rowling);

			// Genres
			Genre fiction = genreRepository.save(Genre.builder().name("Fiction").build());
			Genre nonFiction = Genre.builder().name("Non-Fiction").build();

			// Books
			Book harryPotter = Book.builder().author(rowling).genres(Set.of(nonFiction)).pages(223)
					.published(LocalDate.of(1995, 06, 26)).title("Sorcerer Stone").build();
			// Reading List
			ReadingList readingList = ReadingList.builder().name("Adrian's List")
					.books(Set.of(harryPotter)).build();
			harryPotter.setReadingList(Set.of(readingList));
			bookRepository.saveAll(Set.of(harryPotter));

			// Users
			userRepository.saveAll(List.of(
					User.builder().username("adrian").password("password").readingList(Set.of(readingList)).build()));

			// bookRepository.deleteById(1L);
			// userRepository.deleteById(1L);
			// User user = userRepository.findById(1L).orElse(User.builder().build());
			// user.setReadingList(Set.of(ReadingList.builder().name("Adrian's Better List")
			// .books(books.stream().collect(Collectors.toSet())).build()));
			// userRepository.save(user);
			log.info("leaving startup runner");
		}
	}
}
