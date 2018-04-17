package org.androidpn.server.xmpp;

public class ErrorCode {

	public static final String REGISTER_SUCCEED = "200";
	public static final String REGISTER_ERROR_201 = "201";
	public static final String LOGIN_SUCCEED = "300";
	public static final String LOGIN_ERROR_301 = "301";
	public static final String LOGIN_ERROR_302 = "302";
	public static final String LOGIN_ERROR_303 = "303";
	public static final String CREATE_GROUP_SUCCEED = "400";
	public static final String CREATE_GROUP_ERROR_401 = "401";
	public static final String SEND_MESSAGE_4_GROUP_SUCCEED = "500";
	public static final String SEND_MESSAGE_4_GROUP_501 = "501";
	public static final String JOIN_GROUP_SUCCEED = "600";
	public static final String JOIN_GROUP_601 = "601";
	public static final String JOIN_GROUP_602 = "602";
	public static final String JOIN_GROUP_603 = "603";
	public static final String HANDLE_GROUP_REQUEST_AGREE = "700";
	public static final String HANDLE_GROUP_REQUEST_DISAGREE = "701";
	public static final String HANDLE_GROUP_REQUEST_702 = "702";
	public static final String CHANGE_PUSHABLE_4_GROUP_800 = "800";
	public static final String CHANGE_PUSHABLE_4_GROUP_801 = "801";
	public static final String CHANGE_PUSHABLE_4_GROUP_802 = "802";
	public static final String CHANGE_PUSHABLE_4_GROUP_803 = "803";
	public static final String CHANGE_PUSHABLE_4_GROUP_804 = "804";
	public static final String CHANGE_PUSHABLE_4_GROUP_805 = "805";
	public static final String OUT_MEMBER_4_GROUP_SUCCEED = "900";
	public static final String OUT_MEMBER_4_GROUP_901= "901";
	public static final String OUT_MEMBER_4_GROUP_902 = "902";
	
	
	
	

	public static String getEmsg(String ecode) {
		int ecodeInt = Integer.parseInt(ecode);
		switch (ecodeInt) {
		case 200:
			return "注册成功，请登录";
		case 201:
			return "注册账户已存在";
		case 300:
			return "登录成功";
		case 301:
			return "密码错误";
		case 302:
			return "账号不存在";
		case 303:
			return "登录异常（与服务器时间相差5分钟）";
		case 400:
			return "创建成功";
		case 401:
			return "创建失败";
		case 901:
			return "您没有权限操作";
		case 902:
			return "用户不存在";
			
		default:
			return "";

		}
	}

}
