package com.ntl.frs.util.impl;

import java.util.Random;

import com.ntl.frs.bean.CredentialsBean;
import com.ntl.frs.bean.ProfileBean;
import com.ntl.frs.dao.impl.CredentialsBeanDaoImpl;
import com.ntl.frs.dao.impl.ProfileBeanDaoImpl;
import com.ntl.frs.util.Authentication;
import com.ntl.frs.util.User;

public class SignedUp implements User{
LoggedIn log = new LoggedIn();

CredentialsBeanDaoImpl cred=new CredentialsBeanDaoImpl();
	/**
 * 
 */
public SignedUp(CredentialsBeanDaoImpl c) {
	super();
	cred=c;
	
}


public SignedUp(LoggedIn lod,CredentialsBeanDaoImpl c) {
	super();
	cred=c;
	log=lod;
	
}


ProfileBeanDaoImpl profile=new ProfileBeanDaoImpl();
public SignedUp(ProfileBeanDaoImpl prof) {
	super();
	profile=prof;
}

	/**
 * 
 */
public SignedUp() {
	super();
}


 

public SignedUp(LoggedIn logindao) {
	super();
	log=logindao;
}

	public String login(CredentialsBean credit) {
		
		if(log.authenticate(credit))
		{
			if(credit.getLoginStatus()==0) {
			String userType=log.authorize(credit.getUserID());
			 log.changeLoginStatus(credit,credit.getLoginStatus());
			 return userType;
			}
			else {
				
				return "fail";
			}
		}
		else {
			return "invalid";
		}
		
	}

	public boolean logout(String userId) {
		//System.out.println("welcome");
	
		CredentialsBean prof=new CredentialsBean();
		 prof=cred.findByID(userId);
		// System.out.println("gtyu");
		// System.out.println(prof.getUserID());
		LoggedIn log=new LoggedIn();
		
		
		if(log.changeLoginStatus(prof, prof.getLoginStatus()))
		{
			System.out.println("Successfully Logged Out! ");
		}
		else {
			System.out.println("Unable to log you out! Sorry");
		}
		
		return false;
	}


	
	
	public String changePassword(CredentialsBean credentialsBean, String newPassword) {
		// TODO Auto-generated method stub
		
	
		if(cred.changingPassword(credentialsBean)) {
			return credentialsBean.getUserID();
		}
		else {
			return null;
		}
		
	
	}
	
	
	
	
	public String register(ProfileBean profileBean) {
	
		
		String uid=profile.createProfileBean(profileBean);
		if(uid!=null)
		{
			return "success";
		}
		else {
			
			return null;
		}
		
	}

}
