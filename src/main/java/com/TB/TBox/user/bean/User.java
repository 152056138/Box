/**
 * 用户类
 */
package com.TB.TBox.user.bean;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
@Component
public class User {
	private int uid; //用户id
	private String number; //账号
	private String username; //昵称
	private String password; //密码
	private String phone; //手机号
	private String place; //所在地
	private String constellation; //星座
	private String blood; //血型
	private String signature; //个性签名
	private String birthday; //生日
	private String ufacing; //头像
	private String hobby; //兴趣
	private String job; //职业
	private String gender; //性别
	private String personalPassword; //私人密码
	private String fingerprint; //指纹信息
	private int age; //年龄
	
	private static Gson gson = new Gson();
	public User(){
		
	}
	public User(int uid, String number, String username, String password, String phone, String place,
			String constellation, String blood, String signature, String birthday, String ufacing, String hobby, String job,
			String gender, String personalPassword, String fingerprint, int age) {
		super();
		this.uid = uid;
		this.number = number;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.place = place;
		this.constellation = constellation;
		this.blood = blood;
		this.signature = signature;
		this.birthday = birthday;
		this.ufacing = ufacing;
		this.hobby = hobby;
		this.job = job;
		this.gender = gender;
		this.personalPassword = personalPassword;
		this.fingerprint = fingerprint;
		this.age = age;
	}


	//注册账号时调用这个构造函数
	public User(String number, String password, String place,String ufacing) {
		super();
		this.number = number;
		this.password = password;
		this.ufacing = ufacing;
	}
	
	
	//创建角色的时候调用这个构造函数
	public User(int uid,String username, String phone,String constellation, String blood, String signature, String birthday, String ufacing, String hobby, String job,
			String gender, String personalPassword, int age) {
		super();
		this.uid = uid;
		this.username = username;
		this.phone = phone;
		this.constellation = constellation;
		this.blood = blood;
		this.signature = signature;
		this.birthday = birthday;
		this.ufacing = ufacing;
		this.hobby = hobby;
		this.job = job;
		this.gender = gender;
		this.personalPassword = personalPassword;
		this.age = age;
	}
	//set-get
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	public String getBlood() {
		return blood;
	}
	public void setBlood(String blood) {
		this.blood = blood;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getUfacing() {
		return ufacing;
	}
	public void setUfacing(String ufacing) {
		this.ufacing = ufacing;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPersonalPassword() {
		return personalPassword;
	}
	public void setPersonalPassword(String personalPassword) {
		this.personalPassword = personalPassword;
	}
	public String getFingerprint() {
		return fingerprint;
	}
	public void setFingerprint(String fingerprint) {
		this.fingerprint = fingerprint;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", number=" + number + ", username=" + username + ", password=" + password
				+ ", phone=" + phone + ", place=" + place + ", constellation=" + constellation + ", blood=" + blood
				+ ", signature=" + signature + ", birthday=" + birthday + ", ufacing=" + ufacing + ", hobby=" + hobby
				+ ", job=" + job + ", gender=" + gender + ", personalPassword=" + personalPassword + ", fingerprint="
				+ fingerprint + ", age=" + age + "]";
	}
	
	public String toJson(){
		return gson.toJson(this);
	}
}
