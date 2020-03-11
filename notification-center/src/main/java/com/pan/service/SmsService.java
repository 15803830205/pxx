package com.pan.service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.pan.model.Sms;
import com.pan.utils.Page;

import java.util.Map;


public interface SmsService {

	void save(Sms sms, Map<String, String> params);

	void update(Sms sms);

	Sms findById(Long id);

	Page<Sms> findSms(Map<String, Object> params);

	/**
	 * 发送短信
	 */
	SendSmsResponse sendSmsMsg(Sms sms);
}
