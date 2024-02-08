package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    public List<Board> findAll() {
        Query query = em.createNativeQuery("SELECT * FROM board_tb ORDER BY id DESC", Board.class);
        return query.getResultList();
    }
}
