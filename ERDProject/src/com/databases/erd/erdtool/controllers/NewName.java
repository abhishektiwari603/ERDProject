package com.databases.erd.erdtool.controllers;

/**
 * 
 * @author ABHISHEK
 * @Since Jul 17, 2016
 */
public class NewName
{

    public static String getNewName(String oldstr)
    {

        for (int i = 0; i < oldstr.length(); i++)
        {

            if (oldstr.charAt(i) == ' ')
            {
                if ((oldstr.charAt(i + 1) == ' ') || (i + 1 == oldstr.length()))
                    oldstr = deleteString(oldstr, i);
                else
                    oldstr = deleteCharAt(oldstr, i);
            }
        }

        return oldstr;
    }

    public static String deleteCharAt(String str, int index)
    {
        return str.substring(0, index) + str.substring(index + 1);
    }

    public static String deleteString(String str, int index)
    {
        return str.substring(0, index);
    }
}
