package org.androidpn.server.dao.hibernate;

import org.androidpn.server.dao.CommentDao;
import org.androidpn.server.model.Comment;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import java.util.List;

public class CommentDaoHibernate extends HibernateDaoSupport implements CommentDao {

	@Override
	public Comment findById(long id) {
		return (Comment) getHibernateTemplate().get(Comment.class,id);
	}

	@Override
	public void saveComment(Comment comment) {
		getHibernateTemplate().save(comment);
		getHibernateTemplate().flush();
	}

	@Override
	public void deleteComment(Comment comment) {
		getHibernateTemplate().delete(comment);
	}

	@Override
	public void updateComment(Comment comment) {
		getHibernateTemplate().update(comment);
	}

	@Override
	public List<Comment> findCommentByNotID(long notId) {
		String hql ="from Comment where notId = ?";
		List<Comment> commentList= getHibernateTemplate().find(hql,notId);
		if(!commentList.isEmpty()&&commentList.size()>0){
			return commentList;
		}else{
			return null;
		}
	}

	@Override
	public List<Comment> findCommentByAccount(String account) {
		String hql ="from Comment where account = ?";
		List<Comment> commentList= getHibernateTemplate().find(hql,account);
		if(!commentList.isEmpty()&&commentList.size()>0){
			return commentList;
		}else{
			return null;
		}
	}
}
