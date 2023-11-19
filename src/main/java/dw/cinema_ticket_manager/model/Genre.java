package dw.cinema_ticket_manager.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long genre_id;
    private String name;
    @ManyToMany(mappedBy = "genres")
    private Set<Movie> movies;

    public Genre() {}

    public Genre(String name) {
        this.name = name;
        movies = new HashSet<>();
    }

    public void addMovie(Movie movie) {
        if (movies.contains(movie))
            return;

        movies.add(movie);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(name, genre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "name='" + name + '\'' +
                '}';
    }
}
