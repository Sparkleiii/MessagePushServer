package org.androidpn.server.service.impl;

import org.androidpn.server.dao.CommentDao;
import org.androidpn.server.dao.LoginDao;
import org.androidpn.server.model.Comment;
import org.androidpn.server.model.Login;
import org.androidpn.server.service.CommentService;
import org.androidpn.server.service.LoginService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class CommentServiceImpl implements CommentService {
	protected final Log log = LogFactory.getLog(getClass());

	private CommentDao commentDao;

	public CommentDao getCommentDao() {
		return commentDao;
	}

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}


	@Override
	public Comment findById(long id) {
		return commentDao.findById(id);
	}

	@Override
	public void saveComment(Comment comment) {
		commentDao.saveComment(comment);
	}

	@Override
	public void deleteComment(Comment comment) {
		commentDao.deleteComment(comment);
	}

	@Override
	public void updateComment(Comment comment) {
		commentDao.updateComment(comment);
	}

	@Override
	public List<Comment> findCommentByNotID(long notId) {
		return commentDao.findCommentByNotID(notId);
	}

	@Override
	public List<Comment> findCommentByAccount(String account) {
		return commentDao.findCommentByAccount(account);
	}
}
