package com.dbteam.dbhero.models.geofox;

public class Info {
	private String id;
	private String beginOfService;
	private String endOfService;
	private String dataId;
	private String buildDate;
	private String buildTime;
	private String buildText;
	private String returnCode;
	private String errorText;
	private String errorDevInfo;
	
	public String getErrorDevInfo() {
		return errorDevInfo;
	}
	public void setErrorDevInfo(String errorDevInfo) {
		this.errorDevInfo = errorDevInfo;
	}
	public String getErrorText() {
		return errorText;
	}
	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBeginOfService() {
		return beginOfService;
	}
	public void setBeginOfService(String beginOfService) {
		this.beginOfService = beginOfService;
	}
	public String getEndOfService() {
		return endOfService;
	}
	public void setEndOfService(String endOfService) {
		this.endOfService = endOfService;
	}
	public String getDataId() {
		return dataId;
	}
	public void setDataId(String dateId) {
		this.dataId = dateId;
	}
	public String getBuildDate() {
		return buildDate;
	}
	public void setBuildDate(String buildDate) {
		this.buildDate = buildDate;
	}
	public String getBuildTime() {
		return buildTime;
	}
	public void setBuildTime(String buildTime) {
		this.buildTime = buildTime;
	}
	public String getBuildText() {
		return buildText;
	}
	public void setBuildText(String buildText) {
		this.buildText = buildText;
	}
}


//{
//    "returnCode": "OK",
//    "beginOfService": "19.10.2017",
//    "endOfService": "10.12.2017",
//    "id": "03.29.01.22.01",
//    "dataId": "29.77.01",
//    "buildDate": "19.10.2017",
//    "buildTime": "12:53:51",
//    "buildText": "Regelfahrplan 2017"
//}