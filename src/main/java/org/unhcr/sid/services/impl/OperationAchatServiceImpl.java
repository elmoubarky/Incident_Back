package org.unhcr.sid.services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unhcr.sid.dao.OperationAchatRepository;
import org.unhcr.sid.entities.OperationAchat;
import org.unhcr.sid.services.OperationAchatService;

@Service
@Transactional
public class OperationAchatServiceImpl implements OperationAchatService{
	
	@Autowired
	private OperationAchatRepository operationAchatRepository;

	@Override
	public OperationAchat saveOperatonAchat(String type, String reqId, String poNb, String montant, String detail,
			String statut, String username) {
		// TODO Auto-generated method stub
		
		OperationAchat op = new OperationAchat();
		//op.setAttachement(attachement);
		op.setDetail(detail);
		op.setMontant(montant);
		op.setPoNb(poNb);
		op.setReqId(reqId);
		op.setStatut(statut);
		op.setType(type);
		op.setUserName(username);
		SimpleDateFormat sdf2  = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
		op.setDateEnreg(sdf2.format(new Date()));
		operationAchatRepository.save(op);
		return op;
	}

	@Override
	public OperationAchat loadOperatonAchatByType(String type) {
		// TODO Auto-generated method stub
		return operationAchatRepository.findByType(type);
	}

	@Override
	public OperationAchat loadOperatonAchatById(int id) {
		// TODO Auto-generated method stub
		return operationAchatRepository.findByIdOperation(id);
	}

	@Override
	public OperationAchat loadOperatonAchatByReqId(String reqId) {
		// TODO Auto-generated method stub
		return operationAchatRepository.findByReqId(reqId);
	}

	@Override
	public OperationAchat loadOperatonAchatByPoNb(String poNb) {
		// TODO Auto-generated method stub
		return operationAchatRepository.findByPoNb(poNb);
	}

	@Override
	public OperationAchat updateOperatonAchat(int idOperation, String type, String reqId, String poNb, String montant,
			String detail, String statut, String username) {
		// TODO Auto-generated method stub
		
		OperationAchat op = operationAchatRepository.findByIdOperation(idOperation);
		if(op==null)throw new RuntimeException("Operation not exists");
		//op.setAttachement(attachement);
		op.setDetail(detail);
		op.setMontant(montant);
		op.setPoNb(poNb);
		op.setReqId(reqId);
		op.setStatut(statut);
		op.setType(type);
		op.setUserName(username);
		SimpleDateFormat sdf2  = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
		op.setDateEnreg(sdf2.format(new Date()));
		operationAchatRepository.saveAndFlush(op);
		return op;
	}

	@Override
	public OperationAchat updateOperationAttachement(int id, String attachement) {
		// TODO Auto-generated method stub
		OperationAchat op = operationAchatRepository.findByIdOperation(id);
		if(op==null)throw new RuntimeException("Operation not exists");
		op.setAttachement(attachement);
		operationAchatRepository.saveAndFlush(op);
		
		return op;
	}

	@Override
	public List<OperationAchat> getAllOpers() {
		// TODO Auto-generated method stub
		return operationAchatRepository.findAll();
	}

	@Override
	public List<OperationAchat> getAllOpersByDate(String date1, String date2) {
		// TODO Auto-generated method stub
		System.out.println("date 1 "+date1);
		System.out.println("date 2 "+date2);
		List<OperationAchat> lis = new ArrayList<>();
		List<OperationAchat> lis2 = new ArrayList<>();
		lis = operationAchatRepository.findOperByDate(date1, date2);
		lis2 = operationAchatRepository.findByDateEnregBetween(date1, date2);
		System.out.println("retour getAllOpersByDate "+lis);
		System.out.println("retour findByDateEnregBetween "+lis2);
		return lis;
	}

	@Override
	public List<OperationAchat> RechOpers(String option, String search) {
		// TODO Auto-generated method stub
		List<OperationAchat> lis = new ArrayList<>();
		if(option==null) throw new RuntimeException("Value search are null"); 
		if(option.equals("Type")) {
			lis = operationAchatRepository.findByTypeContains(search);
			System.out.println("retour findByTypeContains "+lis);
		}else if(option.equals("Req Id")) {
			lis = operationAchatRepository.findByReqIdContains(search);
			System.out.println("retour findByReqIdContains "+lis);
		}else if(option.equals("PoNb")) {
			lis = operationAchatRepository.findByPoNbContains(search);
			System.out.println("retour findByPoNbContains "+lis);
		}
		return lis;
	}

}
