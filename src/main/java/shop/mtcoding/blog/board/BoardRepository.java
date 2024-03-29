package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shop.mtcoding.blog._core.Constant;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    public List<Board> findAll(int page) {
        int value = page * Constant.PAGING_COUNT;
        Query query = em.createNativeQuery("select * from board_tb order by id desc limit ?, ?", Board.class);
        query.setParameter(1, value);
        query.setParameter(2, Constant.PAGING_COUNT);

        List<Board> boardList = query.getResultList();
        return boardList;
    }

    public int count(){
        Query query = em.createNativeQuery("select count(*) from board_tb");
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    @Transactional
    public void save(BoardRequest.saveDTO requestDTO) {
        Query query = em.createNativeQuery("INSERT INTO board_tb (title, content, author, created_at) VALUES (?, ?, ?, now())");
        query.setParameter(1, requestDTO.title);
        query.setParameter(2, requestDTO.content);
        query.setParameter(3, requestDTO.author);
        query.executeUpdate();
    }

    @Transactional
    public void deleteById(int id) {
        Query query = em.createNativeQuery("DELETE FROM board_tb WHERE id = ?");
        query.setParameter(1, id);
        query.executeUpdate();
    }

    public Board findById(int id) {
        Query query = em.createNativeQuery("SELECT * FROM board_tb WHERE id = ?", Board.class);
        query.setParameter(1, id);
        Board board = (Board) query.getSingleResult();
        return board;
    }

    @Transactional
    public void update(int id, BoardRequest.updateDTO requestDTO) {
        Query query = em.createNativeQuery("UPDATE board_tb SET author = ?,title = ?, content = ? WHERE id = ?");
        query.setParameter(1, requestDTO.author);
        query.setParameter(2, requestDTO.title);
        query.setParameter(3, requestDTO.content);
        query.setParameter(4, id);
        query.executeUpdate();
    }
}
