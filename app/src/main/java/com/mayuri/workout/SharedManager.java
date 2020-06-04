package com.mayuri.workout;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedManager extends Application {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;
   public static final String SHARED_PREF_NAME="BigArt";


    @SuppressLint("CommitPrefEdits")
    public SharedManager(Context context)
    {
        this.context=context;
        preferences=context.getSharedPreferences(SHARED_PREF_NAME,0);
        editor=preferences.edit();
    }

    public void setUserEmail(String UserEmail)
    {
        editor.putString("User_Email",UserEmail).commit();
    }


    public void getIsEmployer(){
        preferences.getString("isEmployer","0");
    }
 public void setIsEmployer(String s){
     editor.putString("isEmployer",s).commit();

 }

    public String getUserEmail()
    {
        return preferences.getString("User_Email","");
    }

    public void setUserId(String UserId)
    {
        editor.putString("User_Id",UserId).commit();
    }
    public void setStudentId(String UserId)
    {
        editor.putString("Student_Id",UserId).commit();
    }

    public String getStudentID()
    {
        return preferences.getString("Student_Id","");
    }
    public String getUserID()
    {
        return preferences.getString("User_Id","");
    }

    public void setFirstName(String FirstName)
    {
        editor.putString("User_FirstName",FirstName).commit();
    }
    public String getFirstName()
    {
        return preferences.getString("User_FirstName","");
    }

    public void setLastName(String LastName)
    {
        editor.putString("User_LastName",LastName).commit();
    }
    public String getLastName()
    {
        return preferences.getString("User_LastName","");
    }
    public void setMiddleName(String MiddleName)
    {
        editor.putString("User_MiddleName",MiddleName).commit();
    }
    public String getMiddleName()
    {
        return preferences.getString("User_MiddleName","");
    }

    public void setUserTypeId(String UserTypeId)
    {
        editor.putString("User_UserTypeId",UserTypeId).commit();
    }
    public String getUserTypeId()
    {
        return preferences.getString("User_UserTypeId","");
    }
    public void setIsActive(String IsActive)
    {
        editor.putString("User_IsActive",IsActive).commit();
    }
    public String getIsActive()
    {
        return preferences.getString("User_IsActive","");
    }


    public void setUserMobile(String UserMobile)
    {
        editor.putString("User_Mobile",UserMobile).commit();
    }

    public String getUserMobile()
    {
        return preferences.getString("User_Mobile","");
    }


    public void setUserToken(String UserToken)
    {
        editor.putString("User_Token",UserToken).commit();
    }

    public String getUserToken()
    {
        return preferences.getString("User_Token","");
    }

    public void setUserEducationsectorid(String UserEducationsectorid)
    {
        editor.putString("User_Educationsectorid",UserEducationsectorid).commit();
    }

    public String getUserEducationsectorid()
    {
        return preferences.getString("User_Educationsectorid","");
    }

    public void setCurrentAppVersion(String CurrentAppVersion)
    {
        editor.putString("CurrentAppVersion",CurrentAppVersion).commit();
    }

    public String getCurrentAppVersion()
    {
        return preferences.getString("CurrentAppVersion","");
    }

    public void setLoginType(String loginType)
    {
        editor.putString("Login_Type",loginType).commit();
    }

    public String getLoginType()
    {
        return preferences.getString("Login_Type","");
    }

    public void setversionCode(String versionCode)
    {
        editor.putString("versionCode",versionCode).commit();
    }

    public String getversionCode()
    {
        return preferences.getString("versionCode","");
    }
}
