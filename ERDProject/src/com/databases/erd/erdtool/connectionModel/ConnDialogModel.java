package com.databases.erd.erdtool.connectionModel;

/**
 * 
 * @author ABHISHEK
 *
 */
public class ConnDialogModel 
{
	private String dbnameStr,localStr,portnoStr,usernameStr,passwordStr;
	
	public String getDbnameStr() {
		return dbnameStr;
	}
	public void setDbnameStr(String dbnameStr) {
		this.dbnameStr = dbnameStr;
	}
	public String getLocalStr() {
		return localStr;
	}
	public void setLocalStr(String localStr) {
		this.localStr = localStr;
	}
	public String getPortnoStr() {
		return portnoStr;
	}
	public void setPortnoStr(String portnoStr) {
		this.portnoStr = portnoStr;
	}
	public String getUsernameStr() {
		return usernameStr;
	}
	public void setUsernameStr(String usernameStr) {
		this.usernameStr = usernameStr;
	}
	public String getPasswordStr() {
		return passwordStr;
	}
	public void setPasswordStr(String passwordStr) {
		this.passwordStr = passwordStr;
	}
}
