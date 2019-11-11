package org.unhcr.sid.services;

import java.util.List;

import org.unhcr.sid.entities.OperationAchat;

public interface OperationAchatService {
	
	public OperationAchat saveOperatonAchat(String type, String reqId, String poNb, String montant, 
			String detail, String statut, String username);
	public OperationAchat loadOperatonAchatByType(String type);
	public OperationAchat loadOperatonAchatById(int id);
	public OperationAchat loadOperatonAchatByReqId(String reqId);
	public OperationAchat loadOperatonAchatByPoNb(String poNb);
	public OperationAchat updateOperatonAchat(int idOperation, String type, String reqId, String poNb, String montant, 
			String detail, String statut, String username);
	public OperationAchat updateOperationAttachement(int id, String attachement);
	public List<OperationAchat> getAllOpers();
	public List<OperationAchat> getAllOpersByDate(String date1, String date2);
	public List<OperationAchat> RechOpers(String value, String search);



}
