package com.example.endyearproject.Types;

public class Student {
    private String privateName;
    private String familyName;
    private int personalID;
    private int grade;
    private int classNum;
    private Vacin Vacination1;
    private Vacin Vacination2;
    private boolean canVacinate;
    public Student()
    {
        this.privateName = "";
        this.familyName = "";
        this.grade = -1;
        this.classNum = -1;
        this.personalID = -1;
        this.Vacination1 = new Vacin();
        this.Vacination2 = new Vacin();
        this.canVacinate = false;
    }
    public Student(String privateName, String familyName, int grade, int classNum, int personalID, Vacin Vacination1
    , Vacin Vacination2, boolean canVacinate)
    {
        this.privateName = privateName;
        this.familyName =familyName;
        this.grade = grade;
        this.classNum = classNum;
        this.personalID = personalID;
        this.Vacination1 = Vacination1;
        this.Vacination2 = Vacination2;
        this.canVacinate = canVacinate;
    }
    public void setPersonalID(int ID) {this.personalID = ID;}
    public void setPrivateName(String privateName)
    {
        this.privateName = privateName;
    }
    public void setFamilyName(String familyName)
    {
        this.familyName = familyName;
    }
    public void setGrade(int grade)
    {
        this.grade = grade;
    }
    public void setClassNum(int classNum)
    {
        this.classNum = classNum;
    }
    public void setVacination1(Vacin Vacination1)
    {
        this.Vacination1 = Vacination1;
    }
    public void setVacination2(Vacin Vacination2)
    {
        this.Vacination2 = Vacination2;
    }
    public void setCanVacinate(boolean canVacinate)
    {
        this.canVacinate = canVacinate;
    }
    public int getPersonalID(){return this.personalID;}
    public String getPrivateName()
    {
        return this.privateName;
    }
    public String getFamilyName()
    {
        return this.familyName;
    }
    public int getGrade()
    {
        return this.grade;
    }
    public int getClassNum()
    {
        return this.classNum;
    }
    public Vacin getVacination1()
    {
        return this.Vacination1;
    }
    public Vacin getVacination2()
    {
        return this.Vacination2;
    }
    public boolean getCanVacinate()
    {
        return this.canVacinate;
    }

}
