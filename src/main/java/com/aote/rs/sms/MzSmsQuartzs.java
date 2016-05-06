package com.aote.rs.sms;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONObject;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.aote.rs.util.JsonTransfer;
import com.aote.rs.sms.SmsService;

public class MzSmsQuartzs {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	public void syqf(){
		String sql = "select b.f_username, b.f_phone from t_handplan h "+
			" left join t_userfiles b"+
			" on h.f_userid = b.f_userid"+
			" where convert(varchar(6),h.f_inputdate,112)=convert(varchar(6),dateadd(month,-1,getdate()),112) and h.shifoujiaofei = '��' and h.f_state = '�ѳ���' ";
		String templatename = "Ƿ��һ����";
		smsQuartz(sql, templatename);
	}
	
	public void ssyqf(){
		String sql = "select b.f_username, b.f_phone from t_handplan h "+
				" left join t_userfiles b"+
				" on h.f_userid = b.f_userid"+
				" where convert(varchar(6),h.f_inputdate,112)=convert(varchar(6),dateadd(month,-2,getdate()),112) and h.shifoujiaofei = '��' and h.f_state = '�ѳ���' ";
		String templatename = "Ƿ��������";
		smsQuartz(sql, templatename);
	}
	
	public void sssyqf(){
		String sql = "select b.f_username, b.f_phone from t_handplan h "+
				" left join t_userfiles b"+
				" on h.f_userid = b.f_userid"+
				" where convert(varchar(6),h.f_inputdate,112)=convert(varchar(6),dateadd(month,-3,getdate()),112) and h.shifoujiaofei = '��' and h.f_state = '�ѳ���' ";
		String templatename = "Ƿ��������";
		smsQuartz(sql, templatename);
	}
//
//	// Ԥ���û�����20
//	public void zhyeLt20() {
//		String sql = "select f_userid, f_username, f_phone from t_userinfo where f_zhye <20 and f_zhye >=0";
//		String templatename = "Ԥ���û�����";
//		smsQuartz(sql, templatename);
//	}
//
//	// ����Ƿ���û�
//	public void lastMonthQf() {
//		String sql = "select f_username ,f_phone , SUM(money) as money "
//				+ "from "
//				+ "( select u2.f_username,u.f_phone,(f.oughtfee-u2.f_zhye) as money ,  u.f_userinfoid "
//				+ "from (select f_userid,sum(oughtfee) as oughtfee from t_handplan "
//				+ "where f_state  =  '�ѳ���' and shifoujiaofei  =  '��'  and   convert(char(7),f_inputdate,120)=convert(char(7), dateadd(month,-1,getdate()) ,120)"
//				+ "group by f_userid ) f  " + "left join t_userfiles u  "
//				+ "on f.f_userid  = u.f_userid " + "left join t_userinfo u2 "
//				+ "on u.f_userinfoid = u2.f_userid) t"
//				+ " group by f_username ,f_phone ";
//		String templatename = "����Ƿ���û��߷Ѷ���";
//		smsQuartz(sql, templatename);
//	}
//
//	// ������ҵǷ��
//	public void thisMonthSy() {
//		String sql = "select f_username ,f_phone , SUM(money) as money "
//				+ " from "
//				+ "( select u2.f_username,u.f_phone,(f.oughtfee-u.f_zhye) as money ,  u.f_userinfoid ,u.f_stairtype "
//				+ "from (select f_userid,sum(oughtfee) as oughtfee from t_handplan "
//				+ "where f_state  =  '�ѳ���' and shifoujiaofei  =  '��' and f_stairtype like '%��ҵ%' and convert(char(7),f_inputdate ,120)=convert(char(7),getdate(),120) "
//				+ "group by f_userid ) f  "
//				+ " left join t_userfiles u  "
//				+ " on f.f_userid  = u.f_userid "
//				+ "left join t_userinfo u2 "
//				+ "  on u.f_userinfoid = u2.f_userid)t "
//				+ " where t.f_stairtype like '%��ҵ%' group by f_username ,f_phone ";
//		String templatename = "��������δ���û�";
//		smsQuartz(sql, templatename);
//	}

	public void smsQuartz(String thissql, String templatename) {
		JSONObject result = new JSONObject();
		SmsService smsService = new SmsService();
		try {
			smsService.setHibernateTemplate(hibernateTemplate); // newʱ��Ӧ������ģ��
			JSONObject res = new JSONObject();
			String param;
			final String sql = thissql;
			List<Map<String, Object>> list = (List<Map<String, Object>>) hibernateTemplate
					.execute(new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException {
							Query q = session.createSQLQuery(sql);
							q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
							List result = q.list();
							return result;
						}
					});
			Iterator it = list.iterator();
			while (it.hasNext()) {
				Map<String, Object> map = (Map<String, Object>) it.next();
				res = (JSONObject) new JsonTransfer().MapToJson(map);
				param = res.toString();
				JSONObject attr = new JSONObject();
				result = smsService.sendTemplate(param, map.get("f_phone")
						.toString(), templatename);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}