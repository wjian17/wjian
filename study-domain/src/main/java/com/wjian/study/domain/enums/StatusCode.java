package com.wjian.study.domain.enums;

import com.wjian.study.domain.basic.BasicResponse;

public enum StatusCode {

	/* 成功状态码 */
	SUCCESS(10000, "操作成功"), 

	OTHER_EXCEPTION(0, "其他异常"), 

	 NOT_FOUND_RESOURCE(404, "路径错误"), 
	
	 INTERNAL_SERVER_ERROR(500, "服务器内部错误"), 
	/* 参数错误 */
	PARAM_IS_INVALID(10001, "参数无效"), 
	PARAM_IS_BLANK(10002, "参数为空"), 
	PARAM_TYPE_BIND_ERROR(10003, "参数类型错误"), 
	PARAM_NOT_COMPLETE(10004, "参数缺失"), 
	ID_NOT_EQUAL(10005, "信息ID异常"), 
	PHONE_IS_ERROR(10006, "手机号错误"), 
	PHONECAPTCHA_IS_ERROR(10007, "手机验证码错误"), 
	CAPTCHA_IS_ERROR(10008, "验证码错误"), 
	EMAIL_IS_ERROR(10009, "邮箱错误"), 
	USER_INPUT_IS_BLANK(10010, "账号或密码为空"), 
	NAME_OR_PASSWRD_IS_BLANK(10011, "联系人或密码为空"), 
	PASSWRD_NOT_ALIKE(10012, "密码不一致"), 
	PROTOCOL_NOT_READER(10013, "请先阅读并同意相关协议"), 
	PASSWRD_IS_ERROR(10014, "原密码输入错误"), 

	/* 用户错误 */
	USER_NOT_LOGGED_IN(20001, "用户未登录或SESSION已过期"), 
	USER_LOGIN_ERROR(20002, "账号不存在或密码错误"), 
	USER_ACCOUNT_FORBIDDEN(20003, "账号已被禁用"), 
	USER_NOT_EXIST(20004, "用户不存在"), 
	LOGINNAME_HAS_EXISTED(20005, "用户名已被注册"), 
	PHONE_HAS_EXISTED(20006, "手机号已经注册"), 
	EMAIL_HAS_EXISTED(20007, "邮箱已经注册"), 
	USER_INFO_ERROR(20008, "用户信息异常"), 
	USER_LOGIN_FAILED(20009, "系统异常，请联系管理员"), 
	PASSWRD_RESET_FAILED(20010, "密码重置失败"),
	PHONE_NOT_EXIST(20011, "手机号未注册"), 
	PASSWRD_HAS_OUTTIME(20012, "密码已过期,请联系管理员"),
	USER_HAS_OUTTIME(20013,"账户已过期,请联系管理员"),
	USER_ACCOUNT_LOCKED(20014, "账户被锁定,请联系管理员"),

	/* 数据错误 */
	RESULE_DATA_NONE(30001, "数据未找到"), 
	DATA_IS_WRONG(30002, "数据有误"), 
	DATA_ALREADY_EXISTED(30003, "数据已存在"), 
	OPERATE_EXCEPTION(30004, "操作异常"), 
	CAPTCHA_BUILD_FAILED(30005, "验证码生成失败"), 
	PHONECAPTCHA_BUILD_FAILED(30006, "手机验证码发送失败"), 
	ROLL_BACK_FAILED(30007, "数据回滚失败"), 
	INIT_INFO_FAILED(30008, "信息初始化失败"), 
	UPLOAD_FILE_FAILED(30009, "文件上传失败"), 
	EXCEED_MAX_AMOUNT(30010, "文件数量超过上限"), 
	EXCEED_MAX_SIZE(30011, "文件大小超过上限"), 
	FILE_NOT_EXIST(30012, "文件不存在"), 
	ORDER_STATUS_ERROR(30013, "工单状态异常"), 
	WORD_EXCEED_MAX_SIZE(30014, "文字长度不符合规定"), 
	CUSTOMER_DONT_DELETE(30015, "合作中的客户，不能删除"), 
	SLA_DONT_OPERATION(30016, "使用中的SLA，不能编辑和删除"), 

	/* 权限错误 */
	PERMISSION_NO_ACCESS(40001, "无访问权限"), 
	PERMISSION_NO_OPERATION(40002, "无操作权限"), 

	SYSTEM_ERROR(99999, "系统异常");
	private Integer code;
	private String message;

	StatusCode(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public static BasicResponse getBasicResponse(StatusCode statusCode){
		BasicResponse basicResponse = new BasicResponse();
		basicResponse.setErrorCode(statusCode.code.toString());
		basicResponse.setErrorMsg(statusCode.message);
		return basicResponse;
	} 

	public Integer code() {
		return this.code;
	}

	public String message() {
		return this.message;
	}

	public static StatusCode getStatusCode(Integer code) {
		for (StatusCode item : StatusCode.values()) {
			if (item.code().equals(code)) {
				return item;
			}
		}
		return null;

	}

	public static String getMessage(String name) {
		for (StatusCode item : StatusCode.values()) {
			if (item.name().equals(name)) {
				return item.message;
			}
		}
		return name;
	}

	public static Integer getCode(String name) {
		for (StatusCode item : StatusCode.values()) {
			if (item.name().equals(name)) {
				return item.code;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return this.name();
	}


}