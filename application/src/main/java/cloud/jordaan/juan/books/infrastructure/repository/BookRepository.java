package cloud.jordaan.juan.books.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    @Query(value = "select b.* from book as b limit :limit offset :offset", nativeQuery = true)
    List<BookEntity> findAllPaged(Long limit, Long offset);
}