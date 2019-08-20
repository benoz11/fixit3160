package fixit3160.db;

import java.util.ArrayList;

/**
 * @author Benjamin McDonnell (c3166457)
 *
 */

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fixit3160.entities.Comment;
import fixit3160.entities.User;

@Repository
public interface CommentDao extends CrudRepository<Comment, Long> {
	@Transactional
	public ArrayList<Comment> findAll();
}
