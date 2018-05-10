package org.androidpn.server.service;

import org.androidpn.server.model.Comment;

import java.util.List;


public interface CommentService {
	public Comment findById(long id);
	public void saveComment(Comment comment);
	public void deleteComment(Comment comment);
	public void updateComment(Comment comment);
	public List<Comment> findCommentByNotID(long notId);
	public List<Comment> findCommentByAccount(String account);

}
