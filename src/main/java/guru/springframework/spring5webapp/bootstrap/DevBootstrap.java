package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("-- Default Values will be added to Database --");
        initData();
    }

    private void initData() {
        // Eric
        Author eric = new Author("Eric", "Evans");
        Publisher publisherddd = new Publisher("Harper Collins", "London");
        Book ddd = new Book("Domain Driven Design", "1234", publisherddd);
        eric.getBooks().add(ddd);

        authorRepository.save(eric);
        publisherRepository.save(publisherddd);
        bookRepository.save(ddd);

        // Rod
        Author rod = new Author("Rod", "Johnson");
        Publisher publisherNoEJB = new Publisher("Worx", "USA");
        Book noEJB = new Book("J2EE Development without EJB", "23444", publisherNoEJB);
        rod.getBooks().add(noEJB);

        authorRepository.save(rod);
        publisherRepository.save(publisherNoEJB);
        bookRepository.save(noEJB);
    }
}
