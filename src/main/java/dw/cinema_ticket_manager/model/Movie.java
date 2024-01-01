package dw.cinema_ticket_manager.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private int runtime;
    private int releaseYear;
    @Getter
    @Column(name = "poster_path")
    private String poster;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    public Movie(){}

    public Movie(String title, String description, int runtime, int releaseYear, String poster, List<Genre> genres) {
        this.title = title;
        this.description = description;
        this.runtime = runtime;
        this.releaseYear = releaseYear;
        this.poster = poster;
        this.genres = genres;
    }

    public void addGenre(Genre genre) {
        if (genres.contains(genre)) {
            return;
        }
        genres.add(genre);
    }

    public String getGenresAsString() {
        return genres.stream()
                .map(Genre::getName)
                .sorted()
                .collect(Collectors.joining(", ", "Gatunek filmu: ", ""));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return runtime == movie.runtime && releaseYear == movie.releaseYear &&
                Objects.equals(title, movie.title) &&
                Objects.equals(description, movie.description) &&
                Objects.equals(poster, movie.poster) &&
                Objects.equals(genres, movie.genres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, runtime, releaseYear, poster, genres);
    }
}


















