package com.example.crudroom.Model;

public class NewUser {
    String fName="",lName="",phoneNumber="",gender="",dob="",employeeName="",designation="",accType="",bankName="",bankBranchName="",AccNo="",IFSCcode="",imagePath="";
    Long id=0L,employeeNo=0L;
    int workExp=0;

    public NewUser() {
    }

    public NewUser(String fName, String lName, String phoneNumber, String gender, String dob, String employeeName, String designation, String accType, String bankName, String bankBranchName, String accNo, String IFSCcode, String imagePath, Long employeeNo, int workExp) {
        this.fName = fName;
        this.lName = lName;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.dob = dob;
        this.employeeName = employeeName;
        this.designation = designation;
        this.accType = accType;
        this.bankName = bankName;
        this.bankBranchName = bankBranchName;
        this.AccNo = accNo;
        this.IFSCcode = IFSCcode;
        this.imagePath = imagePath;
        this.employeeNo = employeeNo;
        this.workExp = workExp;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankBranchName() {
        return bankBranchName;
    }

    public void setBankBranchName(String bankBranchName) {
        this.bankBranchName = bankBranchName;
    }

    public String getAccNo() {
        return AccNo;
    }

    public void setAccNo(String accNo) {
        AccNo = accNo;
    }

    public String getIFSCcode() {
        return IFSCcode;
    }

    public void setIFSCcode(String IFSCcode) {
        this.IFSCcode = IFSCcode;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(Long employeeNo) {
        this.employeeNo = employeeNo;
    }

    public int getWorkExp() {
        return workExp;
    }

    public void setWorkExp(int workExp) {
        this.workExp = workExp;
    }
}
